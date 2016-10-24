import java.io.File;
import java.io.FileReader;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import model.HealthProfile;
import model.Person;
import dao.PeopleStore;

public class HealthProfileWriter {
	public static PeopleStore people = new PeopleStore();

	public static void initializeDB() {
		Person pallino = new Person();
		Person pallo = new Person(new Long(1), "Pallo", "Pinco", "1984-06-21");
		HealthProfile hp = new HealthProfile(68.0, 1.72);
		Person john = new Person(new Long(2), "John", "Doe", "1985-03-20", hp);

		people.getData().add(pallino);
		people.getData().add(pallo);
		people.getData().add(john);
	}

	public static void initializeDB2(){

		HealthProfile hp = new HealthProfile(RandomDate.random(50,90), (double)(RandomDate.random(100,200))/100.0);
		Person person=new Person(new Long(1),"Guido","Piano",RandomDate.randomDOB(),hp);
		people.getData().add(person);

		hp = new HealthProfile(RandomDate.random(50,90), (double)(RandomDate.random(100,200))/100.0);
		person=new Person(new Long(2),"Ilaria","Condizionata",RandomDate.randomDOB(),hp);
		people.getData().add(person);

		hp = new HealthProfile(RandomDate.random(50,90), (double)(RandomDate.random(100,200))/100.0);
		person=new Person(new Long(3),"Bianca","Carta",RandomDate.randomDOB(),hp);
		people.getData().add(person);

		hp = new HealthProfile(RandomDate.random(50,90), (double)(RandomDate.random(100,200))/100.0);
		person=new Person(new Long(4),"Gustavo","Lapasta",RandomDate.randomDOB(),hp);
		people.getData().add(person);

		hp = new HealthProfile(RandomDate.random(50,90), (double)(RandomDate.random(100,200))/100.0);
		person=new Person(new Long(5),"Mario","Rossi",RandomDate.randomDOB(),hp);
		people.getData().add(person);

		hp = new HealthProfile(RandomDate.random(50,90), (double)(RandomDate.random(100,200))/100.0);
		person=new Person(new Long(6),"John","Doe",RandomDate.randomDOB(),hp);
		people.getData().add(person);

		hp = new HealthProfile(RandomDate.random(50,90), (double)(RandomDate.random(100,200))/100.0);
		person=new Person(new Long(7),"Salice","Piangente",RandomDate.randomDOB(),hp);
		people.getData().add(person);

		hp = new HealthProfile(RandomDate.random(50,90), (double)(RandomDate.random(100,200))/100.0);
		person=new Person(new Long(8),"Paolo","Bitta",RandomDate.randomDOB(),hp);
		people.getData().add(person);

		hp = new HealthProfile(RandomDate.random(50,90), (double)(RandomDate.random(100,200))/100.0);
		person=new Person(new Long(9),"Donato","Cavallo",RandomDate.randomDOB(),hp);
		people.getData().add(person);

		hp = new HealthProfile(RandomDate.random(50,90), (double)(RandomDate.random(100,200))/100.0);
		person=new Person(new Long(10),"Daria","Corrente",RandomDate.randomDOB(),hp);
		people.getData().add(person);

		hp = new HealthProfile(RandomDate.random(50,90), (double)(RandomDate.random(100,200))/100.0);
		person=new Person(new Long(11),"John","Smith",RandomDate.randomDOB(),hp);
		people.getData().add(person);

		hp = new HealthProfile(RandomDate.random(50,90), (double)(RandomDate.random(100,200))/100.0);
		person=new Person(new Long(12),"Ilaria","Condizionata",RandomDate.randomDOB(),hp);
		people.getData().add(person);

		hp = new HealthProfile(RandomDate.random(50,90), (double)(RandomDate.random(100,200))/100.0);
		person=new Person(new Long(13),"Ali","Mentari",RandomDate.randomDOB(),hp);
		people.getData().add(person);

		hp = new HealthProfile(RandomDate.random(50,90), (double)(RandomDate.random(100,200))/100.0);
		person=new Person(new Long(14),"Andrea","Fumagalli",RandomDate.randomDOB(),hp);
		people.getData().add(person);

		hp = new HealthProfile(RandomDate.random(50,90), (double)(RandomDate.random(100,200))/100.0);
		person=new Person(new Long(15),"Ermenegildo","Brambilla",RandomDate.randomDOB(),hp);
		people.getData().add(person);

		hp = new HealthProfile(RandomDate.random(50,90), (double)(RandomDate.random(100,200))/100.0);
		person=new Person(new Long(16),"Fulvio","Brambilla",RandomDate.randomDOB(),hp);
		people.getData().add(person);

		hp = new HealthProfile(RandomDate.random(50,90), (double)(RandomDate.random(100,200))/100.0);
		person=new Person(new Long(17),"Italo","Medio",RandomDate.randomDOB(),hp);
		people.getData().add(person);

		hp = new HealthProfile(RandomDate.random(50,90), (double)(RandomDate.random(100,200))/100.0);
		person=new Person(new Long(18),"Patrizia","Trento",RandomDate.randomDOB(),hp);
		people.getData().add(person);

		hp = new HealthProfile(RandomDate.random(50,90), (double)(RandomDate.random(100,200))/100.0);
		person=new Person(new Long(19),"Davide","Illuminato",RandomDate.randomDOB(),hp);
		people.getData().add(person);

		hp = new HealthProfile(RandomDate.random(50,90), (double)(RandomDate.random(100,200))/100.0);
		person=new Person(new Long(20),"Gilberto","Fumagalli",RandomDate.randomDOB(),hp);
		people.getData().add(person);

	}

	public static void main(String[] args) throws Exception {

		initializeDB2();

		JAXBContext jc = JAXBContext.newInstance(PeopleStore.class);
        Marshaller m = jc.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        m.marshal(people,new File("people.xml")); // marshalling into a file
        m.marshal(people, System.out);			  // marshalling into the system default output
    }
}
