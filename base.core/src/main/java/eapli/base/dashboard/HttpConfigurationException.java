package eapli.base.dashboard;

import java.io.FileNotFoundException;

public class HttpConfigurationException extends RuntimeException {

    public HttpConfigurationException(FileNotFoundException e) {
    }

    public HttpConfigurationException(String message) {
        super(message);
    }
}
