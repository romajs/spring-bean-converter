package romajs.spring.bean.converter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;
import romajs.spring.BeanConverterContext;
import romajs.spring.model.BeanConverterKey;
import romajs.spring.model.BeanConverterValue;

import java.lang.reflect.Method;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class BeanConverterContextTest {

    @SpyBean
    BeanConverterContext beanConverterContext;

    class FirstClass {
    }

    class SecondClass {
    }

    class ConvertClass {
        public SecondClass convert(FirstClass firstClass) {
            return null;
        }
    }

    @Test
    public void shouldAdd() throws NoSuchMethodException {
        final ConvertClass convertClass = new ConvertClass();
        final Method method = convertClass.getClass().getDeclaredMethod("convert", FirstClass.class);

        beanConverterContext.add(FirstClass.class, SecondClass.class, method, convertClass);
        verify(beanConverterContext).add(FirstClass.class, SecondClass.class, method, convertClass);
    }

    @Test
    public void shouldAddFromKeyAndValue() throws NoSuchMethodException {
        final ConvertClass convertClass = new ConvertClass();
        final Method method = convertClass.getClass().getDeclaredMethod("convert", FirstClass.class);

        final BeanConverterKey beanConverterKey = new BeanConverterKey(FirstClass.class, SecondClass.class);
        final BeanConverterValue beanConverterValue = new BeanConverterValue(method,convertClass);

        beanConverterContext.add(beanConverterKey, beanConverterValue);
        verify(beanConverterContext).add(beanConverterKey, beanConverterValue);
    }

    @Test
    public void shouldGet() {
        assertNotNull(beanConverterContext.get(FirstClass.class, SecondClass.class));
    }

    @Test
    public void shouldGetFromkey() {
        final BeanConverterKey beanConverterKey = new BeanConverterKey(FirstClass.class, SecondClass.class);
        assertNotNull(beanConverterContext.get(beanConverterKey));
    }
}
