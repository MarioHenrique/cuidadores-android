package br.com.softcare.cuidadores.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.List;

import br.com.softcare.cuidadores.adapter.SintomasAdapter;
import br.com.softcare.cuidadores.client.WebServices;
import br.com.softcare.cuidadores.dto.SintomaDTO;
import br.com.softcare.cuidadores.dto.TratamentoDTO;
import br.com.softcare.cuidadores.dto.Usuario;
import br.com.softcare.cuidadores.enuns.Perfil;
import gp1.ihc.cuidadores.R;

public class SintomesActivity extends Activity {

    private Long contratoId = null;
    private Usuario usuarioLogado = null;
    private List<SintomaDTO> sintomas = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sintomes);
        contratoId = getIntent().getLongExtra("contratoId",0L);
        usuarioLogado = (Usuario)getIntent().getSerializableExtra("usuario");
        if(usuarioLogado.getPerfil().contains(Perfil.RESPONSAVEL)){
            ImageButton button = (ImageButton)findViewById(R.id.novo_sintomas_button);
            button.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        doInBackground(this);
    }

    public void novoSintoma(View viwe){
        final Intent intent = new Intent(this, SintomasEditActivity.class);
        intent.putExtra("contratoId",contratoId);
        startActivity(intent);
    }

    @Override
    protected void operation() throws Exception {
        sintomas = WebServices.cuidadores.listaDeSintomas(contratoId);
    }

    @Override
    protected void onSuccess() {
        ListView sintomasList =  (ListView)findViewById(R.id.lista_tratamentos);
        sintomasList.setAdapter(new SintomasAdapter(this,sintomas));
        sintomasList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SintomaDTO sintoma = (SintomaDTO)parent.getItemAtPosition(position);
                final Intent intent = new Intent(SintomesActivity.this, SintomasEditActivity.class);
                intent.putExtra("contratoId",contratoId);
                intent.putExtra("sintoma",sintoma);
                intent.putExtra("usuario",usuarioLogado);
                startActivity(intent);
            }
        });
    }
}
