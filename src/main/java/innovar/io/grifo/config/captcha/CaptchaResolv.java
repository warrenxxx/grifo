/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

/**
 * The CaptchaResolv class is implemment to
 *
 * @version :1.0
 * @Author :Warren
 * @since :11/05/2018
 */
package innovar.io.grifo.config.captcha;

import innovar.io.grifo.entity.Person;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.bson.types.ObjectId;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.lept;
import org.bytedeco.javacpp.tesseract;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.HttpCookie;
import java.util.List;
import java.util.stream.Stream;


@Service
public class CaptchaResolv {
    public TextField txtReniec;
    public TextField txtSunnat;
    public ImageView imgSunnat;
    public ImageView imgReniec;


    public static String codReniec;
    public static String codSunnat;

    Stage stage;

    public CaptchaResolv setImageReniec(Image imageReniec){
        this.imgReniec.setImage(imageReniec);
        return this;
    }
    public CaptchaResolv setImageSunnat(Image imageSunnat){
        this.imgSunnat.setImage(imageSunnat);
        return this;
    }

    public CaptchaResolv setStage(Stage stage) {
        this.stage = stage;
        return this;
    }

    public void iniciar(ActionEvent actionEvent) {
        codReniec=txtReniec.getText();
        codSunnat=txtSunnat.getText();
        Platform.setImplicitExit(false);

        stage.hide();

    }
    List<Object> logeoReniec;
    List<Object> logeoSunnat;

    public Person getRuc(String ruc, File tessdata, String lang){

        System.out.println(tessdata.toURI().toString());
        System.out.println(lang);
        try {

            logeoSunnat = new BusquedaCookie().buscarCookieLogueo("http://e-consultaruc.sunat.gob.pe/cl-ti-itmrconsruc/frameCriterioBusqueda.jsp");
            new PeticionCookie().peticionConCookieImagen("http://e-consultaruc.sunat.gob.pe/cl-ti-itmrconsruc/captcha?accion=image", "POST", "", (List<HttpCookie>) logeoSunnat.get(0));
            codSunnat=read(PeticionCookie.fileSunnat,tessdata,lang);


        } catch (Exception e) {
            e.printStackTrace();
        }

        StringBuilder sb_parametros = new StringBuilder();
        sb_parametros.append("accion=consPorRuc&");//ok
        sb_parametros.append("nroRuc=");
        sb_parametros.append(ruc);
        sb_parametros.append("&");
        sb_parametros.append("codigo=");
        sb_parametros.append(codSunnat);


        String parametros = sb_parametros.toString();
        StringBuilder sb_url = new StringBuilder();
        sb_url.append("http://e-consultaruc.sunat.gob.pe/cl-ti-itmrconsruc/jcrS00Alias;");


        List<HttpCookie> cookies = (List<HttpCookie>) logeoSunnat.get(0);
        for (HttpCookie httpCookie : cookies) {
            if (httpCookie.getName().compareToIgnoreCase("jsessionid") == 0) {
                sb_url.append(httpCookie.getName());
                sb_url.append("=");
                sb_url.append(httpCookie.getValue());
            }
        }
        String rpta = "";
        System.out.println("wwssxx");

        try {
            rpta = new PeticionCookie().peticionConCookieString(sb_url.toString(), "POST", parametros, (List<HttpCookie>)logeoSunnat.get(0));
            System.out.println(rpta);
            Document doc=Jsoup.parse(rpta);
            Element table=doc.getElementsByTag("table").get(0);
            Stream.of(rpta).forEach(e->System.out.println("--->"+e));
            return getUser(table);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Person();
    }
    public Person getDni(String dni, File tessdata, String lang){
        System.out.println("buscando dni");
        try {
            logeoSunnat = new BusquedaCookie().buscarCookieLogueo("http://e-consultaruc.sunat.gob.pe/cl-ti-itmrconsruc/frameCriterioBusqueda.jsp");
            new PeticionCookie().peticionConCookieImagen("http://e-consultaruc.sunat.gob.pe/cl-ti-itmrconsruc/captcha?accion=image", "POST", "", (List<HttpCookie>) logeoSunnat.get(0));
            codSunnat=read(PeticionCookie.fileSunnat,tessdata,lang);
        } catch (Exception e) {
            e.printStackTrace();
        }
        StringBuilder sb_parametros = new StringBuilder();
        sb_parametros.append("accion=consPorTipdoc&");//ok
        sb_parametros.append("nrodoc=");
        sb_parametros.append(dni);
        sb_parametros.append("&");
        sb_parametros.append("tipdoc=");
        sb_parametros.append("1");
        sb_parametros.append("&");
        sb_parametros.append("codigo=");
        sb_parametros.append(codSunnat);

        String parametros = sb_parametros.toString();
        StringBuilder sb_url = new StringBuilder();
        sb_url.append("http://e-consultaruc.sunat.gob.pe/cl-ti-itmrconsruc/jcrS00Alias;");


        List<HttpCookie> cookies = (List<HttpCookie>) logeoSunnat.get(0);
        for (HttpCookie httpCookie :    cookies) {
            if (httpCookie.getName().compareToIgnoreCase("jsessionid") == 0) {
                sb_url.append(httpCookie.getName());
                sb_url.append("=");
                sb_url.append(httpCookie.getValue());
            }
        }
        String rpta = "";

        System.out.println("buscando dni");
        try {
            rpta = new PeticionCookie().peticionConCookieString(sb_url.toString(), "POST", parametros, (List<HttpCookie>)logeoSunnat.get(0));
            System.out.println(rpta);
            Document doc=Jsoup.parse(rpta);
            Elements td=doc.getElementsByTag("td");

            return new Person().setDni(dni)
                    .setAddress(null)
                    .setRucUser(td.get(7).text())
                    .setAllName(td.get(8).text());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Person();
    }
    private String getData(int x, int y, Element table){
        return table.select("tr").get(x).select("td").get(y).text();
    }
    private Person getUser(Element table){
        return new Person()
                .set_id(new ObjectId())
                .setAllName(getData(2,1,table).split("-")[1])
                .setDni(getData(2,1,table).split("-")[0].split(" ")[1])
                .setRucUser(getData(16,4,table).split("-")[0])
                ;
    }
    public String getDni(String dni){
        StringBuilder sb_parametros = new StringBuilder();
        sb_parametros.append("accion=buscar&");
        sb_parametros.append("nuDni=");
        sb_parametros.append(dni);
        sb_parametros.append("&");
        sb_parametros.append("imagen=");
        sb_parametros.append(codReniec);

        String parametros = sb_parametros.toString();
        StringBuilder sb_url = new StringBuilder();
        sb_url.append("https://cel.reniec.gob.pe/valreg/valreg.do;");

        System.out.println(dni);
        System.out.println(codReniec);
        List<HttpCookie> cookies = (List<HttpCookie>) logeoReniec.get(0);
        for (HttpCookie httpCookie : cookies) {
            if (httpCookie.getName().compareToIgnoreCase("jsessionid") == 0) {
                sb_url.append(httpCookie.getName());
                sb_url.append("=");
                sb_url.append(httpCookie.getValue());
            }
        }

        String[]rpta = new String[0];
        try {
            rpta= new PeticionCookie().peticionConCookieStringSSL(sb_url.toString(), "POST", parametros, (List<HttpCookie>) logeoReniec.get(0));
            java.util.stream.Stream.of(rpta).forEach(
                    e->System.out.println("---->>"+e)
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rpta[0];
    }
    public String read(File file, File TESSDATA, String Lang) {

        tesseract.TessBaseAPI api =new tesseract.TessBaseAPI();

        if (api.Init(TESSDATA.getPath().toString(), Lang) != 0) {
            System.err.println("Could not initialize tesseract.");
//            System.exit(1);
        }
        BytePointer outText = null;
        lept.PIX image = null;
        try {
            image = lept.pixRead(file.getAbsolutePath());

            api.SetImage(image);
            // Get OCR result
            outText = api.GetUTF8Text();
            return outText == null ? "" : outText.getString();
        } finally {
            if (outText != null) {
                outText.deallocate();
            }
            if (image != null) {
                lept.pixDestroy(image);
            }
            api.End();
            System.out.println("complete");
        }
    }
}
