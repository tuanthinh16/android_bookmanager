package com.example.muontrasach;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.muontrasach.adapter.ListBookApdapter;
import com.example.muontrasach.model.Book;
import com.example.muontrasach.SQL.AccountReaderSQLite;
import com.example.muontrasach.model.PhieuMuon;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BookManagerActivity extends AppCompatActivity implements ListBookApdapter.onClickItem,DialogBorrow.DialogBorrowListener {
    private AccountReaderSQLite bookReaderSQLite;
    private RecyclerView rvListBook;
    private LinearLayoutManager layoutManager;
    String name,date,person;
    int time;
    private ListBookApdapter listBookApdapter;
    String usr;
    private Button btnHome;
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
        usr = getIntent().getStringExtra("username");
        Log.d("usr","ten lay tu main: "+usr);

    }

    public void setBtnHome(View v) {
        Intent i1 = new Intent(this,HomePageActivity.class);
        startActivity(i1);
    }

    public void addBook(View v){
        Intent t1 = new Intent(this,AddBookActivity.class);
        startActivity(t1);
    }
    public void openDialog(Book book) {

        DialogBorrow dialogBorrow = new DialogBorrow(book.getName(),usr);
        dialogBorrow.show(getSupportFragmentManager(), "Borow dialog");
        Log.d("usr","username geted: "+usr);
    }
    @Override
    public void onClickBook(int fun, Book book) {
        //Toast.makeText(this, fun + " " + book.getName(), Toast.LENGTH_SHORT).show();
        switch (fun){
            case 0:
                //borrow
                Toast.makeText(this, "Edit book "+book.getName() +" username: "+usr+" author: "+book.getAuthor()+" at ID: "+book.getId(), Toast.LENGTH_SHORT).show();
                try{
                    openDialog(book);

                }catch (Exception e){
                    Toast.makeText(this, "Loi: "  +e, Toast.LENGTH_SHORT).show();
                }
                break;
            case 1:
                //del
                break;
            case 2:
                //edit
                break;
        }
    }

    @Override
    public void getIDtoBorrow(Book book) {

    }

    @Override
    public void btn_Borow(String namesach, String datemuon, String permuon, String timemuon) {
        PhieuMuon pm = new PhieuMuon();
        pm.setId_Muon(""+System.currentTimeMillis());
        pm.setBook_Muon(namesach);
        pm.setDate(datemuon);
        pm.setPerson_Muon(permuon);
        pm.setTime(timemuon);

        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            c.setTime(sdf.parse(datemuon));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE , Integer.parseInt(timemuon));
        String output = sdf.format(c.getTime());
        if(datemuon.equals(output)){
            pm.setTrangThai(1);
        }
        else{
            pm.setTrangThai(0);
        }
            try{
            long rs = bookReaderSQLite.InsertPhieuMuon(pm);
            if(rs>0){
                Toast.makeText(this, "Tao phieu muon thanh cong", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "Loi roi rs:"+rs, Toast.LENGTH_LONG).show();
                Log.d("borrow","id: "+pm.getId_Muon()+"sach: "+pm.getBook_Muon()+"date: "+pm.getDate()+"nguoi muon :"+pm.getPerson_Muon()+"ngay tra:"+pm.getTime()+"trang thai: "+pm.getTrangThai());
            }
        }catch (Exception e){
            Toast.makeText(this, "loi: "+e, Toast.LENGTH_SHORT).show();
        }
    }


}