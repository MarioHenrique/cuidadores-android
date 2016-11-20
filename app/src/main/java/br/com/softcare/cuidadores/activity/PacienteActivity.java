package br.com.softcare.cuidadores.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import br.com.softcare.cuidadores.adapter.PacientesAdapter;
import br.com.softcare.cuidadores.client.WebServices;
import br.com.softcare.cuidadores.dto.PacienteDTO;
import gp1.ihc.cuidadores.R;

public class PacienteActivity extends Activity {

    private List<PacienteDTO> pacientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paciente);
    }

    @Override
    protected void onResume() {
        super.onResume();
        doInBackground(this);
    }

    @Override
    protected void operation() throws Exception {
       pacientes = WebServices.cuidadores.listaDePacientes();
    }


    public void novoPaciente(View view){
        final Intent intent = new Intent(PacienteActivity.this, PacienteEditActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onSuccess() {
        ListView listView = (ListView)findViewById(R.id.lista_pacientes);
        final PacientesAdapter adapter = new PacientesAdapter(PacienteActivity.this, pacientes);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PacienteDTO paciente  = (PacienteDTO)parent.getItemAtPosition(position);
                final Intent intent = new Intent(PacienteActivity.this, PacienteEditActivity.class);
                intent.putExtra("paciente",paciente);
                startActivity(intent);
            }
        });
    }

}
