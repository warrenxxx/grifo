package innovar.io.grifo.config;

/**
 * The Properties class is implemment to
 *
 * @version :1.0
 * @Author :warren
 * @since :21/03/2018
 */

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Stream;

public class AppProperties {
    private final static String applicationProperties = "/application.properties";

    public static Map<String, String> readProperties(String properties[]) {
        Properties prop = new Properties();
        InputStream input = null;
        Map res = new HashMap();

        try {
            input = AppProperties.class.getResourceAsStream(applicationProperties);
            prop.load(input);
            Stream.of(properties).forEach(e -> res.put(e, prop.get(e)));
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return res;
        }
    }

    public static Map<String, String> readAllProperties() {
        Properties prop = new Properties();
        InputStream input = null;
        Map res = new HashMap();
        try {
            input = AppProperties.class.getResourceAsStream(applicationProperties);
            prop.load(input);
            prop.forEach((e, k) -> res.put(e.toString(), k.toString()));
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return res;
        }
    }

    public static String readProperties(String key) {
        Properties prop = new Properties();
        InputStream input = null;
        String res = null;
        try {
            input = AppProperties.class.getResourceAsStream(applicationProperties);
            prop.load(input);

            res = prop.getProperty(key);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return res;
        }
    }


}
