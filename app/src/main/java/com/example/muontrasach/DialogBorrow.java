package com.example.muontrasach;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.muontrasach.SQL.AccountReaderSQLite;
import com.example.muontrasach.adapter.ListBookApdapter;
import com.example.muontrasach.model.Book;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DialogBorrow extends AppCompatDialogFragment implements ListBookApdapter.onClickItem {
    private EditText name,nmuon,permuon,timemuon;
    private DialogBorrowListener listener;
    private ListBookApdapter listBookApdapter;
    private String idPerson,idsach;
    private AccountReaderSQLite pmReaderSQL;
    public  DialogBorrow(String namebook,String nameper){
        this.idsach = namebook;
        this.idPerson = nameper;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog_borrow, null);

        name = view.findViewById(R.id.muonsach);
        nmuon = view.findViewById(R.id.ngaymuon);
        permuon = view.findViewById(R.id.nguoimuon);
        timemuon = view.findViewById(R.id.thoigianmuon);

        name.setText(idsach);
        permuon.setText(idPerson);
        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        nmuon.setText(currentDate);
        Log.d("usr","username borrow: "+idPerson);
//        Calendar c = Calendar.getInstance();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        c.setTime(sdf.parse(currentDate));
//        c.add(Calendar.DATE , Integer.parseInt(timemuon.getText().toString()));
//        String output = sdf.format(c.getTime());
        builder.setView(view)
                .setTitle("Borrow Book")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Dang Ky", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        try{
                            listener.btn_Borow(name.getText().toString(),nmuon.getText().toString(),permuon.getText().toString(),timemuon.getText().toString());
                        }catch (Exception e){
                            Log.d("Borrow","loi :"+e);
                        }
                    }
                });



        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (DialogBorrowListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement ExampleDialogListener");
        }
    }

    @Override
    public void onClickBook(int fun, Book book) {
        name.setText("aloo: "+book.getId());
    }

    @Override
    public void getIDtoBorrow(Book book) {
        name.setText("alo; "+book.getId());
    }


    public interface DialogBorrowListener {
        void btn_Borow(String name, String Nmuon, String Nguoimuon, String Timemuon);
    }
}
