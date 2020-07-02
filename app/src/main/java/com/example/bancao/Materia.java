package com.example.bancao;

import android.content.Context;
import android.provider.ContactsContract;

public class Materia {
    int id;
    public Integer questao;
    public Integer correto;
    public double perc;
    private DatabaseAcess db;

    Materia(int id, DatabaseAcess databaseAcess){
        this.id = id;
        this.db=databaseAcess;
        this.questao = db.getQuestao(id);
        this.correto = db.getCorreto(id);
        if (questao == 0 || questao == null) {
            this.perc = 100;
        } else {
            this.perc = (correto/questao)* 100;
        }
    }

    void execute(){
         if(!questao.equals(0) )this.perc = (correto/questao)* 100;
        else this.perc = 100;
    }
}
