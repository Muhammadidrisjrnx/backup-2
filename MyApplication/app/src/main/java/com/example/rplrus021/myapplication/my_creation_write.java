package com.example.rplrus021.myapplication;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.share.Share;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.UUID;

public class my_creation_write extends AppCompatActivity {
    EditText edt_text_my_creation;
    EditText edt_judul_my_creation;
    private ImageView imageView;
    FloatingActionButton fab_save;
    private Button btn_picture;
    private int camera = 1;
    private int galery = 2;
    private StorageReference storageReference;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_creation_write);
        edt_judul_my_creation = (EditText) findViewById(R.id.edt_judul_my_creation);
        edt_text_my_creation = (EditText) findViewById(R.id.edt_text_my_creation);
        fab_save = (FloatingActionButton) findViewById(R.id.fab_save);
        btn_picture = (Button) findViewById(R.id.btn_picture);
        imageView = (ImageView) findViewById(R.id.image_status);
        storageReference = FirebaseStorage.getInstance().getReference();
        btn_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(my_creation_write.this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{android.Manifest.permission.CAMERA},
                                5);
                    }
                }
                show_picture();
            }
        });
        fab_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imageView.getVisibility() == View.VISIBLE) {
                    upload();
                } else if (imageView.getVisibility() == View.INVISIBLE) {
                    String text = edt_text_my_creation.getText().toString();
                    String judul = edt_judul_my_creation.getText().toString();
                    if (judul.equals("")) {
                        edt_judul_my_creation.setError("Please Write Your Title Creation");
                    } else {
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference reference = database.getReference("my_creation");
                        reference.keepSynced(true);
                        String key = reference.push().getKey();
                        Log.d("key", "onClick: " + key);
                        DatabaseReference reference1 = reference.child(user.getUid()).child(key);
                        reference1.setValue(new my_creation_model(user.getEmail(), user.getDisplayName(), text, "", judul));
                        Intent intent = new Intent(getApplicationContext(), my_creation.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });
    }

    private void show_picture() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pick Picture");
        String[] picture_action = {"Camera", "Galery"};
        builder.setItems(picture_action, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i) {
                    case 0:
                        camera();
                        break;
                    case 1:
                        galery();
                        break;
                }
            }
        });
        builder.show();
    }

    private void upload() {
        if (uri != null) {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Upload");
            progressDialog.show();
            final StorageReference storageReference1 = storageReference.child("upload/" + UUID.randomUUID().toString());
            String storage = storageReference1.toString();
            Log.d("TAG", "image upload: " + storage);
            storageReference1.putFile(uri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Uri uri = taskSnapshot.getUploadSessionUri();
                            progressDialog.dismiss();
                            String text = edt_text_my_creation.getText().toString();
                            String judul = edt_judul_my_creation.getText().toString();
                            if (judul.equals("")) {
                                edt_judul_my_creation.setError("Please Write Your Title Creation");
                            } else {
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                DatabaseReference reference = database.getReference("my_creation");
                                reference.keepSynced(true);
                                String key = reference.push().getKey();
                                DatabaseReference reference1 = reference.child(user.getUid()).child(key);
                                reference1.setValue(new my_creation_model(user.getEmail(), user.getDisplayName(), text, storageReference1.toString(), judul));
                                Toast.makeText(getApplicationContext(), "File Upload", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), my_creation.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "Fail Upload", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnCanceledListener(new OnCanceledListener() {
                        @Override
                        public void onCanceled() {
                            Toast.makeText(getApplicationContext(), "Cancel Upload", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                            progressDialog.setTitle("Uploading " + ((int) progress) + " %");
                        }
                    });
        } else {
            Toast.makeText(getApplicationContext(), "PIL 1", Toast.LENGTH_SHORT).show();
        }
    }

    private void galery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, galery);

    }

    private void camera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, camera);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == camera && resultCode == RESULT_OK && data != null && data.getData() != null) {
            try {
                uri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(uri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                imageView.setVisibility(View.VISIBLE);
                Glide.with(this)
                        .load(selectedImage)
                        .into(imageView);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Fail", Toast.LENGTH_SHORT).show();
            }


        } else if (requestCode == galery && resultCode == RESULT_OK && data != null && data.getData() != null) {
            try {
                uri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(uri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                imageView.setVisibility(View.VISIBLE);
                Glide.with(this)
                        .load(selectedImage)
                        .into(imageView);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(my_creation_write.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }
        }

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
