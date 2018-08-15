package com.zc.controller.recheckorder;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.zc.common.core.result.Result;
import com.zc.common.core.utils.DoubleUtils;
import com.zc.common.core.utils.page.PageBean;
import com.zc.entity.cashreceivestation.CashReceiveStation;
import com.zc.entity.checklog.CheckLog;
import com.zc.service.checklog.CheckLogService;
import com.zc.vo.CheckLogVO;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.util.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @package : com.zc.controller.recheckorder
 * @progect : Finance-Management
 * @Description : 特殊订单（复审订单展示）控制层
 * @Description : logger切Cat日志监控  更新时间2018年07月09日14:20
 * @Author by :史云顺
 * @Creation Date ：2018年06月3日16:42
 */
@Controller
@RequestMapping("pc/view/recheckorder")
public class RecheckOrderController {

    private Logger logger = Logger.getLogger(RecheckOrderController.class);


    @Reference
    private CheckLogService checkLogService;


    /**
     * @description: 跳转到订单复审查询页面
     * @author: 史云顺
     * @date: 2018-6-3 9:35
     * @version: 2.0.0
     */
    @RequestMapping("tocCheckOrderQuery")
    public String toOrderQuery() {
        return "recheckorder/CheckorderQuery";
    }

    /**
     * @description: 查询复审订单列表
     * @author:  shiyunshun
     * @date:  2018-06-3 9:40
     * @version: 2.0.0
     * @param pageBean
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Object findOrderList(PageBean pageBean, CheckLogVO checkLog) {
        Map<String, Object> map = checkLogService.findOrderList(pageBean,checkLog);
        Cat.logEvent("查询代付复审订单Controller", "查询代付复审订单列表", Event.SUCCESS, "参数="+ map.toString());
        return map;
    }

    /**
     * @description: 三方渠道下拉列表
     * @author:  shiyunshun
     * @date:  2018/6/6
     * @version: 1.0.0
     * @param
     * @return
     */
    @RequestMapping("selectOrder")
    @ResponseBody
    public Result getChannelSel() {
        Result result = checkLogService.getChannelSel();
        Cat.logEvent("查询代付复审订单Controller", "三方渠道下拉列表展示", Event.SUCCESS, "参数="+ JSON.toJSON(result));
        return result;
    }



    /**
     * @return
     * @throws
     * @author shiyunshun
     * @version
     * @Description: 复审记录添加(单条，批量)
     */
    @RequestMapping(value = "batchinsert")
    @ResponseBody
    public Result getBatchInsert(String[] chk_value) {
        Cat.logEvent("查询代付复审订单Controller", "代付复审订单入参", Event.SUCCESS, "参数="+chk_value);
        Result result =  checkLogService.getBatchInsert(chk_value);
        Cat.logEvent("查询代付复审订单Controller", "代付复审订单出参", Event.SUCCESS, "参数="+ JSON.toJSON(result));
        return result;
    }


    /**
     * @description: 下载绑定的复审记录
     * @author:  史云顺
     * @date:  2018-06-05 11:21
     * @version: 1.0.0
     * @param response
     * @param chk_value
     */
    @RequestMapping(value = "downloadPay",method = RequestMethod.GET)
    public void downloadYestdayBelt(HttpServletResponse response,String[] chk_value) {
        Cat.logEvent("查询代付复审订单Controller", "下载绑定的复审记录入参", Event.SUCCESS, "参数="+chk_value);
        List<CheckLog> checkLog = checkLogService.getCheckLog(chk_value);
        //logger.info("------------------下载复审订单记录开始--------------------");
        Cat.logEvent("查询代付复审订单Controller","=====下载复审订单记录开始=====");
        String format = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String name = "复审订单记录";
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("123" + "_" + format);
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
        String[] header = {"订单号", "创建订单时间", "发起来源", "交易金额", "订单状态", "备注"};
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

        for (int j = 0; j < checkLog.size(); j++) {

            // Map<String,Object> listData = sList.get(j);
            CheckLog checkLog1 = checkLog.get(j);

            // 插入行数
            HSSFRow rowLine = sheet.createRow(j + 1);

            // ====订单号======//
            HSSFCell cell1 = rowLine.createCell((short) 0);
            cell1.setCellValue(new HSSFRichTextString(checkLog1.getOrderNum()));
            // ====创建订单时间======//
            HSSFCell cell2 = rowLine.createCell((short) 1);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateString = formatter.format(checkLog1.getCreatedTime());
            cell2.setCellValue(new HSSFRichTextString(dateString));
            // ====发起来源======//
            HSSFCell cell3 = rowLine.createCell((short) 2);
            cell3.setCellValue(new HSSFRichTextString(checkLog1.getSystemFromName()));
            // ====交易金额(元)======//
            Long money = checkLog1.getOrderMoney();
            String orderMoney = String.valueOf(money);
            // Double totalPrice = DoubleUtils.scaleDouble(Double.parseDouble(money + "") / 100, 2);
            HSSFCell cell4 = rowLine.createCell((short) 3);
            cell4.setCellValue(new HSSFRichTextString(orderMoney));
            // ====订单状态======//
            String status = "";
            if("1".equals(checkLog1.getPayStatus())){
                status = "处理中";
            }else if("2".equals(checkLog1.getPayStatus())){
                status = "成功";
            }else{
                status = "失败";
            }
            HSSFCell cell5 = rowLine.createCell((short) 4);
            cell5.setCellValue(new HSSFRichTextString(status));
            // ====订单描述======//
            HSSFCell cell6 = rowLine.createCell((short) 5);
            cell6.setCellValue(new HSSFRichTextString(""));
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
            // filedisplay = StringUtils.isBlank(name) ? systemName + "_" + format + ".xls" : name + "_" + format + ".xls";
            filedisplay = name + "_" + format + ".xls";
            response.setHeader("Content-disposition",
                    "attachment; filename =" + new String((filedisplay).getBytes("utf-8"), "iso8859-1"));
            response.setContentType("application/msexcel");
            // (6)将得到的workbook写入流中
            wb.write(outputStream);

        } catch (FileNotFoundException e) {
            //logger.info("-----------------导出失败--------" + e.getMessage());
             Cat.logError("=====导出失败=====",e);
        } catch (IOException e) {
          //  logger.info("-----------------导出失败--------" + e.getMessage());
            Cat.logError("=====导出失败=====",e);
        } finally {
            IOUtils.closeQuietly(outputStream);
        }
      //  logger.info("---------------------导出成功----------------------");
        Cat.logEvent("查询代付复审订单Controller","=========导出成功========");


    }

}
