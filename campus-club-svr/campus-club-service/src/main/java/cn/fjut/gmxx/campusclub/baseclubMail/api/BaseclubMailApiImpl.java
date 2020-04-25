package cn.fjut.gmxx.campusclub.baseclubMail.api;/**
 * Created by admin on 2020/4/25.
 */

import cn.fjut.gmxx.campusclub.baseclubMail.service.IBaseClubMailService;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author : shenjindui
 * @date : 2020-04-25 13:39
 **/
@Service("baseclubMailApi")
public class BaseclubMailApiImpl implements BaseclubMailApi {
    @Autowired
    IBaseClubMailService baseClubMailService;
    @Override
    public void sendHtmlMail(Map<String, Object> params) {
        String to= MapUtils.getString(params,"to");//发送给谁
        String subject=MapUtils.getString(params,"subject");//主题
        String content=MapUtils.getString(params,"content");//内容
        baseClubMailService.sendHtmlMail(to,subject,content);
    }
}