package com.example.cs360inventoryapp.fragments;

import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.cs360inventoryapp.data.AppDatabase;
import com.example.cs360inventoryapp.data.Item;
import com.example.cs360inventoryapp.data.ItemDao;
import com.example.cs360inventoryapp.ui.ItemAdapter;
import com.example.cs360inventoryapp.R;
import com.example.cs360inventoryapp.viewmodel.SharedViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    private ItemAdapter itemAdapter;
    NavController navController;
    private SharedViewModel sharedViewModel;
    AppDatabase db;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        db = AppDatabase.getInstance(requireContext());
        ItemDao itemDao = db.itemDao();

        insertAll(new Item("Apple" ,"A delicious red fruit.", 99, 1));
        sharedViewModel.addItems(itemDao.getAll());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        // Button buttonHomeToItemDetails = view.findViewById(R.id.buttonHomeToItemDetails);
        NavHostFragment navHostFragment = (NavHostFragment) requireActivity()
                .getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        assert navHostFragment != null;
        navController = navHostFragment.getNavController();

        // buttonHomeToItemDetails.setOnClickListener(v -> navController.navigate(R.id.action_homeFragment_to_itemDetails));
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewItems);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        LiveData<List<Item>> itemList = sharedViewModel.getItemList();
        itemAdapter = new ItemAdapter(itemList.getValue(), navController);
        recyclerView.setAdapter(itemAdapter);

        sharedViewModel.getItemList().observe(getViewLifecycleOwner(), items -> itemAdapter.notifyDataSetChanged());

        FloatingActionButton fab = requireActivity().findViewById(R.id.fabAddItem);
        fab.setVisibility(View.VISIBLE);
        fab.setOnClickListener(v -> {
            navController.navigate(R.id.action_homeFragment_to_addItemFragment);
        });

        if (!(ContextCompat.checkSelfPermission(requireActivity(), android.Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED)) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), android.Manifest.permission.SEND_SMS)) {
                Toast.makeText(requireActivity(), "SMS permission is required to send messages.", Toast.LENGTH_SHORT).show();
            }
            ActivityCompat.requestPermissions(requireActivity(), new String[]{android.Manifest.permission.SEND_SMS}, 100); // the sms permission code is 100
        }
    }
}