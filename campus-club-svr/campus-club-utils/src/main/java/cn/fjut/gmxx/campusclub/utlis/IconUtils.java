package cn.fjut.gmxx.campusclub.utlis;/**
 * Created by admin on 2020/1/19.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author : shenjindui
 * @date : 2020-01-19 15:32
 **/
public class IconUtils {
    private static Random random = new Random();
    /**
     * 随机获取一个菜单的图标
     * @return
     */
    public static String getRomdam(){
        List<String> list = new ArrayList<String>();
        list.add("el-icon-lx-home");
        list.add("el-icon-lx-cascades");
        list.add("el-icon-lx-copy");
        list.add("el-icon-lx-calendar");
        list.add("el-icon-lx-emoji");
        list.add("el-icon-lx-favor");
        list.add("el-icon-rank");
        list.add("el-icon-lx-warn");
        return list.get(random.nextInt(list.size()));
    }
}
