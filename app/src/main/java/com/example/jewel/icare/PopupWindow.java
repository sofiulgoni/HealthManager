package com.example.jewel.icare;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by Jewel on 11/15/2015.
 */
public class PopupWindow {
    android.widget.PopupWindow addprofile;
    public void showPopup(Activity context){
        RelativeLayout viewgroup=(RelativeLayout) context.findViewById(R.id.popup);
        LayoutInflater layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View popupWindow=layoutInflater.inflate(R.layout.addprofile_layout,viewgroup);
        addprofile=new android.widget.PopupWindow(context);
        addprofile.setContentView(popupWindow);
        addprofile.setHeight(900);
        addprofile.setWidth(600);
        addprofile.setFocusable(false);
        addprofile.showAtLocation(popupWindow, Gravity.CENTER,0,0);
        EditText profileName=(EditText)popupWindow.findViewById(R.id.editprofilename);
        Button fromDisk=(Button)popupWindow.findViewById(R.id.file);
        final Button fromCamera=(Button)popupWindow.findViewById(R.id.camera);
        final Button save=(Button)popupWindow.findViewById(R.id.savebutton);
        final Button cancel=(Button)popupWindow.findViewById(R.id.canccelbutton);
        ImageView profilePicture=(ImageView)popupWindow.findViewById(R.id.profilepicture);
        profilePicture.setImageResource(R.drawable.nopreview);
        fromDisk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        fromCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addprofile.dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addprofile.dismiss();
            }
        });
    }
}
