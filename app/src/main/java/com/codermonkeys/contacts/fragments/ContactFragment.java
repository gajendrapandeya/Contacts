package com.codermonkeys.contacts.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.codermonkeys.contacts.R;

import java.util.Objects;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class ContactFragment extends Fragment {

    //widgets
    private Toolbar toolbar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        toolbar = view.findViewById(R.id.contact_toolbar);

        ((AppCompatActivity) Objects.requireNonNull(getActivity())).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);

        //navigation for the back arrow
        ImageView ivBackArrow = view.findViewById(R.id.image_view_contact_back_arrow);
        ivBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //remove previous fragment from the backStack (therefore navigating back)
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().popBackStack();

            }
        });

        //navigate to edit contact fragment to edit the contact selected
        ImageView ivEdit = view.findViewById(R.id.image_view_edit);
        ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditContactFragment fragment = new EditContactFragment();

                FragmentTransaction transaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                //replace whatever is in the fragment container view with this fragment
                //and add the transaction to the back stack so that the user can navigate back
                transaction.replace(R.id.fragment_container, fragment);
                transaction.addToBackStack(getResources().getString(R.string.edit_contact_fragment));
                transaction.commit();
            }
        });

        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.contact_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menuitem_delete) {
            Log.d("ContactFragment", "onOptionsItemSelected: deleted");
        }
        return super.onOptionsItemSelected(item);
    }
}
