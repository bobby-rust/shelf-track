package com.example.cs360inventoryapp.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.cs360inventoryapp.data.Item;
import com.example.cs360inventoryapp.ui.ItemAdapter;
import com.example.cs360inventoryapp.R;
import com.example.cs360inventoryapp.viewmodel.SharedViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddItemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddItemFragment extends Fragment {

    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageView imageViewSelected;
    private Uri imageUri;
    private List<Item> itemList;
    private ItemAdapter itemAdapter;
    HomeFragment homeFragment;
    private SharedViewModel sharedViewModel;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddItemFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddItemFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddItemFragment newInstance(String param1, String param2) {
        AddItemFragment fragment = new AddItemFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_add_item, container, false);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageViewSelected = view.findViewById(R.id.imageViewSelected);
        MaterialButton selectImageButton = view.findViewById(R.id.buttonSelectImage);
        selectImageButton.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent, PICK_IMAGE_REQUEST);
        });

        MaterialButton addButton = view.findViewById(R.id.buttonAddItem);
        addButton.setOnClickListener(v -> {
            TextInputLayout nameInput = view.findViewById(R.id.editTextItemName);
            TextInputLayout descriptionInput = view.findViewById(R.id.editTextItemDescription);

            String itemName = nameInput.getEditText().getText().toString().trim();
            String itemDescription = descriptionInput.getEditText().getText().toString().trim();

            if (itemName.length() > 0 && itemDescription.length() > 0) {
                // TODO: allow actual image selection
                Item item = new Item(itemName, itemDescription, 99, 1);
                sharedViewModel.addItem(item);

                NavController navController = Navigation.findNavController(view);
                navController.popBackStack();
            } else {
                Toast.makeText(getContext(), "All fields are required.", Toast.LENGTH_SHORT).show();
            }
        });


    }
}