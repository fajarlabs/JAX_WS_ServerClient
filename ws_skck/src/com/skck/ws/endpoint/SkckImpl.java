package com.skck.ws.endpoint;
import javax.jws.WebService;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.skck.ws.concept.ISkck;

//Service Implementation
@WebService(endpointInterface = "com.skck.ws.concept.ISkck")
public class SkckImpl implements ISkck{

	@Override
	public String getQueue(String name) {
		// Mengirim data dalam bentuk List<Map>
		JSONObject obj = new JSONObject();
		JSONArray list = new JSONArray();
		list.add("msg 1");
		list.add("msg 2");
		list.add("msg 3");
		obj.put("data", list);
		return obj.toJSONString();
	}

}