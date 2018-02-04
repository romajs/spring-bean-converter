package romajs.spring.bean.converter;

import romajs.spring.bean.converter.model.BeanConverterKey;
import romajs.spring.bean.converter.model.BeanConverterValue;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Component
public class BeanConverterContext {

    private static final Map<BeanConverterKey, BeanConverterValue> map = new HashMap<>();

    public boolean add(Class fromClass, Class toClass, Method method, Object bean) {
        final BeanConverterKey beanConverterKey = new BeanConverterKey(fromClass, toClass);
        final BeanConverterValue beanConverterValue = new BeanConverterValue(method, bean);
        return add(beanConverterKey, beanConverterValue);
    }

    public boolean add(BeanConverterKey beanConverterKey, BeanConverterValue beanConverterValue) {
        return map.put(beanConverterKey, beanConverterValue) != null;
    }

    public BeanConverterValue get(Class fromClass, Class toClass) {
        final BeanConverterKey beanConverterKey = new BeanConverterKey(fromClass, toClass);
        return get(beanConverterKey);
    }

    public BeanConverterValue get(BeanConverterKey beanConverterKey) {
        return map.get(beanConverterKey);
    }
}
