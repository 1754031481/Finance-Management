package com.zc.controller.upload;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.zc.common.core.utils.CommonConstants;
import com.zc.common.core.utils.verifycode.CheckOtp;
import com.zc.common.util.core.ResultUtil;
import com.zc.common.util.result.Result;
import com.zc.config.shiro.MyShiroRealm;
import com.zc.dto.permission.UserDTO;
import com.zc.entity.finance.AlPayConfig;
import com.zc.entity.permission.User;
import com.zc.service.finance.AlPayService;
import com.zc.service.permission.ILoginService;
import com.zc.service.permission.IUserService;
import com.zc.service.pushupload.PushUploadServcie;
import com.zc.vo.permission.SessionUserVO;
import com.zc.vo.permission.UserVO;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 杨文杰
 * @Title: PushUploadController
 * @Description: 导入推送订单
 * @date 2018/4/8
 */

@Controller
@RequestMapping("/web/push")
public class PushUploadController {

    private static Logger logger = LoggerFactory.getLogger(PushUploadController.class);

    @Reference
    private PushUploadServcie pushUploadServcie;

    @Autowired
    MyShiroRealm myShiroRealm;

    @Reference
    private ILoginService loginService;

    @Reference
    private IUserService userService;

    CheckOtp checkOtp = new CheckOtp();

    public static final String VALIDATE = "\\s*|\t|\r|\n";


    /**
     * 跳转页面
     * @author : 杨文杰
     * @Creation Date ： 2018/4/9
     * @version 1.0.0
     * @param
     *
     */
    @RequestMapping(value = "/toPushPage",method = {RequestMethod.POST,RequestMethod.GET})
    public  String toPushPage(){
        return "upload/upload";
    }

    @RequestMapping("upload")
    @ResponseBody
    public Result lotSave(@RequestParam(value = "file", required = false) MultipartFile file,String otp,RedirectAttributesModelMap model,
                          HttpSession httpsession, HttpServletRequest request) {
       // logger.info("--------------------导入推送订单开始---------------------------");
       // logger.info("--------------------校验动态验证码，输入为"+otp+"----------------");
        Cat.logEvent("PushUploadController","校验动态验证码", Event.SUCCESS,"otp="+otp);
        SessionUserVO sessionUserVO= getLoginUser();
        String tel = sessionUserVO.getTelphone();
        String otp1 = null;
        //校验动态口令
        Result result1 = pushUploadServcie.getOtpByTel(tel);
        if(result1.isSuccess()){
            otp1 = (String)result1.getData();
            if(null==otp1){
                return ResultUtil.setResult(false,"系统繁忙，请稍后再试");
            }
            try {
                com.zc.common.core.result.Result checkOtp2 = checkOtp.checkOtp(otp1, otp);
                if(checkOtp2==null||!"1".equals(checkOtp2.getCode()+"")){
                    return ResultUtil.setResult(false,"您输入的动态口令有误，请重新输入");

                }
            } catch (Exception e) {
                return ResultUtil.setResult(false,"验证动态口令异常，请联系客服");
            }
        }
        //验证
        if(file.isEmpty()){
            return ResultUtil.setResult(false,"文件不存在!");
        }
        boolean isFile = StringUtils.endsWithAny(StringUtils.lowerCase(file.getOriginalFilename()), new String[]{".csv"});
        if(!isFile){
            return ResultUtil.setResult(false,"文件格式不正确，请上传csv文件！");
        }
        String path = request.getSession().getServletContext().getRealPath("upload") + "";
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar calendar = Calendar.getInstance();
        String dateName = df.format(calendar.getTime());
        String fileName = dateName + ".csv";
        File targetFile1 = new File(path);
        File targetFile = new File(path, fileName);
        if (!targetFile1.exists()) {
            targetFile1.mkdirs();
        }
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.setResult(false,"存储文件出现异常，请联系开发人员");
        }

        String filePath = path + File.separatorChar + fileName;

        List<Map<String,String>> list =new ArrayList<Map<String,String>>();

        BufferedReader bufferedReader = null;
        try {


            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath),"gbk"));


            //编码格式调整
            //  InputStreamReader isr = new InputStreamReader(new FileInputStream(filePath), "UTF-8");
            //  bufferedReader = new BufferedReader(isr);

            String line = null;
            int j=0;
            try {
                while ((line = bufferedReader.readLine()) != null) {
                    j=j+1;
                    if(j==1){
                        continue;
                    }
                    String[] columns = line.split(",");
                    String t="";
                    if(columns.length!=8){
                        return ResultUtil.setResult(false,"上传文件内容不正确！");
                    }else{
                        String orderNum = columns[0]; //获取 shen
                        String cardNum = columns[1];
                        String userName = columns[2];
                        String money = columns[3];
                        String mark = columns[5];
                        String status = columns[6];
                        String bankMoney =columns[7];
                        Map<String,String> map = new HashMap<String,String>();
                        map.put("orderNum", orderNum);
                        map.put("money", money);
                        map.put("status", status);
                        map.put("userName", userName);
                        map.put("cardNum", cardNum);
                        map.put("bankMoney", bankMoney);
                        list.add(map);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return ResultUtil.setResult(false,"添加回调记录失败，请稍后再试");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.setResult(false,"添加回调记录失败，请稍后再试");
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Map<String, String> paramMap = list.get(0);
        String businessnumber = paramMap.get("orderNum").trim();

        businessnumber = replace(businessnumber);

        businessnumber = clearStartAndEndQuote(businessnumber);
        String pushUrl = null;
        //获取配置
        Result result = pushUploadServcie.getPushUrl(businessnumber);
        if(result.isSuccess()){
            pushUrl = (String)result.getData();
        }else{
            return ResultUtil.setResult(false,"添加回调记录失败，请稍后再试");
        }
        for (Map<String, String> map : list) {

            pushUploadServcie.pushXlsInfo(map,pushUrl);
        }
        return  ResultUtil.setResult(true,"添加推送记录成功，请稍后查看结果");
    }

    public String replace(String param) {
        Pattern p = Pattern.compile(VALIDATE);
        Matcher m = p.matcher(param);
        return m.replaceAll("");
    }

    public static String clearStartAndEndQuote(String str) {
        if (str != null && str.length() >= 2) {
            if (str.indexOf("\"") == 0) {
                str = str.substring(1, str.length());
            }// 去掉第一个 "
            if (str.lastIndexOf("\"") == (str.length() - 1)) {
                str = str.substring(0, str.length() - 1); // 去掉最后一个 "
                return str;
            }
            // str = str.replaceAll("\"\"","\"");//把两个双引号换成一个双引号
        }
        return str;
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

    /**
     * 模板下载
     * @author : 杨文杰
     * @Creation Date ： 2018/4/24
     * @version 1.0.0
     * @param
     *
     */
    @RequestMapping("download")
    public void download(HttpServletResponse response, HttpServletRequest request) throws IOException {

        BufferedOutputStream outputStream = null;
        String filedisplay = null;
        try {
            outputStream = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/csv;charset=UTF-8");
            filedisplay =" 模板.csv";

            response.setHeader("Content-disposition",
                    "attachment; filename =" + new String((filedisplay).getBytes("utf-8"), "iso8859-1"));

            outputStream.write(("订单号,收款人账号,收款户名,金额,是否发送短信,备注,订单状态,回款").getBytes("gbk"));
            outputStream.write("\r\n".getBytes("gbk"));
            outputStream.write("M20171220161348689671539,6212260000000000000\t,刘凤兰,43318.00\t,否, ,成功,49995.00\t".getBytes("gbk"));




        } catch (Exception e) {
            //logger.info("导出失败" + e.getMessage());
            Cat.logError("导出模板失败",e);

        } finally {
            org.apache.poi.util.IOUtils.closeQuietly(outputStream);
        }
        //logger.info("导出成功");


    }
}
