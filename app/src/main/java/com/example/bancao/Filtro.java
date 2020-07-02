package com.example.bancao;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Filtro extends AppCompatActivity implements View.OnClickListener {
    public static final String ID = "Filtro" ;
    private ViewHolder mViewHolder = new ViewHolder();
    public int position ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filtro);

        this.mViewHolder.espanhol = findViewById(R.id.Espanhol);
        this.mViewHolder.ingles = findViewById(R.id.Ingles);
        this.mViewHolder.linguagens = findViewById(R.id.Linguagens);
        this.mViewHolder.humanas = findViewById(R.id.Humanas);
        this.mViewHolder.matematica = findViewById(R.id.Matematica);
        this.mViewHolder.natureza = findViewById(R.id.Natureza);
        this.mViewHolder.estatistica = findViewById(R.id.Estatictica);

        this.mViewHolder.espanhol.setOnClickListener(this);
        this.mViewHolder.ingles.setOnClickListener(this);
        this.mViewHolder.linguagens.setOnClickListener(this);
        this.mViewHolder.humanas.setOnClickListener(this);
        this.mViewHolder.matematica.setOnClickListener(this);
        this.mViewHolder.natureza.setOnClickListener(this);
        this.mViewHolder.estatistica.setOnClickListener(this);

    }

    public boolean testeId(View v) {
        if (v.getId() == R.id.Espanhol) {
            return true;
        } else if (v.getId() == R.id.Ingles) {
            return true;
        }else if (v.getId() == R.id.Linguagens) {
            return true;
        }else if (v.getId() == R.id.Humanas) {
            return true;
        }else  if (v.getId() == R.id.Matematica) {
            return true;
        }else if (v.getId() == R.id.Natureza) {
            return true;
        }else if(v.getId() == R.id.Estatictica){
            return false;
        }else{
            return false;
        }
    }


    @Override
    public void onClick(View v) {
        if(testeId(v)) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(ID, v.getId());
            startActivity(intent);
        }else if(v.getId() == R.id.Estatictica){
            Intent intent = new Intent(this, Estatistica.class);
            startActivity(intent);
        }else{

        }
    }
}
