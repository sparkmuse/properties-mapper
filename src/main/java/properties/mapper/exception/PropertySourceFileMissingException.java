package properties.mapper.exception;

public class PropertySourceFileMissingException extends RuntimeException{
    public PropertySourceFileMissingException(String message) {
        super(message);
    }
}
