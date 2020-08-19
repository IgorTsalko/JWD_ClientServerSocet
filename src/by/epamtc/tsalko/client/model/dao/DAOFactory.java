package by.epamtc.tsalko.client.model.dao;

import by.epamtc.tsalko.client.model.dao.exception.DAOException;
import by.epamtc.tsalko.client.model.dao.impl.TextDAOImpl;

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
