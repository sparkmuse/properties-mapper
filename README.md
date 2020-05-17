# Properties Mapper

Small project to easily map properties in yaml to Java POJOs


# Install

Add the bellow dependency to your maven project

```xml
<dependency>
    <groupId>com.github.sparkmuse</groupId>
    <artifactId>properties-mapper</artifactId>
    <version>${version}</version>
</dependency>
```

or to gradle

```groovy
compile 'com.github.sparkmuse:properties-mapper:${version}'
```

The version can be obtained by going to the link https://mvnrepository.com/artifact/com.github.sparkmuse/property-mapper

# Usage

Create the java POJO to be bound to the yaml properties and annotate it

```java
@Property(source="person.yaml", prefix = "person")
public class Person {
    private String name;
    private Long age;

    // No arguments constructor
    
    // Standard getters and setters
}
```

And on the yaml file person.yaml

```yaml
person:
  name: John
  age: 25
```

An instance of the property can be accessed as follows
```java
public class Main {
    public static void main(String[] args) {
        
        // Get an instance of person with all values filled in
        Optional<Person> person = Binder.get(Person.class);
        
        if (person.isPresent()) {
            System.out.println("I have created a person!");
        }
    }
}
```

## Description

- **Source**: This is the source of the file containing the values for the mapping.
- **Prefix**: The prefix where the values can be accessed. It can be empty if the elements are in the top level yaml document.
