package br.com.softcare.cuidadores.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.List;

import br.com.softcare.cuidadores.adapter.ProcedimentoAdapter;
import br.com.softcare.cuidadores.adapter.SintomasAdapter;
import br.com.softcare.cuidadores.client.WebServices;
import br.com.softcare.cuidadores.dto.ProcedimentoDTO;
import br.com.softcare.cuidadores.dto.SintomaDTO;
import br.com.softcare.cuidadores.dto.Usuario;
import br.com.softcare.cuidadores.enuns.Perfil;
import gp1.ihc.cuidadores.R;

public class ProcedimentoActivity extends Activity {

    private Long contratoId = null;
    private Usuario usuarioLogado;
    private List<ProcedimentoDTO> procedimentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procedimento);
        contratoId = getIntent().getLongExtra("contratoId", 0L);
        usuarioLogado = (Usuario)getIntent().getSerializableExtra("usuario");
        if(usuarioLogado.getPerfil().contains(Perfil.RESPONSAVEL)){
            ImageButton button = (ImageButton)findViewById(R.id.procedimento_novo_button);
            button.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        doInBackground(this);
    }

    public void novoProcedimento(View view){
        Intent intent = new Intent(this,ProcedimentoEditActivity.class);
        intent.putExtra("usuario",usuarioLogado);
        intent.putExtra("contratoId",contratoId);
        startActivity(intent);
    }

    @Override
    protected void operation() throws Exception {
        procedimentos  = WebServices.cuidadores.listaDeProcedimentos(contratoId);;
    }

    @Override
    protected void onSuccess() {
        ListView sintomasList =  (ListView)findViewById(R.id.lista_procedimento);
        sintomasList.setAdapter(new ProcedimentoAdapter(this,procedimentos));
        sintomasList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ProcedimentoDTO procedimento = (ProcedimentoDTO)parent.getItemAtPosition(position);
                final Intent intent = new Intent(ProcedimentoActivity.this, ProcedimentoEditActivity.class);
                intent.putExtra("contratoId",contratoId);
                intent.putExtra("procedimento",procedimento);
                intent.putExtra("usuario",usuarioLogado);
                startActivity(intent);
            }
        });
    }
}
