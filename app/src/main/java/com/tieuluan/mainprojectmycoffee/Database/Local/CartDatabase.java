package com.tieuluan.mainprojectmycoffee.Database.Local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.tieuluan.mainprojectmycoffee.Database.ModelDB.Cart;

//=======================================================
//CartDatabase: Class extend từ RoomDatabase nơi trực tiếp và thực hiện các câu truy vấn xuống database

@Database(entities =  {Cart.class},version = 1,exportSchema = false)
public abstract class CartDatabase extends RoomDatabase {

    public abstract CartDAO cartDAO();
    private static CartDatabase instance;

    public static CartDatabase getInstance(Context context)
    {
        if(instance == null)
        {
            instance=Room.databaseBuilder(context,CartDatabase.class,"CoffeeHighLandDB")
                    .allowMainThreadQueries().build();
        }
        return  instance;
    }



}
