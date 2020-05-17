package properties.mapper.property;

import lombok.Data;
import properties.mapper.annotation.Property;

@Data
@Property(source="property/person-prefixed.yaml", prefix = "person")
public class PersonPrefixed {
    private String name;
    private Long age;
}
