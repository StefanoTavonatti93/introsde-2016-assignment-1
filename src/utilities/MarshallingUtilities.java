package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import dao.PeopleStore;

public class MarshallingUtilities {
	
	public static PeopleStore unMarshalling(String fileName) throws JAXBException, FileNotFoundException{
		JAXBContext jc = JAXBContext.newInstance(PeopleStore.class);
        
        Unmarshaller um = jc.createUnmarshaller();
        PeopleStore people = (PeopleStore) um.unmarshal(new FileReader(fileName));
        return people;
	}
	
	public static void marshalling(PeopleStore people,String fileName) throws JAXBException{
		JAXBContext jc = JAXBContext.newInstance(PeopleStore.class);
        Marshaller m = jc.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        m.marshal(people,new File(fileName)); // marshalling into a file
	}

}
