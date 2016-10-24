
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import dao.PeopleStoreXML;

public class Evaluation {
	
	private PeopleStoreXML peopleStoreXML;
	
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
		
		try {
			//System.out.println("weight: "+peopleStoreXML.getWeight(new Long(5))+" height: "+peopleStoreXML.getHeight(new Long(5)));
			//System.out.println(peopleStoreXML.list());
			//System.out.println(peopleStoreXML.list(peopleStoreXML.getWeight(56, "=")));
			System.out.println(peopleStoreXML.list(peopleStoreXML.getHeight(1.43, "=")));
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args){
		new Evaluation();
	}
}
