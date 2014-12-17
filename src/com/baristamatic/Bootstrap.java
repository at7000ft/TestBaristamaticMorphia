package com.baristamatic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baristamatic.dao.DrinkDao;
import com.baristamatic.dao.InventoryEntryDao;
import com.baristamatic.dao.MongoConnectionManager;
import com.baristamatic.domain.Drink;
import com.baristamatic.domain.InventoryEntry;
import com.google.code.morphia.annotations.Entity;
import com.google.common.collect.Lists;
import com.mongodb.Mongo;

/**
 * <p>
 * Title: Bootstrap.java
 * </p>
 * <p>
 * Description:
 * 
 * </p>
 * <p>
 * Nov 1, 2012
 * </p>
 * 
 * @author RGH
 * 
 * 
 */
public class Bootstrap {
   private static InventoryEntryDao idao = new InventoryEntryDao();
   private static DrinkDao ddao = new DrinkDao();

   public static void init() {
      drop();
      initInventoryEntryData();
      initDrinkData();
   }

   /**
    * Inits the data.
    */
   public static void initDrinkData() {
      Map<String, Integer> recipe = new HashMap<String, Integer>();
      // Caffe Americano
      recipe = new HashMap<String, Integer>();
      recipe.put("espresso", new Integer(3));
      Drink drink = new Drink(1, "caffeAmericano", "Caffe Americano", recipe);
      ddao.save(drink);
      // Caffe Latte
      recipe = new HashMap<String, Integer>();
      recipe.put("espresso", new Integer(2));
      recipe.put("steamedMilk", new Integer(1));
      drink = new Drink(2, "caffeLatte", "Caffe Latte", recipe);
      ddao.save(drink);
      // Caffe Mocha
      recipe = new HashMap<String, Integer>();
      recipe.put("espresso", new Integer(1));
      recipe.put("cocoa", new Integer(1));
      recipe.put("steamedMilk", new Integer(1));
      recipe.put("whippedCream", new Integer(1));
      drink = new Drink(3, "caffeMocha", "Caffe Mocha", recipe);
      ddao.save(drink);
      // Cappuccino
      recipe = new HashMap<String, Integer>();
      recipe.put("espresso", new Integer(2));
      recipe.put("steamedMilk", new Integer(1));
      recipe.put("foamedMilk", new Integer(1));
      drink = new Drink(4, "cappuccino", "Cappuccino", recipe);
      ddao.save(drink);
      // Coffee
      recipe = new HashMap<String, Integer>();
      recipe.put("coffee", new Integer(3));
      recipe.put("sugar", new Integer(1));
      recipe.put("cream", new Integer(1));
      drink = new Drink(5, "coffee", "Coffee", recipe);
      ddao.save(drink);
      // DecafCoffee
      recipe = new HashMap<String, Integer>();
      recipe.put("decafCoffee", new Integer(3));
      recipe.put("sugar", new Integer(1));
      recipe.put("cream", new Integer(1));
      drink = new Drink(6, "decafCoffee", "DecafCoffee", recipe);
      ddao.save(drink);
   }

   public static void initInventoryEntryData() {
      InventoryEntryDao dao = new InventoryEntryDao();
      InventoryEntry entry = new InventoryEntry("Cocoa", "cocoa", 90, 10);
      idao.save(entry);
      entry = new InventoryEntry("Coffee", "coffee", 75, 10);
      idao.save(entry);
      entry = new InventoryEntry("Cream", "cream", 25, 10);
      idao.save(entry);
      entry = new InventoryEntry("DecafCoffee", "decafCoffee", 75, 10);
      idao.save(entry);
      entry = new InventoryEntry("Espresso", "espresso", 110, 10);
      idao.save(entry);
      entry = new InventoryEntry("Foamed Milk", "foamedMilk", 35, 10);
      idao.save(entry);
      entry = new InventoryEntry("Steamed Milk", "steamedMilk", 35, 10);
      idao.save(entry);
      entry = new InventoryEntry("Sugar", "sugar", 25, 10);
      idao.save(entry);
      entry = new InventoryEntry("Whipped Cream", "whippedCream", 100, 10);
      idao.save(entry);
   }

   public static void drop() {
      // Class<?>[] classes = { Order.class, Product.class, Customer.class };
      Mongo mongo = MongoConnectionManager.instance().getDb().getMongo();
      @SuppressWarnings("unchecked")
      List<Class<?>> collections = Lists.newArrayList(Drink.class, InventoryEntry.class);
      // List<Class<?>> collections = new ArrayList<Class<?>>(Arrays.asList(classes));
      for (Class<?> collection : collections) {
         mongo.getDB(MongoConnectionManager.DB_NAME).getCollection(getCollectionName(collection)).drop();
      }
   }

   private static String getCollectionName(Class<?> clazz) {
      Entity a = clazz.getAnnotation(Entity.class);
      return a.value();
   }
}
