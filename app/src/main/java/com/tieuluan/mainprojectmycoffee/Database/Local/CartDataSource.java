package com.tieuluan.mainprojectmycoffee.Database.Local;

import com.tieuluan.mainprojectmycoffee.Database.DataSource.ICartDataSource;
import com.tieuluan.mainprojectmycoffee.Database.ModelDB.Cart;

import java.util.List;

import io.reactivex.Flowable;

public class CartDataSource implements ICartDataSource {

    private CartDAO cartDAO;
    private static CartDataSource instance;

    public CartDataSource(CartDAO cartDAO) {
        this.cartDAO = cartDAO;
    }

    public static CartDataSource getInstance(CartDAO cartDAO)
    {
        if(instance==null)
            instance=new CartDataSource(cartDAO);

            return instance;

    }
    @Override
    public Flowable<List<Cart>> getCartItems() {
        return cartDAO.getCartItems();
    }

    @Override
    public Flowable<List<Cart>> getCartItemsByID(int CartItemId) {
        return cartDAO.getCartItemsByID(CartItemId);
    }

    @Override
    public int CountCartItems() {
        return cartDAO.CountCartItems();
    }

    @Override
    public float sumPrices() {
        return cartDAO.sumPrices();
    }


    @Override
    public void insertToCart(Cart... carts) {
        cartDAO.insertToCart(carts);
    }

    @Override
    public void updateToCart(Cart... carts) {
        cartDAO.updateToCart(carts);
    }

    @Override
    public void DeleteCartItem(Cart cart) {
        cartDAO.DeleteCartItem(cart);
    }

    @Override
    public void emptyCart() {
        cartDAO.emptyCart();
    }
}
