package gp1.ihc.cuidadores;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class BuscaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca);
        Button botaoBuscar = (Button) findViewById(R.id.busca_botao);
        botaoBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BuscaActivity.this, "Buscando.........", Toast.LENGTH_SHORT).show();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Toast.makeText(BuscaActivity.this, "Erro ao buscar", Toast.LENGTH_SHORT).show();
                }
                Intent intentLista = new Intent(BuscaActivity.this, ListaActivity.class);
                startActivity(intentLista);
            }
        });
    }


}
