package com.example.testing;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.WallpaperManager;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import android.graphics.Color;
import android.os.*;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.util.*;
import android.graphics.Bitmap.Config;

import android.graphics.drawable.Drawable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.testing.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class UpdateInformation extends AppCompatActivity {

    EditText etInput;
    Button btGenerate;
    ImageView ivOutput;
    Button openScanner;
    // Declare the View object references
    Button buttonSave;
    EditText etName;
    EditText etSex;
    EditText etDOB;
    EditText etMedical;
    EditText etContact1Name;
    EditText etContact1Number;
    EditText etContact2Name;
    EditText etContact2Number;
    EditText etContact3Name;
    EditText etContact3Number;

    // Define some String variables, initialized with empty string
    String filename = "";
    String filepath = "";
    String fileContent = "";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_information);

        //Variables for Input Form
        etName = findViewById(R.id.etName);
        etSex = findViewById(R.id.etSex);
        etDOB = findViewById(R.id.etDOB);
        etMedical = findViewById(R.id.etMedical);
        etContact1Name = findViewById(R.id.etContact1Name);
        etContact1Number = findViewById(R.id.etContact1Number);
        etContact2Name = findViewById(R.id.etContact2Name);
        etContact2Number = findViewById(R.id.etContact2Number);
        etContact3Name = findViewById(R.id.etContact3Name);
        etContact3Number = findViewById(R.id.etContact3Number);

        // Get handles for the views
        buttonSave = findViewById(R.id.buttonSave);
//        tvLoad = findViewById(R.id.tvLoad);
        // Initialize two String variables for storing filename and filepath
        filename = "myFile.txt";
        filepath = "MyFileDir";

        //Clears Wall Paper
        setterWallpaper();

        //Sets Wall Paper
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                setWallpaper();
            }
        }, 30000);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the input from EditText
                fileContent = etName.getText().toString().trim() + " | " + etSex.getText().toString().trim() + " | " +
                        etDOB.getText().toString().trim() + " | " + etMedical.getText().toString().trim() + " | " +
                        etContact1Name.getText().toString().trim() + " | " + etContact1Number.getText().toString().trim()  + " | " +
                        etContact2Name.getText().toString().trim() + " | " + etContact2Number.getText().toString().trim()  + " | " +
                        etContact3Name.getText().toString().trim() + " | " + etContact3Number.getText().toString().trim();

                etName.setText("");
                etName.setBackgroundColor(Color.GREEN);

                etSex.setText("");
                etSex.setBackgroundColor(Color.GREEN);

                etDOB.setText("");
                etDOB.setBackgroundColor(Color.GREEN);

                etMedical.setText("");
                etMedical.setBackgroundColor(Color.GREEN);

                etContact1Name.setText("");
                etContact1Name.setBackgroundColor(Color.GREEN);

                etContact1Number.setText("");
                etContact1Number.setBackgroundColor(Color.GREEN);

                etContact2Name.setText("");
                etContact2Name.setBackgroundColor(Color.GREEN);

                etContact2Number.setText("");
                etContact2Number.setBackgroundColor(Color.GREEN);

                etContact3Name.setText("");
                etContact3Name.setBackgroundColor(Color.GREEN);

                etContact3Number.setText("");
                etContact3Number.setBackgroundColor(Color.GREEN);

                Toast.makeText(UpdateInformation.this, "Information saved.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Overlay images

    private Bitmap overlay(Bitmap bmp1, Bitmap bmp2) {
        Bitmap bmOverlay = Bitmap.createBitmap(bmp1.getWidth(), bmp1.getHeight(), bmp1.getConfig());
        Canvas canvas = new Canvas(bmOverlay);
        canvas.translate(50f,150f);
        canvas.drawBitmap(bmp1, new Matrix(), null);
        canvas.drawBitmap(bmp2, new Matrix(), null);
        return bmOverlay;
    }

    private void setWallpaper() {
        WallpaperManager manager = WallpaperManager.getInstance(getApplicationContext());
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        MultiFormatWriter writer = new MultiFormatWriter();

        try{
            BitMatrix matrix = writer.encode(fileContent, BarcodeFormat.QR_CODE,350,350);
            BarcodeEncoder encoder = new BarcodeEncoder();
            Bitmap bitmap = encoder.createBitmap(matrix);
            //ivOutput.setImageBitmap(bitmap);
            //   InputMethodManager manager2 = (InputMethodManager) getSystemService(
            //          Context.INPUT_METHOD_SERVICE
            // );
            //    manager2.hideSoftInputFromWindow(etInput.getApplicationWindowToken(),0);
            Drawable cw = manager.getDrawable();
            Bitmap wallp = ((BitmapDrawable)cw).getBitmap();
            Bitmap nwp=overlay(wallp,bitmap);
            try{
                manager.setBitmap(nwp);
                Toast.makeText(this, "Wallpaper set!", Toast.LENGTH_SHORT).show();
            } catch (IOException e){
                Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show();
            }
        }catch(WriterException e){
            e.printStackTrace();
        }


    }

    private void setterWallpaper() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.emergobackg);
        WallpaperManager manager = WallpaperManager.getInstance(getApplicationContext());

        try{
            manager.setBitmap(bitmap);
            Toast.makeText(this, "Wallpaper Cleared!", Toast.LENGTH_SHORT).show();
        } catch (IOException e){
            Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show();
        }
    }

}