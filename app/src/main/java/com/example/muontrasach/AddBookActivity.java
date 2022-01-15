package com.example.muontrasach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.muontrasach.model.Book;
import com.example.muontrasach.SQL.AccountReaderSQLite;

public class AddBookActivity extends AppCompatActivity {
    private AccountReaderSQLite bookReaderSQLite;
    private EditText name,type,author,nxb;
    private Button btnadd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        bookReaderSQLite = new AccountReaderSQLite(this);

        name = (EditText)findViewById(R.id.txtname);
        type = (EditText)findViewById(R.id.txttype);
        author = (EditText)findViewById(R.id.txtauthor);
        nxb = (EditText)findViewById(R.id.txtnxb);
        btnadd = (Button)findViewById(R.id.btnadd);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Book book = new Book();
                    book.setId(""+System.currentTimeMillis());
                    book.setName(name.getText().toString());
                    book.setType(type.getText().toString());
                    book.setAuthor(author.getText().toString());
                    book.setNXB(nxb.getText().toString());

                    long rs = bookReaderSQLite.InsertBook(book);
                    if (rs > 0) {
                        Toast.makeText(AddBookActivity.this, "Successfully", Toast.LENGTH_LONG).show();
                        Intent t1 = new Intent(AddBookActivity.this, BookManagerActivity.class);
                        startActivity(t1);
                    } else {
                        Toast.makeText(AddBookActivity.this, "Loi roi ... rs:"+rs, Toast.LENGTH_LONG).show();
                    }
                }catch (Exception e){
                    Toast.makeText(AddBookActivity.this, "Error: "+e, Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}