package com.codermonkeys.contacts.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.codermonkeys.contacts.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ViewContactFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_viewcontacts, container, false);

        FloatingActionButton floatingActionButton = view.findViewById(R.id.fab_add_contacts);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //navigate to add contact fragments
            }
        });

        ImageView imageView = view.findViewById(R.id.image_view_search_icon);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open the search bar
            }
        });

        return view;

    }
}
