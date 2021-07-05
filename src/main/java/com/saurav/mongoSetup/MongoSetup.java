package com.saurav.mongoSetup;

import org.apache.log4j.Logger;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

@SuppressWarnings("deprecation")
public class MongoSetup {

	private static Logger log = Logger.getLogger(MongoSetup.class);
	 
    private static MongoClient mongo;
 
    private MongoSetup() {
		// TODO Auto-generated constructor stub
	}
 
    // Returns a mongo instance.
    public static MongoClient getMongo() {
        int portNo = 27017;
        String host = "localhost";      
        if (mongo == null) {
            try {
                mongo = new MongoClient(host, portNo);                                                                       
            } catch (MongoException ex) {
                log.error(ex);
            }
        }
        return mongo;
    }
 
    // Fetches the mongo database.
	public static DB getDB(String db_name) {        
        return getMongo().getDB(db_name);
    }
 
    // Fetches the collection from the mongo database.
    public static DBCollection getCollection(String db_name, String db_collection) {
        return getDB(db_name).getCollection(db_collection);
    }
	
}
