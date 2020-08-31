package com.tieuluan.mainprojectmycoffee.Database.DataSource;

import com.tieuluan.mainprojectmycoffee.Database.ModelDB.Cart;

import java.util.List;

import io.reactivex.Flowable;

public interface ICartDataSource {

    Flowable<List<Cart>> getCartItems();
    Flowable<List<Cart>> getCartItemsByID(int CartItemId);
    int CountCartItems();
    float sumPrices();
    void insertToCart(Cart...carts);
    void updateToCart(Cart...carts);
    void DeleteCartItem(Cart cart);
    void emptyCart();
}
