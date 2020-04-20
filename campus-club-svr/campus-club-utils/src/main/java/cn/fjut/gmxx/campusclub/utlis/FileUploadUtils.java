package cn.fjut.gmxx.campusclub.utlis;/**
 * Created by admin on 2020/1/19.
 */

import cn.fjut.gmxx.campusclub.exception.ExceptionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author : shenjindui
 * @date : 2020-01-19 17:49
 **/
@Component
public class FileUploadUtils {
    /**
     * 默认大小 50M
     */
    public static final long DEFAULT_MAX_SIZE = 50 * 1024 * 1024;

    /**
     * 默认的文件名最大长度 100
     */
    public static final int DEFAULT_FILE_NAME_LENGTH = 100;

    /**
     * 默认上传的地址
     */

    private static String defaultBaseDir;

    /**
     * 默认文件类型jpg
     */
    public static final String IMAGE_JPG_EXTENSION = ".jpg";

    private static int counter = 0;

    @Value("${file-service.profile}")
    public  void setDefaultBaseDir(String defaultBaseDir) {
        FileUploadUtils.defaultBaseDir = defaultBaseDir;
    }

    public  String getDefaultBaseDir() {
        return defaultBaseDir;
    }

    /**
     * 根据文件路径上传
     *
     * @param baseDir 相对应用的基目录
     * @param file    上传的文件
     * @return 文件名称
     * @throws IOException
     */
    public static final String uploadPicture(String baseDir, MultipartFile file) throws IOException {
        try {
            return upload(baseDir, file, FileUploadUtils.IMAGE_JPG_EXTENSION);
        } catch (Exception e) {
            throw new IOException(e.getMessage(), e);
        }
    }

    /**
     * 根据文件路径上传
     *
     * @param file    上传的文件
     * @return 文件名称
     * @throws IOException
     */
    public static  String uploadText( MultipartFile file,String fileName) throws IOException {
        try {
             String  filePath = defaultBaseDir;
            return  uploadFile(file, filePath,fileName);
        } catch (Exception e) {
            throw new IOException(e.getMessage(), e);
        }
    }

    /**
     * 文件上传
     *
     * @param baseDir   相对应用的基目录
     * @param file      上传的文件
     * @param extension 上传文件类型
     * @return 返回上传成功的文件名
     * @throws IOException 比如读写文件出错时
     */
    public static final String  upload(String baseDir, MultipartFile file, String extension) throws IOException{
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }
        int fileNamelength = file.getOriginalFilename().length();
        if (fileNamelength > FileUploadUtils.DEFAULT_FILE_NAME_LENGTH) {
            // 此处可进行异常处理throws
            System.out.println("文件名长度超出限定长度");
        }

        assertAllowed(file);

        String fileName = extractFilename(file, extension);
        String filePath = "D:/profile/temp/";
        File dest = getAbsoluteFile(fileName);
        //File desc = getAbsoluteFile(baseDir, baseDir + file.getOriginalFilename());
        file.transferTo(dest);
        return fileName;
    }

    public static final String extractFilename(MultipartFile file, String extension) {
        String filename = file.getOriginalFilename();
        filename = DateUtils.datePath() + "\\" + encodingFilename(filename) + "." +extension;
        return filename;
    }

    private static final File getAbsoluteFile(String filename) throws IOException {
        File desc = new File(File.separator + filename);

        if (!desc.getParentFile().exists()) {
            desc.getParentFile().mkdirs();
        }
        if (!desc.exists()) {
            desc.createNewFile();
        }
        return desc;
    }

    /**
     * 编码文件名
     */
    private static final String encodingFilename(String filename) {
        filename = filename.replace("_", " ");
        filename = MD5Utils.stringToMD5(filename + System.nanoTime() + counter++);
        return filename;
    }

    /**
     * 文件大小校验
     *
     * @param file 上传的文件
     */
    public static final void assertAllowed(MultipartFile file) {//throws FileSizeLimitExceededException {
        long size = file.getSize();
        if (size > DEFAULT_MAX_SIZE) {
            // 此处可进行异常处理throws
            System.out.println("文件大小超出最大限定大小");
        }
    }

    //文件上传工具类服务方法

    public static String uploadFile(MultipartFile file, String filePath, String fileName) throws Exception{
        if (file==null) {
            throw ExceptionFactory.getBizException("上传失败，请选择文件");
        }
        int fileNamelength = file.getOriginalFilename().length();
        if (fileNamelength > FileUploadUtils.DEFAULT_FILE_NAME_LENGTH) {
            throw ExceptionFactory.getBizException("文件名长度超出限定长度");
        }
        assertAllowed(file);
        fileName=FileUploadUtils.extractFilename(file,file.getOriginalFilename().substring(file.getOriginalFilename().indexOf(".")+1,file.getOriginalFilename().length()));
        File targetFile = new File(filePath+fileName);
        if (!targetFile.getParentFile().exists()) {
            targetFile.getParentFile().mkdirs();
        }
        if (!targetFile.exists()) {
            targetFile.createNewFile();
        }
        FileOutputStream out = new FileOutputStream(filePath+fileName);
        out.write(file.getBytes());
        out.flush();
        out.close();
        //这边需要将"\"转为"/"
        return UrlUtils.changeUrlType(filePath)+UrlUtils.changeUrlType(fileName);
    }
}
