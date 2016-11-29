package br.com.softcare.cuidadores.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.LinkedHashMap;
import java.util.List;

import br.com.softcare.cuidadores.adapter.ContratosAdapter;
import br.com.softcare.cuidadores.adapter.PerfilAdapter;
import br.com.softcare.cuidadores.adapter.StatusDaPropostaAdapter;
import br.com.softcare.cuidadores.adapter.TratamentoAdapter;
import br.com.softcare.cuidadores.client.WebServices;
import br.com.softcare.cuidadores.dto.LinkedMapTransferDTO;
import br.com.softcare.cuidadores.dto.PropostaResponseDTO;
import br.com.softcare.cuidadores.dto.TratamentoDTO;
import br.com.softcare.cuidadores.dto.Usuario;
import br.com.softcare.cuidadores.enuns.Perfil;
import br.com.softcare.cuidadores.enuns.Status;
import gp1.ihc.cuidadores.R;

public class ContratoActivity extends Activity {

    private List<LinkedHashMap> propostas;
    private Usuario usuarioLogado;
    private Status statusDoContrato = Status.INITIALIZED;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contrato);
        usuarioLogado = (Usuario)getIntent().getSerializableExtra("usuario");
        Spinner contratoSpinner = (Spinner) findViewById(R.id.contrato_status_escolha);
        contratoSpinner.setAdapter(new StatusDaPropostaAdapter(this));

    }

    public void pesquisaStatus(View view){
        Spinner spinner = (Spinner) findViewById(R.id.contrato_status_escolha);
        statusDoContrato = (Status)spinner.getSelectedItem();
        ContratoActivity.this.onResume();
    }

    @Override
    protected void onResume() {
        super.onResume();
        doInBackground(this);
    }

    @Override
    protected void operation() throws Exception {
        propostas = WebServices.cuidadores.listaDeContratos(statusDoContrato);
    }

    @Override
    protected void onSuccess() {
        ListView lista = (ListView)findViewById(R.id.lista_contratos);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Intent intent = new Intent(ContratoActivity.this, ContratoConsultaActivity.class);
                LinkedHashMap contrato = (LinkedHashMap)parent.getItemAtPosition(position);
                final LinkedMapTransferDTO linked = new LinkedMapTransferDTO(contrato);
                intent.putExtra("contrato",linked);
                intent.putExtra("usuario",usuarioLogado);
                startActivity(intent);
            }
        });
        lista.setAdapter(new ContratosAdapter(this,propostas));

    }
}
