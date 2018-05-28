package innovar.io.grifo;

import innovar.io.grifo.config.captcha.CaptchaResolv;
import innovar.io.grifo.repository.PersonDao;
import innovar.io.grifo.security.CorsConfiguration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.web.server.WebFilter;

import java.awt.print.Pageable;

@SpringBootApplication
public class GrifoApplication {

    public static void main(String[] args) {
        SpringApplication.run(GrifoApplication.class, args);
//        com.sun.javafx.application.PlatformImpl.startup(()->{});
    }

    @Bean
    CaptchaResolv captchaResolv(){
        return new CaptchaResolv();
    };
    @Bean
    public WebFilter corsFilter() {
        return CorsConfiguration.corsFilter();
    }

    @Bean
    CommandLineRunner commandLineRunner(PersonDao dao){
        return args -> {

//            dao.findAllBy(TextCriteria.forDefaultLanguage().matchingPhrase("ESCA")).limitRequest(10).subscribe(System.out::println,null,()->System.out.print("termino"));
//                dao.findAllByAllNameContaining("PESCA").subscribe(System.out::println,null,()->{
//                    System.out.println("wwww");
//                });

//            dao.findAllByAllNameContaining("ENEL",PageRequest.of(0,10)).subscribe(System.out::println,null,()->{
//                System.out.println("termino");
//            });
            dao.findAllByAllNameContainingOrRucUserContaining("03","20",PageRequest.of(0,6)).subscribe(System.out::println,null,()->{
                System.out.println("temino 2");
            });
        };
    }
}
