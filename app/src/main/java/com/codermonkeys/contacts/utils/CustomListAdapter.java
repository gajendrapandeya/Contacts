package com.codermonkeys.contacts.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.codermonkeys.contacts.R;
import com.codermonkeys.contacts.models.Contacts;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CustomListAdapter extends ArrayAdapter<Contacts> {

    private LayoutInflater mInflater;
    private List<Contacts> mContacts = null;
    private ArrayList<Contacts> arrayList; //used for search bar
    private int layoutResource;
    private Context mContext;
    private String mAppend;


    public CustomListAdapter(@NonNull Context context, int resource, List<Contacts> contacts, String append) {
        super(context, resource, contacts);

        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutResource = resource;
        mContext = context;
        mAppend = append;
        mContacts = contacts;
        arrayList = new ArrayList<>();
        this.arrayList.addAll(mContacts);

    }

    private static class ViewHolder{

        TextView name;
        CircleImageView contactImage;
        ProgressBar mProgressBar;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //ViewHolder Build pattern starts over here

        final ViewHolder holder;

        if(convertView == null) {
            convertView = mInflater.inflate(layoutResource, parent, false);
            holder = new ViewHolder();

            /*----------Stuffs change from here----------------------*/
            holder.name = convertView.findViewById(R.id.contact_name);
            holder.contactImage = convertView.findViewById(R.id.contact_image);
            holder.mProgressBar = convertView.findViewById(R.id.contact_progress_bar);

            //-----------------------------------------------------------------

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        /*----------Stuffs change from here----------------------*/
        String name = getItem(position).getName();
        String imagePath = getItem(position).getProfileImage();

        holder.name.setText(name);

        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(mAppend + imagePath, holder.contactImage, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                holder.mProgressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                holder.mProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                holder.mProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                holder.mProgressBar.setVisibility(View.GONE);
            }
        });

        //-----------------------------------------------------------------

        return convertView;
    }
}
