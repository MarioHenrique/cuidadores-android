package br.com.softcare.cuidadores.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.softcare.cuidadores.dto.PacienteDTO;
import br.com.softcare.cuidadores.enuns.Perfil;
import gp1.ihc.cuidadores.R;

/**
 * Created by mario on 19/11/16.
 */

public class PacientesAdapter extends BaseAdapter {

    private Context context;
    private List<PacienteDTO> pacientes;

    public PacientesAdapter(Context context, List<PacienteDTO> pacientes){
        this.context = context;
        this.pacientes = pacientes;
    }

    @Override
    public int getCount() {
        return pacientes.size();
    }

    @Override
    public Object getItem(int position) {
        return pacientes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return pacientes.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PacienteDTO paciente = pacientes.get(position);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = convertView;
        if(convertView == null){
            view = inflater.inflate(R.layout.item_paciente, parent, false);
        }

        TextView pacienteNome = (TextView)view.findViewById(R.id.paciente_perfil_nome);
        pacienteNome.setText(paciente.getNome());

        TextView pacienteTelefone = (TextView)view.findViewById(R.id.paciente_perfil_telefone);
        pacienteTelefone.setText(paciente.getContato());

        ImageView imageView = (ImageView)view.findViewById(R.id.paciente_perfil_img);
        imageView.setImageResource(R.drawable.user);

        return view;
    }
}
