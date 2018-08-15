package com.zc.controller.ontheorder;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.dianping.cat.Cat;
import com.mysql.fabric.xmlrpc.base.Data;
import com.zc.common.core.result.Result;
import com.zc.common.core.utils.CodeConstExt;
import com.zc.common.core.utils.DoubleUtils;
import com.zc.common.core.utils.page.PageBean;
import com.zc.entity.paycontext.PayContext;
import com.zc.service.paycontext.PayContextService;
import com.zc.vo.PayContextVO;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.util.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @package : com.zc.controller.ontheorder
 * @progect : Finance-Management
 * @Description : 关于订单（订单查询）控制层
 * @Author by :ZhaoJunBiao
 * @Creation Date ：2018年04月23日22:42
 */
@Controller
@RequestMapping("pc/view/ontheorder")
public class OnTheOrderController {

    private Logger logger = Logger.getLogger(OnTheOrderController.class);


    @Reference
    private PayContextService payContextService;


    /**
     * @description: 跳转到订单查询页面
     * @author: ZhaoJunBiao
     * @date: 2018-04-24 9:35
     * @version: 1.0.0
     */
    @RequestMapping("toOrderQuery")
    public String toOrderQuery() {
        return "ontheorder/orderQuery";
    }

    /**
     * @description: 查询订单列表
     * @author:  ZhaoJunBiao
     * @date:  2018-04-24 9:40
     * @version: 1.0.0
     * @param pageBean
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Object findOrderList(PageBean pageBean, PayContextVO payContext,Model model) {
        Map<String, Object> map = payContextService.findOrderList(pageBean,payContext);
        return map;
    }

    /**
     * @description: 三方渠道下拉列表
     * @author:  ywj
     * @date:  2018/6/6
     * @version: 1.0.0
     * @param
     * @return
     */
    @RequestMapping("getChannelSel")
    @ResponseBody
    public Result getChannelSel() {
        Result result = payContextService.getChannelSel();
        return result;
    }

    /**
     * @description: 导出订单数据
     * @author:  ywj
     * @date:  2018/6/7
     * @version: 1.0.0
     * @param response
     * @param request
     * @param ids
     */
    @RequestMapping(value = "downloadOrder",method = RequestMethod.POST)
    @ResponseBody
    public void downloadYestdayBelt(HttpServletResponse response, HttpServletRequest request, String[] ids) {
        if(ids.length==0) {
            return;
        }
            List<PayContext> payContexts = payContextService.getPayContentByIds(ids);
        String format = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String name = request.getParameter("name");
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("订单数据");
        sheet.autoSizeColumn(1, true);

        HSSFFont font = wb.createFont();
        font.setFontName("黑体");
        font.setFontHeightInPoints((short) 12);
        // 表头样式
        HSSFCellStyle cellStyle = wb.createCellStyle(); // 设置单元格样式
        // 指定单元格垂直居中对齐
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        cellStyle.setFillBackgroundColor(HSSFColor.BLUE_GREY.index);
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// //居中显示
        cellStyle.setFont(font);

        // 合并单元格样式
        HSSFCellStyle style = wb.createCellStyle(); // 样式对象
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平

        // (2)创建excel的sheet页面
        HSSFPatriarch patriarch = (HSSFPatriarch) sheet.createDrawingPatriarch();

        // (3)创建第一行标题
        HSSFRow row = sheet.createRow(0);
        sheet.setDefaultColumnWidth(30);
        String[] header = {"订单号", "订单创建时间", "三方渠道", "交易金额", "订单状态"};
        int[] clumnWidth = {35, 35, 35, 35, 35, 35};

        int indexHead = 0;
        for (String string : header) {
            ++indexHead;
            sheet.setColumnWidth((short) indexHead, (short) ((clumnWidth[indexHead - 1]) * 256));
            HSSFCell cell01 = row.createCell((short) indexHead - 1);
            cell01.setCellValue(new HSSFRichTextString(string)); // VALUE
            cell01.setCellStyle(cellStyle);
            //cell_01.set
        }
        int count = 1;
        for (int j = payContexts.size()-1; j >=0; j--) {

            // Map<String,Object> listData = sList.get(j);

            PayContext payContext = payContexts.get(j);

            // 插入行数
            HSSFRow rowLine = sheet.createRow(count);

            // ====订单号======//
            HSSFCell cell1 = rowLine.createCell((short) 0);
            cell1.setCellValue(new HSSFRichTextString(payContext.getOrderNum()));
            // ====交易创建时间======//
            HSSFCell cell2 = rowLine.createCell((short) 1);
            cell2.setCellValue(new HSSFRichTextString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(payContext.getCreatedTime())));
            // ====三方渠道======//
            HSSFCell cell3 = rowLine.createCell((short) 2);
            cell3.setCellValue(new HSSFRichTextString(payContext.getThirdPayType()));
            // ====交易金额(元)======//
            Long money = payContext.getMoney();
            Double totalPrice = DoubleUtils.scaleDouble(Double.parseDouble(money + "") / 100, 2);
            HSSFCell cell4 = rowLine.createCell((short) 3);
            cell4.setCellValue(new HSSFRichTextString(totalPrice.toString()));
            // ====订单状态======//
            String status = "";
            if("1".equals(payContext.getPayStatus())){
                status = "处理中";
            }else if("2".equals(payContext.getPayStatus())){
                status = "成功";
            }else{
                status = "失败";
            }
            HSSFCell cell5 = rowLine.createCell((short) 4);
            cell5.setCellValue(new HSSFRichTextString(status));

            count++;
        }
        // (5)指定写出的流对象
        OutputStream outputStream = null;
        String filedisplay = null;
        try {
            outputStream = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            // outputStream = new
            // FileOutputStream("G:/supplierProductOrder.xls");
            String time = System.currentTimeMillis() + "";
            filedisplay = StringUtils.isBlank(name) ?  "订单数据_" + format + ".xls" : name + "_" + format + ".xls";
            //	filedisplay = systemName+"_"+format + ".xls";
            response.setHeader("Content-disposition",
                    "attachment; filename =" + new String((filedisplay).getBytes("utf-8"), "iso8859-1"));
            response.setContentType("application/msexcel");
            // (6)将得到的workbook写入流中
            wb.write(outputStream);

        } catch (FileNotFoundException e) {
            Cat.logError("导出失败",e);

        } catch (IOException e) {
            Cat.logError("导出失败",e);

        } finally {
            IOUtils.closeQuietly(outputStream);
        }
        Cat.logEvent("OnTheOrderController","导出成功");

    }
}
