package by.epamtc.tsalko.server.service.parser;

import by.epamtc.tsalko.server.service.exception.ServiceException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

    private static PropertyReader instance = new PropertyReader();
    private Properties properties = new Properties();

    private PropertyReader() {}

    public static PropertyReader getInstance() {
        return instance;
    }

    public String getProperty(String propertyName) throws ServiceException {
        String property;
        try {
            FileInputStream inputStream = new FileInputStream("resources/regexp.properties");
            properties.load(inputStream);
            property = properties.getProperty(propertyName);
        } catch (IOException e) {
            throw new ServiceException("Ошибка regexp.properties");
        }

        return property;
    }
}
