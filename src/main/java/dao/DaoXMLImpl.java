package dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DaoXMLImpl implements DaoXML {
    @Override
    public void createXML(File file, String xml) throws IOException {
        if (!file.exists()){
            file.createNewFile();
        }
        try (FileWriter fileWriter = new FileWriter(file)){
            fileWriter.write(xml);
        }
    }
}