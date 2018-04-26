package com.marble;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by hp-pc on 06-01-2018.
 */

class CatAdapter extends RecyclerView.Adapter<CatAdapter.MyViewHolder> {
    Context context;
    Dialog dialog;
    List<CatModel> catModelArrayList;

    public CatAdapter(Context context, List<CatModel> catModelArrayList) {
        this.context = context;
        this.catModelArrayList = catModelArrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final CatModel catModel = (CatModel) catModelArrayList.get(position);
        Picasso.with(context).load(catModel.getCat_photo())
                .placeholder(R.drawable.ic_menu_gallery)
                .error(R.drawable.ic_menu_gallery)
                .into(holder.cat_photo);
        holder.cat_name.setText(catModel.getCategory_name());
        holder.cat_description.setText(catModel.getGetCategory_description());
        holder.category_price.setText(catModel.getCategory_price());


        holder.cat_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.showimage);
                ImageView imageView = dialog.findViewById(R.id.Show_image);
                Picasso.with(context).load(catModel.getCat_photo())
                        .placeholder(R.drawable.ic_menu_gallery)
                        .error(R.drawable.ic_menu_gallery)
                        .into(imageView);
                dialog.show();
            }
        });
        holder.category_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("Price",holder.category_price.getText().toString());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return catModelArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView cat_name;
        TextView cat_description;
        TextView category_price;
        ImageView cat_photo;

        public MyViewHolder(View view) {
            super(view);
            cat_name = view.findViewById(R.id.cat_name);
            cat_description = view.findViewById(R.id.cat_description);
            category_price = view.findViewById(R.id.cat_price);
            cat_photo = view.findViewById(R.id.cat_photo);

        }
    }
}
