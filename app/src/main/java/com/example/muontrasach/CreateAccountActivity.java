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

public class CreateAccountActivity extends AppCompatActivity {
    private AccountReaderSQLite accountReaderSQLite;
    private EditText name;
    private EditText username;
    private EditText password;
    private EditText repass;
    private EditText email;
    private Button btnCreate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        accountReaderSQLite = new AccountReaderSQLite(this);

        name = (EditText)findViewById(R.id.txtName);
        username = (EditText)findViewById(R.id.txtUsr);
        password = (EditText)findViewById(R.id.txtPass);
        repass = (EditText)findViewById(R.id.txtRepass);
        email = (EditText)findViewById(R.id.txtMail);
        btnCreate = (Button)findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Account account = new  Account();
                    if(password.getText().toString().equals(repass.getText().toString())) {
                        account.setId("" + System.currentTimeMillis());
                        account.setName(name.getText().toString());
                        account.setUsername(username.getText().toString());
                        account.setPassword(password.getText().toString());
                        account.setEmail(email.getText().toString());
                        long rs = accountReaderSQLite.InsertAccount(account);
                        if (rs > 0) {
                            Toast.makeText(CreateAccountActivity.this, "Successfully", Toast.LENGTH_LONG).show();
                            Intent t1 = new Intent(CreateAccountActivity.this, MainActivity.class);
                            startActivity(t1);
                        } else {
                            Toast.makeText(CreateAccountActivity.this, "Loi roi ...", Toast.LENGTH_LONG).show();
                        }
                    }
                    else{
                        Toast.makeText(CreateAccountActivity.this, "Password and repassword must be like", Toast.LENGTH_LONG).show();
                    }
                }
                catch (Exception e){
                    Toast.makeText(CreateAccountActivity.this, "loi gi do roi: "+e, Toast.LENGTH_LONG).show();
                }
                }

        });
    }
}