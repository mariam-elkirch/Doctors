package com.mina.doctors_fadfadly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mina.doctors_fadfadly.R;
import com.squareup.picasso.Picasso;

public class Register2 extends AppCompatActivity {
  private  static  final  int pick_Img_Request=1;
         private Button mButtonchooseImg;
         private  Button mButtonChooseUpload;
         private TextView mTextViewShowUploads;
         private EditText mEditTextFileName;
         private ImageView mImageView;
         private ProgressBar mProgressBar;

         private Uri mImageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        mButtonchooseImg = findViewById(R.id.chooseImg);
        mButtonChooseUpload = findViewById(R.id.upload);
        mTextViewShowUploads = findViewById(R.id.text_view_show_uploads);
        mEditTextFileName = findViewById(R.id.edit);
        mImageView = findViewById(R.id.img);

        mButtonchooseImg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        mButtonChooseUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mTextViewShowUploads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, pick_Img_Request);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == pick_Img_Request && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            mImageUri = data.getData();

            Picasso.with(this).load(mImageUri).into(mImageView);
        }
    }
}