package com.example.realmproject.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.realmproject.MyApplication;
import com.example.realmproject.R;
import com.example.realmproject.model.Register;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etUser,etPass;
    private Realm mRealm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.btnLogin);
        Button button2 = findViewById(R.id.btnRegister);

        etPass=(EditText)findViewById(R.id.etPassword);
        etUser=(EditText)findViewById(R.id.etUsername);


       mRealm= Realm.getDefaultInstance();
        button.setOnClickListener(this);
        button2.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnRegister:
                Intent intent= new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;

            case R.id.btnLogin:
                if(etUser.getText().toString().trim().length()==0){
                    etUser.setError("Enter username");
                    etUser.requestFocus();
                }
                if(etPass.getText().toString().trim().length()==0){
                    etPass.setError("Enter password");
                    etPass.requestFocus();
                }
                else{

                    String email = etUser.getText().toString();
                    String password = etPass.getText().toString();

                    Register user = mRealm.where(Register.class)
                            .equalTo("username", email)
                            .equalTo("password", password)
                            .findFirst();

                    if(user != null){
                        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
                        Intent intenty= new Intent(MainActivity.this, Main2Activity.class);
                        startActivity(intenty);
                        break;
                    }else {
                        Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
                    }

                }


        }

    }
}