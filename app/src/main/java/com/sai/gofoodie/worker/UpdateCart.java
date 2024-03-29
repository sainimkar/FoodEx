package com.sai.gofoodie.worker;

import android.os.AsyncTask;

import com.sai.gofoodie.db.AppDatabase;
import com.sai.gofoodie.model.CartItem;
import com.sai.gofoodie.model.FoodDetails;

public class UpdateCart extends AsyncTask<FoodDetails,Void,Void> {

    private AppDatabase db;
    public UpdateCart(AppDatabase db) {
        this.db = db;
    }

    @Override
    protected Void doInBackground(FoodDetails... foodDetails) {
        if(db!=null){
            if(foodDetails[0]!=null) {
                if (foodDetails[0].getQuantity() == 0) {
                    db.cartItemDao().deleteCartItem(foodDetails[0].getName());
                    return null;
                }
                CartItem cartItem = new CartItem();
                cartItem.setName(foodDetails[0].getName());
                cartItem.setPrice(foodDetails[0].getPrice());
                cartItem.setQuantity(foodDetails[0].getQuantity());
                db.cartItemDao().add(cartItem);
            }
        }
        return null;
    }

}
