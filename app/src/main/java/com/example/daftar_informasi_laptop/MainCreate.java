package com.example.daftar_informasi_laptop;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;

public class MainCreate extends AppCompatActivity {
    private MyDatabase db;
    private ImageView Eimage;
    private EditText Enama, Etipe, Ewarna, Eharga;
    private String Snama, Stipe, Swarna, Sharga;
    private byte[] Simage;
    final int REQUEST_CODE_GALLERY = 999;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_create);
        db = new MyDatabase(this);
        Eimage = findViewById(R.id.iconid);
        Enama = (EditText) findViewById(R.id.create_nama);
        Etipe = (EditText) findViewById(R.id.create_tipe);
        Ewarna = (EditText) findViewById(R.id.create_warna);
        Eharga = (EditText) findViewById(R.id.create_harga);

        Eimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(
                        MainCreate.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );
            }
        });
        Button btnCreate = (Button) findViewById(R.id.create_btn);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Simage = imageViewToByte(Eimage);
                Snama = String.valueOf(Enama.getText());
                Stipe = String.valueOf(Etipe.getText());
                Swarna = String.valueOf(Ewarna.getText());
                Sharga = String.valueOf(Eharga.getText());
                if (Snama.equals("")){
                    Enama.requestFocus();
                    Toast.makeText(MainCreate.this, "Silahkan isi Nama Laptop",
                            Toast.LENGTH_SHORT).show();
                } else if (Stipe.equals("")){
                    Etipe.requestFocus();
                    Toast.makeText(MainCreate.this, "Silahkan isi Tipe",
                            Toast.LENGTH_SHORT).show();
                } else if (Swarna.equals("")){
                    Ewarna.requestFocus();
                    Toast.makeText(MainCreate.this, "Silahkan isi Warna",
                            Toast.LENGTH_SHORT).show();
                } else if (Sharga.equals("")){
                    Eharga.requestFocus();
                    Toast.makeText(MainCreate.this, "Silahkan isi Harga",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Eimage.setImageResource(R.drawable.ic_laptop);
                    Enama.setText("");
                    Etipe.setText("");
                    Ewarna.setText("");
                    Eharga.setText("");
                    Toast.makeText(MainCreate.this, "Data telah ditambah",
                            Toast.LENGTH_SHORT).show();
                    db.CreateLaptop(new Laptop(null,Simage, Snama, Stipe, Swarna, Sharga));
                    Intent a = new Intent(MainCreate.this, MainActivity.class);
                    startActivity(a);
                }
            }
        });
    }
    private byte[] imageViewToByte(ImageView mImageView) {
        Bitmap bitmap = ((BitmapDrawable) mImageView.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_GALLERY){
            if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //gallery intent
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, REQUEST_CODE_GALLERY);
            }
            else {
                Toast.makeText(this, "Don't have permission to access file location", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK){
            Uri imageUri = data.getData();
            CropImage.activity(imageUri)
                    .setGuidelines(CropImageView.Guidelines.ON) //enable image guidlines
                    .setAspectRatio(1,1)// image will be square
                    .start(this);
        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result =CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK){
                Uri resultUri = result.getUri();
                //set image choosed from gallery to image view
                Eimage.setImageURI(resultUri);
            }
            else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE){
                Exception error = result.getError();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}

