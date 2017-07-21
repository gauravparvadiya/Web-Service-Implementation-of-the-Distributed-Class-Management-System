package com.server;

import javax.xml.ws.Endpoint;

public class Publish {

	public static void main(String[] args) {
		
		Endpoint endpointMTL = Endpoint.publish("http://localhost:8080/Service/MTL", new CenterServerMTL());
		System.out.println(endpointMTL.isPublished());
	}
}
