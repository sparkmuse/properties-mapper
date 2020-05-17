package properties.mapper.property;

import lombok.Data;
import properties.mapper.annotation.Property;

@Data
@Property(source="property/person-top-level.yaml")
public class PersonTopLevel {
    private String name;
    private Long age;
}
