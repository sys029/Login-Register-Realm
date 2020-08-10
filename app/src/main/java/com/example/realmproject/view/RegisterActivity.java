package com.example.realmproject.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.realmproject.R;
import com.example.realmproject.model.Register;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    TextView resultTxt;
    EditText username;
    EditText password;
    Button Register;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = (EditText)findViewById(R.id.etUsername1);
        password = (EditText)findViewById(R.id.etPassword1);
        Register =(Button)findViewById(R.id.btnSave);
        resultTxt=(TextView)findViewById(R.id.resultText);


        realm= Realm.getDefaultInstance();
        Register.setOnClickListener(this);

        showResults();

    }
    @Override
    public void onClick(View view)
    {
        if(username.getText().toString().trim().length()==0){
            username.setError("Enter username");
            username.requestFocus();
        }
        if(password.getText().toString().trim().length()==0){
            password.setError("Enter password");
            password.requestFocus();
        }
        else{
            writeTodb(username.getText().toString().trim(), password.getText().toString().trim());
        }

    }

    public void writeTodb(final String username, final String password){

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                    Register userRegister = bgRealm.createObject(Register.class);
                    userRegister.setUsername(username);
                    userRegister.setPassword(password);

            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                // success.
                Log.v("Database", "Data inserted");
                Toast.makeText(RegisterActivity.this, "Register Success", Toast.LENGTH_SHORT).show();
                showResults();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                // failed
                Log.e("Database", error.getMessage());
                Toast.makeText(RegisterActivity.this, "Register Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }


    private void showResults() {

        RealmResults<Register> userList=realm.where(Register.class).findAll();
        resultTxt.setText(userList.toString());
    }

}
