package br.com.softcare.cuidadores.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import br.com.softcare.cuidadores.adapter.ImageButtonAdapter;
import gp1.ihc.cuidadores.R;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        GridView gridView = (GridView)findViewById(R.id.gridview);
        gridView.setAdapter(new ImageButtonAdapter(this));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ImageButtonAdapter adapter = (ImageButtonAdapter)parent.getAdapter();
                switch ((int)adapter.getItemId(position)){
                    case R.drawable.calendrio:
                        Toast.makeText(MenuActivity.this,"Calendario",Toast.LENGTH_SHORT).show();
                        break;
                    case R.drawable.pacientes:
                        Toast.makeText(MenuActivity.this,"Pacientes",Toast.LENGTH_SHORT).show();
                        break;
                    case R.drawable.cuidadores:
                        final Intent intentBusca = new Intent(MenuActivity.this, BuscaActivity.class);
                        startActivity(intentBusca);
                        break;
                    case R.drawable.config:
                        Toast.makeText(MenuActivity.this,"Configurações",Toast.LENGTH_SHORT).show();
                        break;
                }

            }
        });
    }
}
