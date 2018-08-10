package sample;

import java.io.InputStream;
import java.net.URL;
import java.io.FileWriter;

import javax.json.JsonReader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonArray;

public class JsonReadWriteTest {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 1. get a json file from google and process it..
		URL url = null;
		try {
			// you can check the url below in a browser, see the format of json
			url = new URL("https://maps.googleapis.com/maps/api/geocode/json?address=York+University+Toronto+Canada");
		} catch (Exception e) {
			e.printStackTrace();
		}
		InputStream is = null;
		try {
			// open a stream
			is = url.openStream();
			// create a reader
			JsonReader rdr = Json.createReader(is);
			// read the object
			JsonObject obj = rdr.readObject();
			// get the result as an array
			/*********** JSON specific **************/
			JsonArray results = obj.getJsonArray("results");
			for (JsonObject result : results.getValuesAs(JsonObject.class)) {
				// get some data from the structure
				System.out.println("full Address: " + result.getString("formatted_address"));
				System.out.println("geometry type: " + result.getJsonObject("geometry").getString("location_type"));
				System.out.println(
						"lng: " + result.getJsonObject("geometry").getJsonObject("location").getJsonNumber("lng"));
				System.out.println(
						"lat: " + result.getJsonObject("geometry").getJsonObject("location").getJsonNumber("lat"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 2. create a simple json object and serialize it..
		/*
		 * { "firstName": "John", "lastName": "Smith", "address" : { "streetAddress":
		 * "21 2nd Street", }, "phoneNumber": [ { "type": "home", "number":
		 * "212 555-1234" }, { "type": "fax", "number": "646 555-4567" } ] }
		 */
		JsonObject value = Json.createObjectBuilder().add("firstName", "John").add("lastName", "Smith")
				.add("address", Json.createObjectBuilder().add("streetAddress", "21 2nd Street"))
				.add("phoneNumber",
						Json.createArrayBuilder()
								.add(Json.createObjectBuilder().add("type", "home").add("number", "212 555-1234"))
								.add(Json.createObjectBuilder().add("type", "fax").add("number", "646 555-4567")))
				.build();
		// serialize it in a file..

		try (FileWriter file = new FileWriter("JSON.txt")) {
			file.write(value.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		// serialize it to console..
		System.out.println(value.toString());
	}
}
