package com.server;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style=Style.RPC)
public interface Center {
	/**
	 * method to create new Teacher Record
	 * 
	 * @param firstName
	 * @param lastName
	 * @param address
	 * @param phone
	 * @param specialization
	 * @param location
	 * @throws RemoteException
	 */
	public Boolean createTRecord(String firstName, String lastName, String address, String phone, String specialization,
			String location, String managerID);

	/**
	 * method to create new Student Record
	 * 
	 * @param firstName
	 * @param lastName
	 * @param courseRegistered
	 * @param status
	 * @param statusDate
	 * @throws RemoteException
	 */
	public Boolean createSRecord(String firstName, String lastName, String courseRegistered, String status,
			String statusDate, String managerID);

	/**
	 * method to get record counts
	 * 
	 * @param managerID
	 * @return String number of records with location
	 * @throws RemoteException
	 */
	public String getRecordCounts(String managerID);

	/**
	 * method to edit record
	 * 
	 * @param recordID
	 * @param fieldName
	 * @param newValue
	 * @throws RemoteException
	 */
	public Boolean editRecord(String recordID, String fieldName, String newValue, String managerID);

	public String transferRecord(String managerID, String recordI);

}
