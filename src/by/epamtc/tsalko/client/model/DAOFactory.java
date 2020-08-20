package by.epamtc.tsalko.client.model;

import by.epamtc.tsalko.client.model.exception.DAOException;
import by.epamtc.tsalko.client.model.impl.TextDAOImpl;

public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private static TextDAO textDAO;

    private DAOFactory() {}

    public static DAOFactory getInstance() {
        return instance;
    }

    public TextDAO getTextDAO() throws DAOException {
        if (textDAO == null) {
            textDAO = new TextDAOImpl();
        }
        return textDAO;
    }
}
