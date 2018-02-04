# spring-bean-converter

Created decoupled and reusable bean converters, by simply annotating methods with `@BeanConverter`

## Configuration

```xml
<repositories>
    <repository>
        <id>myMavenRepo</id>
        <url>https://mymavenrepo.com/repo/H7y9TxxC8tlHnK4oS5RL/</url>
    </repository>
</repositories>
```

```xml
<dependencies>
    <dependency>
        <groupId>com.romajs.spring</groupId>
        <artifactId>spring-bean-converter</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>
</dependencies>
```

At your `@SpringBootApplication`, add the following:

```java
@ComponentScan(basePackages = {"com.mypackage", "com.romajs.spring"})
```

## Usage

```java
@Component
public class SomeComponent {

    @Autowired
    com.romajs.spring.bean.converter.BeanConverter beanConverter;

    @com.romajs.spring.annotation.BeanConverter
    public Response convertToResponse(SomeEntity someEntity) {
        Response response = new Response();
        response.setValue(someEntity.getValue());
        return response;
    }

    public Response getResponse(SomeEntity someEntity) {
        Reponse response = beanConverter.convert(someEntity, Response.class);
        return response;
    }

    public List<Response> getResponseList(List<SomeEntity> someEntityList) {
        List<Reponse> responseList = beanConverter.convertList(someEntityList, Response.class);
        return responseList;
    }
    
}
```

