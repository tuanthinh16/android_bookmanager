package com.example.muontrasach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomePageActivity extends AppCompatActivity {
    private Button btnAccount;
    private Button btnBook;
    private Button btnBorrow;
    private Button btnReturn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__manager);

        btnAccount = (Button)findViewById(R.id.btnAccount);
        btnBook = (Button)findViewById(R.id.btnBook);
        btnBorrow = (Button)findViewById(R.id.btnBorrow);
        btnReturn = (Button)findViewById(R.id.btnReturn);
    }
    public void goAccount(View v){
        Intent i1 = new Intent(this, BookManagerActivity.class);
        startActivity(i1);
    }
    public void goBook(View v){
        Intent i1 = new Intent(this, BookManagerActivity.class);
        startActivity(i1);
    }
    public void goBorrow(View v){
        Intent i1 = new Intent(this, BookManagerActivity.class);
        startActivity(i1);
    }
    public void goReturn(View v){
        Intent i1 = new Intent(this, BookManagerActivity.class);
        startActivity(i1);
    }
}