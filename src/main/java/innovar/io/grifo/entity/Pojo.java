/**
 * The pojo class is implemment to
 *
 * @version :1.0
 * @Author :Warren
 * @since :28/05/2018
 */
package innovar.io.grifo.entity;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.bson.types.ObjectId;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.*;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
@Document
public class Pojo {
    @Id
    private String id;
    private String nombre;
    private Long edad;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date lastModified;

    @CreatedBy
    private User createBy;
    @LastModifiedBy
    private User lastModifiedBy;
}
@Configuration
@EnableMongoAuditing
class config{
            @Bean
            public AuditorAware<User> myAuditorProvider() {
                return new SpringSecurityAuditorAware();
            }
}

//@Configuration
//class MongoConfiguration  {
//
//    public @Bean MongoClient mongoClient() {
//        return new MongoClient("localhost");
//    }
//
//    public @Bean
//    MongoTemplate mongoTemplate() {
//        return new MongoTemplate(mongoClient(), "mydatabase");
//    }
//
//
//
//}
class SpringSecurityAuditorAware implements AuditorAware<User> {
    @Override
    public Optional<User> getCurrentAuditor() {
        return Optional.of(new User().setAllName("warrencitopa es el q edito esto").setRucUser(new ObjectId().toString()).setUserName(new ObjectId().toString()).setCompany(new Company().setRuc(new ObjectId().toString())));
    }
}