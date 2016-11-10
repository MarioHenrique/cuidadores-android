package gp1.ihc.cuidadores;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

import br.com.softcare.cuidadores.dto.Cuidador;
import br.com.softcare.cuidadores.dto.ListaDeCuidadores;

public class ListaActivity extends ListActivity implements AdapterView.OnItemClickListener{

    private List<Map<String, Object>> mapListCuidadores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ListaDeCuidadores listaDeCuidadores =  (ListaDeCuidadores) getIntent().getSerializableExtra(BuscaActivity.EXTRA_LISTA_DE_CUIDADORES);
        if (listaDeCuidadores != null) {
            mapListCuidadores = listaDeCuidadores.getMapListCuidadores();
            String[] de = {"nome", "contato"};
            int[] para = {R.id.lista_nome, R.id.lista_contato};
            SimpleAdapter adapter = new SimpleAdapter(this, mapListCuidadores, R.layout.activity_lista, de, para);

            setListAdapter(adapter);

            getListView().setOnItemClickListener(this);
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Map<String, Object> mapCuidador = mapListCuidadores.get(position);
        String email = (String) mapCuidador.get("email");
        Toast.makeText(this, email, Toast.LENGTH_SHORT).show();
    }
}
