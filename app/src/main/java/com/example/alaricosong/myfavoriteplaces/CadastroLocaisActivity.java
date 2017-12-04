package com.example.alaricosong.myfavoriteplaces;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.*;
import android.widget.*;

import com.example.alaricosong.myfavoriteplaces.DAO.LocalDAO;
import com.example.alaricosong.myfavoriteplaces.adapter.LocalAdapter;
import com.example.alaricosong.myfavoriteplaces.modelo.Local;

import java.io.File;

public class CadastroLocaisActivity extends AppCompatActivity{

    public static final int codigo_camera = 567;
    private CadastroLocaisHelper helper;
    private String caminhoFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_cad_locais);

        helper = new CadastroLocaisHelper(this);

        Intent intent = getIntent();
        Local local = (Local) intent.getSerializableExtra("local");
        if (local != null) {
            helper.preencheCadastro(local);
        }

        Button photoBtn = (Button) findViewById(R.id.btn_photo);
        photoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                caminhoFoto = getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".jpg";
                File arquivoFoto = new File(caminhoFoto);
                intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(arquivoFoto));
                startActivityForResult(intentCamera, codigo_camera);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == codigo_camera) {
                helper.carregaImagem(caminhoFoto);

            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_act_cad_locais, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_cadastro_ok:
                Local local = helper.pegaLocal();

                LocalDAO dao = new LocalDAO(this);
                if (local.getId() != null) {
                    dao.altera(local);
                    Toast.makeText(CadastroLocaisActivity.this, "Local " + local.getLocal() + " Alterado!", Toast.LENGTH_LONG).show();
                } else {
                    dao.insere(local);
                    Toast.makeText(CadastroLocaisActivity.this, "Local " + local.getLocal() + " Salvo!", Toast.LENGTH_LONG).show();
                }
                dao.close();

                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}