import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ConfigParser extends DefaultHandler {

        private String name,rate,price;
        private String thisElement = "";
        private Map<String,String> orderList= new LinkedHashMap<>();

        @Override
        public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
            thisElement = qName;
        }

        @Override
        public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
            thisElement = "";
        }

        @Override
        public void characters(char[] ch, int start, int length) {
            String information = new String(ch, start, length);

            if (thisElement.equals("name")) {
                name = information;
                orderList.put(thisElement,name);
            }
            if (thisElement.equals("rate")) {
                rate = information;
                orderList.put(thisElement,rate);
            }
            if (thisElement.equals("price")) {
                price = information;
                orderList.put(thisElement,price);
            }
        }

        public Map<String,String> getSortConfig() {
            return orderList;
        }
    }



