package br.edu.ifspsaocarlos.agenda.message;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by zigui on 15/12/2017.
 */

public class Alert {

    public void shortAlert(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public void longAlert(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}