package by.epamtc.tsalko.client.model.dao.impl;

import by.epamtc.tsalko.bean.impl.Text;
import by.epamtc.tsalko.client.model.dao.ReaderFromFile;
import by.epamtc.tsalko.client.model.dao.TextDAO;
import by.epamtc.tsalko.client.model.dao.exception.DAOException;
import by.epamtc.tsalko.client.model.dao.parser.ComponentParser;
import by.epamtc.tsalko.client.model.dao.parser.ParserFactory;

public class TextDAOImpl implements TextDAO {

    private final ComponentParser componentParser;
    private final ReaderFromFile reader;

    public TextDAOImpl() throws DAOException {
        componentParser = ParserFactory.getComponentParser();
        reader = new ReaderFromFile();
    }

    @Override
    public Text getText() throws DAOException {
        return componentParser.createText(reader.readAllText());
    }
}
