package br.edu.ifspsaocarlos.agenda.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import br.edu.ifspsaocarlos.agenda.R;
import br.edu.ifspsaocarlos.agenda.model.Contato;
import br.edu.ifspsaocarlos.agenda.model.ContatoRealm;

/**
 * Created by zigui on 15/12/2017.
 */

public class ContatoAdapterRealm extends ArrayAdapter<ContatoRealm> {

    private Context context;
    private List<ContatoRealm> contacts;

    TextView name, email, fone;

    public ContatoAdapterRealm(Context context, List<ContatoRealm> contacts){
        super(context, R.layout.line, contacts);
        this.context = context;
        this.contacts = contacts;
    }

    public View getView(int position, View view, ViewGroup vg){

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.line, vg, false);

        name = rowView.findViewById(R.id.tv_name);
        name.setText(contacts.get(position).getNome());
        email = rowView.findViewById(R.id.tv_email);
        email.setText(contacts.get(position).getEmail());
        fone = rowView.findViewById(R.id.tv_fone);
        fone.setText(contacts.get(position).getFone());

        return rowView;
    }

}
