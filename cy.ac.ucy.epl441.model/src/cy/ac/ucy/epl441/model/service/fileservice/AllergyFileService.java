package cy.ac.ucy.epl441.model.service.fileservice;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.osgi.service.component.annotations.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import cy.ac.ucy.epl441.model.Allergy;
import cy.ac.ucy.epl441.model.Patient;
import cy.ac.ucy.epl441.model.service.AllergyService;

@Component
public class AllergyFileService implements AllergyService{
	private File allergyfile;
	private Document document;
	private ArrayList<Allergy> allergies = new ArrayList<Allergy>();

	public AllergyFileService() {
		allergyfile= new File("Allergies.xml");
		DocumentBuilderFactory xml = DocumentBuilderFactory.newInstance();
	    try {
			DocumentBuilder db= xml.newDocumentBuilder();
			 document = db.parse(allergyfile);

		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		
	}
		

@Override
public void create(Allergy item) {
	// TODO Auto-generated method stub
	
    
		Element root = document.getDocumentElement();
		Element allergy =document.createElement("Allergy");
		Element name= document.createElement("name");
		Element id= document.createElement("id");
		id.appendChild(document.createTextNode(Integer.toString(item.getAllergyId())));
        allergy.appendChild(id);
        name.appendChild(document.createTextNode(item.getName()));
        allergy.appendChild(name); 
		root.appendChild(allergy);
		
        DOMSource source = new DOMSource(document);

		
        try {
        	TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            StreamResult result = new StreamResult("Allergies.xml");
			transformer.transform(source, result);
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
}

@Override
public ArrayList<Allergy> getAll() {
	// TODO Auto-generated method stub

    NodeList all = document.getElementsByTagName("Allergy");
    int i=0;
    Allergy temp;
    String name;
    String sid;
    int id;
    for(i=0; i<all.getLength(); i++) {
    	 Node node = all.item(i);
    	 Element element = (Element) node;
             
         name= element.getElementsByTagName("name").item(0).getTextContent();
         id=Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent());
         temp= new Allergy(id,name);

         allergies.add(temp);
    }

	return allergies;
}

@Override
public Allergy get(int id) {
	// TODO Auto-generated method stub

	if(allergies.isEmpty()) {
		getAll();
	}
	
	int i=0;
	for(i=0; i< allergies.size(); i++) {
		if(id== allergies.get(i).getAllergyId()) {
			return allergies.get(i);
		}
	}
	return null;
}

@Override
public void update(Allergy item) {
	// TODO Auto-generated method stub
    NodeList all = document.getElementsByTagName("Allergy");
    int i,id;
    String newname=item.getName();
    for(i=0; i<all.getLength(); i++) {
   	 Node node = all.item(i);//perni kathe fora ena allergy
   	 Element element = (Element) node;
   	 
     id=Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent());
     if(id== item.getAllergyId()) {
    	 element.getElementsByTagName("name").item(0).setTextContent(newname);
    	 break;
     }
   	 
   	 }
    DOMSource source = new DOMSource(document);

	
    try {
    	TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        StreamResult result = new StreamResult("Allergies.xml");
		transformer.transform(source, result);
	} catch (TransformerException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    getAll();

	
	
}

@Override
public void delete(int id) {
	// TODO Auto-generated method stub
	NodeList all = document.getElementsByTagName("Allergy");
    int i,allergyid;
    Node root = document.getDocumentElement();

    for(i=0; i<all.getLength(); i++) {
   	 Node node = all.item(i);//perni kathe fora ena allergy
   	 Element element = (Element) node;
   	 
     allergyid=Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent());
     if(id== allergyid) {
    	 root.removeChild(element);
    	 break;
     }
   	 
   	 }
    DOMSource source = new DOMSource(document);

	
    try {
    	TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        StreamResult result = new StreamResult("Allergies.xml");
		transformer.transform(source, result);
	} catch (TransformerException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    getAll();
}

@Override
public ArrayList<Patient> getPatients(Allergy allergy) {
	// TODO Auto-generated method stub
	
	File patientallergy;
	Document doc;
	ArrayList<Patient>  patients= new ArrayList<Patient>();
	NodeList all;
	int i,allergyid,j;
	int patientid;
	//save patient id
	ArrayList<Integer> patientsid= new ArrayList<Integer>();
	ArrayList<Patient>  patientsallergy= new ArrayList<Patient>();

	
		patientallergy= new File("PatientsAllergies.xml");
		DocumentBuilderFactory xml = DocumentBuilderFactory.newInstance();
	    try {
			DocumentBuilder db= xml.newDocumentBuilder();
			doc = db.parse(patientallergy);
			all = doc.getElementsByTagName("PatientAllergy");
			for(i=0; i<all.getLength(); i++) {
			   	Node node = all.item(i);
			   	Element element = (Element) node;
			   	 
			    allergyid=Integer.parseInt(element.getElementsByTagName("allergyid").item(0).getTextContent());
			    if(allergyid== allergy.getAllergyId()) {
			    patientid=allergyid=Integer.parseInt(element.getElementsByTagName("patientid").item(0).getTextContent());
			    patientsid.add(patientid);			    
			     }
			   	 
			   	 }
			
			PatientFileService patientfile = new PatientFileService();
			patients=patientfile.getAll();
		
			for(i=0; i<patientsid.size(); i++) {
				for(j=0; j<patients.size(); j++) {
					if(patientsid.get(i)==patients.get(j).getPatientId()) {
						patientsallergy.add(patients.get(j));
						break;
					}
				}
			}
			


		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();}
	    
	   

	    
	
	
	return patientsallergy;
}


@Override
public void setConnection(Connection con) {
	// TODO Auto-generated method stub
	
}

}
