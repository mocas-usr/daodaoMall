package daodao.Controller.util;/**
 * Created with IntelliJ IDEA
 *
 * @Author: mocas
 * @Date: 2020/9/14 下午2:23
 * @email: wangyuhang_mocas@163.com
 */

import daodao.service.util.FileUploadUtil;
import daodao.service.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 *@program: daodao
 *@description:上传文件util
 *@author: mocas_wang
 *@create: 2020-09-14 14:23
 */
@Controller
@RequestMapping("/Upload")
public class UploadController {

    @Autowired
    private FileUploadUtil fileUploadUtil;

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping("/systemIcon")
    @ResponseBody
    public Map systemIcon(MultipartFile uploadFile)
    {

        Map result = fileUploadUtil.uploadPicture(uploadFile);
        //为了保证功能的兼容性，需要把Result转换成json格式的字符串。
        String json = JsonUtils.objectToJson(result);
        return result;

    }

}
