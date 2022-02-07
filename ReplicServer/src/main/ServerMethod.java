package main;

import java.io.File;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

public class ServerMethod {
	public static String RestaurarObjetos() throws ParserConfigurationException {

        try {
        	
    		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File("persistencia.xml"));
			return toString(document);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "ERROR";

		} 
	
	}
	
	public static boolean ReplicarObjetos(String stringDocument) {

        try {
        	
        	DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(stringDocument);
            DOMSource source = new DOMSource(document);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            StreamResult result = new StreamResult("persistencia.xml");
            transformer.transform(source, result);
            return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;

		} 

	}
		
public static String toString(Document doc) {
    try {
    	
        StringWriter sw = new StringWriter();
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.transform(new DOMSource(doc), new StreamResult(sw));
        return sw.toString();
        
    } catch (Exception ex) {
    	
        throw new RuntimeException("Error converting to String", ex);
        
    }
}

}


