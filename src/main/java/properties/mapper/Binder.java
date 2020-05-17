package properties.mapper;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import properties.mapper.annotation.Property;

import java.util.Map;
import java.util.Optional;

/**
 * Main class for the binder. Most of the logic is in here.
 */
public class Binder {

    /**
     * Entry point for the binder
     *
     * @param clazz The class to use
     * @param <TYPE> The type for the class
     * @return Optional of the class with the populated values
     */
    public static <TYPE> Optional<TYPE> get(Class<TYPE> clazz) {

        Property annotation = clazz.getAnnotation(Property.class);

        PropertyReader reader = new PropertyReader();

        return reader
                .get(annotation.source())
                .flatMap(content -> getType(clazz, annotation.prefix(), content));

    }

    /**
     * Method to try read the value
     * @param clazz Class to be casted to
     * @param prefix The prefix to search for
     * @param contents The Contents of the file
     * @param <TYPE> The type of the class
     * @return An optional of the value
     */
    private static <TYPE> Optional<TYPE> getType(Class<TYPE> clazz,
                                                 String prefix,
                                                 String contents) {
        Yaml yamlGeneric = new Yaml();
        Map<String, Object> load = yamlGeneric.load(contents);
        Map<String, Object> finalObjectLoad = getFinalObjectLoad(prefix, load);

        String dumpString = yamlGeneric.dump(finalObjectLoad);

        Yaml yamlForClass = new Yaml(new Constructor(clazz));
        //noinspection unchecked
        return Optional.of((TYPE) yamlForClass.load(dumpString));
    }


    /**
     * Gets the final load object
     *
     * @param prefix The prefix to search for
     * @param load The startting load
     * @return The final most load
     */
    private static Map<String, Object> getFinalObjectLoad(String prefix,
                                                          Map<String, Object> load) {
        Map<String, Object> currentLoad = load;
        String[] strings = prefix.split("\\.");

        int currentIndex = 0;
        while (currentIndex < strings.length) {
            String key = strings[currentIndex];
            if (currentLoad.containsKey(key)) {
                //noinspection unchecked
                currentLoad = (Map<String, Object>) currentLoad.get(key);
            }
            currentIndex++;
        }
        return currentLoad;
    }
}
