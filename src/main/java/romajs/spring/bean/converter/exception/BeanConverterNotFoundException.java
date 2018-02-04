package romajs.spring.bean.converter.exception;

import romajs.spring.bean.converter.model.BeanConverterKey;

public class BeanConverterNotFoundException extends RuntimeException {
    public BeanConverterNotFoundException(BeanConverterKey beanConverterKey) {
        super(beanConverterKey != null ? beanConverterKey.toString() : null);
    }
}
