package com.codermonkeys.contacts.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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

public class ContactPropertyListAdapter extends ArrayAdapter<String> {

    private LayoutInflater mInflater;
    private List<String > mProperties = null;
    private int layoutResource;
    private Context mContext;

    public ContactPropertyListAdapter(@NonNull Context context, int resource, List<String > properties) {
        super(context, resource, properties);

        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutResource = resource;
        mContext = context;
        mProperties = properties;



    }

    private static class ViewHolder{

        TextView property;
        ImageView rightIcon;
        ImageView leftIcon;

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
            holder.property = convertView.findViewById(R.id.tvMiddleCardView);
            holder.leftIcon = convertView.findViewById(R.id.iconLeftCardView);
            holder.rightIcon = convertView.findViewById(R.id.iconRightCardView);

            //-----------------------------------------------------------------

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        /*----------Stuffs change from here----------------------*/

        final String property = getItem(position);
        holder.property.setText(property);

        //if it's an email or phone number

        //email
        assert property != null;
        if(property.contains("@")) {
            holder.leftIcon.setImageResource(mContext.getResources().getIdentifier("@drawable/ic_email", null, mContext.getPackageName()));

        } else if(property.length() != 0 ) {

            holder.leftIcon.setImageResource(mContext.getResources().getIdentifier("@drawable/ic_phone", null, mContext.getPackageName()));
            holder.rightIcon.setImageResource(mContext.getResources().getIdentifier("@drawable/ic_message", null, mContext.getPackageName()));

        }

        //-----------------------------------------------------------------

        return convertView;
    }
}
