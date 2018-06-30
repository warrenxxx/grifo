/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package innovar.io.grifo.config.captcha;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.List;

//import java.net.HttpURLConnection;
//import javax.net.ssl.HttpsURLConnection;

/**
 *
 * @author CMOP
 */
public class PeticionCookie {
    public static File fileReniec;
    public static File fileSunnat;


    public Image peticionConCookieImagenSSL(String urlp, String metodo, String parametros, List<HttpCookie> cookies) throws Exception {
        System.setProperty("http.agent", BusquedaCookie.USER_AGENT_FIREFOX);
        try {
            CookieManager manager = new CookieManager();
            CookieHandler.setDefault(manager);
            CookieStore cookieJar = manager.getCookieStore();
            URL url = new URL(urlp);
            javax.net.ssl.HttpsURLConnection connection = (javax.net.ssl.HttpsURLConnection) url.openConnection();
            connection.setSSLSocketFactory(SSLByPass.getInstancia().getSslSocketFactory());
            cookieJar.add(url.toURI(), cookies.get(0));
//            cookieJar.add(url.toURI(), cookies.get(1));
 
            connection.setRequestMethod(metodo);
            //-----------------------------------------------------------------------------------------
            connection.connect(); //Conectar
            connection.getContent();

            InputStream initialStream = connection.getInputStream();
            InputStream inputStream=connection.getInputStream();
            byte[] buffer = new byte[initialStream.available()];
            initialStream.read(buffer);

            fileReniec = new File(getClass().getResource("/img/w2.jpg").getPath());

            OutputStream outStream = new FileOutputStream(fileReniec);
            outStream.write(buffer);

            BufferedInputStream reader = new BufferedInputStream(connection.getInputStream());
             ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();



            int c;
            while ((c = reader.read()) != -1) {
                byteArrayOut.write(c);
                outStream.write(c);
            }
            Image image = Toolkit.getDefaultToolkit().createImage(byteArrayOut.toByteArray());
            reader.close();
            return image;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se puede obtener Cookies ", "Consulta DNI - peticionConCookieImagenSSL", JOptionPane.ERROR_MESSAGE);
            throw e;
        }
    }
 
    public String[] peticionConCookieStringSSL(String urlp, String metodo, String parametros, List<HttpCookie> cookies) throws Exception {
        System.out.println(urlp);
        System.out.println(metodo);
        System.out.println(parametros);
        System.out.println(cookies);

        System.setProperty("http.agent", BusquedaCookie.USER_AGENT_FIREFOX);
        try {
            
            CookieManager manager = new CookieManager();
            CookieHandler.setDefault(manager);
            CookieStore cookieJar = manager.getCookieStore();
            URL url = new URL(urlp);
            javax.net.ssl.HttpsURLConnection connection = (javax.net.ssl.HttpsURLConnection) url.openConnection();
            connection.setSSLSocketFactory(SSLByPass.getInstancia().getSslSocketFactory());
            cookieJar.add(url.toURI(), cookies.get(0));            
//            cookieJar.add(url.toURI(), cookies.get(1));
 
            connection.setRequestMethod(metodo);
            connection.setDoOutput(true); // Esto permite agregar los parametros
            // Escribiendo las variables
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(parametros);
            writer.flush();
            //-----------------------------------------------------------------------------------------
            connection.connect(); //Conectar            
            connection.getContent();//error
            StringBuilder answer = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"ISO-8859-1"));
            String line;
            int linea = 1;
            while ((line = reader.readLine()) != null) {
                if (linea >= 154 && linea <= 156) {
                    if(linea!=154) answer.append(" ");
                    answer.append(line.trim());
                }
//                System.out.println(linea + " == " + line.trim());
                linea++;
            }
            writer.close();
            reader.close();
            
            String[] rpta = new String[1];
            rpta[0] = answer.toString().replace("<br>", "").trim();
            
            return rpta;
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se puede obtener Cookies ", "Consulta DNI - peticionConCookieStringSSL", JOptionPane.ERROR_MESSAGE);
            throw e;
        }
    }

    public Image peticionConCookieImagen(String urlp, String metodo, String parametros, List<HttpCookie> cookies) throws Exception {
        System.setProperty("http.agent", BusquedaCookie.USER_AGENT_FIREFOX);
        try {
            CookieManager manager = new CookieManager();
            CookieHandler.setDefault(manager);
            CookieStore cookieJar = manager.getCookieStore();
            URL url = new URL(urlp);
            java.net.HttpURLConnection connection = (java.net.HttpURLConnection) url.openConnection();
            cookieJar.add(url.toURI(), cookies.get(0));
//            cookieJar.add(url.toURI(), cookies.get(1));

            connection.setRequestMethod(metodo);
            //-----------------------------------------------------------------------------------------
            connection.connect(); //Conectar
            connection.getContent();
            BufferedInputStream reader = new BufferedInputStream(connection.getInputStream());
            ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();


            fileSunnat = new File(getClass().getResource("/img/w2.jpg").getPath());

            OutputStream outStream = new FileOutputStream(fileSunnat);


            int c;
            while ((c = reader.read()) != -1) {
                byteArrayOut.write(c);
                outStream.write(c);
            }
            Image image = Toolkit.getDefaultToolkit().createImage(byteArrayOut.toByteArray());
            reader.close();
            return image;
        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "No se puede obtener Cookies ", "Consulta RUC - peticionConCookieImagen", JOptionPane.ERROR_MESSAGE);
            System.out.println("error");
            throw e;
        }
    }

    public String peticionConCookieString(String urlp, String metodo, String parametros, List<HttpCookie> cookies) throws Exception {

        System.setProperty("http.agent", BusquedaCookie.USER_AGENT_FIREFOX);
        try {
            CookieManager manager = new CookieManager();
            CookieHandler.setDefault(manager);
            CookieStore cookieJar = manager.getCookieStore();
            URL url = new URL(urlp);
            java.net.HttpURLConnection connection = (java.net.HttpURLConnection) url.openConnection();
            cookieJar.add(url.toURI(), cookies.get(0));
//            cookieJar.add(url.toURI(), cookies.get(1));
 
            connection.setRequestMethod(metodo);
            connection.setDoOutput(true); // Esto permite agregar los parametros
            // Escribiendo las variables
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(parametros);
            writer.flush();
            //-----------------------------------------------------------------------------------------
            connection.connect(); //Conectar

            connection.getContent();
            String answer = "";
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"ISO-8859-1"));
            String line;
            int linea = 1;
            
            while ((line = reader.readLine()) != null) {
                answer=answer+line;
                linea++;
            }
            writer.close();
            reader.close();
            
            return answer;
            
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "No se puede obtener Cookies ", "Consulta RUC - peticionConCookieString", JOptionPane.ERROR_MESSAGE);
            throw e;
        }
    }
}
