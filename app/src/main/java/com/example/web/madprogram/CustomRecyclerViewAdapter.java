package com.example.web.madprogram;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.web.madprogram.JavaBean.Term;

import java.util.ArrayList;

public class CustomRecyclerViewAdapter extends Adapter {
    private ArrayList<Term> terms;


    public CustomRecyclerViewAdapter(ArrayList<Term> terms){
        this.terms = terms;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       //Make the view we want each item in the list to look like
        View view  = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_row, null);
        //Place that view in a ViewHolder
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        //return the CustomViewHolder
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //we use this to populate the content of each view
        //grab the current item in the list
        Term term = terms.get(position);
        //set the content of the current view to the content of the current term
        ((CustomViewHolder) holder).name.setText(term.getName());
        ((CustomViewHolder) holder).definition.setText(term.getDefinition());

    }

    @Override
    public int getItemCount() {
        if(terms != null){
            return terms.size();
        }
        return 0;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView name;
        protected TextView definition;
        //we will pass this CustomViewHolder recyle_row.xml
        public CustomViewHolder(View view){
            super(view);
            //we bind the textview name to name in recycle_row.xml
            this.name = view.findViewById(R.id.name);
            //we bind the TextView definition to definition in recycle_row.xml
            this.definition = view.findViewById(R.id.definition);
        }
    }
}
