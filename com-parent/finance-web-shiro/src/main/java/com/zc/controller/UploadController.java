package com.zc.controller;

import com.zc.common.core.oss.UpLoadUtils;
import com.zc.common.core.utils.UniqueUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.zc.common.core.result.Result;
import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * Created by panlichao on 2018/2/9.
 */
@Controller
    @RequestMapping("/web/upload")
public class UploadController {


    @RequestMapping(value="/test",method= RequestMethod.GET)
    public Object test() {
        return "test";
    }

    @RequestMapping(value="uploadImage",method= RequestMethod.POST)
    @ResponseBody
    public Result importAttachment(@RequestParam(value = "urlfile") MultipartFile urlfile,
                                   @RequestParam(required = false, value = "field") Integer field, HttpServletRequest request) {
        String fileName = null;
        String module = "test";
        Result result = new Result();
        try {

            String basePath = request.getSession().getServletContext().getRealPath("/");
            String path = null;
            fileName = urlfile.getOriginalFilename();
            boolean isFile = StringUtils.endsWithAny(
                    StringUtils.lowerCase(fileName), new String[] { ".png",
                            ".jpg", ".jpeg", ".bmp", ".gif"});
            String sysFileName = UniqueUtils.getOrder() + "."
                    + StringUtils.substringAfter(fileName, ".");
            if (isFile) {
                path = basePath + "/" + sysFileName;
            } else {
                result.setCode(0);
                result.setMsg("文件格式不正确,上传文件失败");
                return result;
            }
            urlfile.transferTo(new File(path));

                UpLoadUtils.alyUpload(module, sysFileName, path ,null);
                result.setCode(0);
                result.setMsg("上传成功");
                result.setContent("http://yst-images.img-cn-hangzhou.aliyuncs.com//upload/"+module+"/"+sysFileName);
                return  result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(0);
            result.setMsg("上传失败");
            return result;
        }
    }
}
