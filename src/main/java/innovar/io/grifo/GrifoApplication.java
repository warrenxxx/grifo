package innovar.io.grifo;

import com.mongodb.DB;
import com.mongodb.MongoClientURI;
import com.mongodb.reactivestreams.client.MongoClients;
import innovar.io.grifo.config.captcha.CaptchaResolv;
import innovar.io.grifo.entity.Pojo;
import innovar.io.grifo.repository.PojoDao;
import innovar.io.grifo.security.CorsConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.SimpleReactiveMongoDatabaseFactory;
import org.springframework.web.server.WebFilter;


@SpringBootApplication
public class GrifoApplication{

    public static void main(String[] args) {
        SpringApplication.run(GrifoApplication.class, args);
        com.sun.javafx.application.PlatformImpl.startup(()->{});
    }

    @Bean
    CaptchaResolv captchaResolv(){
        return new CaptchaResolv();
    };
    @Bean
    public WebFilter corsFilter() {
        return CorsConfiguration.corsFilter();
    }

//    @Bean
//    CommandLineRunner commandLineRunner(PojoDao dao){
//        return args -> {
//            dao.insert(new Pojo().setEdad(45l).setNombre("AMIR")).subscribe(System.out::println);
//        };
//    }
//    @Autowired
//    Environment environment;
//
//    @Bean
//    public ReactiveMongoDatabaseFactory reactiveMongoDatabaseFactory(){
//        return new SimpleReactiveMongoDatabaseFactory(MongoClients.create(),"warren");
//    }

}
