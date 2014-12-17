package com.baristamatic.dao;

import java.net.UnknownHostException;

import com.baristamatic.domain.Drink;
import com.baristamatic.domain.InventoryEntry;
import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

/**
 * <p>
 * Title: MongoConnectionManager.java
 * </p>
 * <p>
 * Description:
 * 
 * </p>
 * <p>
 * Apr 27, 2012
 * </p>
 * 
 * @author tater
 *          
 * 
 *         Start MongoDB on windows command line:
 *         mongod --config C:\Tools\Mongo\mongodb-win32-x86_64-2.0.4\bin\mongodb.config
 * 
 *         Start Mongo shell: >mongo
 *         >use testhw
 *         >db.products.find()
 *         >db.orders.find()
 * 
 * 
 */
public class MongoConnectionManager {
   private static final MongoConnectionManager instance = new MongoConnectionManager();

   // The Datastore interface provides type-safe methods for accessing and storing your java objects in MongoDB.
   // It provides get/find/save/delete methods for working with your java objects.
   private Datastore db;

   public static final String DB_NAME = "testhw";

   private MongoConnectionManager() {
      try {
         Mongo mongo = new Mongo("localhost", 27017);
         //Mapping the classes at the beginning of your application is a good practice. It allows the system to validate your classes 
         //and prepare for storing the data, and retrieving it.
         db = new Morphia().map(Drink.class).map(InventoryEntry.class).createDatastore(mongo, DB_NAME);
         db.ensureIndexes(); //Creates indexes from @Index annotations in your entities. It will then synchronously create your indexes.
      } catch (UnknownHostException e) {
         e.printStackTrace();
      } catch (MongoException e) {
         e.printStackTrace();
      }
   }

   public static MongoConnectionManager instance() {
      return instance;
   }

   public Datastore getDb() {
      return db;
   }
}
