package cn.fjut.gmxx.campusclub.manager;
import cn.fjut.gmxx.campusclub.exception.ExceptionFactory;
import cn.hutool.core.util.ObjectUtil;

/**
 * @author : shenjindui
 * @date : 2020-04-20 13:47
 **/
public class AbstractCampusClubApi {

    /**
     * @功能描述 <pre>校验对象是否为空</pre>
     * @param vali 验证对象
     * @return
     */
    protected void validateNull(Object vali) {
        if(!ObjectUtil.isNotNull(vali)){
            throw ExceptionFactory.getBizException("对象为空");
        }
    }
    /**
     * @功能描述 <pre>校验对象是否为空</pre>
     * @param vali 验证对象数组
     * @return
     */
    protected void validateNull(Object[] vali) {
        for(int i = 0; i < vali.length; i ++){
            if(!ObjectUtil.isNotNull(vali[i])){
                throw ExceptionFactory.getBizException("对象为空");
            }
        }
    }
}
