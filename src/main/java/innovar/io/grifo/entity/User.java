/**
 * The User class is implemment to
 *
 * @version :1.0
 * @Author :Warren
 * @since :26/05/2018
 */
package innovar.io.grifo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.scheduling.support.SimpleTriggerContext;

import java.time.LocalDate;


@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Document
public class User {
    @Id
    private String _id;

    @Indexed
    private String allName;

    @Indexed
    private String rucUser;

    @Indexed
    private String dni;
    private Address address;
    private String estateCondition;

    private LocalDate birthDate;

    private Company company;


    private String email;
    private String password;
    @Indexed(unique = true)

    private String userName;

    private String role;
    private String []employes;

}
