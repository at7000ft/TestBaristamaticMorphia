package com.baristamatic.dao;

import java.util.List;

import org.bson.types.ObjectId;

import com.baristamatic.domain.Drink;
import com.google.code.morphia.dao.BasicDAO;

/**
 * <p> Title: DrinkDao.java </p>
 * <p> Description:   
 *
 * </p>
 * <p> Nov 1, 2012</p>
 * @author RGH
 *
 *
 */
public class DrinkDao extends BasicDAO<Drink, ObjectId> implements IDrinkDao {

   /**
    * Instantiates a new drink dao.
    *
    */
   public DrinkDao() {
      super(Drink.class, MongoConnectionManager.instance().getDb());
       
   }

   /* 
    * @see com.baristamatic.dao.IDrinkDao#saveDrink(com.baristamatic.domain.Drink)
    */
   public void saveDrink(Drink drink) {
      super.save(drink);
   }
   
   /* 
    * @see com.baristamatic.dao.IDrinkDao#findAll()
    */
   public List<Drink> findAll() {
      return super.find().asList();
   }
   
   /* 
    * @see com.baristamatic.dao.IDrinkDao#getDrink(java.lang.Integer)
    */
   public Drink getDrink(Integer num) {
      return createQuery().field("number").equal(num).asList().get(0);
   }
}
