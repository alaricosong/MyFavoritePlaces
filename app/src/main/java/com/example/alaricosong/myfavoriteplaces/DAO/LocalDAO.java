package com.example.alaricosong.myfavoriteplaces.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.example.alaricosong.myfavoriteplaces.adapter.LocalAdapter;
import com.example.alaricosong.myfavoriteplaces.modelo.Local;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alarico Song on 26/11/2017.
 */

public class LocalDAO extends SQLiteOpenHelper{

    public static android.database.sqlite.SQLiteDatabase db;


    public LocalDAO(Context context) {
        super(context, "DB_local", null, 2);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Local (id INTEGER PRIMARY KEY, " +
                "local TEXT NOT NULL, " +
                "endereco TEXT, " +
                "telefone TEXT, " +
                "cidade TEXT, " +
                "observacao TEXT, " +
                "ultimaV DATE, " +
                "nota REAL, " +
                "foto TEXT);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "";
        switch (oldVersion){
            case 1:
                sql = "ALTER TABLE Local ADD COLUMN foto TEXT";
                db.execSQL(sql);
        }

    }

    @NonNull
    private ContentValues pegaDadosLocal(Local local) {
        ContentValues dados = new ContentValues();
        dados.put("local", local.getLocal());
        dados.put("endereco", local.getEndereco());
        dados.put("telefone", local.getTelefone());
        dados.put("cidade", local.getCidade());
        dados.put("observacao", local.getSite());
        dados.put("ultimaV", local.getUtimaVisita());
        dados.put("nota", local.getNota());
        dados.put("foto", local.getFoto());
        return dados;
    }



    public void insere(Local local) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosLocal(local);

        db.insert("Local", null, dados);

    }


    public List<Local> buscaLocais() {

        String sql = "SELECT * FROM Local;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Local> locais = new ArrayList<Local>();

        while(c.moveToNext()) {
            Local local = new Local();
            local.setId(c.getLong(c.getColumnIndex("id")));
            local.setLocal(c.getString(c.getColumnIndex("local")));
            local.setEndereco(c.getString(c.getColumnIndex("endereco")));
            local.setTelefone(c.getString(c.getColumnIndex("telefone")));
            local.setCidade(c.getString(c.getColumnIndex("cidade")));
            local.setSite(c.getString(c.getColumnIndex("observacao")));
            local.setUtimaVisita(c.getString(c.getColumnIndex("ultimaV")));
            local.setNota(c.getDouble(c.getColumnIndex("nota")));
            local.setFoto(c.getString(c.getColumnIndex("foto")));

            locais.add(local);

        }
        c.close();

        return locais;
    }

    public void deleta(Local local) {

        SQLiteDatabase db = getWritableDatabase();

        String[] params = {local.getId().toString()};
        db.delete("Local", "id = ?", params);

    }

    public void altera(Local local) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosLocal(local);

        String[] params = {local.getId().toString()};
        db.update("Local", dados, "id = ?", params);
    }



}