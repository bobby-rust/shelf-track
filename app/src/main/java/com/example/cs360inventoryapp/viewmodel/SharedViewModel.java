package com.example.cs360inventoryapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cs360inventoryapp.data.AppDatabase;
import com.example.cs360inventoryapp.data.Item;
import com.example.cs360inventoryapp.R;

import java.util.ArrayList;
import java.util.List;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<List<Item>> itemList = new MutableLiveData<>(new ArrayList<>());

    public SharedViewModel() {
    }

    public LiveData<List<Item>> getItemList() {
        return this.itemList;
    }

    public void addItem(Item item) {
        List<Item> list = itemList.getValue();
        list.add(item);
        itemList.setValue(list);
    }
    
    public void addItems(List<Item> items) {
        for (Item item : items) {
            addItem(item);
        }
    }

    public void removeItem(int position) {
        List<Item> list = itemList.getValue();
        list.remove(position);
        itemList.setValue(list);
    }
}
