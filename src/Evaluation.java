
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import dao.PeopleStore;
import dao.PeopleStoreXML;
import model.HealthProfile;
import model.Person;
import utilities.MarshallingUtilities;

public class Evaluation {
	
	private PeopleStoreXML peopleStoreXML=null;
	private final static String DEFAULT_FILE_NAME="people.xml";
	private final static String NEW_XML_DATABASE="new_people.xml";
	
	public Evaluation(){
		
		try {
			peopleStoreXML=new PeopleStoreXML();
		} catch (ParserConfigurationException | SAXException | IOException e) {
			System.err.println("Unable to read build.xml");
			e.printStackTrace();
			
			/*
			 * If the file build.xml can not be parsed the execution end
			 */
			System.exit(1);
		}
		
		//EvaluationXPATH();
		EvaluationMarshalling();
		
		/*try {
			//System.out.println("weight: "+peopleStoreXML.getWeight(new Long(5))+" height: "+peopleStoreXML.getHeight(new Long(5)));
			//System.out.println(peopleStoreXML.list());
			System.out.println(peopleStoreXML.list(peopleStoreXML.getWeight(90, ">")));
			//System.out.println(peopleStoreXML.list(peopleStoreXML.getHeight(1.43, "=")));
			System.out.println(peopleStoreXML.getHealtProfile(new Long(5)));
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}*/
		
	}
	
	private void EvaluationXPATH(){
		if(peopleStoreXML==null)
			return;
		
		try {
			System.out.println("=========List all people in the DB==============");
			System.out.println(peopleStoreXML.list());
			System.out.println("=========Visualize the healt profile of the people with id=5 ==============");
			System.out.println(peopleStoreXML.getHealtProfile(new Long(5)));
			System.out.println("=========Visulize person with weight > 90 ==============");
			System.out.println(peopleStoreXML.getWeight(90, ">"));
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
	}
	
	private void EvaluationMarshalling(){
		try {
			
			
			PeopleStore store=new PeopleStore();
			
			////////adding first new person///////////////////////////////
			HealthProfile hp = new HealthProfile(RandomDate.random(50,90), (double)(RandomDate.random(100,200))/100.0);
			Person person=new Person(new Long(1),"Mario","Mario",RandomDate.randomDOB(),hp);
			
			store.getData().add(person);
			
			////////adding second new person///////////////////////////////
			hp = new HealthProfile(RandomDate.random(50,90), (double)(RandomDate.random(100,200))/100.0);
			person=new Person(new Long(2),"Luigi","Mario",RandomDate.randomDOB(),hp);
			
			store.getData().add(person);
			
			////////adding third new person///////////////////////////////
			hp = new HealthProfile(RandomDate.random(50,90), (double)(RandomDate.random(100,200))/100.0);
			person=new Person(new Long(3),"Pierluigi","Zafferano",RandomDate.randomDOB(),hp);
			
			store.getData().add(person);
			
			////////umarshalling of people xml/////////////////////////////
			/*the new people.xml*/
			MarshallingUtilities.marshalling(store, NEW_XML_DATABASE);
			
			
			////////unmarshalling the new xml database///////////////////////////
			PeopleStore xmlDB=MarshallingUtilities.unMarshalling(NEW_XML_DATABASE);
			System.out.println(store.getData().size());
			
		} catch (FileNotFoundException e) {
			System.err.println("File not found");
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		new Evaluation();
	}
}
