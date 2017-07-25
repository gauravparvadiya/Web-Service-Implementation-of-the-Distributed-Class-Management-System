package com.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.apache.log4j.Logger;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.helper.LogHelper;
import com.users.Manager;
import com.users.Student;
import com.users.Teacher;

public class ManagerClient {

	static Center centerImplMTL, centerImplLVL, centerImplDDO;
	public HashMap<String, ArrayList<Manager>> managerHashMap;
	public ArrayList<Manager> mtl, lvl, ddo;
	JsonParser parser;
	static Logger logger = Logger.getLogger(ManagerClient.class);

	/**
	 * Default constructor that reads JSON file and puts data in manager hashmap
	 * 
	 * @throws FileNotFoundException
	 */
	public ManagerClient() throws FileNotFoundException {

		File f = new File("res/manager.json");
		Reader reader = new BufferedReader(new FileReader(f.getAbsolutePath()));

		JsonParser parser = new JsonParser();
		JsonArray array = parser.parse(reader).getAsJsonArray();

		mtl = new ArrayList<Manager>();
		lvl = new ArrayList<Manager>();
		ddo = new ArrayList<Manager>();

		managerHashMap = new HashMap<String, ArrayList<Manager>>();

		if (array != null) {
			for (int i = 0; i < array.size(); i++) {
				JsonObject object = (JsonObject) array.get(i);
				Manager manager = new Manager(object.get("managerID").toString(), object.get("fname").toString(),
						object.get("lname").toString());
				if (object.get("managerID").getAsString().substring(0, 3).equals("MTL")) {
					mtl.add(manager);
				} else if (object.get("managerID").getAsString().substring(0, 3).equals("LVL")) {
					lvl.add(manager);
				} else if (object.get("managerID").getAsString().substring(0, 3).equals("DDO")) {
					ddo.add(manager);
				}
			}
			managerHashMap.put("MTL", mtl);
			managerHashMap.put("LVL", lvl);
			managerHashMap.put("DDO", ddo);
		}
	}

	/**
	 * Method to connect the client to particular server
	 * 
	 * @param managerID
	 * @param fn
	 * @param ln
	 * @param address
	 * @param ph
	 * @param spec
	 * @param loc
	 * @throws RemoteException
	 * @throws NotBoundException
	 * @throws ServerNotActiveException
	 * @throws InvalidName
	 * @throws org.omg.CosNaming.NamingContextPackage.InvalidName
	 * @throws CannotProceed
	 * @throws NotFound
	 */
	public static void connect_teacher(String managerID, String fn, String ln, String address, String ph, String spec,
			String loc) {
		logger.info("Using createTRecord method.");
		if (managerID.substring(0, 3).equals("MTL")) {
			if (centerImplMTL.createTRecord(managerID, fn, ln, address, ph, spec, loc)) {
				System.out.println("Record created successfully. ");
				logger.info("Teacher record created successfully.");
				;
			} else {
				System.out.println("Something went wrong!!! ");
				logger.error("Server returns error creating teacher record.");
			}
		} else if (managerID.substring(0, 3).equals("LVL")) {
			if (centerImplLVL.createTRecord(managerID, fn, ln, address, ph, spec, loc)) {
				System.out.println("Record created successfully. ");
				logger.info("Teacher record created successfully.");
			} else {
				System.out.println("Something went wrong!!! ");
				logger.error("Server returns error creating teacher record.");
			}
		} else {
			if (centerImplDDO.createTRecord(managerID, fn, ln, address, ph, spec, loc)) {
				System.out.println("Record created successfully. ");
				logger.info("Teacher record created successfully.");
			} else {
				System.out.println("Something went wrong!!! ");
				logger.error("Server returns error creating teacher record.");
			}
		}
	}

	/**
	 * Method to connect client to particular server
	 * 
	 * @param managerID
	 * @param fn
	 * @param ln
	 * @param courses
	 * @param status
	 * @param statusDate
	 * @throws RemoteException
	 * @throws NotBoundException
	 */
	public static void connect_student(String managerID, String fn, String ln, String courses, String status,
			String statusDate) {
		logger.info("Using createSRecord method.");
		if (managerID.substring(0, 3).equals("MTL")) {
			if (centerImplMTL.createSRecord(managerID, fn, ln, courses, status, statusDate)) {
				System.out.println("Record created successfully.");
				logger.info("Student record created successfully.");
			} else {
				System.out.println("Something went wrong!!! ");
				logger.error("Server returns error creating student record.");
			}
		} else if (managerID.substring(0, 3).equals("LVL")) {
			if (centerImplLVL.createSRecord(managerID, fn, ln, courses, status, statusDate)) {
				System.out.println("Record created successfully.");
				logger.info("Student record created successfully.");
			} else {
				System.out.println("Something went wrong!!! ");
				logger.error("Server returns error creating student record.");
			}
		} else {
			if (centerImplDDO.createSRecord(managerID, fn, ln, courses, status, statusDate)) {
				System.out.println("Record created successfully.");
				logger.info("Student record created successfully.");
			} else {
				System.out.println("Something went wrong!!! ");
				logger.error("Server returns error creating student record.");
			}
		}
	}

	/**
	 * Method to connect client to particular server
	 * 
	 * @param managerID
	 * @param fieldname
	 * @param newvalue
	 * @param id
	 * @throws RemoteException
	 * @throws NotBoundException
	 */
	public static void connect_edit(String managerID, String id, String fieldname, String newvalue) {
		if (managerID.substring(0, 3).equals("MTL")) {
			logger.debug("connected to Montreal server");
			if (centerImplMTL.editRecord(managerID, id, fieldname, newvalue)) {
				System.out.println("Record edited successfully.");
			} else {
				System.out.println("Error.");
			}
		} else if (managerID.substring(0, 3).equals("LVL")) {
			logger.debug("connected to Laval server");
			if (centerImplLVL.editRecord(managerID, id, fieldname, newvalue)) {
				System.out.println("Record edited successfully.");
			} else {
				System.out.println("Error.");
			}
		} else {
			logger.debug("connected to DDO server");
			if (centerImplDDO.editRecord(managerID, id, fieldname, newvalue)) {
				System.out.println("Record edited successfully.");
			} else {
				System.out.println("Error.");
			}
		}
		logger.info("Using editRecord method");
	}

	/**
	 * Method to connect client to particular server
	 * 
	 * @param managerID
	 * @throws RemoteException
	 * @throws NotBoundException
	 */
	public static void connect_record_count(String managerID) {
		logger.info("Using getRecordCount method.");

		logger.debug("connected to Montreal server");
		String reply = centerImplMTL.getRecordCounts(managerID);
		System.out.println("MTL : \n" + reply);

		logger.debug("connected to Laval server");
		reply = centerImplLVL.getRecordCounts(managerID);
		System.out.println("LVL : \n" + reply);

		logger.debug("connected to DDO server");
		reply = centerImplDDO.getRecordCounts(managerID);
		System.out.println("DDO : \n" + reply);

	}

	/**
	 * 
	 * @param managerID
	 * @param id
	 * @param server_name
	 */
	public static void connect_transfer(String managerID, String id, String server_name) {
		logger.info("Using transfer record method.");
		if (managerID.substring(0, 3).equals("MTL")) {
			logger.debug("connected to Montreal server");
			String o = centerImplMTL.transferRecord(managerID, id);
			if (o != "khabar nai") {
				String[] params = o.split(":");
				if (id.substring(0, 3).equals("MSR")) {
					System.out.println(o);
					System.out.println(params[0]);
					if (server_name.equals("LVL")) {
						centerImplLVL.createSRecord(managerID, params[0], params[1], params[2], params[3], params[4]);
						logger.info("Student record tansfered from MTL and created on LVL successfully.");

					} else {
						centerImplDDO.createSRecord(managerID, params[0], params[1], params[2], params[3], params[4]);
						logger.info("Student record tansfered from MTL and created on DDO successfully.");
					}
				} else {
					if (server_name.equals("LVL")) {
						centerImplLVL.createTRecord(managerID, params[0], params[1], params[2], params[3], params[4],
								params[5]);
						logger.info("Teacher record tansfered from MTL and created on LVL successfully.");

					} else {
						centerImplDDO.createTRecord(managerID, params[0], params[1], params[2], params[3], params[4],
								params[5]);
						logger.info("Teacher record tansfered from MTL and created on DDO successfully.");
					}
				}
				System.out.println("transfer successful");
			} else {
				System.out.println("record not found.");
			}
		} else if (managerID.substring(0, 3).equals("LVL")) {
			logger.debug("connected to Laval server");
			String o = centerImplLVL.transferRecord(managerID, id);
			if (o != "khabar nai") {
				String[] params = o.split(":");
				if (id.substring(0, 3).equals("LSR")) {
					System.out.println(o);
					System.out.println(params[0]);
					if (server_name.equals("MTL")) {
						centerImplMTL.createSRecord(managerID, params[0], params[1], params[2], params[3], params[4]);
						logger.info("Student record tansfered from LVL and created on MTL successfully.");

					} else {
						centerImplDDO.createSRecord(managerID, params[0], params[1], params[2], params[3], params[4]);
						logger.info("Student record tansfered from LVL and created on DDO successfully.");
					}
				} else {
					if (server_name.equals("MTL")) {
						centerImplMTL.createTRecord(managerID, params[0], params[1], params[2], params[3], params[4],
								params[5]);
						logger.info("Teacher record tansfered from DDO and created on MTL successfully.");

					} else {
						centerImplDDO.createTRecord(managerID, params[0], params[1], params[2], params[3], params[4],
								params[5]);
						logger.info("Teacher record tansfered from DDO and created on LVL successfully.");
					}
				}
				System.out.println("transfer successful");
			} else {
				System.out.println("record not found.");
			}
		} else if (managerID.substring(0, 3).equals("DDO")) {
			logger.debug("connected to DDO server");
			String o = centerImplDDO.transferRecord(managerID, id);
			if (o != "khabar nai") {
				String[] params = o.split(":");
				if (id.substring(0, 3).equals("DSR")) {
					System.out.println(o);
					System.out.println(params[0]);
					if (server_name.equals("LVL")) {
						centerImplLVL.createSRecord(managerID, params[0], params[1], params[2], params[3], params[4]);
						logger.info("Student record created successfully.");

					} else {
						centerImplMTL.createSRecord(managerID, params[0], params[1], params[2], params[3], params[4]);
						logger.info("Student record created successfully.");
					}
				} else {
					if (server_name.equals("LVL")) {
						centerImplLVL.createTRecord(managerID, params[0], params[1], params[2], params[3], params[4],
								params[5]);
						logger.info("Teacher record created successfully.");

					} else {
						centerImplMTL.createTRecord(managerID, params[0], params[1], params[2], params[3], params[4],
								params[5]);
						logger.info("Teacher record created successfully.");
					}
				}
				System.out.println("transfer successful");
			} else {
				System.out.println("record not found.");
			}
		}

	}

	/**
	 * Method to validate the edit method parameters
	 * 
	 * @param id
	 * @param fieldName
	 * @param newValue
	 * @return
	 */
	public static boolean validate_edit(String id, String fieldName, String newValue) {

		if (id.length() == 8) {
			if (id.substring(0, 3).equals("MTR") || id.substring(0, 3).equals("LTR")
					|| id.substring(0, 3).equals("DTR")) {
				logger.info("Starting to edit teacher.");
				if (fieldName.equals("address") || fieldName.equals("location") || fieldName.equals("phone")) {
					return true;
				} else {
					System.out.println("Invalid Field");
					return false;
				}
			} else if (id.substring(0, 3).equals("MSR") || id.substring(0, 3).equals("LSR")
					|| id.substring(0, 3).equals("DSR")) {
				logger.info("Starting to edit student.");
				if (fieldName.equals("coursesRegistered") || fieldName.equals("status")
						|| fieldName.equals("statusDueDate")) {
					return true;
				} else {
					System.out.println("Invalid Field");
					return false;
				}
			} else {
				return true;
			}
		} else {
			System.out.println("Please enter valid record ID");
			return false;
		}
	}

	/**
	 * Method to identify if the manager is exists or not
	 * 
	 * @param managerClient
	 * @param managerID
	 * @return
	 * @throws RemoteException
	 * @throws NotBoundException
	 */
	public Boolean managerIdentification(ManagerClient managerClient, String managerID) {
		if (managerID.substring(0, 3).equals("MTL")) {
			for (int i = 0; i < managerClient.managerHashMap.get("MTL").size(); i++) {

				Manager manager = managerClient.managerHashMap.get("MTL").get(i);
				if (manager.getManagerID().substring(1, 8).equals(managerID)) {

					String fname = manager.getFname();
					String lname = manager.getLname();
					System.out.println("Welcome , " + fname.substring(1, fname.length() - 1) + " "
							+ lname.substring(1, lname.length() - 1));
					return true;
				}
			}
		} else if (managerID.substring(0, 3).equals("LVL")) {
			for (int i = 0; i < managerClient.managerHashMap.get("LVL").size(); i++) {
				Manager manager = managerClient.managerHashMap.get("LVL").get(i);
				if (manager.getManagerID().substring(1, 8).equals(managerID)) {
					String fname = manager.getFname();
					String lname = manager.getLname();
					System.out.println("Welcome , " + fname.substring(1, fname.length() - 1) + " "
							+ lname.substring(1, lname.length() - 1));
					return true;
				}
			}
		} else if (managerID.substring(0, 3).equals("DDO")) {
			for (int i = 0; i < managerClient.managerHashMap.get("DDO").size(); i++) {
				Manager manager = managerClient.managerHashMap.get("DDO").get(i);
				if (manager.getManagerID().substring(1, 8).equals(managerID)) {

					String fname = manager.getFname();
					String lname = manager.getLname();
					System.out.println("Welcome , " + fname.substring(1, fname.length() - 1) + " "
							+ lname.substring(1, lname.length() - 1));
					return true;
				}
			}
		} else {
			System.out.println("Manager ID should start with MTL/LVL/DDO");
			return false;
		}
		return false;
	}

	public static void main(String args[]) {
		try {

			// Binding of MTL WebService
			URL urlMTL = new URL("http://localhost:8081/Service/MTL?wsdl");
			QName qnameMTL = new QName("http://server.com/", "CenterServerMTLService");
			Service serviceMTL = Service.create(urlMTL, qnameMTL);
			centerImplMTL = serviceMTL.getPort(Center.class);

			// Binding of LVL WebService
			URL urlLVL = new URL("http://localhost:8081/Service/LVL?wsdl");
			QName qnameLVL = new QName("http://server.com/", "CenterServerLVLService");
			Service serviceLVL = Service.create(urlLVL, qnameLVL);
			centerImplLVL = serviceLVL.getPort(Center.class);

			// Binding of DDO WebService
			URL urlDDO = new URL("http://localhost:8081/Service/DDO?wsdl");
			QName qnameDDO = new QName("http://server.com/", "CenterServerDDOService");
			Service serviceDDO = Service.create(urlDDO, qnameDDO);
			centerImplDDO = serviceDDO.getPort(Center.class);

			ManagerClient managerClient = new ManagerClient();
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			String managerID;
			do {
				System.out.println("Enter the Manager ID : ");
				managerID = reader.readLine();

				if (!managerID.equals("") && managerID.length() == 7
						&& managerClient.managerIdentification(managerClient, managerID)) {
					LogHelper helper = new LogHelper();
					helper.setupLogFile("log/" + managerID + ".log");
					logger.debug("connected to manager client.");
					do {
						System.out.println("\n 1. Create Teacher ");
						System.out.println(" 2. Create Student ");
						System.out.println(" 3. Get Record Counts ");
						System.out.println(" 4. Edit Record ");
						System.out.println(" 5. Transfer Record ");
						System.out.println(" 6. Exit");

						reader = new BufferedReader(new InputStreamReader(System.in));
						System.out.println("\n Enter your choice : ");

						Scanner s = new Scanner(System.in);
						String status;
						String firstName, lastName, address, phone, spec, loc, id, statusDate, fieldName, server_name;
						String DATE_PATTERN = "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)";
						String courses;
						String newValue;
						Pattern pattern;
						Matcher matcher;

						switch (reader.readLine()) {
						case "1":
							logger.info("Starting to create teacher.");
							System.out.println("Enter Teacher Information");
							System.out.println("First Name : ");
							firstName = s.nextLine();
							System.out.println("Last Name : ");
							lastName = s.nextLine();
							System.out.println("Address : ");
							address = s.nextLine();
							System.out.println("Phone : ");
							phone = s.nextLine();
							System.out.println("Specialization : ");
							spec = s.nextLine();
							System.out.println("Location : ");
							loc = s.nextLine();
							boolean isNum = true;
							int size = phone.length();
							if (size == 10) {
								for (int i = 0; i < size; i++) {
									if (!Character.isDigit(phone.charAt(i))) {
										isNum = false;
									}
								}
							}
							else{
								isNum=false;
							}
							if (!firstName.equals("") && !lastName.equals("") && !address.equals("") && isNum
									&& !phone.equals("") && !spec.equals("") && !loc.equals("")) {
								connect_teacher(managerID, firstName, lastName, address, phone, spec, loc);
							} else
								System.out.println("Please enter proper values.");
							break;
						case "2":
							logger.info("Starting to create student.");
							System.out.println("Enter Student Information");
							System.out.println("First Name : ");
							firstName = s.nextLine();
							System.out.println("Last Name : ");
							lastName = s.nextLine();
							System.out.println("Courses registered (separated with comma) : ");
							courses = s.nextLine();
							System.out.println("Status : (active & deactive)");
							status = s.nextLine();
							System.out.println("Status Date : (DD/MM/YYYY)");
							statusDate = s.nextLine();
							pattern = Pattern.compile(DATE_PATTERN);
							matcher = pattern.matcher(statusDate);
							if (!firstName.equals("") && !lastName.equals("") && !status.equals("")
									&& !statusDate.equals("")) {
								if (status.equals("active") || status.equals("deactive")) {
									if (matcher.matches() && (status.equals("active") || status.equals("deactive"))) {
										connect_student(managerID, firstName, lastName, courses, status, statusDate);
									} else
										System.out.println("check if you have entered correct status or date or ID");
								} else {
									System.out.println("Invalid status!");
								}
							} else
								System.out.println("Field can not be blank");
							break;
						case "3":
							connect_record_count(managerID);
							break;
						case "4":
							System.out.println("Enter information to edit : ");
							System.out.println("ID : (e.g. MTR00001/MSR10001)");
							id = s.nextLine();
							System.out.println("Field Name : ");
							fieldName = s.nextLine();
							System.out.println("New Value : ");
							newValue = s.nextLine();
							if (!newValue.equals("")) {
								if (validate_edit(id, fieldName, newValue)) {
									connect_edit(managerID, id, fieldName, newValue);
								}
							} else {
								System.out.println("Please enter all fields.");
							}
							break;
						case "5":
							System.out.println("Enter information to transfer : ");
							System.out.println("ID : (e.g. MTR00001/MSR10001)");
							id = s.nextLine();
							System.out.println("Server name : (MTL/LVL/DDO)");
							server_name = s.nextLine();
							if (id.substring(0, 3).equals("MTR") || id.substring(0, 3).equals("MSR")
									|| id.substring(0, 3).equals("LTR") || id.substring(0, 3).equals("LSR")
									|| id.substring(0, 3).equals("DTR") || id.substring(0, 3).equals("DSR")) {
								connect_transfer(managerID, id, server_name);
							} else
								System.out.println("Enter proper ID.");
							break;
						case "6":
							File file = new File("log/" + managerID + ".log");
							file.delete();
							System.out.println("Bye Bye!!!");
							System.exit(0);
							break;
						default:
							System.out.println("Invalid selection. Please select from given options.");
							break;
						}
						// s.close();
					} while (!reader.readLine().equals("5"));
				} else {
					System.out.println("Manager not found.");
				}
			} while (!managerID.equals("exit"));

		} catch (Exception e) {
			System.out.println("ERROR : " + e);
			e.printStackTrace(System.out);
		}
	}

}