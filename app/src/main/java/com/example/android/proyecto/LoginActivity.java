package com.example.android.proyecto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class LoginActivity extends AppCompatActivity {

    private static final String URL="https://api.hearthstonejson.com/v1/25770/esES/cards.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        /*ImageView image=(ImageView) findViewById(R.id.foto);

        Picasso.with(this)
            .load("https://art.hearthstonejson.com/v1/render/latest/enUS/512x/EX1_116.png")
            .error(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(image);


        RequestQueue queue=Volley.newRequestQueue(this);
        StringRequest request=new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                display(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", error.toString());

            }

        });

        queue.add(request);

    }

    private void display(String response){
        TextView texto=(TextView) findViewById(R.id.displayText);

        texto.setText(response);
    */
    }
}
