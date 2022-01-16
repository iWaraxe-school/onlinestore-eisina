import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class ConfigHelper {

    public HashMap<String,String> getSortConfig() throws ParserConfigurationException, SAXException, IOException {
    SAXParserFactory factory = SAXParserFactory.newInstance();
    SAXParser parser = factory.newSAXParser();
    ConfigParser saxp = new ConfigParser();
    parser.parse(new File("/Users/ekaterinaisina/code/onlinestore-eisina/parent/consoleApp/src/main/resources/config.xml"), saxp);
    return saxp.getSortConfig();
    }
}
