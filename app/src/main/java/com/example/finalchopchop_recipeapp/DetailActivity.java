package com.example.finalchopchop_recipeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class DetailActivity extends AppCompatActivity {

    TextView foodIngredients,RecipeName,RecipeMethod;
    ImageView foodImage;
    String key="";
    String imageUrl="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        RecipeName = (TextView) findViewById(R.id.txtRecipeName);
        RecipeMethod= (TextView) findViewById(R.id.txtMethod);
        foodIngredients = (TextView)findViewById(R.id.txtIngredients);
        foodImage = (ImageView)findViewById(R.id.ivImage2);

        Bundle mBundle = getIntent().getExtras();

        if(mBundle!=null){

            foodIngredients.setText(mBundle.getString("Ingredients"));
            key = mBundle.getString("keyValue");
            imageUrl = mBundle.getString("Image");
            RecipeName.setText(mBundle.getString("RecipeName"));
            RecipeMethod.setText(mBundle.getString("method"));
            // foodImage.setImageResource(mBundle.getInt("Image"));

            Glide.with(this)
                    .load(mBundle.getString("Image"))
                    .into(foodImage);


        }

    }

    public void btnDeleteRecipe(View view) {

        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Recipe");
        FirebaseStorage storage = FirebaseStorage.getInstance();

        StorageReference storageReference = storage.getReferenceFromUrl(imageUrl);

        storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                reference.child(key).removeValue();
                Toast.makeText(com.example.recipe_android_app.DetailActivity.this, "Recipe Deleted", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), com.example.recipe_android_app.RecipeMainActivity.class));
                finish();

            }
        });

    }

    public void btnUpdateRecipe(View view) {

        startActivity(new Intent(getApplicationContext(),UpdateRecipeActivity.class)
                .putExtra("recipeNameKey",RecipeName.getText().toString())
                .putExtra("ingredientsKey",foodIngredients.getText().toString())
                .putExtra("methodKey",RecipeMethod.getText().toString())
                .putExtra("oldimageUrl",imageUrl)
                .putExtra("key",key)
        );



    }
}
