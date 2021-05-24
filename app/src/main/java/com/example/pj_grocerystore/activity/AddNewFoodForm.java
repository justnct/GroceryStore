package com.example.pj_grocerystore.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pj_grocerystore.R;
import com.example.pj_grocerystore.model.CustomToast;
import com.example.pj_grocerystore.model.ProductTest;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import gun0912.tedbottompicker.TedBottomPicker;
import okhttp3.internal.Util;

public class AddNewFoodForm extends AppCompatActivity {
    private EditText et_nameFood, et_priceFood, et_typeFood;
    private TextView tv_choiceImg;
    private ImageView img_imgFood;
    private Button btn_createFood;
    private Bitmap bitmapTEST;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_food_form);
        addUI();
        addAction();

    }

    private void addUI() {
        et_nameFood = findViewById(R.id.nameFood);
        et_priceFood = findViewById(R.id.priceFood);
        et_typeFood = findViewById(R.id.typeFood);
        tv_choiceImg = findViewById(R.id.choiceImg);
        btn_createFood = findViewById(R.id.createFood);
        img_imgFood = findViewById(R.id.imgFood);
        img_imgFood.setVisibility(View.GONE);
    }

    private void addAction() {
        tv_choiceImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPermissions();
//                openImagePicker();
            }
        });

        btn_createFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(et_nameFood.getText())
                        && !TextUtils.isEmpty(et_priceFood.getText())
                        && !TextUtils.isEmpty(et_typeFood.getText())  ){
                    if(et_nameFood.getText().toString().length() < 3){
                        CustomToast.customToast(AddNewFoodForm.this, "Tên phải trên 3 ký tự !!");
                    } else {
                        String nameFood = et_nameFood.getText().toString();
                        int priceFood = Integer.parseInt(et_priceFood.getText().toString());
                        int type = Integer.parseInt(et_typeFood.getText().toString());
                        ProductTest p = new ProductTest(nameFood, priceFood, type);

                        //put product to firebase
                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Product");
                        databaseReference.child(p.getName()).setValue(p);

                        //put pic to firebase storage
                        firebaseStorage = FirebaseStorage.getInstance();
                        storageReference = firebaseStorage.getReference();
                        StorageReference mountainImagesRef = storageReference.child("images/" + et_nameFood.getText().toString() + ".PNG");
                        ProgressDialog progressDialog = new ProgressDialog(AddNewFoodForm.this);
                        progressDialog.setTitle("Upload...");
                        progressDialog.show();
                        mountainImagesRef.putFile(getImageUri(AddNewFoodForm.this, bitmapTEST)).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                progressDialog.dismiss();
                                Snackbar.make(findViewById(android.R.id.content), "Save Done", Snackbar.LENGTH_LONG).show();
                            }
                        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                                double progressPercent = (100.00 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                                progressDialog.setMessage("Percentenge " + progressPercent);
                            }
                        });
                    }
                }
            }
        });
    }

    private void requestPermissions() {
        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                openImagePicker();
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(AddNewFoodForm.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }
        };

        TedPermission.with(this)
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(Manifest.permission.READ_CONTACTS, Manifest.permission.ACCESS_FINE_LOCATION)
                .check();
    }

    private void openImagePicker() {
        TedBottomPicker.OnImageSelectedListener listener = new TedBottomPicker.OnImageSelectedListener() {
            @Override
            public void onImageSelected(Uri uri) {
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    img_imgFood.setVisibility(View.VISIBLE);
                    img_imgFood.setImageBitmap(bitmap);
                    bitmapTEST = bitmap;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        TedBottomPicker tedBottomPicker = new TedBottomPicker.Builder(AddNewFoodForm.this)
                .setOnImageSelectedListener(listener)
                .create();
        tedBottomPicker.show(getSupportFragmentManager());

    }

    //cast bitmap to uri
    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

}