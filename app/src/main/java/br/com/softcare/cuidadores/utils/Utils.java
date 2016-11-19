package br.com.softcare.cuidadores.utils;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * Created by izcss on 09/11/2016.
 */

public class Utils {

    @NonNull
    public static String getValorDaTextView(AppCompatActivity activity, int id) {
        return ((TextView)activity.findViewById(id)).getText().toString();
    }

    @NonNull
    public static boolean isCheckBoxChecked(AppCompatActivity activity,int resource){
        return ((CheckBox)activity.findViewById(resource)).isChecked();
    }

    @NonNull
    public static void setValorDaTextView(AppCompatActivity activity, int id,String texto){
        ((TextView)activity.findViewById(id)).setText(texto);
    }

    @NonNull
    public static void activiCheckBox(AppCompatActivity activity,int resource){
        CheckBox check = (CheckBox)activity.findViewById(resource);
        check.setChecked(true);
    }

}
