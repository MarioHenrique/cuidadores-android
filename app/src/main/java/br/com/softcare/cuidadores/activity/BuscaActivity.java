package br.com.softcare.cuidadores.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.List;

import br.com.softcare.cuidadores.dto.BuscaDeCuidadoresDTO;
import br.com.softcare.cuidadores.dto.Cuidador;
import br.com.softcare.cuidadores.dto.ListaDeCuidadores;
import br.com.softcare.cuidadores.enuns.Disponibilidade;
import br.com.softcare.cuidadores.enuns.Periodo;
import br.com.softcare.cuidadores.exceptions.BusinessException;
import gp1.ihc.cuidadores.R;
import br.com.softcare.cuidadores.client.WebServices;

import static br.com.softcare.cuidadores.utils.Utils.getValorDaTextView;

public class BuscaActivity extends Activity {

    public static final String EXTRA_LISTA_DE_CUIDADORES = "EXTRA_LIST_CUIDS";
    private static Long ELEMENTO_POR_PAGINA = 1000L;
    private static Long PAGINA_UM = 1L;
    private ListaDeCuidadores listaDeCuidadores = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca);
    }

    public void buscar(View view) {
        doInBackground(this);
    }

    @Override
    protected void operation() throws Exception {
        final BuscaDeCuidadoresDTO buscaDeCuidadoresDTO = new BuscaDeCuidadoresDTO();
        buscaDeCuidadoresDTO.setCep(getValorDaTextView(this, R.id.busca_zipcode));
        buscaDeCuidadoresDTO.setRua(getValorDaTextView(this, R.id.busca_rua));
        buscaDeCuidadoresDTO.setBairro(getValorDaTextView(this, R.id.busca_bairro));
        buscaDeCuidadoresDTO.setCidade(getValorDaTextView(this, R.id.busca_cidade));
        buscaDeCuidadoresDTO.setEstado(getValorDaTextView(this,R.id.busca_estado));
        buscaDeCuidadoresDTO.setEmail(getValorDaTextView(this, R.id.busca_email));
        buscaDeCuidadoresDTO.setContato(getValorDaTextView(this, R.id.busca_contato));
        setarDisponibilidate(buscaDeCuidadoresDTO);
        setarPeriodo(buscaDeCuidadoresDTO);
        buscaDeCuidadoresDTO.setElementosPorPagina(ELEMENTO_POR_PAGINA);
        buscaDeCuidadoresDTO.setPagina(PAGINA_UM);
        listaDeCuidadores = WebServices.cuidadores.buscaDeCuidadores(buscaDeCuidadoresDTO);
    }

    @Override
    protected void onSuccess() {
        final List<Cuidador> cuidadores = listaDeCuidadores.getCuidadores();
        if(cuidadores==null || cuidadores.isEmpty()){
            Toast.makeText(BuscaActivity.this,"Nenhum cuidador encontrado",Toast.LENGTH_SHORT).show();
        }else {
            final Intent intentLista = new Intent(BuscaActivity.this, ListaActivity.class);
            intentLista.putExtra(EXTRA_LISTA_DE_CUIDADORES, listaDeCuidadores);
            startActivity(intentLista);
        }
    }

    private void setarPeriodo(BuscaDeCuidadoresDTO buscaDeCuidadoresDTO) {
        if(isCheckBoxChecked(R.id.busca_manha)){
            buscaDeCuidadoresDTO.adicionarPeriodo(Periodo.MANHA);
        }
        if(isCheckBoxChecked(R.id.busca_tarde)){
            buscaDeCuidadoresDTO.adicionarPeriodo(Periodo.TARDE);
        }
        if(isCheckBoxChecked(R.id.busca_noite)){
            buscaDeCuidadoresDTO.adicionarPeriodo(Periodo.NOITE);
        }
    }

    private void setarDisponibilidate(BuscaDeCuidadoresDTO buscaDeCuidadoresDTO) {
        if(isCheckBoxChecked(R.id.busca_segunda)){
            buscaDeCuidadoresDTO.adicionarDisponibilidade(Disponibilidade.SEGUNDA);
        }
        if(isCheckBoxChecked(R.id.busca_terca)){
            buscaDeCuidadoresDTO.adicionarDisponibilidade(Disponibilidade.TERCA);
        }
        if(isCheckBoxChecked(R.id.busca_quarta)){
            buscaDeCuidadoresDTO.adicionarDisponibilidade(Disponibilidade.QUARTA);
        }
        if(isCheckBoxChecked(R.id.busca_quinta)){
            buscaDeCuidadoresDTO.adicionarDisponibilidade(Disponibilidade.QUINTA);
        }
        if(isCheckBoxChecked(R.id.busca_sexta)){
            buscaDeCuidadoresDTO.adicionarDisponibilidade(Disponibilidade.SEXTA);
        }
        if(isCheckBoxChecked(R.id.busca_sabado)){
            buscaDeCuidadoresDTO.adicionarDisponibilidade(Disponibilidade.SABADO);
        }
        if(isCheckBoxChecked(R.id.busca_domingo)){
            buscaDeCuidadoresDTO.adicionarDisponibilidade(Disponibilidade.DOMINGO);
        }
    }

    private boolean isCheckBoxChecked(int resource){
        return ((CheckBox)findViewById(resource)).isChecked();
    }

}
