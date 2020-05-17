package properties.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import properties.mapper.property.PersonPrefixed;
import properties.mapper.property.PersonTopLevel;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class BinderTest {

    @Test
    @DisplayName("gets a top level object")
    void findsTopLevelObject() {

        PersonTopLevel john = new PersonTopLevel();
        john.setName("John");
        john.setAge(25L);

        Optional<PersonTopLevel> expected = Optional.of(john);
        Optional<PersonTopLevel> actual = Binder.get(PersonTopLevel.class);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("gets a prefixed")
    void findsPrefixedObject() {

        PersonPrefixed john = new PersonPrefixed();
        john.setName("John");
        john.setAge(25L);

        Optional<PersonPrefixed> expected = Optional.of(john);
        Optional<PersonPrefixed> actual = Binder.get(PersonPrefixed.class);
        assertThat(actual).isEqualTo(expected);
    }

}
