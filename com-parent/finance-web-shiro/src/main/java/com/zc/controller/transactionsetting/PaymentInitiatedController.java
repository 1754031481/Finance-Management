package com.zc.controller.transactionsetting;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.zc.common.core.interceptor.AvoidDuplicateSubmission;
import com.zc.common.core.result.Result;
import com.zc.common.core.result.ResultUtils;
import com.zc.common.core.utils.CodeConstExt;
import com.zc.common.core.utils.CommonConstants;
import com.zc.common.core.utils.DoubleUtils;
import com.zc.common.core.utils.page.PageBean;
import com.zc.common.core.utils.verifycode.CheckOtp;
import com.zc.common.util.core.ResultUtil;
import com.zc.config.shiro.MyShiroRealm;
import com.zc.dto.permission.UserDTO;
import com.zc.entity.cashreceivestation.CashReceiveStation;
import com.zc.entity.finance.Finance;
import com.zc.entity.logs.LoginOperatingLogs;
import com.zc.entity.permission.User;
import com.zc.service.cashreceivestationany.CashReceiveStationAnysService;
import com.zc.service.finance.FinanceService;
import com.zc.service.inside.InsideService;
import com.zc.service.logs.LoginOperatingLogsService;
import com.zc.service.permission.ILoginService;
import com.zc.service.permission.IUserService;
import com.zc.service.pushupload.PushUploadServcie;
import com.zc.service.systemfrom.SystemFromService;
import com.zc.service.transactionsetting.PaymentInitiatedService;
import com.zc.utils.IpUtil;
import com.zc.vo.permission.SessionUserVO;
import com.zc.vo.permission.UserVO;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.util.IOUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
 * @package : com.zc.controller.paymenttransaction
 * @progect : Finance-Management
 * @Description : 交易设置（代付交易发起）
 * @Author by :ZhaoJunBiao
 * @Creation Date ：2018年04月12日10:37
 */
@Controller
@RequestMapping("pc/view/paymentInit")
public class PaymentInitiatedController {

    private Logger logger = Logger.getLogger(PaymentInitiatedController.class);

    CheckOtp checkOtp = new CheckOtp();

    @Reference
    private PaymentInitiatedService paymentInitiatedService;
    @Reference
    private FinanceService financeService;
    @Reference
    private SystemFromService systemFromService;
    @Reference
    private InsideService insideService;
    @Reference
    private CashReceiveStationAnysService cashReceiveStationAnysService;
    @Reference
    private PushUploadServcie pushUploadServcie;
    @Autowired
    MyShiroRealm myShiroRealm;
    @Reference
    private ILoginService loginService;
    @Reference
    private IUserService userService;
    @Reference
    private LoginOperatingLogsService loginOperatingLogsService;
    /**
     * @description:  跳转到业务渠道分页展示
     * @author:  ZhaoJunBiao
     * @date:  2018-04-12 13:33
     * @version: 1.0.0
     * @return
     */
    @RequestMapping("toBusinesChannels")
    public String toBusinesChannels(){
        return "busineschannels/busineschannels";
    }

    /**
     * @description: 代付渠道分页展示
     * @author:  ZhaoJunBiao
     * @date:  2018-04-12 10:44
     * @version: 1.0.0
     * @param pageBean
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Object businessChannelsList(PageBean  pageBean){
        Map<String, Object> map  = paymentInitiatedService.findBusinessChannelsList(pageBean);
        return map;
    }


    /**
     * @description: 代付交易发起
     * @author:  ZhaoJunBiao
     * @date:  2018-04-13 11:20
     * @version: 1.0.0
     * @param pageBean
     * @param systemFromName
     * @param mobileCode
     * @param session
     * @return
     */
    @RequestMapping("checktakeout")
    @ResponseBody
    @AvoidDuplicateSubmission(needRemoveToken = true)
    public  synchronized Result checktakeout (PageBean pageBean, String systemFromName, String mobileCode, String name,HttpSession session,HttpServletRequest request) {
        Cat.logEvent("PaymentInitiatedController","代付交易发起start", Event.SUCCESS,"项目标识"+systemFromName+"，项目名称"+name);
        HashMap<String, Object> param = new HashMap<String, Object>();
        if (StringUtils.isBlank(systemFromName)) {
            logger.info("发起代付没有传递项目对应的项目,时间为:" + new Date());
            return ResultUtils.returnError("请选择对应的项目");
        }
       if (StringUtils.isBlank(mobileCode)) {
            return ResultUtils.returnError("动态口令不能为空");
        }
        SessionUserVO sessionUserVO= getLoginUser();
        String tel = sessionUserVO.getTelphone();
        param.put("systemName", systemFromName);
        com.zc.common.util.result.Result result1 = pushUploadServcie.getOtpByTel(tel);
        String otp1 = null;
        if(result1.isSuccess()){
            otp1 = (String)result1.getData();
            if(null==otp1){
                return ResultUtils.returnError("系统繁忙，请稍后再试");
            }
            try {
                com.zc.common.core.result.Result checkOtp2 = checkOtp.checkOtp(otp1, mobileCode);
                if(checkOtp2==null||!"1".equals(checkOtp2.getCode()+"")){
                    return ResultUtils.returnError("您输入的动态口令有误，请重新输入");

                }
            } catch (Exception e) {
                return ResultUtils.returnError("验证动态口令异常，请联系客服");
            }
        }

        Result result = financeService.findToDayTakeCount(systemFromName);

        if (result.getCode() != 0) {
//            logger.info("发起代付不通过,原因为:" + result.getMsg() + "项目为:" + systemFromName + ",时间为:" + new Date());
            Cat.logEvent("PaymentInitiatedController代付交易","发起代付不通过",Event.SUCCESS,"原因为:" + result.getMsg() + "项目为:" + systemFromName + ",时间为:" + new Date());
            return result;
        }
        List<Map<String, Object>> systemFroms = systemFromService.getTakeOutSystemFromList2(param);
        if (systemFroms.isEmpty()) {
//            logger.info("查询记录为null，异常" + ",项目为:" + systemFromName + ",时间为:" + new Date());
            Cat.logEvent("PaymentInitiatedController","查询记录为null",Event.SUCCESS,"异常" + ",项目为:" + systemFromName + ",时间为:" + new Date());
            return ResultUtils.returnError("未查询到可发起的代付交易");
        }
        Finance finance = new Finance();

        finance.setProject(systemFroms.get(0).get("systemFromName") + "");
        finance.setTotalMoney(systemFroms.get(0).get("money") + "");
        finance.setTotalCount(systemFroms.get(0).get("checkCount") + "");
        finance.setCreatedTime(new Date());
        finance.setStatus(0);

        Result launchBusiness = insideService.launchBusiness(systemFromName, CodeConstExt.FINACE);
        if (null == launchBusiness) {
//            logger.info("第三方返回结果为null,项目为:" + systemFromName + ",时间为:" + new Date());
            Cat.logEvent("PaymentInitiatedController","",Event.SUCCESS,"第三方返回结果为null,项目为:" + systemFromName + ",时间为:" + new Date());
        } else {
//            logger.info("第三方返回结果为" + launchBusiness.getMsg() + ",项目为:" + systemFromName + ",时间为:" + new Date());
            Cat.logEvent("PaymentInitiatedController","",Event.SUCCESS,"第三方返回结果为" + launchBusiness.getMsg() + ",项目为:" + systemFromName + ",时间为:" + new Date());
        }
        if (launchBusiness != null && launchBusiness.getCode() == 0) {
            SessionUserVO userVO = (SessionUserVO)session.getAttribute(CommonConstants.LOGIN_BACK_USER_SESSION);
            String userName = userVO.getUserName();
            if (result.getCode()==0){
                Cat.logEvent("PaymentInitiatedController","",Event.SUCCESS,"保存日志记录start");
                LoginOperatingLogs loginOperatingLogs = new LoginOperatingLogs();
                loginOperatingLogs.setCreateUser(userName);
                loginOperatingLogs.setCreatedIp(IpUtil.getIpAddr(request));
                loginOperatingLogs.setOperationLogs("用户："+userName+",在"+name+"项目,发起代付交易");
                loginOperatingLogsService.addLoginOperatingLogs(loginOperatingLogs);
            }
          financeService.insertFinance(finance);
        }
        return launchBusiness;
    }



    /**
     * @description: 项目代付数据分流情况展示
     * @author:  ZhaoJunBiao
     * @date:  2018-04-19 13:55
     * @version: 1.0.0
     * @param systemFromName
     * @return
     */
    @RequestMapping("projectShunteDataShow")
    @ResponseBody
    public Object  projectShunteDataShow(String systemFromName){
           List<Map<String,Object>>  map = cashReceiveStationAnysService.projectShunteDataShow(systemFromName);
            return  map;
    }



    /**
     * @description: 下载昨日代付数据
     * @author:  ZhaoJunBiao
     * @date:  2018-04-18 11:21
     * @version: 1.0.0
     * @param response
     * @param request
     * @param systemName
     */
    @RequestMapping(value = "downloadYestdayBelt",method = RequestMethod.GET)
    public void downloadYestdayBelt(HttpServletResponse response, HttpServletRequest request, String systemName) {
        Cat.logEvent("PaymentInitiatedController","下载昨日代付数据start",Event.SUCCESS,"项目为："+systemName);
        if (StringUtils.isBlank(systemName)) {
            return ;
        }
        List<CashReceiveStation> crs = cashReceiveStationAnysService.getCrsInfosByStatus(systemName, 1);
        String format = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String name = request.getParameter("name");
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet(systemName + "_" + format);
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
        String[] header = {"订单号", "收款人账号", "收款户名", "金额", "是否发送短信", "备注"};
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

        for (int j = 0; j < crs.size(); j++) {

            // Map<String,Object> listData = sList.get(j);

            CashReceiveStation cashReceiveStation = crs.get(j);

            // 插入行数
            HSSFRow rowLine = sheet.createRow(j + 1);

            // ====订单号======//
            HSSFCell cell1 = rowLine.createCell((short) 0);
            cell1.setCellValue(new HSSFRichTextString(cashReceiveStation.getMerSeqId()));
            // ====收款人账号======//
            HSSFCell cell2 = rowLine.createCell((short) 1);
            cell2.setCellValue(new HSSFRichTextString(cashReceiveStation.getCardNo()));
            // ====收款人户名======//
            HSSFCell cell3 = rowLine.createCell((short) 2);
            cell3.setCellValue(new HSSFRichTextString(cashReceiveStation.getPersonName()));
            // ====交易金额(元)======//
            Integer money = cashReceiveStation.getMoney();
            Double totalPrice = DoubleUtils.scaleDouble(Double.parseDouble(money + "") / 100, 2);
            HSSFCell cell4 = rowLine.createCell((short) 3);
            cell4.setCellValue(new HSSFRichTextString(DoubleUtils.formatRound(totalPrice, 2)));
            // ====业务类型======//
            HSSFCell cell5 = rowLine.createCell((short) 4);
            cell5.setCellValue(new HSSFRichTextString("否"));
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
            filedisplay = StringUtils.isBlank(name) ? systemName + "_" + format + ".xls" : name + "_" + format + ".xls";
            //	filedisplay = systemName+"_"+format + ".xls";
            response.setHeader("Content-disposition",
                    "attachment; filename =" + new String((filedisplay).getBytes("utf-8"), "iso8859-1"));
            response.setContentType("application/msexcel");
            // (6)将得到的workbook写入流中
            wb.write(outputStream);

        } catch (FileNotFoundException e) {
//            logger.info("导出失败" + e.getMessage());
            Cat.logEvent("PaymentInitiatedController","导出失败",Event.SUCCESS,"异常为："+e.getMessage());

        } catch (IOException e) {
//            logger.info("导出失败" + e.getMessage());
            Cat.logEvent("PaymentInitiatedController","导出失败",Event.SUCCESS,"异常为："+e.getMessage());

        } finally {
            IOUtils.closeQuietly(outputStream);
        }
//        logger.info("导出成功");
        Cat.logEvent("PaymentInitiatedController","导出success",Event.SUCCESS,"无异常");
        //crs表状态变为处理中
        try {
            for (CashReceiveStation c : crs) {
                c.setStatus(4);
                cashReceiveStationAnysService.saveAndModify(c);
            }
        }catch (Exception e){
//            logger.info("下载昨日待发起交易，变更crs表状态失败" + e.getMessage());
            Cat.logEvent("PaymentInitiatedController","下载昨日待发起交易，变更crs表状态失败",Event.SUCCESS,"异常为："+e.getMessage());
        }

    }


    /**
     * @description: 项目代付数据分流配置列表
     * @author:  王楠
     * @date:  2018-04-19 13:55
     * @version: 1.0.0
     * @param systemFromName
     * @return
     */
    @RequestMapping("listShunting")
    @ResponseBody
    public Map<String,List> listShunting(String systemFromName){
        Map<String,List> map = cashReceiveStationAnysService.getSystemCashShunting(systemFromName);
        return map;
    }

    /**
     * @description: 修改项目分流配置
     * @author:  王楠
     * @date:  2018-04-19 13:55
     * @version: 1.0.0
     * @param fromSystem 项目英文缩写
     * @param estimatedMoney 更换渠道的金额
     * @param fromChannel 被更改渠道
     * @param toChannel 目标渠道
     * @return
     */
    @RequestMapping("updateShuntSettings")
    @ResponseBody
    public Result updateShuntSettings(String fromSystem,String estimatedMoney, String fromChannel, String toChannel){
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("fromSystem",fromSystem);
        paramMap.put("estimatedMoney",estimatedMoney);
        paramMap.put("fromChannel",fromChannel);
        paramMap.put("toChannel",toChannel);
        Result result = cashReceiveStationAnysService.updateShuntSettings(paramMap);
        return result;
    }


    /**
     * @description: 去重发渠道配额设置页面
     * @author:  王楠
     * @date:  2018-04-19 13:55
     * @version: 1.0.0
     * @return 页面路径
     */
    @RequestMapping("gotoReShunting")
    public String gotoReShunting(){
        return "busineschannels/reCash";
    }

    /**
     * @description: 去重发渠道配额设置页面
     * @author:  王楠
     * @date:  2018-04-19 13:55
     * @version: 1.0.0
     * @return 页面路径
     */
    @RequestMapping("getReShuntingList")
    @ResponseBody
    public HashMap<String,Object> getReShuntingList(@ModelAttribute PageBean pageBean){
        HashMap<String,Object> map = cashReceiveStationAnysService.getReShuntingList(pageBean);
        return map;
    }


    /**
     * @description: 项目代付数据分流配置列表
     * @author:  王楠
     * @date:  2018-04-19 13:55
     * @version: 1.0.0
     * @param systemFromName
     * @return
     */
    @RequestMapping("listReShunting")
    @ResponseBody
    public Map<String,List> listReShunting(String systemFromName){
        Map<String,List> map = cashReceiveStationAnysService.getReSystemCashShunting(systemFromName);
        return map;
    }


    /**
     * @description: 修改项目分流配置
     * @author:  王楠
     * @date:  2018-04-19 13:55
     * @version: 1.0.0
     * @param fromSystem 项目英文缩写
     * @param estimatedMoney 更换渠道的金额
     * @param fromChannel 被更改渠道
     * @param toChannel 目标渠道
     * @return
     */
    @RequestMapping("updateReShuntSettings")
    @ResponseBody
    public Result updateReShuntSettings(String fromSystem,String estimatedMoney, String fromChannel, String toChannel){
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("fromSystem",fromSystem);
        paramMap.put("estimatedMoney",estimatedMoney);
        paramMap.put("fromChannel",fromChannel);
        paramMap.put("toChannel",toChannel);
        Result result = cashReceiveStationAnysService.updateReShuntSettings(paramMap);
        return result;
    }

    public SessionUserVO getLoginUser() {

        //获取当前的Subject
        Subject currentUser = SecurityUtils.getSubject();
        SessionUserVO info = (SessionUserVO) currentUser.getSession().getAttribute(
                CommonConstants.LOGIN_BACK_USER_SESSION);

        //如果是记住我，处理Session失效
        if (info == null) {
            Object telPhone = currentUser.getPrincipal();
            if (telPhone != null) {
                boolean isRemembered = currentUser.isRemembered();
                if (isRemembered) {
                    //清除权限缓存
                    myShiroRealm.getAuthorizationCache().remove(currentUser.getPrincipals());
                    UserDTO userDTO = new UserDTO();
                    userDTO.setTelphone(telPhone.toString());
                    UserVO userVO = userService.getUserByCondition(userDTO);
                    //对密码进行加密后验证
                    UsernamePasswordToken token = new UsernamePasswordToken(userVO.getTelphone(), userVO.getPassword(), currentUser.isRemembered());
                    //把当前用户放入session
                    currentUser.login(token);
                    Session session = currentUser.getSession();
                    User user = new User();
                    user.setTelphone(userVO.getTelphone());
                    user.setPassword(userVO.getPassword());
                    com.zc.common.core.result.Result result = loginService.login(user);
                    session.setAttribute(CommonConstants.LOGIN_BACK_USER_SESSION, result.getContent());
                    info = (SessionUserVO) result.getContent();
                }
            }

        }
        return info;

    }
}
