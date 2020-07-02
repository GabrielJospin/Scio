package com.example.bancao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseAcess {
    private Cursor c = null;
    private SQLiteDatabase db;
    private SQLiteOpenHelper openHelper;
    private static DatabaseAcess instance;
    int v = 1;

    DatabaseAcess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
        open();

    }

    public static DatabaseAcess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAcess(context);

        }
        return instance;
    }


    public void open() {
        this.db = openHelper.getWritableDatabase();
    }

    public void close() {
        if (db != null) {

            this.db.close();
        }
    }

    public String getPergunta(int numero, int id, String materia) {
        if (id == 0)
            c = db.rawQuery("select pergunta from " +materia+ " where numeroDaQuestão = '" + numero + "'", new String[]{});
        else
            c = db.rawQuery("select pergunta1 from " +materia+ " where numeroDaQuestão = '" + numero + "'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String pergunta = c.getString(0);
            buffer.append("" + pergunta);
        }
        return buffer.toString();

    }

    public String getAlternativa(int numero, String coluna, String materia) {
        c = db.rawQuery("select " + coluna + " from " +materia+ " where numeroDaQuestão = '" + numero + "'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String pergunta = c.getString(0);
            buffer.append("" + pergunta);
        }
        return buffer.toString();

    }

    public String getAnswer(int numero, String materia) {
        c = db.rawQuery("select respostaCerta from "+materia+" where numeroDaQuestão = '" + numero + "'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String pergunta = c.getString(0);
            buffer.append("" + pergunta);
        }
        return buffer.toString();
    }

    public String getImagem(int numero, String materia) {
        c = db.rawQuery("select URL from "+materia+" where numeroDaQuestão = '" + numero + "'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String pergunta = c.getString(0);
            buffer.append("" + pergunta);
        }
        return buffer.toString();
    }

    public int getQuestao(int numero) {
        c = db.rawQuery("select questao from Pontuacao2 where id = '" + numero + "'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String pergunta = c.getString(0);
            buffer.append("" + pergunta);
        }
        if(buffer.toString().equals("")) return 0;
        return Integer.parseInt(buffer.toString());
    }

    public  int getCorreto(int numero) {
        c = db.rawQuery("select correto from Pontuacao2 where id = '" + numero + "'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String pergunta = c.getString(0);
            buffer.append("" + pergunta);
        }
        if(buffer.toString().equals("")) return 0;
        return Integer.parseInt(buffer.toString());
    }
    public  int getPerc(int numero) {
        c = db.rawQuery("select perc from Pontuacao2 where id = '" + numero + "'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String pergunta = c.getString(0);
            buffer.append("" + pergunta);
        }
        if(buffer.toString().equals("")) return 0;
        return Integer.parseInt(buffer.toString());
    }

    public void setPontuacao(Materia materia) {
        double perc = 100;
        if(materia.questao.equals(0));
        else perc = (100*materia.correto/materia.questao);
        c = db.rawQuery("UPDATE Pontuacao2 SET questao = " + materia.questao + ", correto = " + materia.correto + " , perc = " + perc + " WHERE id = " + materia.id, new String[]{});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String pergunta = c.getString(0);
            buffer.append("" + pergunta);
        }

    }
}