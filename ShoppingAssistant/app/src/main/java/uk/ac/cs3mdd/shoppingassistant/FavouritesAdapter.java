package uk.ac.cs3mdd.shoppingassistant;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FavouritesAdapter extends RecyclerView.Adapter<FavouritesAdapter.MyRecyclerView>{

    Context context;
    ArrayList<FavouritesModel> favouritesList;

    public FavouritesAdapter (Context context, ArrayList<FavouritesModel> favouritesModels){
        this.context = context;
        this.favouritesList = favouritesModels;
    }


    @NonNull
    @Override
    public FavouritesAdapter.MyRecyclerView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_favourites, parent, false);

        return new FavouritesAdapter.MyRecyclerView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavouritesAdapter.MyRecyclerView holder, int position) {

        holder.nameText.setText(favouritesList.get(position).getName());

        Resources resources = holder.itemView.getContext().getResources();
        int imageID = resources.getIdentifier(favouritesList.get(position).getMealImage(), "drawable", context.getPackageName());

        holder.imageView.setImageResource(imageID);

        if (favouritesList.get(position).getFavourite().equals("0")){
            holder.favCheckBox.setChecked(false);
        } else {
            holder.favCheckBox.setChecked(true);
        }

        holder.favCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (favouritesList.get(position).getFavourite().equals("0")){
                    favouritesList.get(position).setFavourite("1");

                } else {
                    favouritesList.get(position).setFavourite("0");
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return favouritesList.size();
    }


    public ArrayList<FavouritesModel> getData(){
        return favouritesList;
    }

    public static class MyRecyclerView extends RecyclerView.ViewHolder {

        TextView nameText;
        ImageView imageView;
        CheckBox favCheckBox;

        public MyRecyclerView(@NonNull View itemView) {
            super(itemView);

            nameText = itemView.findViewById(R.id.mealName);

            imageView = itemView.findViewById(R.id.mealImage);

            favCheckBox = itemView.findViewById(R.id.favCheckBox);
        }
    }
}
