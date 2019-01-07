package com.johansen.dk.meremad.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.johansen.dk.meremad.R;

import java.util.ArrayList;

public class dishDescriptionAdapter extends RecyclerView.Adapter<dishDescriptionAdapter.myViewHolder> {
    private ArrayList<String> dataset;
    public static class myViewHolder extends RecyclerView.ViewHolder{
        public CardView niceCard;
        public TextView tv;
        public myViewHolder(@NonNull CardView cv){
            super(cv);
            this.tv = (TextView) cv.findViewById(R.id.dishinfotext);
            niceCard = cv;
        }
    }
    public dishDescriptionAdapter(ArrayList<String> myDataSet){dataset = myDataSet;}

    @NonNull
    public myViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int ViewType){
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.dishinfobox, parent, false);
        myViewHolder vh = new myViewHolder(cv);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        TextView tv = holder.niceCard.findViewById(R.id.dishinfotext);
        tv.setText("•  " + dataset.get(position));
    }

    public int getItemCount() { return dataset.size(); }
}

