package br.com.softcare.cuidadores.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import br.com.softcare.cuidadores.enuns.Perfil;
import gp1.ihc.cuidadores.R;

/**
 * Created by mario on 15/11/16.
 */

public class PerfilAdapter extends BaseAdapter {

    List<Perfil> perfis = Arrays.asList(Perfil.CUIDADOR,Perfil.RESPONSAVEL);
    List<Integer> resource = Arrays.asList(R.drawable.cuidador,R.drawable.responsavel);
    private Context context;

    public PerfilAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return perfis.size();
    }

    @Override
    public Object getItem(int position) {
        return perfis.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Perfil perfil = perfis.get(position);
        Integer resourceId = resource.get(position);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = convertView;
        if(convertView == null){
            view = inflater.inflate(R.layout.item_perfil, parent, false);
        }
        TextView perfilName = (TextView)view.findViewById(R.id.cadastro_perfil_nome);
        perfilName.setText(perfil.getLabel());

        ImageView imageView = (ImageView)view.findViewById(R.id.cadastro_perfil_img);
        imageView.setImageResource(resourceId);

        return view;
    }

}
