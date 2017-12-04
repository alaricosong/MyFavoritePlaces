package com.example.alaricosong.myfavoriteplaces;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.alaricosong.myfavoriteplaces.DAO.LocalDAO;
import com.example.alaricosong.myfavoriteplaces.adapter.LocalAdapter;
import com.example.alaricosong.myfavoriteplaces.modelo.Local;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LocaisActivity extends AppCompatActivity {

    private ListView lst_locais;
    private Button new_place;


    private void carregaLista() {
        LocalDAO dao = new LocalDAO(this);
        List<Local> locais = dao.buscaLocais();
        dao.close();

        //ArrayAdapter<Local> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, locais);
        //lst_locais.setAdapter(adapter);

        LocalAdapter adapter = new LocalAdapter(this, locais);
        Comparator<Local> comp = new Comparator<Local>() {
            public int compare(Local l1, Local l2) {
                return l1.getLocal().compareTo(l2.getLocal());
            }
        };
        Collections.sort(locais,comp);
        lst_locais.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_locais);
        lst_locais = (ListView) findViewById(R.id.lst_locais);

        lst_locais.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int position, long id) {
                Local local = (Local) lst_locais.getItemAtPosition(position);

                Intent VaiProCadastro = new Intent(LocaisActivity.this, CadastroLocaisActivity.class);
                VaiProCadastro.putExtra("local",local);
                startActivity(VaiProCadastro);
            }
        });

        new_place = (Button) findViewById(R.id.new_place);
        new_place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inp;
                inp = new Intent(LocaisActivity.this, CadastroLocaisActivity.class);
                startActivity(inp);
            }
        });

        registerForContextMenu(lst_locais);

    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        final Local local = (Local) lst_locais.getItemAtPosition(info.position);

        MenuItem itemTelefone = menu.add("Ligar para Telefone");
        Intent intentTelefone = new Intent(Intent.ACTION_VIEW);
        intentTelefone.setData(Uri.parse("tel:"+local.getTelefone()));
        itemTelefone.setIntent(intentTelefone);

        MenuItem itemSMS = menu.add("Enviar SMS");
        Intent intentSMS = new Intent(Intent.ACTION_VIEW);
        intentSMS.setData(Uri.parse("sms:"+local.getTelefone()));
        itemSMS.setIntent(intentSMS);

        MenuItem itemMapa = menu.add("Visualizar Mapa");
        Intent intentMapa = new Intent(Intent.ACTION_VIEW);
        intentMapa.setData(Uri.parse("geo:0,0?q="+local.getEndereco()));
        itemMapa.setIntent(intentMapa);

        MenuItem itemSite = menu.add("Visitar Site");
        Intent intentSite = new Intent(Intent.ACTION_VIEW);
        String site = local.getSite();
        if(!site.startsWith("http://")){
            site = "http://" + site;
        }
        intentSite.setData(Uri.parse(site));
        itemSite.setIntent(intentSite);

        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                 AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                 Local local = (Local) lst_locais.getItemAtPosition(info.position);

                 LocalDAO dao = new LocalDAO(LocaisActivity.this);
                 dao.deleta(local);
                 dao.close();

                 Toast.makeText(LocaisActivity.this, "Local "+ local.getLocal() + " deletado!!", Toast.LENGTH_SHORT).show();
                 carregaLista();
                 return false;
            }
        });

    }

}