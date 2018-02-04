package romajs.spring.model;

import java.lang.reflect.Method;

public class BeanConverterValue {

    private final Method method;

    private final Object object;

    public BeanConverterValue(final Method method, final Object object) {
        this.method = method;
        this.object = object;
    }

    public Method getMethod() {
        return method;
    }

    public Object getObject() {
        return object;
    }

}
