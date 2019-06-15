package com.example.gescom_mobile;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceActivity;

public class Configuration extends PreferenceActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preference);
    }

}
