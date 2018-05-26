package innovar.io.grifo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

/**
 * The Person class is implemment to
 *
 * @version :1.0
 * @Author :warren
 * @since :17/03/2018
 */

@Document
@Accessors(chain = true)
@Data
@NoArgsConstructor
public class Person {

    @Id
    private ObjectId _id;

    @Indexed
    private String allName;

    @Indexed
    private String rucUser;

    @Indexed
    private String dni;

    private Address address;

    private String estateCondition;

}

