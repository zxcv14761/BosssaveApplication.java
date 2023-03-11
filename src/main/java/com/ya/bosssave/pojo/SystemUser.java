package com.ya.bosssave.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
public class SystemUser {

    private Integer id;
    private String account;
    private String password;
}
