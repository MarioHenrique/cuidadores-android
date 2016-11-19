package br.com.softcare.cuidadores.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import br.com.softcare.cuidadores.adapter.CuidadoresAdapter;
import br.com.softcare.cuidadores.dto.Cuidador;
import br.com.softcare.cuidadores.dto.ListaDeCuidadores;
import gp1.ihc.cuidadores.R;

public class ListaActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_lista);

            ListaDeCuidadores listaDeCuidadores =  (ListaDeCuidadores) getIntent().getSerializableExtra(BuscaActivity.EXTRA_LISTA_DE_CUIDADORES);

            ListView listView = (ListView)findViewById(R.id.lista_cuidadores);
            ImageButton imageButton = (ImageButton)findViewById(R.id.lista_pesquisa);

            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

            CuidadoresAdapter adapter = new CuidadoresAdapter(this,listaDeCuidadores.getCuidadores());

            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Cuidador cuidador =  (Cuidador)parent.getItemAtPosition(position);
                    Intent intent = new Intent(ListaActivity.this,CuidadorActivity.class);
                    intent.putExtra("cuidador",cuidador);
                    startActivity(intent);
                }
            });

    }

}
