package com.example.muontrasach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.muontrasach.model.Account;
import com.example.muontrasach.SQL.AccountReaderSQLite;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText user;
    private EditText pass;
    private Button btnLogin;
    private AccountReaderSQLite accountReaderSQLite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = (EditText) findViewById(R.id.txtUsername);
        pass = (EditText) findViewById(R.id.txtPassowrd);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        accountReaderSQLite = new AccountReaderSQLite(this);
    }

    private boolean isLogin(){
        String username = user.getText().toString();
        String password = pass.getText().toString();
        List<Account> accountList = accountReaderSQLite.getAllAccount();
        for(int i = 0; i < accountList.size(); i++){
            if(accountList.get(i).getUsername().equals(username) && accountList.get(i).getPassword().equals(password)){
                //Toast.makeText(this, "Dung roi", Toast.LENGTH_SHORT).show();
                return true;
            }

        }
        //Toast.makeText(MainActivity.this, "Sai roi user la: "+accountList.size(), Toast.LENGTH_SHORT).show();
        return false;

    }
    public void Login(View v){
        String username = user.getText().toString();
        String password = pass.getText().toString();
        if(isLogin() == true ){
            Intent i1 = new Intent(this, HomePageActivity.class);
            startActivity(i1);
            Toast toast = Toast.makeText(this,"Login Success with : "+username,Toast.LENGTH_LONG);
            toast.show();

        }
        else{
            String msg = "user: "+username +" -- pass: "+password;
            Toast toast = Toast.makeText(this,msg,Toast.LENGTH_LONG);
            toast.show();
        }
    }
    public void CreAccount(View v){
        Intent t1 = new Intent(this, CreateAccountActivity.class);
        startActivity(t1);
    }
    @Override
    protected void onDestroy() {
        accountReaderSQLite.close();
        super.onDestroy();
    }
}