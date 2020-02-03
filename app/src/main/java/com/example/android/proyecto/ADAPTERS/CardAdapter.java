package com.example.android.proyecto.ADAPTERS;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.proyecto.R;
import com.example.android.proyecto.models.Card;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    private ArrayList<Card> cartas;
    private int layout;
    private Context context;
    private OnClickListener listener;

    public CardAdapter(ArrayList<Card> cartas, int layout, Context context,
                       OnClickListener listener) {
        this.cartas = cartas;
        this.layout = layout;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(layout,viewGroup,false);  //este layout seria el item row
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        //LLena cada uno de los elementos ViewHolder y les asigna a cada uno un ClickListener.

        final Card carta=cartas.get(i);

        String url=carta.getUrl();

        Picasso.with(context)
            .load(url)
                .error(R.mipmap.ic_launcher)
                .centerCrop()
                .fit()
                .into(viewHolder.imagen)
                ;





        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(carta, i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imagen;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imagen=itemView.findViewById(R.id.ItemCarta);

        }

    }

    public interface OnClickListener{
        public void onClick(Card carta, int pos);
    }




}


