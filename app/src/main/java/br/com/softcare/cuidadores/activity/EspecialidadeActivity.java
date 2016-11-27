package br.com.softcare.cuidadores.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import br.com.softcare.cuidadores.adapter.EspecialidadeAdapter;
import br.com.softcare.cuidadores.adapter.TratamentoAdapter;
import br.com.softcare.cuidadores.client.WebServices;
import br.com.softcare.cuidadores.dto.Cuidador;
import br.com.softcare.cuidadores.dto.EspecialidadeDTO;
import gp1.ihc.cuidadores.R;

public class EspecialidadeActivity extends Activity {

    private List<EspecialidadeDTO> especialidades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_especialidade);
    }

    public void novaEspecialidade(View view){
        final Intent intent = new Intent(EspecialidadeActivity.this, EspecialidadeEditActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        doInBackground(this);
    }

    @Override
    protected void operation() throws Exception {
        especialidades = WebServices.cuidadores.listarEspecialidades();
    }

    @Override
    protected void onSuccess() {
        ListView lista = (ListView)findViewById(R.id.lista_especialidades);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Intent intent = new Intent(EspecialidadeActivity.this, EspecialidadeEditActivity.class);
                EspecialidadeDTO especialidade = (EspecialidadeDTO)parent.getItemAtPosition(position);
                intent.putExtra("especialidade",especialidade);
                startActivity(intent);
            }
        });
        lista.setAdapter(new EspecialidadeAdapter(this, especialidades));
    }
}
