package br.edu.ifspsaocarlos.agenda.activity_realm;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifspsaocarlos.agenda.R;
import br.edu.ifspsaocarlos.agenda.activity.MainActivity;
import br.edu.ifspsaocarlos.agenda.adapter.ContatoAdapter;
import br.edu.ifspsaocarlos.agenda.adapter.ContatoAdapterRealm;
import br.edu.ifspsaocarlos.agenda.message.Alert;
import br.edu.ifspsaocarlos.agenda.model.ContatoRealm;
import io.realm.Realm;

public class MainRealmActivity extends AppCompatActivity {

    private List<ContatoRealm> contacts = new ArrayList<>();
    private Realm realm;

    EditText search_realm;
    ListView list;

    String search="";

    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_realm);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        realm = Realm.getDefaultInstance();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainRealmActivity.this, AddContactActivity.class);
                startActivity(i);
            }
        });

        search_realm = findViewById(R.id.search_realm);
        search_realm.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                if(s.length()>=3){
                    search = s.toString();
                    filteredSearch();
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.i("onTextChanged", String.valueOf(count));
                if(s.length()==0){
                    contacts = realm.where(ContatoRealm.class).findAll();
                    listResult();
                }
            }
        });
    }

    private void filteredSearch(){
        contacts = realm.where(ContatoRealm.class).contains("nome", search).findAll();
        Log.i("search>>>>>>>>>>>>> ",search);
        for(ContatoRealm c: contacts){
            Log.i("Nome>>>>>>>>>>>>> ",c.getNome());
        }
        listResult();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem jf = menu.findItem(R.id.pesqContato);

        jf.setVisible(false);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.tochoose_realm:
                startActivity(new Intent(this, MainRealmActivity.class));
                break;
            case R.id.tochoose_sqlite:
                startActivity(new Intent(this, MainActivity.class));
                break;
            default:
                Alert a = new Alert();
                a.longAlert(this, "Menu choice error");
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        list = findViewById(R.id.lv_contacts);
        if(!search.equals("")){
            contacts = realm.where(ContatoRealm.class).contains("nome", search).findAll();
        }else{
            contacts = realm.where(ContatoRealm.class).findAll();
        }
        listResult();
    }

    public void listResult(){
        ContatoAdapterRealm adapter = new ContatoAdapterRealm(this, contacts);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainRealmActivity.this, EditContactActivity.class);
                intent.putExtra("ID", contacts.get(i).getId());
                startActivity(intent);
            }
        });
    }
}
