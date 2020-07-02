package com.example.bancao;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class Estatistica extends Activity {

    ViewHolder mViewHolder = new ViewHolder();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.estatistica);
        DatabaseAcess db = new DatabaseAcess(this);

        this.mViewHolder.eEspanhol = findViewById(R.id.Espanhol);
        this.mViewHolder.eIngles = findViewById(R.id.Ingles);
        this.mViewHolder.eLinguagens = findViewById(R.id.Linguagens);
        this.mViewHolder.eHumanas = findViewById(R.id.Humanas);
        this.mViewHolder.eMatematica = findViewById(R.id.Matematica);
        this.mViewHolder.eNatureza = findViewById(R.id.Natureza);

        Materia espanhol = new Materia(0,db);
        espanhol.execute();
        db.setPontuacao(espanhol);
        double esp = db.getPerc(0);
        color(this.mViewHolder.eEspanhol, esp);

        Materia ingles = new Materia(1,db);
        ingles.execute();
        db.setPontuacao(ingles);
        double ing = db.getPerc(1);
        color(this.mViewHolder.eIngles, ing);

        Materia linguagens = new Materia(2,db);
        linguagens.execute();
        db.setPontuacao(linguagens);
        double lin = db.getPerc(2);
        color(this.mViewHolder.eLinguagens, lin);

        Materia humanas = new Materia(3,db);
        humanas.execute();
        db.setPontuacao(humanas);
        double hum = db.getPerc(3);
        color(this.mViewHolder.eHumanas, hum);

        Materia matematica = new Materia(4,db);
        matematica.execute();
        db.setPontuacao(matematica);
        double mat = db.getPerc(4);
        color(this.mViewHolder.eMatematica, mat);

        Materia natureza = new Materia(5,db);
        natureza.execute();
        db.setPontuacao(natureza);
        double nat = db.getPerc(5);
        color(this.mViewHolder.eNatureza, nat);

        this.mViewHolder.eEspanhol.setText("Espanhol: "+esp + "%");
        this.mViewHolder.eIngles.setText("Ingles: "+ing + "%");
        this.mViewHolder.eLinguagens.setText("Linguagens"+lin + "%");
        this.mViewHolder.eHumanas.setText("Humanas: "+hum + "%");
        this.mViewHolder.eMatematica.setText("Matematica"+mat + "%");
        this.mViewHolder.eNatureza.setText("Natureza:"+nat + "%");
    }

    private void color(TextView materia, double perc){
        if (perc<= 50){
            materia.setTextColor(Color.parseColor("#FF0000"));
        }else if (perc <= 75){
            materia.setTextColor(Color.parseColor("#FFFFEB3B"));
        }else if (perc <= 90){
            materia.setTextColor(Color.parseColor("#4CFF00"));
        }else{
            materia.setTextColor(Color.parseColor("#FF9C27B0"));
        }
    }




}
