package properties.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import properties.mapper.exception.PropertySourceEmptyFileException;
import properties.mapper.exception.PropertySourceFileMissingException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PropertyReaderTest {

    private PropertyReader propertyReader;

    @BeforeEach
    void setUp() {
        propertyReader = new PropertyReader();
    }

    @Test
    @DisplayName("reads from source")
    void readsFromSource() {

        String source = "test.yaml";

        Optional<String> expected = Optional.of("hello");

        Optional<String> actual = propertyReader.get(source);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("throws PropertySourceFileMissingException when file not found")
    void fileNotFoundThrowsException() {

        String source = "not-existing-file.yaml";
        Optional<String> expected = Optional.empty();

        assertThatThrownBy(() -> propertyReader.get(source))
                .isInstanceOf(PropertySourceFileMissingException.class);
    }

    @Test
    @DisplayName("throws PropertySourceEmptyFileException when file not found")
    void emptyFileThrowsException() {

        String source = "test2.yaml";
        Optional<String> expected = Optional.empty();

        assertThatThrownBy(() -> propertyReader.get(source))
                .isInstanceOf(PropertySourceEmptyFileException.class);
    }
}
