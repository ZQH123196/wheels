package org.example.di.factory;

import lombok.Data;

import java.lang.reflect.Constructor;

@Data
public class BeanDefinition {
    private Class<?> clazz;
    private String beanName;
    private Scope scope;
    private boolean lazy = false;

    public BeanDefinition() {
    }

    public BeanDefinition(Class<?> clazz) {
        this.clazz = clazz;
    }



    public static enum Scope {SINGLETON, PROTOTYPE}

}
