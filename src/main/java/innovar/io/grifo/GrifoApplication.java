package innovar.io.grifo;

import innovar.io.grifo.config.captcha.CaptchaResolv;
import innovar.io.grifo.security.CorsConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.server.WebFilter;

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
}
