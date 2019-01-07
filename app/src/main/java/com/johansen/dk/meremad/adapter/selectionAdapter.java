package com.johansen.dk.meremad.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.johansen.dk.meremad.R;
import com.johansen.dk.meremad.model.foodItem;

import java.util.ArrayList;

public class selectionAdapter extends RecyclerView.Adapter<selectionAdapter.myViewHolder>{
    private static ClickListener clickListener;
    public ArrayList<foodItem> dataset;
    public static class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public CardView niceCard;
        public TextView textV;
        public ImageView imageV;
        public myViewHolder(@NonNull CardView cv) {
            super(cv);
            this.textV = (TextView) cv.findViewById(R.id.cardName);
            textV.setOnClickListener(this);
            this.imageV = (ImageView) cv.findViewById(R.id.cardImage);
            imageV.setOnClickListener(this);
            niceCard=cv;
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public selectionAdapter(ArrayList<foodItem> myDataset) {
        dataset = myDataset;
    }

    @NonNull
    //@Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.foodcard, parent, false);
        cv.setRadius((float) 50.0);
        myViewHolder vh = new myViewHolder(cv);
        return vh;
    }

    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        TextView tv = holder.niceCard.findViewById(R.id.cardName);
        tv.setText(dataset.get(position).getName());
        ImageView iv = holder.niceCard.findViewById(R.id.cardImage);
        iv.setId(dataset.get(position).getID());
        iv.setTransitionName(dataset.get(position).getName()+"Trans");
        iv.setImageResource(dataset.get(position).getImageRes());
    }

    public int getItemCount() {
        return dataset.size();
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        selectionAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
    }

    public void clear() {
        final int size = dataset.size();
        dataset.clear();
        notifyItemRangeRemoved(0, size);
    }

}
