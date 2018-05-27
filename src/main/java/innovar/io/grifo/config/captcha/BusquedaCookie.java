/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

/**
 * The busqueda class is implemment to
 *
 * @version :1.0
 * @Author :Warren
 * @since :11/05/2018
 */
package innovar.io.grifo.config.captcha;

import javax.net.ssl.HttpsURLConnection;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CMOP
 */
public class BusquedaCookie {

    public static final String USER_AGENT_FIREFOX = "Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US; rv:1.9.0.10) Gecko/2009042316 Firefox/3.0.10 (.NET CLR 3.5.30729)";
    public static final String NOMBRE_COOKIE = "JSESSIONID";

    public List<Object> buscarCookieLogueoSSL(String urlseguridad) throws Exception {
        List<Object> rpta = new ArrayList<Object>(1);
        List<HttpCookie> cookies;
        HttpsURLConnection connection = null;
        try {
            //Activar Oyente de Cookies
            CookieManager manager = new CookieManager();
            manager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
            CookieHandler.setDefault(manager);
            //-----------------------------------------------------------
            //Asignar User Agent para evitar el filtro de navegador
            System.setProperty("https.protocols", "TLSv1,SSLv3,SSLv2Hello");
            System.setProperty("javax.net.debug", "all");
            System.setProperty("http.agent", USER_AGENT_FIREFOX);
            //Crear un objeto URL
            URL url = new URL(urlseguridad);
            connection = (HttpsURLConnection) url.openConnection();
            connection.setSSLSocketFactory(SSLByPass.getInstancia().getSslSocketFactory());
            connection.setRequestMethod("POST");
            connection.setDoInput(true);  // Esto permite leer el contenido despues de la peticion
            connection.connect(); //Conectar
            connection.getContent(); //Obtener contenido
            //Leyendo la Cookie
            CookieStore cookieJar = manager.getCookieStore();
            cookies = cookieJar.getCookies();
            // Leer la rpta
            rpta.add(cookies);
            StringBuilder answer = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                answer.append(line);
            }
            reader.close();
            connection.disconnect();
            rpta.add(answer);
        } catch (IOException e) {

            if (connection.getResponseCode() != 500) {
                JOptionPane.showMessageDialog(null, "No hay conexión", "Consulta DNI - buscarCookieLogueoSSL", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se puede obtener Cookies", "Consulta DNI - buscarCookieLogueoSSL", JOptionPane.ERROR_MESSAGE);
            }

            throw e;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se puede obtener Cookies", "Consulta DNI - buscarCookieLogueoSSL", JOptionPane.ERROR_MESSAGE);
            throw e;
        }
        return rpta;
    }

    public List<Object> buscarCookieLogueo(String urlseguridad) throws Exception {
        List<Object> rpta = new ArrayList<Object>(1);
        List<HttpCookie> cookies;
        HttpURLConnection connection = null;
        try {
            //Activar Oyente de Cookies
            CookieManager manager = new CookieManager();
            manager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
            CookieHandler.setDefault(manager);
            //-----------------------------------------------------------
            //Asignar User Agent para evitar el filtro de navegador
            System.setProperty("http.agent", USER_AGENT_FIREFOX);
            //Crear un objeto URL
            URL url = new URL(urlseguridad);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);  // Esto permite leer el contenido despues de la peticion
            connection.connect(); //Conectar
            connection.getContent(); //Obtener contenido

            //Leyendo la Cookie
            CookieStore cookieJar = manager.getCookieStore();
            cookies = cookieJar.getCookies();
            // Leer la rpta
            rpta.add(cookies);
            StringBuilder answer = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                answer.append(line);
            }
            reader.close();
            connection.disconnect();
            rpta.add(answer);
        } catch (IOException e) {
            if (connection.getResponseCode() != 500) {
                JOptionPane.showMessageDialog(null, "No hay conexión", "Consulta RUC - buscarCookieLogueo", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se puede obtener Cookies", "Consulta RUC - buscarCookieLogueo", JOptionPane.ERROR_MESSAGE);
            }

            throw e;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se puede obtener Cookies", "Consulta RUC - buscarCookieLogueo", JOptionPane.ERROR_MESSAGE);
            throw e;
        }
        return rpta;
    }
}