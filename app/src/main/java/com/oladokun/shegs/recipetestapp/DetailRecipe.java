package com.oladokun.shegs.recipetestapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.oladokun.shegs.recipetestapp.Model.Recipe;

public class DetailRecipe extends AppCompatActivity {
    public static final String RECIPE_POSITION = "recipe_position";

    TextView nameOfFood, Ingredient;
    ImageView photoOfFood;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_detail);

        photoOfFood = findViewById(R.id.main_backdrop);
        Intent intent = getIntent();
        nameOfFood = findViewById(R.id.text_title);
        Ingredient = findViewById(R.id.recipe_text);

        if (intent != null){
            int position = intent.getIntExtra(RECIPE_POSITION, 0);
            Recipe recipe = MainActivity.getRecipe(position);
            Glide.with(this).load(recipe.getImage()).into(photoOfFood);

            nameOfFood.setText(recipe.getName());
            Ingredient.setText(recipe.getIngredients().toString());
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
}
