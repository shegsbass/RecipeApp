package com.oladokun.shegs.recipetestapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.oladokun.shegs.recipetestapp.Model.Recipe;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private Context mContext;
    private List<Recipe> recipeList;
    //RecipeOnclickListner mListner;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title;
        public ImageView thumbnail, overflow;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            overflow = (ImageView) view.findViewById(R.id.overflow);

            view.setOnClickListener(this);
            thumbnail.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            Intent i = new Intent(mContext, DetailRecipe.class);
            i.putExtra(DetailRecipe.RECIPE_POSITION, getAdapterPosition());
            mContext.startActivity(i);
        }
    }


    public Adapter(Context mContext, List<Recipe> recipeList) {
        this.mContext = mContext;
        this.recipeList = recipeList;
    }

    @Override
    public int getItemViewType (int position) {return super.getItemViewType(position);}

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext)
                .inflate(R.layout.album_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Recipe recipe = recipeList.get(position);
        holder.title.setText(recipe.getName());

        // loading recipe cover using Glide library
        Glide.with(mContext).load(recipe.getImage()).into(holder.thumbnail);

        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(holder.overflow);
            }
        });
    }

    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_album, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }

    /**
     * Click listener for popup menu items
     */
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_add_favourite:
                    Toast.makeText(mContext, "Add to favourite", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_view_next:
                    Toast.makeText(mContext, "Next Recipe", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
        }
    }



    @Override
    public int getItemCount() { return recipeList == null ? 0 : recipeList.size(); }

    public void setRecipeList(List<Recipe> recipes){
        for (Recipe recipe:recipes){
            addRecipe(recipe);
        }
    }

    public void addRecipe (Recipe recipe){
        recipeList.add(recipe);
        notifyItemInserted(recipeList.size());
    }
}