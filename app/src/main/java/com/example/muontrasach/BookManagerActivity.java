package com.example.muontrasach;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.muontrasach.adapter.ListBookApdapter;
import com.example.muontrasach.model.Book;
import com.example.muontrasach.SQL.AccountReaderSQLite;

public class BookManagerActivity extends AppCompatActivity implements ListBookApdapter.onClickItem {
    private AccountReaderSQLite bookReaderSQLite;
    private RecyclerView rvListBook;
    private LinearLayoutManager layoutManager;

    private ListBookApdapter listBookApdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__book);
        rvListBook = findViewById(R.id.listbook);

        listBookApdapter = new ListBookApdapter(this, this);

        bookReaderSQLite = new  AccountReaderSQLite(this);

        listBookApdapter.setListItem(bookReaderSQLite.getAllBook());

        layoutManager = new LinearLayoutManager(this);

        rvListBook.setLayoutManager(layoutManager);
        rvListBook.setAdapter(listBookApdapter);
    }
    public void addBook(View v){
        Intent t1 = new Intent(this,AddBookActivity.class);
        startActivity(t1);
    }

    @Override
    public void onClickBook(int fun, Book book) {
        Toast.makeText(this, fun + " " + book.getName(), Toast.LENGTH_SHORT).show();
    }
}