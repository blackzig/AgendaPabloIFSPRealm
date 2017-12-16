package br.edu.ifspsaocarlos.agenda.activity_realm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.edu.ifspsaocarlos.agenda.R;
import br.edu.ifspsaocarlos.agenda.message.Alert;
import br.edu.ifspsaocarlos.agenda.model.ContatoRealm;
import io.realm.Realm;

public class EditContactActivity extends AppCompatActivity implements View.OnClickListener{

    EditText edit_name, edit_fone, edit_email;
    Button btn_delete, btn_update;

    Realm realm;
    ContatoRealm contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        realm = Realm.getDefaultInstance();
        String id = getIntent().getStringExtra("ID");
        contact = realm.where(ContatoRealm.class).equalTo("id", id).findFirst();

        edit_name = findViewById(R.id.editTextNomeRealm);
        edit_fone = findViewById(R.id.editTextFoneRealm);
        edit_email = findViewById(R.id.editTextEmailRealm);

        edit_name.setText(contact.getNome());
        edit_fone.setText(contact.getFone());
        edit_email.setText(contact.getEmail());

        btn_update = findViewById(R.id.btn_update_contact);
        btn_update.setOnClickListener(this);
        btn_delete = findViewById(R.id.btn_delete_contact);
        btn_delete.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_update_contact:
                updateBook();
                break;
            case R.id.btn_delete_contact:
                deleteBook();
                break;
            default:
                Alert a = new Alert();
                a.longAlert(this, "System Error");
                break;
        }
    }

    private void updateBook(){
        realm.beginTransaction();
        contact.setNome(edit_name.getText().toString());
        contact.setFone(edit_fone.getText().toString());
        contact.setEmail(edit_email.getText().toString());
        realm.copyToRealm(contact);
        realm.commitTransaction();
        realm.close();
        Alert a = new Alert();
        a.longAlert(this, "Contato atualizado.");
        finish();
    }

    private void deleteBook(){
        realm.beginTransaction();
        contact.deleteFromRealm();
        realm.commitTransaction();
        realm.close();
        Alert a = new Alert();
        a.longAlert(this, "Contato removido.");
        finish();
    }
}
