package com.marble;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

/**
 * Created by hp-pc on 25-02-2018.
 */

class RelAdapter extends RecyclerView.Adapter<RelAdapter.MyViewHolder> {
    List<RelModel> relModelArraylist;
    Context context;


    public RelAdapter(Context context, List<RelModel> relModelArraylist) {
        this.context = context;
        this.relModelArraylist = relModelArraylist;
    }
    @Override
    public RelAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.relrow, parent, false);
        return new RelAdapter.MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(RelAdapter.MyViewHolder holder, int position) {
        final RelModel relModel = (RelModel) relModelArraylist.get(position);

        holder.rel_name.setText(relModel.getRetailer_name());
        holder.rel_address.setText(relModel.getRetailer_address());
        holder.rel_city.setText(relModel.getRetailer_city());
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView rel_name;
        TextView rel_address;
        TextView rel_city;

        public MyViewHolder(View view) {
            super(view);
            rel_name = view.findViewById(R.id.rel_name);
            rel_address = view.findViewById(R.id.rel_address);
            rel_city = view.findViewById(R.id.rel_city);


        }
    }

    @Override
    public int getItemCount() {
        return relModelArraylist.size();
    }



}
