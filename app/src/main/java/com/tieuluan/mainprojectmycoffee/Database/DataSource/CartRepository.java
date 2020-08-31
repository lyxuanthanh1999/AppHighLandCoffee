package com.tieuluan.mainprojectmycoffee.Database.DataSource;

import com.tieuluan.mainprojectmycoffee.Database.ModelDB.Cart;

import java.util.List;

import io.reactivex.Flowable;

public class CartRepository implements ICartDataSource {
    private ICartDataSource iCartDataSource;

    public CartRepository(ICartDataSource iCartDataSource) {
        this.iCartDataSource = iCartDataSource;
    }


    private static CartRepository instance;

    public CartRepository() {
    }

    public static CartRepository getInstance(ICartDataSource iCartDataSource)
    {
        if(instance==null){

            instance = new CartRepository(iCartDataSource);
        }
        return instance;
    }



    @Override
    public Flowable<List<Cart>> getCartItems() {
        return iCartDataSource.getCartItems();
    }

    @Override
    public Flowable<List<Cart>> getCartItemsByID(int CartItemId) {
        return iCartDataSource.getCartItemsByID(CartItemId);
    }

    @Override
    public int CountCartItems() {
        return iCartDataSource.CountCartItems();
    }

    @Override
    public float sumPrices() {
        return iCartDataSource.sumPrices();
    }

    @Override
    public void insertToCart(Cart... carts) {
        iCartDataSource.insertToCart(carts);
    }

    @Override
    public void updateToCart(Cart... carts) {
        iCartDataSource.updateToCart(carts);
    }

    @Override
    public void DeleteCartItem(Cart cart) {
        iCartDataSource.DeleteCartItem(cart);
    }

    @Override
    public void emptyCart() {
        iCartDataSource.emptyCart();
    }
}
