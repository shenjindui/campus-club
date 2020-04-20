package cn.fjut.gmxx.campusclub.exception;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @author : shenjindui
 * @date : 2020-01-10 13:42
 **/
public class ExceptionMessageUtils {
    private static Logger logger = LoggerFactory.getLogger(ExceptionMessageUtils.class);
   // private static volatile Properties prop = null;
    //private Map<String, IBizExceptionWarnSpi> bizExceptionWarnSpiMap;
   // private static final String[] errorFiles = new String[]{"classpath*:sys_message/*.properties","classpath:sys_message-*/*.properties"};

    public ExceptionMessageUtils() {
    }
/*
    public static void init() throws IOException {
        if(prop == null) {
            Class var0 = ExceptionMessageUtils.class;
            synchronized(ExceptionMessageUtils.class) {
                if(prop == null) {
                    prop = new Properties();
                    ResourcePatternResolver loader = new PathMatchingResourcePatternResolver();
                    String[] var2 = errorFiles;
                    int var3 = var2.length;

                    for(int var4 = 0; var4 < var3; ++var4) {
                        String errorFile = var2[var4];

                        try {
                            Resource[] resources = loader.getResources(errorFile);
                            Resource[] var7 = resources;
                            int var8 = resources.length;

                            for(int var9 = 0; var9 < var8; ++var9) {
                                Resource resource = var7[var9];
                                if(resource.exists()) {
                                    PropertiesLoaderUtils.fillProperties(prop, resource);
                                }
                            }
                        } catch (IOException var12) {
                            logger.error(var12.getMessage(), var12);
                            throw var12;
                        }
                    }
                }
            }
        }

    }
    */

    public static String getCodeMessage(String errorCode, String... args) {
       /* if(prop == null) {
            try {
                init();
            } catch (Exception var4) {
                throw new RuntimeException(var4.getMessage(), var4);
            }
        }*/

       //message
        String valueForPlace = errorCode;
        if(errorCode != null && !"".equals(errorCode) && valueForPlace != null && !"".equals(valueForPlace) && args != null && args.length > 0) {
            for(int i = 0; i < args.length; ++i) {
                if(args[i] != null) {
                    valueForPlace = valueForPlace.replaceAll("\\{" + i + "\\}", args[i]);
                }
            }
        }

        StringBuffer sb = new StringBuffer();
        sb.append("异常信息【");
        sb.append(valueForPlace);
        sb.append("】");
        return sb.toString();
    }

   /* static {
        try {
            init();
        } catch (IOException var1) {
            logger.warn("异常码信息初始化出错");
        }

    }*/


    public static  void main(String args[]){
        String uuid="323232";
        String text=ExceptionMessageUtils.getCodeMessage(uuid+"错误信息");
        System.out.print(text);

    }
}
