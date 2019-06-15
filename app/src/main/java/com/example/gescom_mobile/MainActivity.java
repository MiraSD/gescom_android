package com.example.gescom_mobile;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.gescom_mobile.helpers.LoginTask;
import com.example.gescom_mobile.lists.ProductsList;

public class MainActivity extends Activity {

    public static final int LOGIN_REQUEST_CODE = 0;

    private Button bLogin;
    private Button configuration ;
    private EditText etEmailAddress, etPassword;
    private final Class<?> LOGIN_DESTINATION = ProductsList.class;
    private SharedPreferences sharedPreferences;

    public void login() {
        this.sharedPreferences.edit().putBoolean("user_logged_in", true)
                .commit();
        this.sharedPreferences
                .edit()
                .putString("username", this.etEmailAddress.getText().toString())
                .commit();
        this.sharedPreferences.edit()
                .putString("password", this.etPassword.getText().toString())
                .commit();
        this.startActivityForResult(new Intent(MainActivity.this,
                this.LOGIN_DESTINATION), MainActivity.LOGIN_REQUEST_CODE);
    }

    /*
     * (non-Javadoc)
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        // get shared preferences
        this.sharedPreferences = this.getPreferences(Context.MODE_PRIVATE);

        // check if user is logged in already
        if (this.sharedPreferences.getBoolean("user_logged_in", false)) {
            // user is logged in, bypass activity
            this.startActivityForResult(new Intent(MainActivity.this,
                    this.LOGIN_DESTINATION), MainActivity.LOGIN_REQUEST_CODE);
        }

        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.activity_main);
        this.bLogin = (Button) this.findViewById(R.id.submit);
        this.configuration = (Button) this.findViewById(R.id.configuration);
        this.etEmailAddress = (EditText) this.findViewById(R.id.username);
        this.etPassword = (EditText) this.findViewById(R.id.password);

        this.bLogin.setOnClickListener(new View.OnClickListener() {
            /*
             * (non-Javadoc)
             * @see android.view.View.OnClickListener#onClick(android.view.View)
             */
            @Override
            public void onClick(final View v) {
                new LoginTask(MainActivity.this).execute(
                        MainActivity.this.etEmailAddress.getText().toString(),
                        MainActivity.this.etPassword.getText().toString());
            }
        });


        this.configuration.setOnClickListener(new View.OnClickListener() {
            /*
             * (non-Javadoc)
             * @see android.view.View.OnClickListener#onClick(android.view.View)
             */
            @Override
            public void onClick(final View v) {
                Intent intent = new Intent(MainActivity.this, Configuration.class);
                startActivity(intent);
            }
        });
    }

    public void showLoginError(final String result) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(
                MainActivity.this);
        if (builder != null) {
            builder.setPositiveButton("ok",
                    new DialogInterface.OnClickListener() {
                        /*
                         * (non-Javadoc)
                         * @see
                         * android.content.DialogInterface.OnClickListener#onClick
                         * (android.content.DialogInterface, int)
                         */
                        @Override
                        public void onClick(DialogInterface dialog,
                                            final int which) {
                            if (dialog != null) {
                                dialog.cancel();
                                dialog = null;
                            }
                        }
                    });
            builder.setMessage("Invalid Username or Password.");
            final AlertDialog alert = builder.create();
            if (alert != null) {
                alert.setCancelable(false);
                alert.show();
            }
        }
    }
}
