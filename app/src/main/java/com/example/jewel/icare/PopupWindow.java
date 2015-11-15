package com.example.jewel.icare;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;



public class PopupWindow extends MainActivity{
    android.widget.PopupWindow addprofile;
    ImageView profilePicture;
    public void showPopup(final Activity context){
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
        profilePicture=(ImageView)popupWindow.findViewById(R.id.profilepicture);
        profilePicture.setImageResource(R.drawable.nopreview);
        fromDisk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fileIntent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                fileIntent.setType("image/*");
                context.startActivityForResult(Intent.createChooser(fileIntent,"select file"),2);

            }
        });
        fromCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if ((cameraIntent.resolveActivity(context.getPackageManager()))!=null) {
                    context.startActivityForResult(cameraIntent, 1);
                }
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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode==1) {
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                profilePicture.setImageBitmap(imageBitmap);
            }else if (requestCode==2){
                Uri selectedImageUri = data.getData();
                String[] projection = { MediaStore.MediaColumns.DATA };
                Cursor cursor = managedQuery(selectedImageUri, projection, null, null,
                        null);
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
                cursor.moveToFirst();
                String selectedImagePath = cursor.getString(column_index);
                Bitmap bm;
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(selectedImagePath, options);
                final int REQUIRED_SIZE = 200;
                int scale = 1;
                while (options.outWidth / scale / 2 >= REQUIRED_SIZE
                        && options.outHeight / scale / 2 >= REQUIRED_SIZE)
                    scale *= 2;
                options.inSampleSize = scale;
                options.inJustDecodeBounds = false;

                bm=BitmapFactory.decodeFile(selectedImagePath,options);
                profilePicture.setImageBitmap(bm);

            }

        }
    }
}
