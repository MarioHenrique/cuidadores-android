package gp1.ihc.cuidadores;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import br.com.softcare.cuidadores.dto.BuscaDeCuidadoresDTO;
import br.com.softcare.cuidadores.dto.ListaDeCuidadores;
import br.com.softcare.cuidadores.exceptions.BusinessException;
import gp1.ihc.cuidadores.ws.WebServices;

import static gp1.ihc.cuidadores.Utils.getValorDaTextView;
import static gp1.ihc.cuidadores.ws.WebServices.cuidadores;

public class BuscaActivity extends AppCompatActivity {

    public static final String EXTRA_LISTA_DE_CUIDADORES = "EXTRA_LIST_CUIDS";
    private static Long ELEMENTO_POR_PAGINA = 1000L;
    private static Long PAGINA_UM = 1L;

    private ProgressDialog progress;

    private final BuscaDeCuidadoresDTO buscaDeCuidadoresDTO = new BuscaDeCuidadoresDTO();

    private ListaDeCuidadores listaDeCuidadores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca);
    }

    public void buscar(View view) {

        final Intent intentLista = new Intent(BuscaActivity.this, ListaActivity.class);

        buscaDeCuidadoresDTO.setEmail(getValorDaTextView(this, R.id.busca_email));
        buscaDeCuidadoresDTO.setCep(getValorDaTextView(this, R.id.busca_zipcode));
        buscaDeCuidadoresDTO.setContato(getValorDaTextView(this, R.id.busca_contato));
        buscaDeCuidadoresDTO.setBairro(getValorDaTextView(this, R.id.busca_bairro));
        buscaDeCuidadoresDTO.setCidade(getValorDaTextView(this, R.id.busca_cidade));
        buscaDeCuidadoresDTO.setRua(getValorDaTextView(this, R.id.busca_rua));
        buscaDeCuidadoresDTO.setElementosPorPagina(ELEMENTO_POR_PAGINA);
        buscaDeCuidadoresDTO.setPagina(PAGINA_UM);

        Thread rest = new Thread(new Runnable() {

            @Override
            public void run() {


                // Handler responsavel para fazer alteração na tela, toda alteração na tela precisa
                // fazer um post passando um Runnable que contem a atualização na tela, pois esse handler
                // executa operações com a thread principal
                Handler mainHandler  = new Handler(getMainLooper());

                try {
                    //chamada do webService
                    listaDeCuidadores = WebServices.cuidadores.buscaDeCuidadores(buscaDeCuidadoresDTO);

                    //como mencionado acima, crio um runnable para atualizar a tela, passando para a tela de busca de cuidadores
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            //retiro o dialog com o carregamento

                            //mudança de tela
                            intentLista.putExtra(EXTRA_LISTA_DE_CUIDADORES, listaDeCuidadores);
                            startActivity(intentLista);
                            progress.dismiss();
                        }
                    });
                } catch (final BusinessException e) {
                    //outro post passando um runnable para criar a mensagem na tela
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            //retiro o dialog com o carregamento
                            progress.dismiss();

                            //Mensagem na tela
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }
        );

        //Inicio a thread para pesquisa
        rest.start();

        //Coloco o dialog de carregamento
        progress = ProgressDialog.show(this,"Por favor aguarde", "Processando", true);

    }



}
