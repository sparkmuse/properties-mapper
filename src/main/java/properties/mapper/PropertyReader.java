package properties.mapper;

import properties.mapper.exception.PropertySourceEmptyFileException;
import properties.mapper.exception.PropertySourceFileMissingException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class PropertyReader {

    public Optional<String> get(String source) {

        ClassLoader classLoader = getClass().getClassLoader();
        try(InputStream stream = classLoader.getResourceAsStream(source)) {

            if (stream == null) {
                throw new PropertySourceFileMissingException("File source is missing");
            }

            String fileContent = new String(stream.readAllBytes(), StandardCharsets.UTF_8);
            if ("".equals(fileContent)) {
                throw new PropertySourceEmptyFileException("File source is empty");
            }

            return Optional.of(fileContent);
        } catch (IOException exception) {
            return Optional.empty();
        }
    }
}
