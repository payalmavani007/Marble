package com.marble;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp-pc on 08-03-2018.
 */

class DelAdapter extends RecyclerView.Adapter<DelAdapter.MyViewHolder> {
    ArrayList<DelModel> delModelArraylist;
    Context context;


    public DelAdapter(Context context, ArrayList<DelModel> delModelArraylist) {
        this.context = context;
        this.delModelArraylist = delModelArraylist;
    }
    @Override
    public DelAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.delrow, parent, false);
        return new DelAdapter.MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(DelAdapter.MyViewHolder holder, int position) {
        final DelModel delModel = (DelModel) delModelArraylist.get(position);

        holder.del_name.setText(delModel.getDealer_name());
        holder.del_address.setText(delModel.getDealer_address());
        holder.del_city.setText(delModel.getDealer_city());
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView del_name;
        TextView del_address;
        TextView del_city;

        public MyViewHolder(View view) {
            super(view);
            del_name = view.findViewById(R.id.del_name);
            del_address = view.findViewById(R.id.del_address);
            del_city = view.findViewById(R.id.del_city);


        }
    }

    @Override
    public int getItemCount() {
        return delModelArraylist.size();
    }



}
