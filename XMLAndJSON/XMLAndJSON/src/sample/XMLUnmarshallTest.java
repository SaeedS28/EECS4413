package sample;

import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamResult;

public class XMLUnmarshallTest {

	public static void main(String[] args) {

		// 1. create a simple Object model

		
		try {
			// 1.create a context
			JAXBContext jaxbContext = JAXBContext.newInstance(PersonBean.class); // instantiate a context
			// note the argument
			// 2. Create a unmarshaller
			Unmarshaller umarshaller = jaxbContext.createUnmarshaller(); // create a marshaller
			//umarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			//4. unmarshall
			PersonBean sb=(PersonBean) umarshaller.unmarshal(new File("person.xml")); // what and where to marshall

			//check two attributes..
			System.out.println(sb.getName()+","+sb.getSin());
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
