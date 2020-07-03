package com.example.bancao;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.RectF;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import  java.util.Random;

@RequiresApi(api = Build.VERSION_CODES.M)
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewHolder mViewHolder = new ViewHolder();

    static int id;
    int lim;
    Materia pointMateria;
    String resp;
    String filtro;
    boolean passe = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Intent intent = getIntent();
        int idFiltro = intent.getIntExtra(Filtro.ID, 0);
        filtro = materiaForId(idFiltro);
        id = getId(filtro);
        pointMateria = new Materia(id, new DatabaseAcess(this));




        //Atribui a mViewholder os itens da activity
        this.mViewHolder.question = findViewById(R.id.question);
        this.mViewHolder.webView = findViewById(R.id.webView);
        this.mViewHolder.question2 = findViewById(R.id.question2);
        this.mViewHolder.alternativaA = findViewById(R.id.a);
        this.mViewHolder.alternativaB = findViewById(R.id.b);
        this.mViewHolder.alternativaC = findViewById(R.id.c);
        this.mViewHolder.alternativaD = findViewById(R.id.d);
        this.mViewHolder.alternativaE = findViewById(R.id.e);




        //Identifica se alguém apertou o botão
        this.mViewHolder.alternativaA.setOnClickListener(this);
        this.mViewHolder.alternativaB.setOnClickListener(this);
        this.mViewHolder.alternativaC.setOnClickListener(this);
        this.mViewHolder.alternativaD.setOnClickListener(this);
        this.mViewHolder.alternativaE.setOnClickListener(this);
        DatabaseAcess db =  sortear();




        if (resp != null && ! resp.equals("")) id = idForChar(resp.charAt(0));

    }

    private int getId(String filtro) {
        switch (filtro){
            case "Espanhol":
                lim = 7;
                return 0;
            case "Ingles":
                lim = 8;
                return 1;
            case "LIN":
                lim = 15;
                return 2;
            case "HUM":
                lim = 15;
                return 3;
            case "MAT":
                lim = 15;
                return 4;
            case "NAT":
                lim = 15;
                return 5;
            default:
                lim = 0;
                return -1;
        }
    }

    String materiaForId(int id){
        switch (id){
            case R.id.Espanhol:
                return "Espanhol";
            case R.id.Ingles :
                return "Ingles";
            case R.id.Humanas:
                return "HUM";
            case R.id.Matematica:
                return "MAT";
            case R.id.Natureza:
                return "NAT";
            case R.id.Linguagens:
                return "LIN";
            default:
                return null;
        }

    }

    private int idForChar(char correctAnswer) {
        //Retorna o id da resposta correta
        switch (correctAnswer) {
            case 'a':
                return R.id.a;
            case 'b':
                return R.id.b;
            case 'c':
                return R.id.c;
            case 'd':
                return R.id.d;
            case 'e':
                return R.id.e;
            default:
                return -1;
        }
    }

    @Override
    public void onClick(View v) {
         if (v.getId() == id) {
            pointMateria.questao += 1;
            pointMateria.correto += 1;
            passe = true;
            Toast toast = Toast.makeText(this, "Correto", Toast.LENGTH_SHORT);
            toast.show();
        } else if (id == -1) {
            passe = true;
            this.mViewHolder.question.setText("Error");
        } else {
            pointMateria.questao++;
            passe = true;
            Toast toast = Toast.makeText(this, "Errado", Toast.LENGTH_SHORT);
            toast.show();
         }

        try {
            Thread.sleep(1000);
            sortear();
            id = idForChar(resp.charAt(0));
        } catch (Exception e){
            this.mViewHolder.question.setText("error cliwue em qualquer next");
            onClick(v);
        }
    }


    @Override
    protected void onStop(){
        super.onStop();
        pointMateria.execute();
    }

    public DatabaseAcess sortear() {
        passe = false;
        DatabaseAcess databaseAcess = DatabaseAcess.getInstance(getApplicationContext());
        String resp = null;
        databaseAcess.open();

        try {
            Random random = new Random();

            int n = random.nextInt(lim);
            String pergunta = databaseAcess.getPergunta(n,0, filtro);
            if(pergunta.equals(null)) return sortear();
            this.mViewHolder.question.setText(pergunta);
            String imagem = databaseAcess.getImagem(n, filtro);
            this.mViewHolder.webView.loadUrl(imagem);
            pergunta = databaseAcess.getPergunta(n,1,filtro);
            this.mViewHolder.question2.setText(pergunta);

            String alternativa;
            alternativa = databaseAcess.getAlternativa(n, "respostaA",filtro);
            this.mViewHolder.alternativaA.setText(alternativa);
            alternativa = databaseAcess.getAlternativa(n, "respostaB",filtro);
            this.mViewHolder.alternativaB.setText(alternativa);
            alternativa = databaseAcess.getAlternativa(n, "respostaC",filtro);
            this.mViewHolder.alternativaC.setText(alternativa);
            alternativa = databaseAcess.getAlternativa(n, "respostaD",filtro);
            this.mViewHolder.alternativaD.setText(alternativa);
            alternativa = databaseAcess.getAlternativa(n, "respostaE",filtro);
            this.mViewHolder.alternativaE.setText(alternativa);

            this.resp = databaseAcess.getAnswer(n,filtro);
            databaseAcess.setPontuacao(pointMateria);

        } catch (Exception e) {
            this.mViewHolder.question.setText("Erro: clique em qualquer botão: \n" + e);
            return sortear();
        }  finally {
            return databaseAcess;
        }
    }

}
