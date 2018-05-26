/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package innovar.io.grifo.dto;

import innovar.io.grifo.entity.Company;
import innovar.io.grifo.entity.Employe;
import innovar.io.grifo.entity.Movement;
import innovar.io.grifo.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestUserDto {


        private String firstName;
        private String lastName;
        private LocalDate birthDate;
        private String email;
        private String userName;
        private String password;
        private Company company;


    public User getUser(){
        System.out.println(this);
        return new User(
                new ObjectId().toString(),
                getFirstName()+getLastName(),
                "",
                "",
                null,
                "",
                getBirthDate(),

                getCompany(),
                getEmail(),
                getPassword(),
                getUserName(),
                "user",
                new String[]{}
        );
    }
    public Employe getEmployes(){
        return new Employe(
                new ObjectId().toString(),
                getFirstName()+getLastName(),
                getBirthDate(),
                getEmail(),
                getPassword(),
                getUserName(),
                new Movement[]{}
        );
    }
}
