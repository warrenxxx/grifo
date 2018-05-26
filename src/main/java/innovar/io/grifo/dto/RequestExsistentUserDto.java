/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package innovar.io.grifo.dto;

import innovar.io.grifo.entity.Company;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class RequestExsistentUserDto {
    private String id;
    private LocalDate birthDate;
    private String email;
    private String userName;
    private String password;
    private Company company;
}
