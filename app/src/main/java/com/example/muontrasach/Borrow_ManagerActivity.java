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
import com.example.muontrasach.model.PhieuMuon;

public class Borrow_ManagerActivity extends AppCompatActivity implements ListBorrowAdapter.onClickItem {
    private AccountReaderSQLite pmReaderSQLite;
    private RecyclerView rvListBook;
    private LinearLayoutManager layoutManager;
    String name,date,person;
    int time;
    private ListBorrowAdapter listBorrowAdapter;
    String usr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow__manager);

        rvListBook = findViewById(R.id.listborrow);

        listBorrowAdapter = new ListBorrowAdapter(this, this);

        pmReaderSQLite = new AccountReaderSQLite(this);

        listBorrowAdapter.setListItem(pmReaderSQLite.getAllPhieuMuonChuaTra());

        layoutManager = new LinearLayoutManager(this);

        rvListBook.setLayoutManager(layoutManager);
        rvListBook.setAdapter(listBorrowAdapter);
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
                Toast.makeText(this, "đang Xác nhận  phiếu có ID: "+pm.getId_Muon()+" đăng ký bởi:"+pm.getPerson_Muon(), Toast.LENGTH_SHORT).show();
                pm.setTrangThai(1);
                try{
                    pmReaderSQLite.UpdatePhieuMuon(pm);
                    Intent i1 = new Intent(this,Return_BookActivity.class);
                    startActivity(i1);
                }catch (Exception e){
                    Log.d("pm","Loi: "+e);
                }
                break;
            case 1:
                // del borrow request
                Toast.makeText(this, "đang xoá phiếu có ID: "+pm.getId_Muon()+" đăng ký bởi:"+pm.getPerson_Muon(), Toast.LENGTH_SHORT).show();

                break;
        }
    }
}