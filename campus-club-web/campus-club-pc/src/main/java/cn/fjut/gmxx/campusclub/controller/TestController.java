package cn.fjut.gmxx.campusclub.controller;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description: springboot集成freemarker导出word文件
 * @Author: weishihuai
 * @Date: 2019/2/23 20:10
 */
@Api(tags = "测试下载接口")
@Controller
@RequestMapping(value = "/api")
public class TestController {

    // 处理下载word文档
    @GetMapping("/download")
    public void downloadWord(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            // 告诉浏览器用什么软件可以打开此文件
            response.setHeader("content-Type", "application/msword");
            // 下载文件的默认名称
            response.setHeader("Content-Disposition", "attachment;filename=违纪处分通知.doc");
            Map<String, Object> dataMap = this.getData();
            //创建配置实例对象
            Configuration configuration = new Configuration();
            //设置编码
            configuration.setDefaultEncoding("UTF-8");
            //加载需要装填的模板
            configuration.setClassForTemplateLoading(this.getClass(), "/");
            //设置对象包装器
            configuration.setObjectWrapper(new DefaultObjectWrapper());
            //设置异常处理器
            configuration.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
            //获取ftl模板对象
            Template template = configuration.getTemplate("export.ftl");

            /*//输出文件
            File outFile = new File(filePath+File.separator+fileName);
            //如果输出目标文件夹不存在，则创建
            if (!outFile.getParentFile().exists()){
                outFile.getParentFile().mkdirs();
            }*/
           //Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile),"UTF-8"));

            //输出文档
            StringBuilder fileName = new StringBuilder("");
            String uuid = UUID.randomUUID().toString();
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
            fileName.append(uuid).append("_").append(sdf1.format(new Date())).append("违纪处分").append(".doc");

            try {
                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition", "attachment;filename="
                        + new String(fileName.toString().getBytes("GBK"), "ISO-8859-1"));
                response.setCharacterEncoding("utf-8");//处理乱码问题
                //生成Word文档
                Writer out = new BufferedWriter(new OutputStreamWriter(response.getOutputStream()));
                template.process(dataMap, out);

             //  template.process(dataMap,response.getWriter());

//                //关闭流
                out.flush();
                out.close();

//                response.flushBuffer();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Map<String, Object> getData() {
        Map<String, Object> dataMap = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //基础数据
        dataMap.put("STNAME", "魏士淮");//社团名称
        dataMap.put("STCD", "计算机学院");
        dataMap.put("COLLEGENO", "计算机科学与技术");
        dataMap.put("SCHOOLNO", "13科技一班");
        dataMap.put("STCHARGENAME", "日常违纪（违反第一条第一项规定）");
        dataMap.put("STNATURE", "日常违纪");
        dataMap.put("CREATETIME", sdf.format(new Date()));

        //表格数据
        List<Map<String, Object>> users = new ArrayList<>();
        for (int i = 0; i <= 9; i++) {
            Map<String, Object> userMap = new HashMap<>();
            userMap.put("MEMBERCD", "000001");
            userMap.put("MEMBERNAME", "sjd");
            userMap.put("MEMBERSNO", "31567890" );
            userMap.put("MAJOR", "31567890" );
            userMap.put("CLASS", "31567890" );
            userMap.put("SEX", "1" );
            userMap.put("CREATETIME", "7777" );
            users.add(userMap);
        }
        dataMap.put("userList", users);
        return dataMap;
    }

    public void createWord(Map dataMap,String templateName,String filePath,String fileName){
        try {
            //创建配置实例
            Configuration configuration = new Configuration();

            //设置编码
            configuration.setDefaultEncoding("UTF-8");

            //ftl模板文件
            configuration.setClassForTemplateLoading(this.getClass(),"/");

            //获取模板
            Template template = configuration.getTemplate(templateName);

            //输出文件
            File outFile = new File(filePath+File.separator+fileName);

            //如果输出目标文件夹不存在，则创建
            if (!outFile.getParentFile().exists()){
                outFile.getParentFile().mkdirs();
            }

            //将模板和数据模型合并生成文件
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile),"UTF-8"));

            //生成文件
            template.process(dataMap, out);

            //关闭流
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
