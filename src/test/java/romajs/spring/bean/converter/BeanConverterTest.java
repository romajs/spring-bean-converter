package romajs.spring.bean.converter;

import romajs.spring.bean.converter.exception.BeanConverterNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BeanConverterTest {

    class FirstClass {
        private Long foo;
    }

    class SecondClass {
        private String bar;
    }

    @Autowired
    BeanConverter beanConverter;

    @romajs.spring.annotation.BeanConverter
    public SecondClass secondClass(FirstClass foo) {
        SecondClass secondClass = new SecondClass();
        secondClass.bar = foo.foo.toString();
        return secondClass;
    }

    @Test
    public void shouldConvertFromFirstClassToSecondClass() {
        final FirstClass firstClass = new FirstClass();
        firstClass.foo = 123L;

        final SecondClass secondClass = beanConverter.convert(firstClass, SecondClass.class);
        assertNotNull(secondClass);
        assertEquals(secondClass.bar, "123");
    }

    @Test(expected = BeanConverterNotFoundException.class)
    public void shouldNotConvertFromSecondClassToFirstClass() {
        final SecondClass secondClass = new SecondClass();
        secondClass.bar = "123";

        beanConverter.convert(secondClass, FirstClass.class);
    }
}
