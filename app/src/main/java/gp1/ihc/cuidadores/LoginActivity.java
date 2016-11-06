package gp1.ihc.cuidadores;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.softcare.cuidadores.client.CuidadoresCliente;
import br.com.softcare.cuidadores.dto.Usuario;
import br.com.softcare.cuidadores.exceptions.BusinessException;
import gp1.ihc.cuidadores.ws.WebServices;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    private ProgressDialog progress;

    public void entrar(View view) {
        //O intent para navegação precisa ficar fora do bloco da thread
        final Intent intentBusca = new Intent(this, BuscaActivity.class);

        //Nova thread para fazer a chamada do webService
        Thread rest = new Thread(new Runnable() {

            @Override
            public void run() {

                //Recuperando os valores da tela
                final String senha = ((EditText) findViewById(R.id.login_senha)).getText().toString();
                final String email = ((EditText) findViewById(R.id.login_email)).getText().toString();

                // Handler responsavel para fazer alteração na tela, toda alteração na tela precisa
                // fazer um post passando um Runnable que contem a atualização na tela, pois esse handler
                // executa operações com a thread principal
                Handler mainHandler  = new Handler(getMainLooper());

                try {
                    //chamada do webService
                    WebServices.cuidadores.login(email, senha);

                    //como mencionado acima, crio um runnable para atualizar a tela, passando para a tela de busca de cuidadores
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            //retiro o dialog com o carregamento
                            progress.dismiss();
                            //mudança de tela
                            startActivity(intentBusca);
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
        progress = ProgressDialog.show(LoginActivity.this,"Por favor aguarde", "Processando", true);

    }

    public void cadastrar(View view) {
        Intent intentCadastrar = new Intent(this, CadastroActivity.class);
        startActivity(intentCadastrar);
    }

}
