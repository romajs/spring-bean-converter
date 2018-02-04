package romajs.spring.exception;

import romajs.spring.model.BeanConverterKey;

public class BeanConverterNotFoundException extends RuntimeException {
    public BeanConverterNotFoundException(BeanConverterKey beanConverterKey) {
        super(beanConverterKey != null ? beanConverterKey.toString() : null);
    }
}
