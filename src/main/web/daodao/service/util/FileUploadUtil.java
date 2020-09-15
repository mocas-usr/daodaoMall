package daodao.service.util;/**
 * Created with IntelliJ IDEA
 *
 * @Author: mocas
 * @Date: 2020/9/14 下午2:19
 * @email: wangyuhang_mocas@163.com
 */

import com.jcabi.ssh.SSHByPassword;
import com.jcabi.ssh.Shell;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Runtime.getRuntime;

/**
 *@program: daodao
 *@description:
 *@author: mocas_wang
 *@create: 2020-09-14 14:19
 */
@Component
public class FileUploadUtil {
    @Value("${remote.host}")
    private String remoteHost;

    @Value("${remote.port}")
    private String remotePort;

    @Value("${remote.user}")
    private String remoteUser;

    @Value("${remote.password}")
    private String remotePassword;

    @Value("${remote.dir}")
    private String remoteDir;

    @Value("${nginx.port}")
    private String nginxPort;

    @Value("${nginx.fileuri}")
    private String nginxFileUri;

    public String getUrlFromRequest(String filename) throws NullPointerException{
        StringBuilder builder = new StringBuilder("http://");
        builder.append(remoteHost);
        builder.append(":");
        builder.append(nginxPort);
        /*builder.append(nginxFileUri);*/
        builder.append("/");
        builder.append(filename);
        return builder.toString();
    }

    public String renameFile(HttpServletRequest request) throws IOException,NullPointerException{
        Shell shell = new SSHByPassword(remoteHost, Integer.valueOf(remotePort), remoteUser, remotePassword);

        Shell.Plain plain = new Shell.Plain(shell);

        String orgFile = request.getParameter("file_name");

        if (orgFile == null) {
            throw new NullPointerException("文件名不存在");
        }
        orgFile.replaceAll(" ", "");
        String hostFile = request.getParameter("file_path");
        if (hostFile == null) {
            throw new NullPointerException("文件路径不存在");
        }

        String timestamp = new SimpleDateFormat("YYYY-MM-dd-HH-mm-ss").format(new Date()).toString();
        String filename = new StringBuilder(timestamp).append("-").append(orgFile).toString().replaceAll(" ", "").replaceAll("/", "");

        String hostFilePath = remoteDir.concat(hostFile.substring(hostFile.lastIndexOf("/") + 1));
        String orgFilePath = remoteDir.concat(filename);

        String renameCmd = new StringBuilder("mv ").append("\'").append(hostFilePath).append("\'").append(" ")
                .append("\'").append(orgFilePath).append("\'").toString();

        String result = plain.exec(renameCmd);

        if (!result.isEmpty()) {
            throw new NullPointerException("命令运行失败，请检查文件名是否有特殊符号,如()");
        }

        return filename;
    }


    public Map uploadPicture(MultipartFile uploadFile)
    {

        Map resultMap = new HashMap<>();
        int num=0;//执行标记位,成功为1
        try {
            //生成一个新的文件名
            //取原始文件名
            String oldName = uploadFile.getOriginalFilename();
            //生成新文件名
            //UUID.randomUUID();
            String newName = IDUtils.genImageName();
            newName = newName + oldName.substring(oldName.lastIndexOf("."));
            //图片上传
            String priexDate = new DateTime().toString("yyyyMMdd");
            String fileName=priexDate+newName;//图片名称
            nginxFileUri=getUrlFromRequest(fileName);
            String uploadFilePath=oldName;


            uploadFile.transferTo(new File(remoteDir+fileName));
            num=1;//成功标记位
//            //ssh通信
//            Shell shell = new SSHByPassword(remoteHost, Integer.valueOf(remotePort), remoteUser, remotePassword);
//            //远程连接主机
//            Shell.Plain plain = new Shell.Plain(shell);
//
//            //执行语句上传
//            String renameCmd = new StringBuilder("chmod 777 ").append("\'").append(remoteDir+fileName).append("\' ").toString();
//            //赋予文件的读写权限
//            String result = plain.exec(renameCmd);
            //赋予文件的读写权限
            Runtime runtime = getRuntime();
            String command = "chmod 755 " + remoteDir+fileName;
            try {
                Process process = runtime.exec(command);
                process.waitFor();
                int existValue = process.exitValue();
                if(existValue != 0){
                    System.out.println("操作失败,权限失败");;
                }
            } catch (Exception e) {
                System.out.println("操作失败");;
            }


            //返回结果
            if(num==0) {
                resultMap.put("error", 1);
                resultMap.put("message", "文件上传失败");
                return resultMap;
            }
            resultMap.put("error", 0);
            resultMap.put("url",nginxFileUri);
            return resultMap;

        } catch (Exception e) {
            resultMap.put("error", 1);
            resultMap.put("message", "文件上传发生异常");
            return resultMap;
        }
    }
}
