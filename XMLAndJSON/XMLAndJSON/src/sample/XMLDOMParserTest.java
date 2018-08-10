package sample;

import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;




public class XMLDOMParserTest {

	public static void main(String[] args) {
		// 1. get an XML file from google and process it..
		// you can check the url below in a browser, see the format of json
		
		String url = "https://maps.googleapis.com/maps/api/geocode/xml?address=York+University+Toronto+Canada&sensor=false";
		try {
		
		//2. you need an XML parser, this is the typical recipe 
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder db=factory.newDocumentBuilder();
        
        //3. Now you can parse the url stream
        Document doc =db.parse(new URL(url).openStream());
        doc.getDocumentElement().normalize();
			
	   	//4. You can then access/change any element of the DOM tree
		NodeList nl=doc.getElementsByTagName("lng");
				System.out.println(
						"lng: " + nl.item(0).getTextContent());			
		
		nl=doc.getElementsByTagName("lat");
				System.out.println(
						"lat: " + nl.item(0).getTextContent());			
		
		} catch (Exception e) {
			//todo: better handling of exceptions..
			e.printStackTrace();
		}
	}

}
