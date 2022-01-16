import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;
import java.util.HashMap;

public class ConfigParser extends DefaultHandler {

        private String name,rate,price;
        private String thisElement = "";
        private HashMap<String,String> orderList= new HashMap<>();

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

        public HashMap<String,String> getSortConfig() {
            return orderList;
        }
    }



