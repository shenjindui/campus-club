package cn.fjut.gmxx.campusclub.config;/**
 * Created by admin on 2020/1/19.
 */

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author : shenjindui
 * @date : 2020-01-19 17:36
 **/
@Component
@ConfigurationProperties(prefix = "file-service")
public class FileServerConfig {
    /**
     * 上传路径
     */
    private static String profile;

    public static String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        FileServerConfig.profile = profile;
    }

    // 获取上传头像路径
    public static String getAvatarPath() {
        return profile + "avatar/";
    }

    // 获取下载路径
    public static String getDownloadPath() {
        return profile + "download/";
    }

    // 获取上传路径
    public static String getUploadPath() {
        return profile + "upload/";
    }
}
