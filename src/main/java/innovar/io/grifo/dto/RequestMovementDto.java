/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

/**
 * The RequestMovementDto class is implemment to
 *
 * @version :1.0
 * @Author :Warren
 * @since :10/05/2018
 */
package innovar.io.grifo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class RequestMovementDto {
    private LocalDate date;
    private String name;
    private String documentNumber;
    private String addres;
    private Double igv;
    private Boolean isTicket;
    private RequestMovementDetailDto[] movementDetails;
    private String placa;
}
