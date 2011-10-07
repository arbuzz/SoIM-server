package util;

import com.mongodb.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
public class MongoHelper {

    private static Logger logger = Logger.getLogger("mongo helper");

    private String host = "127.0.0.1";
    private int port = 27017;

    private final String dbName = "soim";
    private final String collName = "users";

    private final String USERNAME = "username";
    private final String PASSWORD = "password";
    private final String CONTACTS = "contacts";
    private final String CONTACT_NAME = "contact_name";

    private final String USERNAME_INDEX = "username_index";

    private Mongo mongo;

    private static MongoHelper instance;

    public MongoHelper() throws Exception {
        mongo = new Mongo(host, port);
        init();
    }

    public static MongoHelper getInstance() {
        if (instance == null)
            try {
                instance = new MongoHelper();
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Error while connecting to Mongo", e);
            }
        return instance;
    }

    public void init() {
        if (!mongo.getDatabaseNames().contains(dbName)) {
            logger.info("Database not exist. Creating...");
        }
        DB db = mongo.getDB(dbName);

        if (!db.collectionExists(collName)) {
            logger.info("Collection not exists. Creating...");
            DBCollection coll = db.getCollection(collName);
            BasicDBObject idx = new BasicDBObject();
            idx.append(USERNAME, 1);
            coll.ensureIndex(idx, USERNAME_INDEX, true);
        }
    }

    public boolean addNewUser(String login, String password) {
        DBCollection coll = mongo.getDB(dbName).getCollection(collName);
        BasicDBObject query = new BasicDBObject();
        query.append(USERNAME, login);

        BasicDBObject record = new BasicDBObject();
        record.append(USERNAME, login).append(PASSWORD, password);
        return coll.insert(record).getError() == null;
    }

    public boolean addContact(String user, String contact) {
        DBCollection coll = mongo.getDB(dbName).getCollection(collName);
        BasicDBObject query = new BasicDBObject();
        query.append(USERNAME, user);
        DBObject record;
        if ((record = coll.findOne(query)) == null)
            return false;

        if (record.get(CONTACTS) == null) {
            BasicDBList contacts = new BasicDBList();
            contacts.add(new BasicDBObject().append(CONTACT_NAME, contact));
            record.put(CONTACTS, contacts);
        } else {
            BasicDBList contacts = (BasicDBList) record.get(CONTACTS);
            if (!contacts.contains(new BasicDBObject().append(CONTACT_NAME, contact)))
                contacts.add(new BasicDBObject().append(CONTACT_NAME, contact));
            else
                return false;
        }
        coll.update(query, record);
        return true;
    }

    public List<String> getRoster(String user) {
        DBCollection coll = mongo.getDB(dbName).getCollection(collName);
        BasicDBObject query = new BasicDBObject();
        query.append(USERNAME, user);
        DBObject record;
        if ((record = coll.findOne(query)) == null)
            return null;
        BasicDBList contacts = (BasicDBList) record.get(CONTACTS);
        List<String> roster = new ArrayList<String>();
        for (Object contact : contacts) {
            roster.add((String) ((DBObject) contact).get(CONTACT_NAME));
        }
        return roster;
    }

    public boolean deleteContact(String user, String contactName) {
        DBCollection coll = mongo.getDB(dbName).getCollection(collName);
        BasicDBObject query = new BasicDBObject();
        query.append(USERNAME, user);
        DBObject record;
        if ((record = coll.findOne(query)) == null)
            return false;
        BasicDBList contacts = (BasicDBList) record.get(CONTACTS);
        for (int i = 0; i < contacts.size(); i++) {
            if (((DBObject) contacts.get(i)).get(CONTACT_NAME).equals(contactName)) {
                contacts.remove(i);
                coll.update(query, record);
                return true;
            }
        }
        return false;
    }
}