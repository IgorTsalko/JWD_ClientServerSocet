package by.epamtc.tsalko.client.model;

import by.epamtc.tsalko.bean.impl.Text;
import by.epamtc.tsalko.client.model.exception.DAOException;

public interface TextDAO {

    Text getText() throws DAOException;
}
