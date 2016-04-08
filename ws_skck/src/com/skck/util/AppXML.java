package com.skck.util;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.skck.xml.model.OptionXML;

/**
 * 
 * @author masfajar
 *
 */
public class AppXML {
	
	private final static String XML_PATH = "options/options.xml";

	/**
	 * Write XML
	 * 
	 * @param op
	 */
	public static void writeXML(OptionXML op) {
		try {
			File file = new File(XML_PATH);
			JAXBContext jaxbContext = JAXBContext.newInstance(OptionXML.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(op, file);
			jaxbMarshaller.marshal(op, System.out);

		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Get XML
	 * @return
	 */
	public static OptionXML getXML() {
		OptionXML op = null;
		try {

			File file = new File(XML_PATH);
			JAXBContext jaxbContext = JAXBContext.newInstance(OptionXML.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			op = (OptionXML) jaxbUnmarshaller.unmarshal(file);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
		return op;
	}
}
