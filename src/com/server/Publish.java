package com.server;

import javax.xml.ws.Endpoint;

public class Publish {

	public static void main(String[] args) {
		
		Endpoint endpointMTL = Endpoint.publish("http://localhost:8080/Service/MTL", new CenterServerMTL());
		System.out.println(endpointMTL.isPublished());
		
		Endpoint endpointLVL = Endpoint.publish("http://localhost:8080/Service/LVL", new CenterServerLVL());
		System.out.println(endpointLVL.isPublished());
		
		Endpoint endpointDDO = Endpoint.publish("http://localhost:8080/Service/DDO", new CenterServerDDO());
		System.out.println(endpointDDO.isPublished());
	}
}
