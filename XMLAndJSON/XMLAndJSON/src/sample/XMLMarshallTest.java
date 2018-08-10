package sample;

import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

public class XMLMarshallTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//1.create a data object model, that is a "team" object with two "members
		//check the EmployeeBean, PeopleBean and TeamBean to understand the structure of the model
		//can you draw an UML diagram of the model?

		EmployeeBean eb = new EmployeeBean("123", "John", 20, 1000, "regular");// create an object
		EmployeeBean eb2 = new EmployeeBean("124", "Jane", 20, 1000, "contractor");// create an object
		TeamBean tb=new TeamBean();
        tb.getMembers().add(eb);
        tb.getMembers().add(eb2);
        
		try {
			// 2.create a context
			JAXBContext jaxbContext = JAXBContext.newInstance(TeamBean.class); // instantiate a context
			// note the argument
			// 3. Create a marshaller
			Marshaller marshaller = jaxbContext.createMarshaller(); // create a marshaller
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            //4. instantiate the XML Schema..you might need to change the path, in this case the 
			//schema is in the project directory
			SchemaFactory sf = 	
					SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = sf.newSchema(new File("employee.xsd"));
			marshaller.setSchema(schema);
			//standard io
			StringWriter sw = new StringWriter(); // standard IO
			sw.write("\n");
			//5. marshall
			marshaller.marshal(tb, new StreamResult(sw)); // what and where to marshall

			System.out.println(sw.toString()); // for debugging
			//6. write the file..
			String filename = "teamGenerated.xml";
			FileWriter fw = new FileWriter(filename);
			fw.write(sw.toString());
			fw.close();
		} catch (Exception e) {
			//To do: refactor this for a  better exception handling
			e.printStackTrace();
		}

	}

}
