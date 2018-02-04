package romajs.spring;

import romajs.spring.exception.BeanConverterNotFoundException;
import romajs.spring.model.BeanConverterKey;
import romajs.spring.model.BeanConverterValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class BeanConverter {

    @Autowired
    private BeanConverterContext beanConverterContext;

    public <F, T> T convert(F fromObject, Class<T> toClass) {
        final BeanConverterKey beanConverterKey = new BeanConverterKey(fromObject.getClass(), toClass);
        final BeanConverterValue beanConverterValue = beanConverterContext.get(beanConverterKey);
        if(beanConverterValue == null) {
            throw new BeanConverterNotFoundException(beanConverterKey);
        }
        try {
            return (T) beanConverterValue.getMethod().invoke(beanConverterValue.getObject(), fromObject);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    public <F, T> List<T> convertList(Collection<F> fromObjects, Class<T> toClass) {
        List<T> resultCollection = new ArrayList<>(fromObjects.size());
        for(F fromObject: fromObjects) {
            final T result = convert(fromObject, toClass);
            resultCollection.add(result);
        }
        return resultCollection;
    }
}
