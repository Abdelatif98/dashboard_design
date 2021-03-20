package com.example.dashboarddesign;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.ViewHolder> {
    private List<operationsModel> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    recyclerAdapter(Context context, List<operationsModel> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.rec_view_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        operationsModel op = mData.get(position);
        holder.optype.setText(op.getTypeop());
        holder.opdate.setText(op.getDateop());
        holder.opmontant.setText(op.getMontant());
        if(position % 2 == 0){

            holder.itemView.setBackgroundColor(Color.parseColor("#ffffff"));
        }else{
            holder.itemView.setBackgroundColor(Color.parseColor("#f5fdfc"));
        }

        switch(op.getTypeop()){
            case "Virement":
                holder.iconop.setImageResource(R.drawable.ic_virement);
                break;
            case "Paiment par CB":
                holder.iconop.setImageResource(R.drawable.ic_cb);
                break;
            case "Mise à disposition":
                holder.iconop.setImageResource(R.drawable.ic_mad);
                holder.opmontant.setTextColor(Color.parseColor("#009B7B"));
                holder.currency.setTextColor(Color.parseColor("#009B7B"));

                break;

        }


    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView opdate;
        TextView optype;
        TextView opmontant;
        ImageView iconop;
        TextView currency;
        ViewHolder(View itemView) {
            super(itemView);
            opdate = itemView.findViewById(R.id.opdate);
            optype=itemView.findViewById(R.id.optitle);
            opmontant=itemView.findViewById(R.id.opmontant);
            iconop=itemView.findViewById(R.id.imageView4);
            currency=itemView.findViewById(R.id.currency);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    operationsModel getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
