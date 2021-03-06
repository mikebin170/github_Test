package baseFrame.commonUtils;

import baseFrame.apiUtils.ApiConf;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * Created by yaolei on 2017/10/17.
 */
public class BaseConf {
    private static String propFile = "src/main/resources/baseConf.properties";
    private static Map<String, String> confMap = new HashMap<String, String>();
    private static final Logger logger = Logger.getLogger(ApiConf.class);


    //读取apiConf.properties中的配置信息
    static {
        Properties props = new Properties();
        if (!new File(propFile).exists()) {
            logger.info("配置文件不存在,请检查");
            System.exit(0);
        }
        try {
            logger.info("读取配置文件 " + new File(propFile).getAbsolutePath());
            props.load(new InputStreamReader(new FileInputStream(propFile), "UTF-8"));
            setProps(props);
        } catch (IOException e) {
            logger.error("读取配置文件出错 " + new File(propFile).getAbsolutePath());
            e.printStackTrace();
            System.exit(0);
        }

    }

    private static void setProps(Properties props) {
        Iterator<String> keysIt = props.stringPropertyNames().iterator();
        while (keysIt.hasNext()) {
            String key = keysIt.next();
            confMap.put(key, props.getProperty(key));
        }
    }

    public static String getProperty(String property) {
        return confMap.get(property);
    }


}
