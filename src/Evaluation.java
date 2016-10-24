
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import dao.PeopleStoreXML;

public class Evaluation {
	
	private PeopleStoreXML peopleStoreXML=null;
	
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
	
	public static void main(String[] args){
		new Evaluation();
	}
}
