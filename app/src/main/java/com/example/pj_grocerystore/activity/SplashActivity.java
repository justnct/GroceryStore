package com.example.pj_grocerystore.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pj_grocerystore.R;
import com.example.pj_grocerystore.adapter.LikeIsSellProductAdapter;
import com.example.pj_grocerystore.controller.PushOrder;
import com.example.pj_grocerystore.model.Account;
import com.example.pj_grocerystore.model.ProductTest;
import com.example.pj_grocerystore.shared_preference.DataLocalManager;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class SplashActivity extends AppCompatActivity {
    private ImageView haha;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getData();
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent intent = new Intent(SplashActivity.this, LogInActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        }, 1000);

    }
    private void getData() { //get data product from firebase and img from firebase storage
        final ArrayList<ProductTest> mListProduct = new ArrayList<>();
        final ArrayList<Bitmap> mListBitmap = new ArrayList<>();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Product");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() { // get product from firebase
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int count = 0;
                if (snapshot.getChildrenCount() != 0) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        count++;
                        ProductTest productTest = dataSnapshot.getValue(ProductTest.class);
                        mListProduct.add(productTest);
                        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("images/" + productTest.getName() + ".PNG");
                        FileDownloadTask fileDownloadTask = null;
                        try { //get img from firebase storage
                            File file = File.createTempFile(productTest.getName(), "PNG");
                            fileDownloadTask = (FileDownloadTask) storageReference.getFile(file)
                                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                        @Override
                                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                                            mListBitmap.add(bitmap);
                                        }
                                    });
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if (count == snapshot.getChildrenCount()) {
                            while (true) {
                                if (fileDownloadTask.isComplete()) {
                                    DataLocalManager.setListProductTEST("mListProduct", mListProduct);
                                    DataLocalManager.setListBitmap("mListBitmap", mListBitmap);
                                    Toast.makeText(SplashActivity.this, "list bit map " + mListBitmap.size(), Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(SplashActivity.this, LogInActivity.class);
                                    startActivity(intent);
                                    finish();
                                    break;
                                }
                            }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}