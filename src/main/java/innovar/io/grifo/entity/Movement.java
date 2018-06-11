/**
 * The Recivo class is implemment to
 *
 * @version :1.0
 * @Author :warre
 * @since :16/04/2018
 */
package innovar.io.grifo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.util.List;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Movement {
    private String id;
    private LocalDate date;
    private String numberOfDocument;
    private String typeOfDocument;
    private MovementDetail[] movementDetails;
    private NumberOfPrint number[];
    private String name;
    private String address;
}
