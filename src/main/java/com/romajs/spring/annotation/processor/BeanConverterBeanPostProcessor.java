package com.romajs.spring.annotation.processor;

import com.romajs.spring.annotation.BeanConverter;
import com.romajs.spring.bean.converter.BeanConverterContext;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class BeanConverterBeanPostProcessor implements BeanPostProcessor {

    @Autowired
    private BeanConverterContext beanConverterContext;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        final Method[] declaredMethods = bean.getClass().getDeclaredMethods();
        for (Method method : declaredMethods) {
            if(method.isAnnotationPresent(BeanConverter.class)) {
                final Class fromClass = method.getParameterTypes()[0];
                final Class toClass = method.getReturnType();
                beanConverterContext.add(fromClass, toClass, method, bean);
            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
