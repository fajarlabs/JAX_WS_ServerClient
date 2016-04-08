package com.skck.ws.publisher;
import javax.xml.ws.Endpoint;

import com.skck.util.AppLog;
import com.skck.ws.endpoint.SkckImpl;;
/**
 * 
 * @author masfajar
 *
 */

//Endpoint publisher
public class SkckPublisher{
	private Endpoint ep;
	private String url;
	
	public SkckPublisher(String url) {
		this.url = url;
	}
	
	public void start() {
		try {
			ep = Endpoint.create(new SkckImpl());
			ep.publish(this.url);
		} catch (Exception e) {
			AppLog.error().info(e.toString());
		}
	}
	
	public void stop() {
		try {
			ep.stop();
		} catch (Exception e) {
			AppLog.error().info(e.toString());
		}
	}
	
	public Endpoint getEp() {
		return ep;
	}

}