package br.edu.ifspsaocarlos.agenda.settings;

import android.app.Application;

import br.edu.ifspsaocarlos.agenda.activity_realm.MainRealmActivity;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by zigui on 15/12/2017.
 */

public class Configuration extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("AgendaRealm")
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }
}