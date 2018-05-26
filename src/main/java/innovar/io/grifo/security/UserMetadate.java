/**
 * The UserMetadate class is implemment to
 *
 * @version :1.0
 * @Author :Warren
 * @since :26/05/2018
 */
package innovar.io.grifo.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.bson.types.ObjectId;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserMetadate {
    private ObjectId id;
    private String role;
}
