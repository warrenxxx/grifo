/**
 * The Employe class is implemment to
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
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
@Accessors(chain = true)
public class Employe {

    private String _id;
    private String allName;
    private LocalDate birthDate;
    private String email;
    private String password;
    @Indexed(unique = true)

    private String userName;

    private Movement movements[];
}
