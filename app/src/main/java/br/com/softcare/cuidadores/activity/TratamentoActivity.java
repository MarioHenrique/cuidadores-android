package br.com.softcare.cuidadores.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import java.io.Serializable;
import java.util.List;

import br.com.softcare.cuidadores.adapter.TratamentoAdapter;
import br.com.softcare.cuidadores.client.WebServices;
import br.com.softcare.cuidadores.dto.PacienteDTO;
import br.com.softcare.cuidadores.dto.TratamentoDTO;
import br.com.softcare.cuidadores.dto.TratamentosTransferDTO;
import gp1.ihc.cuidadores.R;

public class TratamentoActivity extends Activity {

    private PacienteDTO paciente;
    private List<TratamentoDTO> tratamentos;
    private boolean verApenas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tratamento);
        paciente = (PacienteDTO)getIntent().getSerializableExtra("paciente");
        verApenas = getIntent().getBooleanExtra("verApenas", false);
        if(verApenas){
            tratamentos = ((TratamentosTransferDTO)getIntent().getSerializableExtra("tratamentos")).getTratamentos();
            ImageButton novo =  (ImageButton)findViewById(R.id.novo_tratamento);
            novo.setVisibility(View.GONE);
        }
    }

    public void novoPaciente(View view){
        final Intent intent = new Intent(TratamentoActivity.this, TratamentoEditActivity.class);
        intent.putExtra("paciente",paciente);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        doInBackground(this);
    }

    @Override
    protected void operation() throws Exception {
        if(!verApenas)
            tratamentos = WebServices.cuidadores.listaTratamentos(paciente.getId());
    }

    @Override
    protected void onSuccess() {
        ListView lista = (ListView)findViewById(R.id.lista_tratamentos);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Intent intent = new Intent(TratamentoActivity.this, TratamentoEditActivity.class);
                TratamentoDTO tratamento = (TratamentoDTO)parent.getItemAtPosition(position);
                intent.putExtra("tratamento",tratamento);
                intent.putExtra("verApenas",verApenas);
                intent.putExtra("paciente",paciente);
                startActivity(intent);
            }
        });
        lista.setAdapter(new TratamentoAdapter(this,tratamentos));
    }
}
