package utilities;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;

import generated.People;

public class JSONUtilities {
	
	/**
	 * Marshall the content of a people object on a file
	 * @param fileName the name of the file on which the people object will be printed 
	 * @param the people object that will be printed
	 * @throws IOException
	 */
	public static void MarshallToJSON(String fileName, People people) throws IOException{
		// Jackson Object Mapper 
		ObjectMapper mapper = new ObjectMapper();
		
		// Adding the Jackson Module to process JAXB annotations
        JaxbAnnotationModule module = new JaxbAnnotationModule();
        
		// configure as necessary
		mapper.registerModule(module);
		mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);

        String result = mapper.writeValueAsString(people);
        System.out.println(result);
        mapper.writeValue(new File("people.json"), people);
	}
	
	/**
	 * Marshall the content of a people object on a PrintStream
	 * @param stream the PrintStream on which the People object will be printed
	 * @param people the people object that will be printed
	 * @throws IOException
	 */
	public static void MarshallToJSON(PrintStream stream, People people) throws IOException{
		// Jackson Object Mapper 
		ObjectMapper mapper = new ObjectMapper();
		
		// Adding the Jackson Module to process JAXB annotations
        JaxbAnnotationModule module = new JaxbAnnotationModule();
        
		// configure as necessary
		mapper.registerModule(module);
		mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);

        String result = mapper.writeValueAsString(people);
        System.out.println(result);
        mapper.writeValue(stream, people);
	}
}
