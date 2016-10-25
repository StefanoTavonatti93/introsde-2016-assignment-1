
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import dao.PeopleStoreXML;
import generated.People;
import utilities.JSONUtilities;
import utilities.MarshallingUtilities;

public class Evaluation {
	
	private PeopleStoreXML peopleStoreXML=null;
	private final static String DEFAULT_FILE_NAME="people.xml";
	private final static String NEW_XML_DATABASE="new_people.xml";
	private final static String DEFAULT_JSON_FILE_NAME="people.json";
	
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
		
		EvaluationXPATH();
		EvaluationMarshalling();
		
		////////unmarshalling the new xml database///////////////////////////
		/*People xmlDB;
			try {
				xmlDB = MarshallingUtilities.unMarshalling(DEFAULT_FILE_NAME);
				System.out.println("\n\n===============Printing the unmarshalled content of the new XML database==============\n");
				List<People.Person> people=xmlDB.getPerson();
				Iterator<People.Person> it=people.iterator();
				
				while(it.hasNext()){
					People.Person p=it.next();
					System.out.println(p.toString());
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
			
		
		/*try {
			System.out.println(peopleStoreXML.list());
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
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
			
			
			GregorianCalendar c1 = new GregorianCalendar();
			c1.setTime(new Date(System.currentTimeMillis()));
			XMLGregorianCalendar now = DatatypeFactory.newInstance().newXMLGregorianCalendar(c1);
			
			
			
			People store=new People();
			
			////////adding first new person///////////////////////////////
			GregorianCalendar c = new GregorianCalendar();
			c.set(RandomDate.random(1950, 2000), RandomDate.random(1, 12), RandomDate.random(1, 28));
			XMLGregorianCalendar date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
			
			People.Person.Healthprofile hp=new People.Person.Healthprofile();
			hp.setHeight(RandomDate.random(50,90));
			hp.setHeight((float)((RandomDate.random(100,200))/100.0));
			hp.setBmi((float)(hp.getHeight()/Math.pow(hp.getHeight(), 2)));
			hp.setLastUpdate(now);
			People.Person person=new People.Person();
			person.setId(new Long(1));
			person.setBirthdate(date2);
			person.setFirstname("Mario");
			person.setLastname("Mario");
			person.setHealthprofile(hp);
			
			
			
			store.getPerson().add(person);
			
			////////adding second new person///////////////////////////////
			c = new GregorianCalendar();
			c.set(RandomDate.random(1950, 2000), RandomDate.random(1, 12), RandomDate.random(1, 28));
			date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
			
			hp=new People.Person.Healthprofile();
			hp.setHeight(RandomDate.random(50,90));
			hp.setHeight((float)((RandomDate.random(100,200))/100.0));
			hp.setBmi((float)(hp.getHeight()/Math.pow(hp.getHeight(), 2)));
			hp.setLastUpdate(now);
			person=new People.Person();
			person.setId(new Long(2));
			person.setBirthdate(date2);
			person.setFirstname("Luigi");
			person.setLastname("Mario");
			person.setHealthprofile(hp);
			
			store.getPerson().add(person);
			
			////////adding third new person///////////////////////////////

			c = new GregorianCalendar();
			c.set(RandomDate.random(1950, 2000), RandomDate.random(1, 12), RandomDate.random(1, 28));
			date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
			
			hp=new People.Person.Healthprofile();
			hp.setHeight(RandomDate.random(50,90));
			hp.setHeight((float)((RandomDate.random(100,200))/100.0));
			hp.setBmi((float)(hp.getHeight()/Math.pow(hp.getHeight(), 2)));
			hp.setLastUpdate(now);
			person=new People.Person();
			person.setId(new Long(3));
			person.setBirthdate(date2);
			person.setFirstname("Pierluigi");
			person.setLastname("Zafferano");
			person.setHealthprofile(hp);
			
			store.getPerson().add(person);
			
			
			////////marshalling of people xml/////////////////////////////
			/*marshalling in the new people.xml file*/
			MarshallingUtilities.marshalling(store, NEW_XML_DATABASE);
			
			/*marshalling in the console*/
			System.out.println("\n\n===============Printing the marshalled content of the new XML database=============\n");
			MarshallingUtilities.marshalling(store, System.out);
			
			
			
			////////unmarshalling the new xml database///////////////////////////
			People xmlDB=MarshallingUtilities.unMarshalling(NEW_XML_DATABASE);
			
			System.out.println("\n\n===============Printing the unmarshalled content of the new XML database==============\n");
			List<People.Person> people=store.getPerson();
			Iterator<People.Person> it=people.iterator();
			
			while(it.hasNext()){
				People.Person p=it.next();
				System.out.println(printPerson(p));
			}
			
			
			/////////////marshalling the 3 people created above in to people.json file///////////////
			System.out.println("\n\n====Marshall the three new person to the people.json file=====\n");
			JSONUtilities.MarshallToJSON(DEFAULT_JSON_FILE_NAME, store);
			JSONUtilities.MarshallToJSON(System.out, store);
			
		} catch (FileNotFoundException e) {
			System.err.println("File not found");
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String printPerson(People.Person person){
		return person.getFirstname()+" "+person.getLastname()+" BirthDate: "+person.getBirthdate().toString()+" [ID= "+person.getId()+"]\n\thealt profile: "+printHealthProfile(person.getHealthprofile())+"\n";
	}
	
	private String printHealthProfile(People.Person.Healthprofile hp){
		return "Height="+hp.getHeight()+", Weight="+hp.getWeight()+", BMI: "+hp.getBmi()+"\n\tlast update: "+hp.getLastUpdate();
	}
	
	public static void main(String[] args){
		new Evaluation();
	}
}
