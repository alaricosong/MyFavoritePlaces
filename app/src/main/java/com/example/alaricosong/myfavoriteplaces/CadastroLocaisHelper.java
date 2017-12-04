package com.example.alaricosong.myfavoriteplaces;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;

import com.example.alaricosong.myfavoriteplaces.modelo.Local;

/**
 * Created by Alarico Song on 26/11/2017.
 */

public class CadastroLocaisHelper {

    private final EditText campoLocal;
    private final EditText campoEndereco;
    private final EditText campoTelefone;
    private final EditText campoCidade;
    private final EditText campoUltima;
    private final EditText campoSite;
    private final RatingBar campoNota;
    private final ImageView campoFoto;
    private Local local;

    public CadastroLocaisHelper(CadastroLocaisActivity activity){
        campoLocal = (EditText) activity.findViewById(R.id.txt_place);
        campoEndereco = (EditText) activity.findViewById(R.id.txt_adress);
        campoTelefone = (EditText) activity.findViewById(R.id.txt_phone);
        campoCidade = (EditText) activity.findViewById(R.id.txt_city);
        campoUltima = (EditText) activity.findViewById(R.id.txt_last);
        campoSite = (EditText) activity.findViewById(R.id.txt_site);
        campoNota = (RatingBar) activity.findViewById(R.id.rb_rate);
        campoFoto = (ImageView) activity.findViewById(R.id.photo);
        local = new Local();

    }

    public Local pegaLocal() {
        local.setLocal(campoLocal.getText().toString());
        local.setEndereco(campoEndereco.getText().toString());
        local.setTelefone(campoTelefone.getText().toString());
        local.setCidade(campoCidade.getText().toString());
        local.setSite(campoSite.getText().toString());
        local.setUtimaVisita(campoUltima.getText().toString());
        local.setNota(Double.valueOf(campoNota.getProgress()));
        local.setFoto((String) campoFoto.getTag());
        return local;
    }

    public void preencheCadastro(Local local) {
        campoLocal.setText(local.getLocal());
        campoEndereco.setText(local.getEndereco());
        campoTelefone.setText(local.getTelefone());
        campoCidade.setText(local.getCidade());
        campoSite.setText(local.getSite());
        campoUltima.setText(local.getUtimaVisita());
        campoNota.setProgress(local.getNota().intValue());
        carregaImagem(local.getFoto());
        this.local = local;

    }

    public void carregaImagem(String caminhoFoto) {
        if(caminhoFoto != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);
            Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmap, 300, 300, true);
            campoFoto.setImageBitmap(bitmapReduzido);
            campoFoto.setScaleType(ImageView.ScaleType.FIT_XY);
            campoFoto.setTag(caminhoFoto);
        }
    }

}
