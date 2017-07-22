package com.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import org.apache.log4j.Logger;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.helper.LogHelper;
import com.users.Student;
import com.users.Teacher;

@WebService(endpointInterface = "com.server.Center")
@SOAPBinding(style = Style.RPC)
public class CenterServerDDO implements Center {

	public final HashMap<String, ArrayList<Object>> srtrRecords = new HashMap<String, ArrayList<Object>>();
	public ArrayList<Object> srtrDdo;
	public ArrayList<Object> a;
	public ArrayList<Object> b;
	public ArrayList<Object> c;
	public ArrayList<Object> d;
	public ArrayList<Object> e;
	public ArrayList<Object> f;
	public ArrayList<Object> g;
	public ArrayList<Object> h;
	public ArrayList<Object> i;
	public ArrayList<Object> j;
	public ArrayList<Object> k;
	public ArrayList<Object> l;
	public ArrayList<Object> m;
	public ArrayList<Object> n;
	public ArrayList<Object> o;
	public ArrayList<Object> p;
	public ArrayList<Object> q;
	public ArrayList<Object> r;
	public ArrayList<Object> s;
	public ArrayList<Object> t;
	public ArrayList<Object> u;
	public ArrayList<Object> v;
	public ArrayList<Object> w;
	public ArrayList<Object> x;
	public ArrayList<Object> y;
	public ArrayList<Object> z;
	JsonParser parser;
	String lastSRecordId = new String();
	String lastTRecordId = new String();

	LogHelper helper;
	Logger logger = Logger.getLogger(CenterServerDDO.class);

	public CenterServerDDO() {
		super();

		srtrDdo = new ArrayList<Object>();
		a = new ArrayList<Object>();
		b = new ArrayList<Object>();
		c = new ArrayList<Object>();
		d = new ArrayList<Object>();
		e = new ArrayList<Object>();
		f = new ArrayList<Object>();
		g = new ArrayList<Object>();
		h = new ArrayList<Object>();
		i = new ArrayList<Object>();
		j = new ArrayList<Object>();
		k = new ArrayList<Object>();
		l = new ArrayList<Object>();
		m = new ArrayList<Object>();
		n = new ArrayList<Object>();
		o = new ArrayList<Object>();
		p = new ArrayList<Object>();
		q = new ArrayList<Object>();
		r = new ArrayList<Object>();
		s = new ArrayList<Object>();
		t = new ArrayList<Object>();
		u = new ArrayList<Object>();
		v = new ArrayList<Object>();
		w = new ArrayList<Object>();
		x = new ArrayList<Object>();
		y = new ArrayList<Object>();
		z = new ArrayList<Object>();

		helper = new LogHelper();
		helper.setupLogFile("log/DDOServer.log");
		System.out.println("DDO Called");
		addDefaultRecords();
	}

	/**
	 * Method to add teacher/student object to the hashmap
	 * 
	 * @param obj
	 */
	public void addToMap(Object obj) {

		Student stud;
		Teacher teach;
		Character ch;

		if (obj instanceof Student) {
			stud = (Student) obj;
			ch = stud.getLname().toUpperCase().charAt(0);
		} else {
			teach = (Teacher) obj;
			ch = teach.getLname().toUpperCase().charAt(0);
		}
		switch (ch) {
		case 'A':
			a.add(obj);
			break;
		case 'B':
			b.add(obj);
			break;
		case 'C':
			c.add(obj);
			break;
		case 'D':
			d.add(obj);
			break;
		case 'E':
			e.add(obj);
			break;
		case 'F':
			f.add(obj);
			break;
		case 'G':
			g.add(obj);
			break;
		case 'H':
			h.add(obj);
			break;
		case 'I':
			i.add(obj);
			break;
		case 'J':
			j.add(obj);
			break;
		case 'K':
			k.add(obj);
			break;
		case 'L':
			l.add(obj);
			break;
		case 'M':
			m.add(obj);
			break;
		case 'N':
			n.add(obj);
			break;
		case 'O':
			o.add(obj);
			break;
		case 'P':
			p.add(obj);
			break;
		case 'Q':
			q.add(obj);
			break;
		case 'R':
			r.add(obj);
			break;
		case 'S':
			s.add(obj);
			break;
		case 'T':
			t.add(obj);
			break;
		case 'U':
			u.add(obj);
			break;
		case 'V':
			v.add(obj);
			break;
		case 'W':
			w.add(obj);
			break;
		case 'X':
			x.add(obj);
			break;
		case 'Y':
			y.add(obj);
			break;
		case 'Z':
			z.add(obj);
			break;
		default:
			break;
		}

		srtrRecords.put("A", a);
		srtrRecords.put("B", b);
		srtrRecords.put("C", c);
		srtrRecords.put("D", d);
		srtrRecords.put("E", e);
		srtrRecords.put("F", f);
		srtrRecords.put("G", g);
		srtrRecords.put("H", h);
		srtrRecords.put("I", i);
		srtrRecords.put("J", j);
		srtrRecords.put("K", k);
		srtrRecords.put("L", l);
		srtrRecords.put("M", m);
		srtrRecords.put("N", n);
		srtrRecords.put("O", o);
		srtrRecords.put("P", p);
		srtrRecords.put("Q", q);
		srtrRecords.put("R", r);
		srtrRecords.put("S", s);
		srtrRecords.put("T", t);
		srtrRecords.put("U", u);
		srtrRecords.put("V", v);
		srtrRecords.put("W", w);
		srtrRecords.put("X", x);
		srtrRecords.put("Y", y);
		srtrRecords.put("Z", z);
	}

	/**
	 * Method to add default records to the hashmap
	 */
	public void addDefaultRecords() {
		File student = new File("res/student.json");
		File teacher = new File("res/teacher.json");
		Reader reader;
		try {

			reader = new BufferedReader(new FileReader(student.getAbsolutePath()));
			JsonParser parser = new JsonParser();
			JsonArray array = parser.parse(reader).getAsJsonArray();

			if (array != null) {
				for (int i = 7; i < 10; i++) {
					JsonObject object = (JsonObject) array.get(i);
					Student s = new Student(object.get("fname").getAsString(), object.get("lname").getAsString(),
							object.get("coursesRegistered").getAsString(), object.get("status").getAsString(),
							object.get("statusDueDate").getAsString(), object.get("id").getAsString());
					srtrDdo.add(s);
					lastSRecordId = object.get("id").getAsString();
				}
			}

			reader = new BufferedReader(new FileReader(teacher.getAbsolutePath()));
			array = parser.parse(reader).getAsJsonArray();

			if (array != null) {
				for (int i = 7; i < 10; i++) {
					JsonObject object = (JsonObject) array.get(i);
					Teacher t = new Teacher(object.get("fname").getAsString(), object.get("lname").getAsString(),
							object.get("address").getAsString(), object.get("phone").getAsString(),
							object.get("specialization").getAsString(), object.get("location").getAsString(),
							object.get("id").getAsString());
					srtrDdo.add(t);
					lastTRecordId = object.get("id").getAsString();
				}
			}

			for (int ij = 0; ij < srtrDdo.size(); ij++) {
				addToMap(srtrDdo.get(ij));
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Boolean createTRecord(String managerID, String fname, String lastName, String address, String phone,
			String specialization, String location) {
		int id = Integer.parseInt(lastTRecordId.substring(3, 8));
		lastTRecordId = "DTR" + "" + ++id;
		Teacher t = new Teacher(fname, lastName, address, phone, specialization, location, lastTRecordId);
		logger.info(managerID + "| createTRecord method | Teacher information - [{" + fname + ", " + lastName + ", "
				+ address + ", " + phone + ", " + specialization + ", " + location + "}]");
		addToMap(t);
		logger.info(managerID + "| Teacher created successfully with id " + lastTRecordId);
		return true;
	}

	@Override
	public Boolean createSRecord(String managerID, String fname, String lastName, String courseRegistered,
			String status, String statusDate) {
		int id = Integer.parseInt(lastSRecordId.substring(3, 8));
		lastSRecordId = "DSR" + "" + ++id;
		Student s = new Student(fname, lastName, courseRegistered, status, statusDate, lastSRecordId);
		logger.info(managerID + "| createSRecord method | Student information - [{" + fname + ", " + lastName + ", "
				+ courseRegistered + ", " + status + ", " + statusDate + "}]");
		addToMap(s);
		logger.info(managerID + "| Student created successfully with id " + lastSRecordId);
		return true;
	}

	@Override
	public String getRecordCounts(String managerID) {
		System.out.println("DDO count Called");
		logger.info(managerID + "| Using getRecordCounts method.");
		int counter = 0;
		if (srtrRecords.size() > 0) {
			for (int i = 65; i < 91; i++) {
				String key = Character.toString((char) i);
				ArrayList<Object> array = srtrRecords.get(key);
				counter += array.size();
			}
			return "" + counter;
		} else {
			return "0";
		}
	}

	@Override
	public Boolean editRecord(String managerID, String recordID, String fieldName, String newValue) {
		Boolean result = false;
		logger.info(managerID + "| Using editRecord method. Record ID : " + recordID);
		synchronized (this) {
			if (recordID.substring(0, 3).equals("DSR")) {
				Student s;
				for (int i = 65; i < 91; i++) {
					String key = Character.toString((char) i);
					ArrayList<Object> array = srtrRecords.get(key);
					for (int j = 0; j < array.size(); j++) {
						if (array.get(j) instanceof Student) {
							s = (Student) array.get(j);
							if (s.getId().equals(recordID)) {
								System.out.println("Student found");
								logger.info(managerID + "| Record id " + recordID + " identified as a student.");
								result = true;
								if (fieldName.equals("status")) {
									if (newValue.equals("active") || newValue.equals("deactive")) {
										s.setStatus(newValue);
										logger.info(managerID + "| Record - " + recordID + " status changed to "
												+ newValue);
										result = true;
									} else {
										logger.info(managerID + "| Entered invalid status number.");
										result = false;
									}
								} else if (fieldName.equals("statusDueDate")) {
									Pattern pattern;
									Matcher matcher;
									String DATE_PATTERN = "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)";
									pattern = Pattern.compile(DATE_PATTERN);
									matcher = pattern.matcher(newValue);
									if (matcher.matches()) {
										s.setStatusDueDate(newValue);
										logger.info(managerID + "| Record - " + recordID + " status date changed to "
												+ newValue);
										result = true;
									} else {
										logger.info(managerID + "| Entered invalid date.");
										result = false;
									}
								} else if (fieldName.equals("coursesRegistered")) {
									s.setCoursesRegistered(newValue);
									logger.info(managerID + "| Record - " + recordID + " registered courses changed to "
											+ newValue);
									result = true;
								}
								return result;
							} else {
								result = false;
							}
						}
					}
				}
			} else if (recordID.substring(0, 3).equals("DTR")) {
				Teacher t;
				for (int i = 65; i < 91; i++) {
					String key = Character.toString((char) i);
					ArrayList<Object> array = srtrRecords.get(key);
					for (int j = 0; j < array.size(); j++) {
						if (array.get(j) instanceof Teacher) {
							t = (Teacher) array.get(j);
							if (t.getId().equals(recordID)) {
								// System.out.println("Teacher found");
								logger.info(managerID + "| Record id " + recordID + " identified as a teacher.");
								result = true;
								if (fieldName.equals("address")) {
									t.setAddress(newValue);
									logger.info(
											managerID + "| Record - " + recordID + " address changed to " + newValue);
									result = true;
								} else if (fieldName.equals("location")) {
									t.setLocation(newValue);
									logger.info(
											managerID + "| Record - " + recordID + " location changed to " + newValue);
									result = true;
								} else if (fieldName.equals("phone")) {
									t.setPhone(newValue);
									logger.info(managerID + "| Record - " + recordID + " phone number changed to "
											+ newValue);
									result = true;

								}
								return result;
							} else {
								System.out.println("hiii");
								result = false;
							}
						}

					}
				}

			} else {
				result = false;
			}
			if (!result) {
				logger.info(managerID + "| Record - " + recordID + " not found.");
				System.out.println("no record found");
				return result;
			} else {
				return result;
			}
		}
	}

	@Override
	public synchronized String transferRecord(String managerID, String recordID) {
		if (recordID.substring(0, 3).equals("DSR")) {
			Student s;
			String o = new String();
			o = null;
			for (int i = 65; i < 91; i++) {
				String key = Character.toString((char) i);
				ArrayList<Object> array = srtrRecords.get(key);
				System.out.println("Hello " + array.size());
				for (int j = 0; j < array.size(); j++) {
					if (array.get(j) instanceof Student) {
						s = (Student) array.get(j);
						if (s.getId().equals(recordID)) {
							o = s.getFname() + ":" + s.getLname() + ":" + s.getCoursesRegistered() + ":" + s.getStatus()
									+ ":" + s.getStatusDueDate();
							System.out.println(o);
							array.remove(j);
							System.out.println("here 2");
							return o;
						}
					}
				}
			}
		} else if (recordID.substring(0, 3).equals("DTR")) {
			Teacher t;
			String o = new String();
			for (int i = 65; i < 91; i++) {
				String key = Character.toString((char) i);
				ArrayList<Object> array = srtrRecords.get(key);
				for (int j = 0; j < array.size(); j++) {
					if (array.get(j) instanceof Teacher) {
						t = (Teacher) array.get(j);
						if (t.getId().equals(recordID)) {
							o = t.getFname() + ":" + t.getLname() + ":" + t.getAddress() + ":" + t.getPhone() + ":"
									+ t.getSpecialization() + ":" + t.getLocation();
							array.remove(j);
							return o;
						}
					}
				}
			}
		}

		return "khabar nai";
	}
}