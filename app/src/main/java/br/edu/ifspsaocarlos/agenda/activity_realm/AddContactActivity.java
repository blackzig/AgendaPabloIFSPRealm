package br.edu.ifspsaocarlos.agenda.activity_realm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.UUID;

import br.edu.ifspsaocarlos.agenda.R;
import br.edu.ifspsaocarlos.agenda.message.Alert;
import br.edu.ifspsaocarlos.agenda.model.ContatoRealm;
import io.realm.Realm;

public class AddContactActivity extends AppCompatActivity {

    Button btn_add_contact;
    EditText name, fone, email;

    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        name = findViewById(R.id.editTextNomeRealm);
        fone = findViewById(R.id.editTextFoneRealm);
        email = findViewById(R.id.editTextEmailRealm);

        btn_add_contact = findViewById(R.id.btn_add_contact);

        btn_add_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContatoRealm cr = new ContatoRealm();
                String uniqueId = UUID.randomUUID().toString();
                cr.setId(uniqueId);
                cr.setNome(name.getText().toString());
                cr.setFone(fone.getText().toString());
                cr.setEmail(email.getText().toString());

                realm = Realm.getDefaultInstance();
                realm.beginTransaction();
                realm.copyToRealm(cr);
                realm.commitTransaction();

                realm.close();

                Alert a = new Alert();
                a.longAlert(AddContactActivity.this, "Contato adicionado.");

                finish();
            }
        });
    }
}
