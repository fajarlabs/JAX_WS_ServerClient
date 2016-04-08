package com.skck.ws.client.receiver;

import java.net.URL;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.skck.ws.concept.ISkck;

public class SkckReceiver {

	public static void main(String[] args) throws Exception {

		URL url = new URL("http://localhost:9999/ws/skck?wsdl");
		QName qname = new QName("http://endpoint.ws.skck.com/", "SkckImplService");

		Service service = Service.create(url, qname);

		ISkck hello = service.getPort(ISkck.class);
		List<String> result = hello.getQueue("DATA SKCK");
		System.out.println(result);

	}

}
