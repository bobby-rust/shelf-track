package com.example.cs360inventoryapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<List<Item>> itemList = new MutableLiveData<>(new ArrayList<>());


    public SharedViewModel() {
        this.addItem(new Item("Apple", "A delicious red fruit.", R.drawable.apple));
    }

    public LiveData<List<Item>> getItemList() {
        return this.itemList;
    }

    public void addItem(Item item) {
        List<Item> list = itemList.getValue();
        list.add(item);
        itemList.setValue(list);
    }

    public void removeItem(int position) {
        List<Item> list = itemList.getValue();
        list.remove(position);
        itemList.setValue(list);
    }
}
