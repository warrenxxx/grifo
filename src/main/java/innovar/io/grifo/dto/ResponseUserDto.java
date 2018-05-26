/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package innovar.io.grifo.dto;

import innovar.io.grifo.entity.Company;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

/**
 * The ResponseUserDto class is implemment to
 *
 * @version :1.0
 * @Author :warren
 * @since :20/03/2018
 */

@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Data
@Document
public class ResponseUserDto {

    private String _id;
    private String allName;
    private LocalDate birthDate;
    private String email;
    private String userName;

    private String token;
    private Company company;


}
