package innovar.io.grifo;

import innovar.io.grifo.config.captcha.CaptchaResolv;
import innovar.io.grifo.repository.UserDao;
import innovar.io.grifo.security.CorsConfiguration;
import innovar.io.grifo.service.EmployesService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.web.server.WebFilter;



@SpringBootApplication
public class GrifoApplication{

    public static void main(String[] args) {
        SpringApplication.run(GrifoApplication.class, args);

    }

    @Bean
    CaptchaResolv captchaResolv(){
        return new CaptchaResolv();
    };

    @Bean
    CommandLineRunner runner(EmployesService service , UserDao userDao){
        return args -> {
            System.out.println("la csmre");
        };
    }

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
