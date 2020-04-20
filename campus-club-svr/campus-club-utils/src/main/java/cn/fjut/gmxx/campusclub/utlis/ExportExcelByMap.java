package cn.fjut.gmxx.campusclub.utlis;/**
 /**
 一个Java导出Excel的通用类     for List<Map<String,Object>>

 因为自己需要用，所以照着entity数据源改成了List<Map<String,Object>>    记录一下

 传入本方法指定的参数直接调用就可以了

 */

import org.apache.poi.hssf.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class ExportExcelByMap {
     /**
            * 这是一个通用的方法，
            * @param sheetName
            *                        表格sheet名
            * @param headers
            *                        表格属性列名数组
            * @param headersField
            *                                           表格属性列名数组所对应的Map的Key值的集合
            * @param excelData
            *                        需要显示的数据集合,集合中一定要放置符合Map风格的类的对象。此方法支持的
            *                        javabean属性的数据类型有基本数据类型及String,Date,byte[](图片数据)
            * @param  response
            *                        与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
            * @param pattern
            *                        如果有时间数据，设定输出格式。
            * @throws Exception     
            */
        public static void exportExcels(String sheetName,String[] headers,String[] headersField,List<Map<String, Object>> excelData,
String pattern,HttpServletResponse response) throws Exception{
// 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
                    int iMaxLines = 65534;
                  // 生成一个表格
                    int index = 0;
                    int page =1;
                  HSSFSheet sheet = workbook.createSheet(sheetName);
                  // 设置表格默认列宽度为15个字节
                  sheet.setDefaultColumnWidth((int) 15);
                  // 产生表格标题行
                  HSSFRow row = sheet.createRow(0);
                  for (int i = 0; i < headers.length; i++) {
                          HSSFCell cell = row.createCell(i);
                          HSSFRichTextString text = new HSSFRichTextString(headers[i]);
                          cell.setCellValue(text);
                  }
                  // 遍历集合数据，产生数据行
                    Iterator<Map<String, Object>> it = excelData.iterator();
                    //String[] headersField = this.selectTableColumn(ztreeids);
                    
                    while (it.hasNext()) {
                                          index++;
                      row = sheet.createRow(index);
                      Map<String, Object> t =    it.next();
                      int m=0;
                            for(short n = 0;n<headersField.length;n++) {
                                          if(n==0)m=0;
                              HSSFCell cell = row.createCell(m);
                              m++;
                              Object value = t.get(headersField[n]);
                              // 判断值的类型后进行强制类型转换
                              String textValue = null;
                              if (value instanceof Date) {
                                      Date date = (Date) value;
                                      SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                                      textValue = sdf.format(date);
                              } else {
                                      // 其它数据类型都当作字符串简单处理
                                      textValue = value==null?null:value.toString();
                              }
                              // 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
                              if (textValue != null) {

/*^//d+(//.//d+)? */

                                      Pattern p = Pattern.compile("^//d+(//.//d+)?");
                                      Pattern p1 = Pattern.compile("^\\d+$");
                                      Matcher matcher = p.matcher(textValue);
                                      Matcher matcher1 = p1.matcher(textValue);
                                      if (matcher.matches()) {
                                              // 是数字当作double处理
                                              cell.setCellValue(Double.parseDouble(textValue));
                                      }else {
                                                     if(matcher1.matches()){
                                                        cell.setCellValue(Long.parseLong(textValue));
                                                 }else {
                                                      cell.setCellValue(textValue);
                                                 }
                                      }
                              }
                      }
                 }
                      
                            //将文件存到浏览器设置的下载位置 注释中为直接保存到后台设置的存储位置
                            OutputStream out = response.getOutputStream();//获得输出流
                            /*try {
                                          out = new FileOutputStream("D:"+sheetName+".xls");
                                          workbook.write(out);
                                          String str1 = "导出" + sheetName + "成功！";
                                          System.out.println(str1);
                                          out.close();
                            } catch (Exception e) {
                                          e.printStackTrace();
                                          String str1 = "导出" + sheetName + "失败！";
                                          System.out.println(str1);
                            } */
                            
                            response.reset();// 清空输出流
                      response.setContentType("application/msexcel");// 定义输出类型
     
                            String filename = sheetName + ".xls";
                            
                            try {
                                          response.setContentType("application/ms-excel;charset=UTF-8");// 定义输出类型    ;charset=UTF-8
                                          response.addHeader("Content-Disposition", "attachment;filename="+
                                                                                       new String(filename.getBytes(), "iso-8859-1"));// 设定输出文件头    new String(filename.getBytes())
                                          
                                          workbook.write(out);// 将数据写出去
                                          String str = "导出" + sheetName + "成功！";
                                          System.out.println(str);
                            } catch (Exception e) {
                                          e.printStackTrace();
                                          String str1 = "导出" + sheetName + "失败！";
                                          System.out.println(str1);
                            } finally {
                                          out.close();
                            }
     }              
               
}