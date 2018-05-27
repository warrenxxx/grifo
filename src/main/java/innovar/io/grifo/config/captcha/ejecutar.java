/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

/**
 * The ejecutar class is implemment to
 *
 * @version :1.0
 * @Author :Warren
 * @since :25/05/2018
 */
package innovar.io.grifo.config.captcha;

import java.net.HttpCookie;
import java.util.List;

public class ejecutar {
    public String cokie="PHPSESSID";
    public String value="tld6tldoeabqb383tae7vj8kr3";

    public static List<Object> logeoSunnat;

    public static void main(String args[]) {
        try {
            logeoSunnat = new BusquedaCookie().buscarCookieLogueo("http://bienestar.unsaac.edu.pe/");
            logeoSunnat.forEach(e->
                    System.out.println(e));
        } catch (Exception e) {
            e.printStackTrace();
        }

        StringBuilder sb_parametros = new StringBuilder();
        sb_parametros.append("accion=registro.php&");//ok
        sb_parametros.append("codigo=");
        sb_parametros.append("100505");
        sb_parametros.append("&");
        sb_parametros.append("pass=87592");
        sb_parametros.append("&");
        sb_parametros.append("cap=sKZ53");

        String parametros = sb_parametros.toString();
        StringBuilder sb_url = new StringBuilder();
        sb_url.append("http://bienestar.unsaac.edu.pe/registro.php;");

        System.out.println(sb_parametros.toString());
        List<HttpCookie> cookies = (List<HttpCookie>) logeoSunnat.get(0);
        for (HttpCookie httpCookie : cookies) {
            if (httpCookie.getName().compareToIgnoreCase("PHPSESSID") == 0) {
                sb_url.append(httpCookie.getName());
                sb_url.append("=");
                sb_url.append(httpCookie.getValue());
            }
        }
        String rpta=null;
        try {
            rpta = new PeticionCookie().peticionConCookieString(sb_url.toString(), "POST", parametros, (List<HttpCookie>)logeoSunnat.get(0));
            System.out.println(rpta);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
