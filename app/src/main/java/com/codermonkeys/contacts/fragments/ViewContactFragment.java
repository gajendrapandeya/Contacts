package com.codermonkeys.contacts.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.codermonkeys.contacts.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

public class ViewContactFragment extends Fragment {

    //Ui Component
    private AppBarLayout viewContactsBar, searchBar;


    //var's
    public static final int STANDARD_APPBAR = 0;
    public static final int SEARCH_APPBAR = 1;
    private int mAppBarState;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_viewcontacts, container, false);

        viewContactsBar = view.findViewById(R.id.view_contacts_toolbar_id);
        searchBar = view.findViewById(R.id.search_contacts_toolbar_id);

        setAppBarState(STANDARD_APPBAR);

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
}
