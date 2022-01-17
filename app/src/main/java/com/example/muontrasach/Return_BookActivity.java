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
import com.example.muontrasach.adapter.ListBookApdapter;
import com.example.muontrasach.adapter.ListBorrowAdapter;
import com.example.muontrasach.adapter.ListReturnAdapter;
import com.example.muontrasach.model.PhieuMuon;

public class Return_BookActivity extends AppCompatActivity implements ListReturnAdapter.onClickItem {
    private AccountReaderSQLite returnReaderSQLite;
    private RecyclerView rvListBook;
    private LinearLayoutManager layoutManager;
    String name,date,person;
    int time;
    private ListReturnAdapter listReturnAdapter;
    String usr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return__book);
        rvListBook = findViewById(R.id.listreturn);

        listReturnAdapter = new ListReturnAdapter(this, this);

        returnReaderSQLite = new AccountReaderSQLite(this);

        listReturnAdapter.setListItem(returnReaderSQLite.getAllPhieuMuonDaTra());

        layoutManager = new LinearLayoutManager(this);

        rvListBook.setLayoutManager(layoutManager);
        rvListBook.setAdapter(listReturnAdapter);
    }
    public void SetHome(View v){
        Intent i1 = new Intent(this,HomePageActivity.class);
        startActivity(i1);
    }

    @Override
    public void onClickConfirm(int fun, PhieuMuon pm) {
        switch (fun){
            case 0:
                // confirm borrow request
                Toast.makeText(this, "đang Xác nhận đã trả phiếu có ID: "+pm.getId_Muon()+" đăng ký bởi:"+pm.getPerson_Muon(), Toast.LENGTH_SHORT).show();

                break;
            case 1:
                // del borrow request
                Toast.makeText(this, "đang xoá phiếu có ID: "+pm.getId_Muon()+" đăng ký bởi:"+pm.getPerson_Muon(), Toast.LENGTH_SHORT).show();

                break;
        }
    }
    }

