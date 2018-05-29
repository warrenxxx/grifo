/**
 * The pojo class is implemment to
 *
 * @version :1.0
 * @Author :Warren
 * @since :28/05/2018
 */
package innovar.io.grifo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class Audit {
    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date lastModified;

    @CreatedBy
    private User createBy;
    @LastModifiedBy
    private User lastModifiedBy;
}
