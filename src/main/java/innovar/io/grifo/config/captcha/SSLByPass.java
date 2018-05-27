/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

/**
 * The sslByp class is implemment to
 *
 * @version :1.0
 * @Author :Warren
 * @since :11/05/2018
 */
package innovar.io.grifo.config.captcha;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

/**
 *
 * @author Cmop
 */
public class SSLByPass {

    private static SSLByPass instancia;
    private SSLSocketFactory sslSocketFactory;

    public SSLByPass() throws NoSuchAlgorithmException, KeyManagementException {
        // Create a trust manager that does not validate certificate chains
        final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            @Override
            public void checkClientTrusted(final X509Certificate[] chain, final String authType) {
            }

            @Override
            public void checkServerTrusted(final X509Certificate[] chain, final String authType) {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        }};

        // Install the all-trusting trust manager
        final SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
        // Create an ssl socket factory with our all-trusting manager
        sslSocketFactory = sslContext.getSocketFactory();
    }

    public static SSLByPass getInstancia() throws NoSuchAlgorithmException, KeyManagementException {
        if (instancia == null) {
            instancia = new SSLByPass();
        }
        return instancia;
    }

    public SSLSocketFactory getSslSocketFactory() {
        return sslSocketFactory;
    }
}
