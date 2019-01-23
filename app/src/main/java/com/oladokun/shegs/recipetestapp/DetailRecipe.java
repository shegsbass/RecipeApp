package com.oladokun.shegs.recipetestapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.oladokun.shegs.recipetestapp.Model.Recipe;

public class DetailRecipe extends AppCompatActivity {
    public static final String RECIPE_POSITION = "recipe_position";

    TextView nameOfFood, Ingredient, Description, Calories, Carbos, Time;
    ImageView photoOfFood;
    RatingBar Rating;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_detail);

        photoOfFood = findViewById(R.id.main_backdrop);
        Intent intent = getIntent();
        nameOfFood = findViewById(R.id.text_title);
        Ingredient = findViewById(R.id.recipe_text);
        Description = findViewById(R.id.description_text);
        Time = findViewById(R.id.time_text);
        Calories = findViewById(R.id.calories_text);
        Carbos = findViewById(R.id.carbos_text);
        Rating = findViewById(R.id.rating);

        if (intent != null){
            int position = intent.getIntExtra(RECIPE_POSITION, 0);
            Recipe recipe = MainActivity.getRecipe(position);
            Glide.with(this).load(recipe.getImage()).into(photoOfFood);

            nameOfFood.setText(recipe.getName());
            Ingredient.setText(recipe.getIngredients().toString());
            Description.setText(recipe.getDescription().toString());
            Time.setText(recipe.getTime().toString());
            Calories.setText(recipe.getCalories().toString());
            Carbos.setText(recipe.getCarbos().toString());

            if (recipe.getRating() == null){
                Rating.setRating(0);
            }else {
                Rating.setRating(recipe.getRating().floatValue());
            }
        }
    }

    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.main_collapsing);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.main_appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }



    /*public void Description (View v){
        showDialog(0);}

    public void Protein (View v){
        showDialog(0);}

    public void Product (View v){
        showDialog(0);}

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case 0: findViewById(R.id.description);
                return new AlertDialog.Builder(this)
                        .setIcon(R.drawable.salad)
                        .setTitle("DESCRIPTION")
                        .setNegativeButton("OK",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        Toast.makeText(getBaseContext(),
                                                "Let's make some dishes now!", Toast.LENGTH_SHORT).show();
                                    }
                                }).setMessage("Description text goes in here")
                        .create();
            case 1: findViewById(R.id.protein);
                return new AlertDialog.Builder(this)
                        .setIcon(R.drawable.salad)
                        .setTitle("PROTEINS")
                        .setNegativeButton("OK",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        Toast.makeText(getBaseContext(),
                                                "You just got protein!", Toast.LENGTH_SHORT).show();
                                    }
                                }).setMessage("Protein text goes in here")
                        .create();
            case 2: findViewById(R.id.product);
                return new AlertDialog.Builder(this)
                        .setIcon(R.drawable.salad)
                        .setTitle("PRODUCTS")
                        .setNegativeButton("OK",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        Toast.makeText(getBaseContext(),
                                                "Awesome products right?", Toast.LENGTH_SHORT).show();
                                    }
                                }).setMessage("Product text goes in here")
                        .create();
        }
        return null;
    }*/



}
