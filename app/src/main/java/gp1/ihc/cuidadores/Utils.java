package gp1.ihc.cuidadores;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by izcss on 09/11/2016.
 */

public class Utils {
    @NonNull
    public static String getValorDaTextView(AppCompatActivity activity, int id) {
        return ((TextView)activity.findViewById(id)).getText().toString();
    }

}
