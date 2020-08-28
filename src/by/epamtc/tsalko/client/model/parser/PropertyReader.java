package by.epamtc.tsalko.client.model.parser;

import by.epamtc.tsalko.client.model.exception.DAOException;

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

    public String getProperty(String propertyName) throws DAOException {
        String property;
        try {
            FileInputStream inputStream = new FileInputStream("resources/regexp.properties");
            properties.load(inputStream);
            property = properties.getProperty(propertyName);
        } catch (IOException e) {
            throw new DAOException("Ошибка regexp.properties", e);
        }

        return property;
    }
}
