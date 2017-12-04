package com.example.alaricosong.myfavoriteplaces.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alaricosong.myfavoriteplaces.LocaisActivity;
import com.example.alaricosong.myfavoriteplaces.R;
import com.example.alaricosong.myfavoriteplaces.modelo.Local;

import java.util.List;

/**
 * Created by Alarico Song on 27/11/2017.
 */

public class LocalAdapter extends BaseAdapter {

    private static List<Local> locais;
    private final Context context;


    public LocalAdapter(LocaisActivity context, List<Local> locais) {
        this.context = context;
        this.locais = locais;
    }

    @Override
    public int getCount() {
        return locais.size();
    }

    @Override
    public Object getItem(int position) {
        return locais.get(position);
    }

    @Override
    public long getItemId(int position) {
        return locais.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Local local = locais.get(position);

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = convertView;
        if (convertView == null) {
            view = inflater.inflate(R.layout.list_item, parent, false);
        }

        TextView campoLocal = (TextView) view.findViewById(R.id.item_local);
        campoLocal.setText(local.getLocal());

        TextView campoTelefone = (TextView) view.findViewById(R.id.item_telefone);
        campoTelefone.setText(local.getTelefone());

        ImageView campoFoto = (ImageView) view.findViewById(R.id.item_foto);
        String caminhoFoto = local.getFoto();
        if (caminhoFoto != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);
            Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmap, 100, 100, true);
            campoFoto.setImageBitmap(bitmapReduzido);
            campoFoto.setScaleType(ImageView.ScaleType.FIT_XY);
        }

        return view;
    }

}
