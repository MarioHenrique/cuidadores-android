package br.com.softcare.cuidadores.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        doInBackground(this);
    }

    @Override
    protected void operation() throws Exception {
       pacientes = WebServices.cuidadores.listaDePacientes();
    }

    @Override
    protected void onSuccess() {
        ListView listView = (ListView)findViewById(R.id.lista_pacientes);
        final PacientesAdapter adapter = new PacientesAdapter(PacienteActivity.this, pacientes);
        listView.setAdapter(adapter);
    }

}
