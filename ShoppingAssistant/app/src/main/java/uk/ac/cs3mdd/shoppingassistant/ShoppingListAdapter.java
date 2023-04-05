package uk.ac.cs3mdd.shoppingassistant;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.MyRecyclerView> {

    Context context;
    ArrayList<ShoppingListModel> shoppingListModels;

    public ShoppingListAdapter (Context context, ArrayList<ShoppingListModel> shoppingListModels){
        this.context = context;
        this.shoppingListModels = shoppingListModels;
    }


    @NonNull
    @Override
    public ShoppingListAdapter.MyRecyclerView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_shopping_list, parent, false);

        return new ShoppingListAdapter.MyRecyclerView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerView holder, int position) {

        holder.nameText.setText(shoppingListModels.get(position).getProductName());

        Resources resources = holder.itemView.getContext().getResources();
        int imageID = resources.getIdentifier(shoppingListModels.get(position).getProductImage(), "drawable", context.getPackageName());

        holder.imageView.setImageResource(imageID);

    }

    @Override
    public int getItemCount() {
        return shoppingListModels.size();
    }

    public static class MyRecyclerView extends RecyclerView.ViewHolder {

        TextView nameText;
        ImageView imageView;

        public MyRecyclerView(@NonNull View itemView) {
            super(itemView);

            nameText = itemView.findViewById(R.id.productName);

            imageView = itemView.findViewById(R.id.imageView2);
        }
    }
}
