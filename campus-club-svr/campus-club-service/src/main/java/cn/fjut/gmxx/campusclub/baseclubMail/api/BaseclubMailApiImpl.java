package cn.fjut.gmxx.campusclub.baseclubMail.api;

import cn.fjut.gmxx.campusclub.baseclubMail.service.IBaseClubMailService;
import cn.fjut.gmxx.campusclub.exception.ExcetionMsg;
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
    private IBaseClubMailService baseClubMailService;

    @Autowired
    private ExcetionMsg excetionMsg;

    @Override
    public void sendHtmlMail(Map<String, Object> params) {
        String to= MapUtils.getString(params,"to");//发送给谁
        String subject=MapUtils.getString(params,"subject");//主题
        String content=MapUtils.getString(params,"content");//内容
        baseClubMailService.sendHtmlMail(to,subject,content);
    }
}
