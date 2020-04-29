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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.codermonkeys.contacts.R;
import com.codermonkeys.contacts.models.Contacts;
import com.codermonkeys.contacts.utils.UniversalImageLoader;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class ContactFragment extends Fragment {


    //This will evade the null pointer exception when adding to a new bundle from MainActivity
    public ContactFragment() {
        super();
        setArguments(new Bundle());
    }

    //widgets
    private Toolbar toolbar;
    private TextView mContactName;
    private CircleImageView mContactImage;

    //vars
    private Contacts mContact;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        toolbar = view.findViewById(R.id.contact_toolbar);
        mContact  = getContactFromBundle();
        mContactImage = view.findViewById(R.id.contactImage);
        mContactName = view.findViewById(R.id.tvName);

        ((AppCompatActivity) Objects.requireNonNull(getActivity())).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);

        init();

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

            }
        });

        return view;
    }

    private void init() {
        mContactName.setText(mContact.getName());

        UniversalImageLoader.setImage(mContact.getProfileImage(), mContactImage, null, "https://");
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

    private Contacts getContactFromBundle() {

        Bundle bundle = this.getArguments();
        if(bundle != null) {
            return  bundle.getParcelable("Contact");
        } else {
            return null;
        }
    }
}
