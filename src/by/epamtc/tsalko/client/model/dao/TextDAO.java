package by.epamtc.tsalko.client.model.dao;

import by.epamtc.tsalko.bean.impl.Text;
import by.epamtc.tsalko.client.model.dao.exception.DAOException;

public interface TextDAO {

    Text getText() throws DAOException;
}
