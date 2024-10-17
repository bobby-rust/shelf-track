package com.example.cs360inventoryapp.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.cs360inventoryapp.fragments.HomeFragment;

@Database(entities = {User.class, Item.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance = null;

    public AppDatabase() {};

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDatabase.class,
                    "inventory_database"
            ).build();
        }

        return instance;
    }

    public abstract UserDao userDao();
    public abstract ItemDao itemDao();
}
