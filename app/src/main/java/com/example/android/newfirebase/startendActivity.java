package com.example.android.newfirebase;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class startendActivity extends AppCompatActivity {


    private static final String TAG = "startendActivity";
    private static final int PICK_IMAGE_REQUEST = 234;
    EditText startpoint;
    EditText endpoint;
    EditText date;
    EditText comment;
    EditText fav;
    Button addbtn;
    Button choosebtn;

    ImageView imageView;

    private Uri filePath;
    private StorageReference storageReference;


    DatabaseReference databaseTripDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startend);


        storageReference = FirebaseStorage.getInstance().getReference();
        databaseTripDetail = FirebaseDatabase.getInstance().getReference("tripdetail");

        choosebtn = (Button) findViewById(R.id.choosebtn);
        imageView = (ImageView) findViewById(R.id.imageView);
        startpoint = (EditText) findViewById(R.id.startpoint);
        endpoint = (EditText) findViewById(R.id.endpoint);
        date = (EditText) findViewById(R.id.date);
        comment = (EditText) findViewById(R.id.comment);
        addbtn = (Button) findViewById(R.id.addbtn);
        fav = (EditText) findViewById(R.id.fav);


        choosebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFileChooser();
            }
        });


        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                addDetails();
                uplaodFile();


            }
        });

    }

    Uri downloadUrl;

    private void uplaodFile() {

        if (filePath != null) {

            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();


            // images/ user1 / - image1,image2.....
            StorageReference riversRef = storageReference.child("images/"
                    + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/" + System.currentTimeMillis() + ".jpg");

            riversRef.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            downloadUrl = taskSnapshot.getDownloadUrl();
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "File Uplaoded", Toast.LENGTH_LONG).show();
                            addDetails();

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {

                            progressDialog.dismiss();


                            Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                            double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                            progressDialog.setMessage(((int) progress) + "% Uploaded...");
                        }
                    })

            ;
        } else {

            //make toast
        }

    }

    private void showFileChooser() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select an Image"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            filePath = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void addDetails() {

        String startp = startpoint.getText().toString().trim();
        String endp = endpoint.getText().toString().trim();
        String dat = date.getText().toString().trim();
        String cmnt = comment.getText().toString().trim();
//        String fav= fav.getText().toString


        if (!TextUtils.isEmpty(startp) && !TextUtils.isEmpty(endp) && !TextUtils.isEmpty(dat) && !TextUtils.isEmpty(cmnt)) {

            String id = databaseTripDetail.push().getKey();

            TripDetail tripDetail = new TripDetail(id, startp, endp, dat, cmnt, downloadUrl.toString(), true);

            databaseTripDetail.child(id).setValue(tripDetail);

            Toast.makeText(this, "Data Saved", Toast.LENGTH_LONG).show();
        } else {

            Toast.makeText(this, "You should enter Start Place", Toast.LENGTH_LONG).show();

        }
    }


}

