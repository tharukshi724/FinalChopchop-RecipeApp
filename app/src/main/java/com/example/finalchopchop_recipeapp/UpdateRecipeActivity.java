package com.example.finalchopchop_recipeapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class UpdateRecipeActivity extends AppCompatActivity {

    ImageView recipeImage;
    Uri uri;
    EditText txt_name,txt_ingredients,txt_method;
    String imageUrl;
    String key,oldImageUrl;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    String recipename,recipeIngredients,recipeMethod;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_recipe);

        recipeImage = (ImageView)findViewById(R.id.iv_foodImage);
        txt_name = (EditText)findViewById(R.id.txt_recipe_name);
        txt_ingredients = (EditText)findViewById(R.id.text_ingredients);
        txt_method = (EditText)findViewById(R.id.text_method);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){

            Glide.with(com.example.recipe_android_app.UpdateRecipeActivity.this)
                    .load(bundle.getString("oldimageUrl"))
                    .into(recipeImage);
            txt_name.setText(bundle.getString("recipeNameKey"));
            txt_ingredients.setText(bundle.getString("ingredientsKey"));
            txt_method.setText(bundle.getString("methodKey"));
            key = bundle.getString("key");
            oldImageUrl = bundle.getString("oldimageUrl");
        }


        databaseReference = FirebaseDatabase.getInstance().getReference("Recipe").child(key);



    }

    public void btnSelectImage(View view) {
        Intent photoPicker = new Intent(Intent.ACTION_PICK);
        photoPicker.setType("image/*");
        startActivityForResult(photoPicker,1);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){

            uri = data.getData();
            recipeImage.setImageURI(uri);

        }
        else Toast.makeText(this, "You haven't picked image", Toast.LENGTH_SHORT).show();

    }


    public void btnUpdateRecipe(View view) {

        recipename = txt_name.getText().toString().trim();
        recipeIngredients = txt_ingredients.getText().toString().trim();
        recipeMethod = txt_method.getText().toString();


        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Recipe Uploading....");
        progressDialog.show();
        storageReference = FirebaseStorage.getInstance()
                .getReference().child("RecipeImage").child(uri.getLastPathSegment());
        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while(!uriTask.isComplete());
                Uri urlImage = uriTask.getResult();
                imageUrl = urlImage.toString();
                uploadRecipe();
                progressDialog.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
            }
        });


    }

    public void uploadRecipe(){



        com.example.recipe_android_app.FoodData foodData = new com.example.recipe_android_app.FoodData(
                recipename,
                recipeIngredients,
                recipeMethod,
                imageUrl
        );


        databaseReference.setValue(foodData).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                StorageReference storageReferenceNew  = FirebaseStorage.getInstance().getReferenceFromUrl(oldImageUrl);
                storageReferenceNew.delete();
                Toast.makeText(com.example.recipe_android_app.UpdateRecipeActivity.this, "Data Updated", Toast.LENGTH_SHORT).show();
            }
        });




    }

}
