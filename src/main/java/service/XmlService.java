package service;

import dao.DaoXML;
import dao.DaoXMLImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

public class XmlService {

    DaoXML daoXML = new DaoXMLImpl();
    public void buildXML(String pathXml, Map<String, String> capitals) {

        try {
            Document doc = getDocument();

            Element root = doc.createElement("ciudades");
            doc.appendChild(root);

            for (Map.Entry<String, String> entry: capitals.entrySet()) {
                Element element = doc.createElement("ciudad");
                element.setAttribute("pais", entry.getKey());
                element.appendChild(doc.createTextNode(entry.getValue()));
                root.appendChild(element);
            }

            DOMSource domSource = new DOMSource(doc);

            Transformer transformer = getTransformer();
            StringWriter sw = new StringWriter();
            StreamResult sr = new StreamResult(sw);
            transformer.transform(domSource, sr);
            System.out.println(sw);

            daoXML.createXML(new File(pathXml), sw.toString());

        } catch (ParserConfigurationException | TransformerException | IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static Transformer getTransformer() throws TransformerConfigurationException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();

        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        return transformer;
    }

    private static Document getDocument() throws ParserConfigurationException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.newDocument();
        doc.setXmlVersion("1.0");
        return doc;
    }
}