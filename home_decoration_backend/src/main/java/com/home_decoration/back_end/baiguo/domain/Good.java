package com.home_decoration.back_end.baiguo.domain;

import lombok.Data;

@Data
public class Good {
    private int id;
    private String picture;
    private String title;
    private String Content;
    private String introduction;
    private float price;
    private Designer designer;
}
