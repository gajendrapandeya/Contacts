package com.codermonkeys.contacts;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import com.codermonkeys.contacts.fragments.ContactFragment;
import com.codermonkeys.contacts.fragments.ViewContactFragment;
import com.codermonkeys.contacts.models.Contacts;
import com.codermonkeys.contacts.utils.UniversalImageLoader;
import com.nostra13.universalimageloader.core.ImageLoader;

public class MainActivity extends AppCompatActivity implements ViewContactFragment.onContactSelectedListner {

    //Ui's
    Toolbar mToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initImageLoader();
        init();
    }

    //initialize the first fragment (ViewContactFragment)
    private void init() {

        ViewContactFragment fragment = new ViewContactFragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //replace whatever is in the fragment container view with this fragment
        //and add the transaction to the back stack so that the user can navigate back
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void initImageLoader() {
        UniversalImageLoader imageLoader = new UniversalImageLoader(this);
        ImageLoader.getInstance().init(imageLoader.getConfig());
    }

    @Override
    public void onContactSelected(Contacts contacts) {
        ContactFragment fragment = new ContactFragment();
        Bundle args = new Bundle();
        args.putParcelable("Contact", contacts);
        fragment.setArguments(args);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(getResources().getString(R.string.view_contact_fragment));
        transaction.commit();
    }
}
