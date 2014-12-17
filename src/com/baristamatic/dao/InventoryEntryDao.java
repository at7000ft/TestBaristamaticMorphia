package com.baristamatic.dao;

import java.util.List;

import org.bson.types.ObjectId;

import com.baristamatic.domain.InventoryEntry;
import com.google.code.morphia.dao.BasicDAO;

/**
 * <p> Title: InventoryEntryDao.java </p>
 * <p> Description:   
 *
 * </p>
 * <p> Nov 1, 2012</p>
 * @author RGH
 *
 *
 */
public class InventoryEntryDao extends BasicDAO<InventoryEntry, ObjectId> implements IInventoryEntryDao {
   
 
   /**
    * Instantiates a new inventory entry dao.
    *
    */
   public InventoryEntryDao() {
      super(InventoryEntry.class, MongoConnectionManager.instance().getDb());
   }

   /* 
    * @see com.baristamatic.dao.IInventoryEntryDao#saveInventoryEntry(com.baristamatic.domain.InventoryEntry)
    */
   public void saveInventoryEntry(InventoryEntry entry) {
      super.save(entry);
   }
   
   /* 
    * @see com.baristamatic.dao.IInventoryEntryDao#getByName(java.lang.String)
    */
   public InventoryEntry getByName(String name) {
      return createQuery().field("name").equal(name).asList().get(0);
   }

   /* 
    * @see com.baristamatic.dao.IInventoryEntryDao#findAll()
    */
   public List<InventoryEntry> findAll() {
      return find().asList();
   }
}
