package properties.mapper.property;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import properties.mapper.annotation.Property;

import java.util.List;
import java.util.Map;

@Data
@Property(source= "property/person.yaml", prefix = "person")
public class Person {

    private String name;
    private int age;
    private Map<String, Address> locations;
    private List<Department> departments;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Address {
        String street;
        int zip;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Department {
        private String name;
        private int id;
        private Type type;
    }

    public enum Type {
        INTERNAL, EXTERNAL
    }
}
