package com.home_decoration.back_end.baiguo.domain;

import lombok.Data;

@Data
public class YingPin {
    private int id;
    private String name;
    private int sex;
    private int birthYear;
    private int education;
    private String school;
    private String phone;
    private String skill;
    private ZhaoPin zhaoPin;
}
