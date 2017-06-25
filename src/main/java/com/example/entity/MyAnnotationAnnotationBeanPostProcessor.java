package com.example.entity;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.thymeleaf.spring4.expression.Fields;

import java.lang.reflect.Field;
import java.util.Random;

/**
 * Created by tzurc on 6/25/2017.
 */

public class MyAnnotationAnnotationBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object object, String s) throws BeansException {
        System.out.println(object.getClass());
        Field[] fields = object.getClass().getFields();
        for (Field field : fields) {
            MyAnnotation myAnnotation = field.getAnnotation(MyAnnotation.class);
            if(myAnnotation != null){
                int min = myAnnotation.min();
                System.out.println(min);
                int max = myAnnotation.max();
                Random random = new Random();
                int i = min + random.nextInt(max - min);
                field.setAccessible(true);
                ReflectionUtils.setField(field, object, i);
            }
        }
        return object;
    }

    @Override
    public Object postProcessAfterInitialization(Object object, String s) throws BeansException {
        return object;
    }
}
