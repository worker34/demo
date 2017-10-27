package com.example.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by tzurc on 6/18/2017.
 */
@Getter
@Setter
public class SearchObject {
    private String title;
    private String author;
    private String publisher;
    private float priceFrom;
    private float priceTo;
}
