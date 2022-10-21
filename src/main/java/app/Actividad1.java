package app;

import service.XmlService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

    public class Actividad1 {
        private static final String PATH_XML = "src/main/resources/actividad1.xml";
        public static void main(String[] args) {
            Map<String, String> capitals = new HashMap<>();
            capitals.put("Italia", "Roma");
            capitals.put("Francia", "París");
            capitals.put("Alemania", "Berlín");
            capitals.put("España", "Madrid");
            XmlService xmlService = new XmlService();
            xmlService.buildXML(PATH_XML, capitals);
        }
    }