package cn.fjut.gmxx.campusclub.controller.fileServerController;/**
 * Created by admin on 2020/1/19.
 */

import cn.fjut.gmxx.campusclub.basefilersc.api.IBaseFileRscApi;
import cn.fjut.gmxx.campusclub.common.BaseAppAction;
import cn.fjut.gmxx.campusclub.common.Constant;
import cn.fjut.gmxx.campusclub.common.ResponseVO;
import cn.fjut.gmxx.campusclub.common.TokenCheck;
import cn.fjut.gmxx.campusclub.config.FileServerConfig;
import cn.fjut.gmxx.campusclub.config.SysOperationLog;
import cn.fjut.gmxx.campusclub.pagehelper.PageInfo;
import cn.fjut.gmxx.campusclub.utlis.FileUploadUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.util.Map;

/**
 * @author : shenjindui
 * @date : 2020-01-19 17:52
 **/
@Api(tags = "文件服务接口")
@Controller
@RequestMapping(value = "/api")
public class FileServerController extends BaseAppAction {
    protected final static Logger logger = LoggerFactory.getLogger(BaseAppAction.class);
    /**
     * 保存文件接口
     * @param file 文件
     * @return 结果
     */

    @Autowired
    IBaseFileRscApi baseFileRscApi;  //文件服务

    @SysOperationLog("文件列表")
    @TokenCheck  //此注解使用开启token验证，对于访问后台数据的方法，请打开此注解
    @ApiOperation(value = "文件列表", notes = "文件列表方法", httpMethod = "POST")
    @RequestMapping(value = "/fileList", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseVO fileList(@RequestBody @Valid Map<String, Object> params, BindingResult result)throws Exception {
        //参数校验
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return errorResponse(error);
            }
        }
        try {
            PageInfo<Map<String, Object>> resultMaps=baseFileRscApi.findBaseFileRscPage(params);
            if(resultMaps!=null){
                return successResponse(resultMaps,Constant.SUCCESS);
            }else{
                return errorResponse(Constant.FAIL);
            }
        } catch (Exception e) {
            return errorResponse(e.getMessage());
        }
    }

    @SysOperationLog("保存文件")
    @TokenCheck  //此注解使用开启token验证，对于访问后台数据的方法，请打开此注解
    @ApiOperation(value = "保存文件", notes = "保存文件", httpMethod = "POST")
    @PostMapping("/updateFile")
    @ResponseBody
    public ResponseVO updateFile(@RequestParam Map<String,Object> map,@RequestParam("file") MultipartFile file) {
        try {
            if(map.isEmpty()|| MapUtils.getString(map,"stCd").equals("")){
                return errorResponse("请先完善基础资料后上传文件！");
            }
            String fileName=null;
            if (!file.isEmpty()) {
                //fileName = FileUploadUtils.extractFilename(file,file.getOriginalFilename().substring(file.getOriginalFilename().indexOf(".")+1,file.getOriginalFilename().length()));
                fileName=file.getOriginalFilename();
                //文件存放路径
                String url=FileUploadUtils.uploadText(file,fileName);//保存本地文件后返回的数据
                map.put("file",file);
                map.put("url",url);
                map.put("fileName",fileName);
                Map<String, Object> resultMaps=baseFileRscApi.saveBaseFileRscTrans(map);
                if(resultMaps!=null){
                    return successResponse(resultMaps,Constant.SAVE_SUCCESS);
                }else{
                    return errorResponse(Constant.FAIL);
                }
            }
            return errorResponse(Constant.FAIL);
        } catch (Exception e) {
            return errorResponse(e.getMessage());
        }
    }

    /*
    * 保存头像
     * @return 结果
     */
    @SysOperationLog("保存头像")
    @TokenCheck  //此注解使用开启token验证，对于访问后台数据的方法，请打开此注解
    @ApiOperation(value = " 保存头像", notes = " 保存头像", httpMethod = "POST")
    @PostMapping("/updateAvatar")
    @ResponseBody
    public Object updateAvatar(@RequestParam Map<String,String> map, @RequestParam("file") MultipartFile file) {
        try {
            if (!file.isEmpty()) {
                String avatar = FileUploadUtils.uploadPicture(FileServerConfig.getAvatarPath(), file);
                System.out.println("avatar:" + avatar);
                // TODO: 此处可以根据项目需求的业务进行操作，例如此处操作的是头像，可以将头像url保存到数据库，用户登录后，获取相应的url获取头像图片。
            }
            return "success";
        } catch (Exception e) {
            return "error";
        }
    }

    /**
     * 查看文件
     *
     * @param url 图片url
     * @param response 请求响应
     */
    @SysOperationLog("查看文件")
    @TokenCheck  //此注解使用开启token验证，对于访问后台数据的方法，请打开此注解
    @ApiOperation(value = " 查看文件", notes = " 查看文件", httpMethod = "POST")
    @RequestMapping(value = "/noLogin/readImageFile",method = RequestMethod.GET)
    @ResponseBody
    public void getUrlFile(String url, HttpServletResponse response) {
        // 这里的url，我为了测试，直接就写静态的。
        url = "D:/profile/avatar/2019/05/20/d025ba6f937f59999a021989a12a1aab.jpg";
        File file = new File(url);
        // 后缀名
        String suffixName = url.substring(url.lastIndexOf("."));
        //判断文件是否存在如果不存在就返回默认图片或者进行异常处理
        if (!(file.exists() && file.canRead())) {
//            file = new File("xxx/xxx.jpg");
            System.out.println("文件不存在");
        }
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            inputStream.close();
            response.setContentType("image/png;charset=utf-8");
            OutputStream stream = response.getOutputStream();
            stream.write(data);
            stream.flush();
            stream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将文件内容（.txt）封装到一个String里
     * @param url
     * @param request
     * @param response
     * @return
     */
    @SysOperationLog("将文件内容")
    @TokenCheck  //此注解使用开启token验证，对于访问后台数据的方法，请打开此注解
    @ApiOperation(value = " 将文件内容（.txt）封装到一个String里", notes = " 将文件内容（.txt）封装到一个String里", httpMethod = "POST")
    @RequestMapping(value = "/noLogin/readFileToString",method = RequestMethod.GET)
    @ResponseBody
    public String getUrlFile(String url, HttpServletRequest request, HttpServletResponse response) {
//        url = "D:/profile/upload/2019/05/20/01017f12b88d9f4e79dc38060e3883da.txt";

        // 通过url创建文件
        File file = new File(url);
        // 后缀名
        String suffixName = url.substring(url.lastIndexOf("."));

        //判断文件是否存在如果不存在就进行异常处理
        if (!(file.exists() && file.canRead())) {
            System.out.println("文件不存在");
        }

        FileInputStream inputStream = null;
        String content = "";
        try {
            inputStream = new FileInputStream(file);
            inputStream.close();
            // 使用FileUtils将File内容以UTF-8的编码写到String里
            content = FileUtils.readFileToString(file, "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    /**
     * 下载文件
     *
     * @param url 文件url
     * @param response 请求响应
     */
    @SysOperationLog("下载文件")
    @TokenCheck  //此注解使用开启token验证，对于访问后台数据的方法，请打开此注解
    @ApiOperation(value = " 下载文件", notes = " 下载文件", httpMethod = "POST")
    @RequestMapping(value = "/noLogin/downloadFile",method =RequestMethod.GET)
    @ResponseBody
    public void getUrlDownload(String url, HttpServletResponse response) {
        url = "D:/profile/avatar/2019/05/20/d025ba6f937f59999a021989a12a1aab.jpg";
        File file = new File(url);
        // 后缀名
        String suffixName = url.substring(url.lastIndexOf("."));
        //判断文件是否存在如果不存在就进行异常处理
        if (!(file.exists() && file.canRead())) {
            System.out.println("文件不存在");
        }
        FileInputStream inputStream = null;
        BufferedInputStream bufferedInputStream = null;
        try {
            inputStream = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            int length = inputStream.read(data);
            inputStream.close();
            response.setContentType("application/force-download");
            //通过设置头信息给文件命名，也即是，在前端，文件流被接受完还原成原文件的时候会以你传递的文件名来命名
            response.addHeader("Content-Disposition",String.format("attachment; filename=\"%s\"", file.getName()));
            OutputStream stream = response.getOutputStream();
            stream.write(data);
            stream.flush();
            stream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @SysOperationLog("移除文件")
    @TokenCheck  //此注解使用开启token验证，对于访问后台数据的方法，请打开此注解
    @ApiOperation(value = "移除文件", notes = "移除文件", httpMethod = "POST")
    @RequestMapping(value = "/deleteFile", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseVO deleteFile(@RequestBody @Valid Map<String, Object> params, BindingResult result)throws Exception{
       //参数校验
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return errorResponse(error);
            }
        }
        try {
            Map<String, Object> resultMaps=baseFileRscApi.deleteBaseFileRscTrans(params);
            if(resultMaps!=null){
                return successResponse(resultMaps,Constant.SUCCESS);
            }else{
                return errorResponse(Constant.FAIL);
            }
        } catch (Exception e) {
            return errorResponse(e.getMessage());
        }
    }

    @ApiOperation(value = "文件列表前台", notes = "文件列表方法", httpMethod = "POST")
    @RequestMapping(value = "/fileListFace", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ResponseVO fileListByClubFace(@RequestBody @Valid Map<String, Object> params, BindingResult result)throws Exception {
        //参数校验
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                return errorResponse(error);
            }
        }
        try {
            PageInfo<Map<String, Object>> resultMaps=baseFileRscApi.findBaseFileRscPage(params);
            if(resultMaps!=null){
                return successResponse(resultMaps,Constant.SUCCESS);
            }else{
                return errorResponse(Constant.FAIL);
            }
        } catch (Exception e) {
            return errorResponse(e.getMessage());
        }
    }
}
