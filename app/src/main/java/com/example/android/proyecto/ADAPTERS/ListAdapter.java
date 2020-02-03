package com.example.android.proyecto.ADAPTERS;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.proyecto.R;
import com.example.android.proyecto.models.Card;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private ArrayList<Card> cartas;
    private int layout;
    private Context context;
    private OnClickListener listener;

    public ListAdapter(ArrayList<Card> cartas, int layout, Context context,
                       OnClickListener listener) {
        this.cartas = cartas;
        this.layout = layout;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(layout,viewGroup,false);  //este layout seria el item row
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        //LLena cada uno de los elementos ViewHolder y les asigna a cada uno un ClickListener.

        final Card carta=cartas.get(i);

        String nombre=carta.getName();
        String texto=carta.getText();

        if(nombre.length()>24){
            nombre=nombre.substring(0,24)+"...";
        }
        if(texto.length()>65){
            texto=texto.substring(0,65)+"...";
        }

        String coste= Integer.toString(carta.getCost());
        String ataque= Integer.toString(carta.getAttack());
        String salud= Integer.toString(carta.getHealth());

        viewHolder.name.setText(nombre);
        viewHolder.text.setText(texto);
        viewHolder.cost.setText(coste);
        viewHolder.attack.setText(ataque);
        viewHolder.health.setText(salud);

        if(carta.getDoble()){
            viewHolder.doble.setText("x2");
        }



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

        TextView name;
        TextView text;
        TextView cost;
        TextView attack;
        TextView health;
        TextView doble;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.tName);
            text=itemView.findViewById(R.id.tText);
            cost=itemView.findViewById(R.id.tCost);
            attack=itemView.findViewById(R.id.tAttack);
            health=itemView.findViewById(R.id.tHealth);
            doble=itemView.findViewById(R.id.x2);



        }

    }

    public interface OnClickListener{
        public void onClick(Card carta,int pos);
    }
}