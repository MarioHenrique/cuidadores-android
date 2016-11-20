package br.com.softcare.cuidadores.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by mario on 17/11/16.
 */

public abstract class Activity extends AppCompatActivity{

    private ProgressDialog progress;

    protected final void doInBackground(final Context context){
        final Handler uiThread = new Handler(getMainLooper());
        final Thread task = new Thread(new Runnable() {
            @Override
            public void run() {
                uiThread.post(new Runnable() {
                    @Override
                    public void run() {
                        progress = ProgressDialog.show(context, "Por favor aguarde", "Processando", true);
                    }
                });
                try {
                    operation();
                    uiThread.post(new Runnable() {
                        @Override
                        public void run() {
                            onSuccess();
                            progress.dismiss();
                        }
                    });
                }catch(final Exception e){
                    uiThread.post(new Runnable() {
                        @Override
                        public void run() {
                            progress.dismiss();
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        task.start();
    }

    protected abstract void  operation() throws Exception;

    protected abstract void onSuccess();

}
