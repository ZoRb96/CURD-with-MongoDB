package com.saurav.employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.saurav.mongoSetup.MongoSetup;

@Service("empdetails")
@Transactional
public class EmployeeDAO {

	static String db_name = "empDetails", db_collection = "employeeCollection";

	private static Logger log = Logger.getLogger(EmployeeDAO.class);
	
	public List<EmpDetails> getAll() {
		List<EmpDetails> empList = new ArrayList<EmpDetails>();
		DBCollection coll = MongoSetup.getCollection(db_name, db_collection);

		DBCursor cursor = coll.find();
		while (cursor.hasNext()) {
			DBObject dbObject = cursor.next();

			EmpDetails empDetails = new EmpDetails();
			empDetails.setEmpno(dbObject.get("empno").toString());
			empDetails.setName(dbObject.get("name").toString());
			empDetails.setPosition(dbObject.get("position").toString());
			empDetails.setSalary(dbObject.get("salary").toString());
			empDetails.setManagername(dbObject.get("managername").toString());
			empDetails.setDept(dbObject.get("dept").toString());

			empList.add(empDetails);
			
		}
		
		log.debug("Total records fetched from the mongo database are= " + empList.size());
		return empList;
	}

	
	public Boolean add(EmpDetails empDetails) {
		boolean output = false;
		Random ran = new Random();
		log.debug("Adding a new user to the mongo database; Entered user_name is= " + empDetails.getName());
		try {
			DBCollection coll = MongoSetup.getCollection(db_name, db_collection);

			BasicDBObject doc = new BasicDBObject();
			doc.put("empno", String.valueOf(ran.nextInt(1000)));
			doc.put("name", empDetails.getName());
			doc.put("position", empDetails.getPosition());
			doc.put("salary", empDetails.getSalary());
			doc.put("managername", empDetails.getManagername());
			doc.put("dept", empDetails.getDept());

			coll.insert(doc);
			output = true;
		} catch (Exception e) {
			output = false;
			log.error("An error occurred while saving a new user to the mongo database", e);
		}
		return output;
	}

	
	public Boolean edit(EmpDetails empDetails) {
		boolean output = false;
		log.debug("Updating the existing user in the mongo database; Entered user_id is= " + empDetails.getEmpno());
		try {
			
			BasicDBObject existing = (BasicDBObject) getDBObject(empDetails.getEmpno());

			DBCollection coll = MongoSetup.getCollection(db_name, db_collection);

			BasicDBObject obj = new BasicDBObject();
			obj.put("empno", empDetails.getEmpno());
			obj.put("name", empDetails.getName());
			obj.put("position", empDetails.getPosition());
			obj.put("salary", empDetails.getSalary());
			obj.put("managername", empDetails.getManagername());
			obj.put("dept", empDetails.getDept());

			coll.update(existing, obj);
			output = true;
		} catch (Exception e) {
			output = false;
			log.error("An error has occurred while updating an existing user to the mongo database", e);
		}
		return output;
	}


	public Boolean delete(String id) {
		boolean output = false;
		log.debug("Deleting an existing user from the mongo database; Entered user_id is= " + id);
		try {
			
			BasicDBObject item = (BasicDBObject) getDBObject(id);

			DBCollection coll = MongoSetup.getCollection(db_name, db_collection);

			coll.remove(item);
			output = true;
		} catch (Exception e) {
			output = false;
			log.error("An error occurred while deleting an existing user from the mongo database", e);
		}
		return output;
	}


	private DBObject getDBObject(String id) {
		DBCollection coll = MongoSetup.getCollection(db_name, db_collection);

		DBObject where_query = new BasicDBObject();

		where_query.put("empno", id);
		return coll.findOne(where_query);
	}

	
	public EmpDetails findUserId(String id) {
		EmpDetails empDetails = new EmpDetails();
		DBCollection coll = MongoSetup.getCollection(db_name, db_collection);

		DBObject where_query = new BasicDBObject();
		where_query.put("empno", id);

		DBObject dbo = coll.findOne(where_query);
		empDetails.setEmpno(dbo.get("empno").toString());
		empDetails.setName(dbo.get("name").toString());
		empDetails.setPosition(dbo.get("position").toString());
		empDetails.setSalary(dbo.get("salary").toString());
		empDetails.setManagername(dbo.get("managername").toString());
		empDetails.setDept(dbo.get("dept").toString());

		return empDetails;
	}

}
