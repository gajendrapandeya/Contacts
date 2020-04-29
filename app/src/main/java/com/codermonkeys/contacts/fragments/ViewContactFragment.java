package com.codermonkeys.contacts.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.codermonkeys.contacts.R;
import com.codermonkeys.contacts.models.Contacts;
import com.codermonkeys.contacts.utils.CustomListAdapter;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Objects;

public class ViewContactFragment extends Fragment {

    private static final String TAG = "ViewContactFragment";
    //Ui Component
    private AppBarLayout viewContactsBar, searchBar;
    private ListView contactList;


    //var's
    public static final int STANDARD_APPBAR = 0;
    public static final int SEARCH_APPBAR = 1;
    private int mAppBarState;

    private String testImageURL = "tse3.mm.bing.net/th?id=OIP.ghrb7on_QV8B6DP0S34KxQHaEc&pid=Api&P=0&w=297&h=179";
    private CustomListAdapter mListAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_viewcontacts, container, false);

        viewContactsBar = view.findViewById(R.id.view_contacts_toolbar_id);
        searchBar = view.findViewById(R.id.search_contacts_toolbar_id);
        contactList = view.findViewById(R.id.contacts_list);

        setAppBarState(STANDARD_APPBAR);
        setupContacts();

        FloatingActionButton floatingActionButton = view.findViewById(R.id.fab_add_contacts);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //navigate to add contact fragments
            }
        });

        ImageView ivSearchContact = view.findViewById(R.id.image_view_search_icon);
        ivSearchContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open the search bar
                toogleToolbarState();
            }
        });

        ImageView ivBackArrow = view.findViewById(R.id.image_view_back_arrow);
        ivBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               toogleToolbarState();
            }
        });

        return view;

    }

    //Initiates the appbar state toggle
    private void toogleToolbarState() {

        if(mAppBarState == SEARCH_APPBAR) {
            setAppBarState(STANDARD_APPBAR);
        } else {
            setAppBarState(SEARCH_APPBAR);
        }
    }

    // Set the appbar state to either the 'search mode' or standard mode
    private void setAppBarState(int state) {

        mAppBarState = state;

        if(mAppBarState == SEARCH_APPBAR) {
            viewContactsBar.setVisibility(View.GONE);
            searchBar.setVisibility(View.VISIBLE);

            //making the soft keyboard visible
            InputMethodManager imm = (InputMethodManager) Objects.requireNonNull(getActivity()).getSystemService(Context.INPUT_METHOD_SERVICE);
            assert imm != null;
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);



        } else if(mAppBarState == STANDARD_APPBAR) {

            viewContactsBar.setVisibility(View.VISIBLE);
            searchBar.setVisibility(View.GONE);

            //making the soft keyboard invisible
            View view = getView();
            InputMethodManager imm = (InputMethodManager) Objects.requireNonNull(getActivity()).getSystemService(Context.INPUT_METHOD_SERVICE);
            try {
                assert imm != null;
                assert view != null;
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            } catch(Exception ignored) {

            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        setAppBarState(STANDARD_APPBAR);
    }

    private void setupContacts() {
        final ArrayList<Contacts> contacts = new ArrayList<>();
        contacts.add(new Contacts("Gajendra Pandeya", "(977)9812610150", "mobile", "gajendrapandeya6@gmail.com", testImageURL));
        contacts.add(new Contacts("Gajendra Pandeya", "(977)9812610150", "mobile", "gajendrapandeya6@gmail.com", testImageURL));
        contacts.add(new Contacts("Gajendra Pandeya", "(977)9812610150", "mobile", "gajendrapandeya6@gmail.com", testImageURL));
        contacts.add(new Contacts("Gajendra Pandeya", "(977)9812610150", "mobile", "gajendrapandeya6@gmail.com", testImageURL));
        contacts.add(new Contacts("Gajendra Pandeya", "(977)9812610150", "mobile", "gajendrapandeya6@gmail.com", testImageURL));
        contacts.add(new Contacts("Gajendra Pandeya", "(977)9812610150", "mobile", "gajendrapandeya6@gmail.com", testImageURL));
        contacts.add(new Contacts("Gajendra Pandeya", "(977)9812610150", "mobile", "gajendrapandeya6@gmail.com", testImageURL));
        contacts.add(new Contacts("Gajendra Pandeya", "(977)9812610150", "mobile", "gajendrapandeya6@gmail.com", testImageURL));
        contacts.add(new Contacts("Gajendra Pandeya", "(977)9812610150", "mobile", "gajendrapandeya6@gmail.com", testImageURL));
        contacts.add(new Contacts("Gajendra Pandeya", "(977)9812610150", "mobile", "gajendrapandeya6@gmail.com", testImageURL));
        contacts.add(new Contacts("Gajendra Pandeya", "(977)9812610150", "mobile", "gajendrapandeya6@gmail.com", testImageURL));
        contacts.add(new Contacts("Gajendra Pandeya", "(977)9812610150", "mobile", "gajendrapandeya6@gmail.com", testImageURL));
        contacts.add(new Contacts("Gajendra Pandeya", "(977)9812610150", "mobile", "gajendrapandeya6@gmail.com", testImageURL));
        contacts.add(new Contacts("Gajendra Pandeya", "(977)9812610150", "mobile", "gajendrapandeya6@gmail.com", testImageURL));

        mListAdapter = new CustomListAdapter(Objects.requireNonNull(getActivity()), R.layout.layout_contactlistitem, contacts, "https://");
        contactList.setAdapter(mListAdapter);

        contactList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onClick: ");

                ContactFragment fragment = new ContactFragment();

                FragmentTransaction transaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                //replace whatever is in the fragment container view with this fragment
                //and add the transaction to the back stack so that the user can navigate back
                transaction.replace(R.id.fragment_container, fragment);
                transaction.addToBackStack(getResources().getString(R.string.edit_contact_fragment));
                transaction.commit();
            }
        });
    }
}
