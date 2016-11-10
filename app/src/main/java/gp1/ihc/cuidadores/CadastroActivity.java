package gp1.ihc.cuidadores;

import android.app.ProgressDialog;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import br.com.softcare.cuidadores.dto.UsuarioAlteracao;
import br.com.softcare.cuidadores.enuns.Disponibilidade;
import br.com.softcare.cuidadores.enuns.Perfil;
import br.com.softcare.cuidadores.enuns.Periodo;
import br.com.softcare.cuidadores.exceptions.BusinessException;
import gp1.ihc.cuidadores.ws.WebServices;

import static gp1.ihc.cuidadores.Utils.getValorDaTextView;

public class CadastroActivity extends AppCompatActivity {

    private Spinner spinnerDisponibilidade;
    private Spinner spinnerPeriodo;
    private Spinner spinnerPerfil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.perfil, android.R.layout.simple_spinner_item);
        spinnerPerfil = (Spinner) findViewById(R.id.cadastro_perfil);
        spinnerPerfil.setAdapter(adapter2);
    }

    final UsuarioAlteracao usuario = new UsuarioAlteracao();

    private ProgressDialog progress;

    public void cadastrar(View view) {
        usuario.setContato(getValorDaTextView(this, R.id.cadastro_contato));
        usuario.setCep(getValorDaTextView(this, R.id.cadastro_zipcode));
        usuario.setEmail(getValorDaTextView(this, R.id.cadastro_email));
        usuario.setNome(getValorDaTextView(this, R.id.cadastro_nome));
        usuario.setSenha(getValorDaTextView(this, R.id.cadastro_senha));
        usuario.adicionarPerfil(Perfil.valueOf(((Spinner)findViewById(R.id.cadastro_perfil)).getSelectedItem().toString()));
        usuario.adicionarDisponibilidade(Disponibilidade.SEGUNDA);
        usuario.adicionarDisponibilidade(Disponibilidade.TERCA);
        usuario.adicionarDisponibilidade(Disponibilidade.QUARTA);
        usuario.adicionarDisponibilidade(Disponibilidade.QUINTA);
        usuario.adicionarDisponibilidade(Disponibilidade.SEGUNDA);
        usuario.adicionarPeriodo(Periodo.MANHA);
        usuario.adicionarPeriodo(Periodo.TARDE);
        Thread rest = new Thread(new Runnable() {

            @Override
            public void run() {


                // Handler responsavel para fazer alteração na tela, toda alteração na tela precisa
                // fazer um post passando um Runnable que contem a atualização na tela, pois esse handler
                // executa operações com a thread principal
                Handler mainHandler  = new Handler(getMainLooper());

                try {
                    //chamada do webService
                    WebServices.cuidadores.cadastrarUsuario(usuario);

                    //como mencionado acima, crio um runnable para atualizar a tela, passando para a tela de busca de cuidadores
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            //retiro o dialog com o carregamento
                            progress.dismiss();
                            //mudança de tela
                            Toast.makeText(getApplicationContext(), "Usuário cadastrado com sucesso.", Toast.LENGTH_SHORT).show();
                            finish();

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
