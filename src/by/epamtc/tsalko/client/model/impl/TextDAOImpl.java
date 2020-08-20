package by.epamtc.tsalko.client.model.impl;

import by.epamtc.tsalko.bean.impl.Text;
import by.epamtc.tsalko.client.model.ReaderFromFile;
import by.epamtc.tsalko.client.model.TextDAO;
import by.epamtc.tsalko.client.model.exception.DAOException;
import by.epamtc.tsalko.client.model.parser.ComponentParser;
import by.epamtc.tsalko.client.model.parser.ParserFactory;

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
