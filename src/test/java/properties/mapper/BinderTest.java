package properties.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import properties.mapper.property.Person;
import properties.mapper.property.Person.Address;
import properties.mapper.property.Person.Department;
import properties.mapper.property.PersonTopLevel;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class BinderTest {

    @Test
    @DisplayName("gets a top level object")
    void findsTopLevelObject() {

        PersonTopLevel john = new PersonTopLevel();
        john.setName("John");
        john.setAge(25);

        Optional<PersonTopLevel> expected = Optional.of(john);
        Optional<PersonTopLevel> actual = Binder.get(PersonTopLevel.class);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("gets a prefixed")
    void findsPrefixedObject() {

        Person john = new Person();
        john.setName("John");
        john.setAge(25);

        Map<String, Address> locations = Map.of(
                "work", new Address("Chestnut Lane 23", 10000),
                "home", new Address("Last Street 100", 20000)
        );

        List<Department> departments = List.of(
                new Department("HR", 1, Person.Type.INTERNAL),
                new Department("Admin", 2, Person.Type.EXTERNAL)
        );

        john.setLocations(locations);
        john.setDepartments(departments);

        Optional<Person> expected = Optional.of(john);
        Optional<Person> actual = Binder.get(Person.class);
        assertThat(actual).isEqualTo(expected);
    }
}
