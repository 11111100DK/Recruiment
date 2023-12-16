package com.example.springrestful.model.request.RequestAccount;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestAccountChangePass {
    private int id;
    private String oldPass;
    private String newPass;
}
