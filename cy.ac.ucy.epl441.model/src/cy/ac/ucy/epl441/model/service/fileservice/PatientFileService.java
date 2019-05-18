package cy.ac.ucy.epl441.model.service.fileservice;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import cy.ac.ucy.epl441.model.Allergy;
import cy.ac.ucy.epl441.model.Consultation;
import cy.ac.ucy.epl441.model.Incident;
import cy.ac.ucy.epl441.model.InformationChanges;
import cy.ac.ucy.epl441.model.Patient;
import cy.ac.ucy.epl441.model.Relative;
import cy.ac.ucy.epl441.model.Treatment;
import cy.ac.ucy.epl441.model.service.PatientService;

public class PatientFileService implements PatientService{
	private File patientfile;
	private Document document;
	ArrayList<Patient> patients = new ArrayList<Patient>();

	public PatientFileService() {
		patientfile= new File("Patients.xml");
		DocumentBuilderFactory xml = DocumentBuilderFactory.newInstance();
	    try {
			DocumentBuilder db= xml.newDocumentBuilder();
			Document document = db.parse(patientfile);

		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	

	@Override
	public void create(Patient item) {
		// TODO Auto-generated method stub
		Element root = document.getDocumentElement();
		Element patient =document.createElement("Patient");
		Element name= document.createElement("name");
		Element patientid= document.createElement("patientid");
		Element email= document.createElement("email");
		Element phone= document.createElement("phone");
		Element selfharm= document.createElement("selfharm");



		patientid.appendChild(document.createTextNode(Integer.toString(item.getPatientId())));
        patient.appendChild(patientid);
        name.appendChild(document.createTextNode(item.getName()));
        patient.appendChild(name); 
        email.appendChild(document.createTextNode(item.getEmail()));
        phone.appendChild(document.createTextNode(item.getPhone()));
        selfharm.appendChild(document.createTextNode(Boolean.toString(item.isSelfHarm())));
        patient.appendChild(email);
        patient.appendChild(phone);
        patient.appendChild(selfharm);
        
		root.appendChild(patient);
		
        DOMSource source = new DOMSource(document);

		
        try {
        	TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            StreamResult result = new StreamResult("Patients.xml");
			transformer.transform(source, result);
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		
		
		
	}

	@Override
	public ArrayList<Patient> getAll() {
		// TODO Auto-generated method stub
		 NodeList all = document.getElementsByTagName("Patient");
		    int i=0;
		    Patient temp;
		    String name;
		    String email, phone;
		    Boolean selfharm;
		    int id;
		    for(i=0; i<all.getLength(); i++) {
		    	 Node node = all.item(i);
		    	 Element element = (Element) node;
		             
		         name= element.getElementsByTagName("name").item(0).getTextContent();
		         id=Integer.parseInt(element.getElementsByTagName("patientid").item(0).getTextContent());
		         email= element.getElementsByTagName("email").item(0).getTextContent();
		         phone= element.getElementsByTagName("phone").item(0).getTextContent();
		         selfharm= Boolean.parseBoolean(element.getElementsByTagName("name").item(0).getTextContent());

		         temp= new Patient(id,name,email,phone,selfharm);

		         patients.add(temp);
		    }

			return patients;
		
	}

	@Override
	public Patient get(int id) {
		// TODO Auto-generated method stub
		if(patients.isEmpty()) {
			getAll();
		}
		
		int i=0;
		for(i=0; i< patients.size(); i++) {
			if(id== patients.get(i).getPatientId()) {
				return patients.get(i);
			}
		}
		return null;
	}
		

	@Override
	public void update(Patient item) {
		// TODO Auto-generated method stub
		
		 NodeList all = document.getElementsByTagName("Patient");
		    int i,id;
		    String newname=item.getName();
		    for(i=0; i<all.getLength(); i++) {
		   	 Node node = all.item(i);//perni kathe fora ena patient
		   	 Element element = (Element) node;
		   	 
		     id=Integer.parseInt(element.getElementsByTagName("patientid").item(0).getTextContent());
		     if(id== item.getPatientId()) {
		    	 element.getElementsByTagName("name").item(0).setTextContent(newname);
		    	 element.getElementsByTagName("email").item(0).setTextContent(item.getEmail());
		    	 element.getElementsByTagName("phone").item(0).setTextContent(item.toString());
		    	 element.getElementsByTagName("selfharm").item(0).setTextContent(Boolean.toString(item.isSelfHarm()));

		    	 break;
		     }
		   	 
		   	 }
		    
		    DOMSource source = new DOMSource(document);

			
	        try {
	        	TransformerFactory transformerFactory = TransformerFactory.newInstance();
	            Transformer transformer = transformerFactory.newTransformer();
	            StreamResult result = new StreamResult("Patients.xml");
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
		
		NodeList all = document.getElementsByTagName("Patient");
	    int i,patientid;
	    Node root = document.getDocumentElement();

	    for(i=0; i<all.getLength(); i++) {
	   	 Node node = all.item(i);//perni kathe fora ena allergy
	   	 Element element = (Element) node;
	   	 
	     patientid=Integer.parseInt(element.getElementsByTagName("patientid").item(0).getTextContent());
	     if(id== patientid) {
	    	 root.removeChild(element);
	    	 break;
	     }
	   	 
	   	 }
	    DOMSource source = new DOMSource(document);

		
	    try {
	    	TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        Transformer transformer = transformerFactory.newTransformer();
	        StreamResult result = new StreamResult("Patients.xml");
			transformer.transform(source, result);
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    getAll();
		
		
		
		
	}

	@Override
	public ArrayList<Treatment> getTreatments(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Allergy> getAllergies(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Relative> getRelatives(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<InformationChanges> getInformationChanges(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Incident> getIncidents(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Consultation> getConsultations(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
