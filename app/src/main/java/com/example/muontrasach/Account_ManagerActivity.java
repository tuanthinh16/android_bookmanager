package com.example.muontrasach;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.muontrasach.SQL.AccountReaderSQLite;
import com.example.muontrasach.adapter.ListAccountAdapter;
import com.example.muontrasach.adapter.ListBookApdapter;
import com.example.muontrasach.model.Account;

public class Account_ManagerActivity extends AppCompatActivity implements ListAccountAdapter.onClickItem {
    private AccountReaderSQLite accountReaderSQLite;
    private RecyclerView rvListBook;
    private LinearLayoutManager layoutManager;
    String name,date,person;
    int time;
    private ListAccountAdapter listAccountAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account__manager);

        try{
            rvListBook = findViewById(R.id.listAccount);

            listAccountAdapter = new ListAccountAdapter(this, this);

            accountReaderSQLite = new AccountReaderSQLite(this);

            listAccountAdapter.setListItem(accountReaderSQLite.getAllAccount());

            layoutManager = new LinearLayoutManager(this);

            rvListBook.setLayoutManager(layoutManager);
            rvListBook.setAdapter(listAccountAdapter);

        }catch (Exception e){
            Log.d("account","error: "+e);
        }
    }
    public void addAccount(View v){
        Intent i1 = new Intent(this,CreateAccountActivity.class);
        startActivity(i1);
    }
    public void goHome(View v) {
        Intent i1 = new Intent(this,HomePageActivity.class);
        startActivity(i1);
    }
    @Override
    public void onClickAccount(int fun, Account account) {
        Toast.makeText(this, "account"+account, Toast.LENGTH_SHORT).show();

    }
}