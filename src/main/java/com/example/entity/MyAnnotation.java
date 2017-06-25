package com.example.entity;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by tzurc on 6/25/2017.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
    int min();
    int max();
}
