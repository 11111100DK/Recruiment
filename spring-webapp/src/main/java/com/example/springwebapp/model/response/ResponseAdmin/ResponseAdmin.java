package com.example.springwebapp.model.response.ResponseAdmin;/*
 * Created by IntelliJ IDEA.
 * User: Nguyễn Ngọc Thiện
 * Date: 10/1/2023
 * Time: 10:31 AM
 * To change this template use File | Settings | File and Code Templates.
 */

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseAdmin {
    private int id;
    private String role;
    private int status;
    private String fullName;
    private String description;
    private Integer managedBy;
    private String major;
    private int experienceYear;
    private int accountId;
//    private int activeSession;
}
