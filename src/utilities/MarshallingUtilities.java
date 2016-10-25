package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import dao.PeopleStore;

public class MarshallingUtilities {
	
	/**
	 * Un-marshall the content of the given file into a PeopleStore instance
	 * @param fileName a String containing the name of the file to be un-marshalled
	 * @return a PeopleStore instance with the content of ad XML DB
	 * @throws JAXBException
	 * @throws FileNotFoundException
	 */
	public static PeopleStore unMarshalling(String fileName) throws JAXBException, FileNotFoundException{
		JAXBContext jc = JAXBContext.newInstance(PeopleStore.class);
        
        Unmarshaller um = jc.createUnmarshaller();
        PeopleStore people = (PeopleStore) um.unmarshal(new FileReader(fileName));
        return people;
	}
	
	/**
	 * Marshall the content of an PeopleStore object to a file
	 * @param people the PeopleStore object containing the data to be marshalled
	 * @param fileName the file where the data will be saved
	 * @throws JAXBException
	 */
	public static void marshalling(PeopleStore people,String fileName) throws JAXBException{
		JAXBContext jc = JAXBContext.newInstance(PeopleStore.class);
        Marshaller m = jc.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        m.marshal(people,new File(fileName)); // marshalling into a file
	}
	
	/**
	 * Marshall the content of an PeopleStore object to a PrintStream (i.e. System.out, for printing in the console)
	 * @param people the PeopleStore object containing the data to be marshalled
	 * @param stream fileName the file where the data will be saved
	 * @throws JAXBException
	 */
	public static void marshalling(PeopleStore people,PrintStream stream) throws JAXBException{
		JAXBContext jc = JAXBContext.newInstance(PeopleStore.class);
        Marshaller m = jc.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        m.marshal(people,stream); // marshalling into a PrintStream
	}

}
