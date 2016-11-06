package gp1.ihc.cuidadores;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListaActivity extends AppCompatActivity {

    String[] cuidadores = {
            "Matheus",
            "Priscila",
            "Alexandre"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cuidadores);
        ListView lista = (ListView) findViewById(R.id.activity_lista);
        lista.setAdapter(adapter);
    }
}
