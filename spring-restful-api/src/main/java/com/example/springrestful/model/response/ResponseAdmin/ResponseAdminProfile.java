package com.example.springrestful.model.response.ResponseAdmin;/*
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

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseAdminProfile {
    private int id;
    private String title;
    private String description;
    private String link;
    private Date dateCreated;
    private Date dateUpdated;
    private int admin_id;
}
