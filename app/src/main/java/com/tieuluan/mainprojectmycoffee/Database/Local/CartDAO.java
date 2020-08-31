package com.tieuluan.mainprojectmycoffee.Database.Local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.tieuluan.mainprojectmycoffee.Database.ModelDB.Cart;

import java.util.List;

import io.reactivex.Flowable;

//=======================================================
//CartDAO:Interface định nghĩa các câu truy vấn Database

@Dao
public interface CartDAO {
    @Query("SELECT * FROM Cart")
    Flowable<List<Cart>> getCartItems();

    @Query("SELECT * FROM Cart")
    List<Cart> getAllCartItems();

    @Query("SELECT * FROM Cart WHERE id =:CartItemId")
    Flowable<List<Cart>> getCartItemsByID(int CartItemId);

    @Query("SELECT Count(*) FROM Cart")
    int CountCartItems();

    @Query("SELECT Sum(Price) FROM Cart")
    float sumPrices();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertToCart(Cart...carts);

    @Update
    void updateToCart(Cart...carts);

    @Delete
    void DeleteCartItem(Cart cart);

    @Query("DELETE FROM Cart")
    void emptyCart();

}
