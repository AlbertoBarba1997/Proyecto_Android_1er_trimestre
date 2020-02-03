package com.example.android.proyecto.ADAPTERS;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.android.proyecto.R;
import com.example.android.proyecto.models.Deck;

import java.util.ArrayList;
import java.util.List;

public class DeckAdapter extends RecyclerView.Adapter<DeckAdapter.ViewHolder> {

    private List<Deck> decks;
    private int layout;
    private Context context;
    private DeckAdapter.OnClickListener listener;

    public DeckAdapter(List<Deck> decks, int layout, Context context,
                       DeckAdapter.OnClickListener listener) {
        this.decks = decks;
        this.layout = layout;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public DeckAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(layout,viewGroup,false);  //este layout seria el item row
        DeckAdapter.ViewHolder viewHolder=new DeckAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DeckAdapter.ViewHolder viewHolder, final int i) {
        //LLena cada uno de los elementos ViewHolder y les asigna a cada uno un ClickListener.

        final Deck deck=decks.get(i);

        long id=deck.getId();

        String clase=deck.getClasedeck();



        viewHolder.nombre.setText("deck nº "+Long.toString(id));

        try {
            viewHolder.clase.setText(clase);
        }catch (Exception e){
            viewHolder.clase.setText("MAZO INTRODUCIDO AUTOMATICAMENTE POR ERROR DESCONOCIDO");
        }

        try {
        switch(clase)
        {
            case "DRUID":
                viewHolder.imagen.setImageResource(R.drawable.ldruida);
                break;
            case "PRIEST":
                viewHolder.imagen.setImageResource(R.drawable.lsacerdote);
                break;
            case "WARLOCK":
                viewHolder.imagen.setImageResource(R.drawable.lbrujo);
                break;
            case "MAGE":
                viewHolder.imagen.setImageResource(R.drawable.lmago);
                break;
            case "HUNTER":
                viewHolder.imagen.setImageResource(R.drawable.lcazador);
                break;
            case "ROGUE":
                viewHolder.imagen.setImageResource(R.drawable.lpicaro);
                break;
            case "PALADIN":
                viewHolder.imagen.setImageResource(R.drawable.lpaladin);
                break;
            case "WARRIOR":
                viewHolder.imagen.setImageResource(R.drawable.lguerrero);
                break;
            default:
                System.out.println("NO HAY CLASE DETECTADA");
        }
        }catch (Exception e){
            viewHolder.clase.setText("");
        }






        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(deck, i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return decks.size();
    }

    public void addDecks(List<Deck> decks){
        this.decks=decks;
        notifyDataSetChanged();

    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView clase;
        TextView nombre;
        ImageView imagen;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nombre=itemView.findViewById(R.id.nombremazo);
            clase=itemView.findViewById(R.id.clasemazo);
            imagen=itemView.findViewById(R.id.fotomazo);

        }

    }

    public interface OnClickListener{
        public void onClick(Deck deck, int pos);
    }




}