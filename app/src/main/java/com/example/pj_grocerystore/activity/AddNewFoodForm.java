package com.example.pj_grocerystore.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pj_grocerystore.R;
import com.example.pj_grocerystore.model.CustomToast;
import com.example.pj_grocerystore.model.ProductTest;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

public class AddNewFoodForm extends AppCompatActivity {
    private EditText et_nameFood, et_priceFood, et_typeFood;
    private TextView tv_choiceImg;
    private ImageView img_imgFood;
    private Button btn_createFood;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
    private Uri uri;

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

                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, 2);
            }
        });

        btn_createFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(et_nameFood.getText())
                        && TextUtils.isEmpty(et_priceFood.getText())
                        && TextUtils.isEmpty(et_typeFood.getText())) {
                    CustomToast.customToast(AddNewFoodForm.this, "Bạn nhập thiếu các dữ liệu");
                } else {
                    if (uri != null) {
                        putToFirebase(uri);
                    } else {
                        CustomToast.customToast(AddNewFoodForm.this, "Bạn chưa chọn hình");
                    }
                }
            }
        });
    }

    private void putToFirebase(Uri uri) {
        StorageReference storageReference = FirebaseStorage.getInstance().getReference("images/" + et_nameFood.getText().toString() + "." + getFileExtension(uri));
        final StorageTask<UploadTask.TaskSnapshot> taskSnapshotStorageTask = storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        ProductTest productTest = new ProductTest(et_nameFood.getText().toString(),
                                Integer.parseInt(et_priceFood.getText().toString()),
                                Integer.parseInt(et_typeFood.getText().toString()), uri.toString());
                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Product");
                        databaseReference.child(et_nameFood.getText().toString()).setValue(productTest);
                        Toast.makeText(AddNewFoodForm.this, "Success", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private String getFileExtension(Uri uri) {
        ContentResolver contentProvider = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentProvider.getType(uri));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2 && resultCode == RESULT_OK && data != null) {
            img_imgFood.setVisibility(View.VISIBLE);
            uri = data.getData();
            img_imgFood.setImageURI(data.getData());
        }
    }
}