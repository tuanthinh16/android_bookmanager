package com.example.muontrasach.SQL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.muontrasach.model.Account;
import com.example.muontrasach.model.Book;
import com.example.muontrasach.model.PhieuMuon;

import java.util.ArrayList;
import java.util.List;

public class AccountReaderSQLite extends SQLiteOpenHelper {
    public AccountReaderSQLite( Context context) {
        super(context, "thuvien.db", null, 1);
    }
    public static final String TABLE_NAME_ACCOUNT = "Account";
    public static final String COLUM_ID_ACCOUNT = "Id";
    public static final String COLUM_NAME_ACCOUNT = "Name";
    public static final String COLUM_USERNAME = "Username";
    public static final String COLUM_PASSOWRD = "Password";
    public static final String COLUM_EMAIL = "Email";
    public static final String COLUM_ADDRESS ="Address";

    public static final String TABLE_NAME_BOOK = "Book";
    public static final String COLUM_ID_BOOK = "Id";
    public static final String COLUM_NAME_BOOK = "Name";
    public static final String COLUM_TYPE = "Type";
    public static final String COLUM_AUTHOR = "Author";
    public static final String COLUM_NXB = "NXB";

    public static final String TABLE_NAME_MUON ="MUON";
    public static final String COLUM_ID_MUON = "Id";
    public static final String COLUM_BOOK_MUON = "MaSachMUON";
    public static final String COLUM_DATE = "NgayMuon";
    public static final String COLUM_PERSON_MUON = "MaNguoiMuon";
    public static final String COLUM_TIME = "ThoiGianMUon";
    public static final String COLUM_STATUS_MUON="TrangThaiMuon";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME_ACCOUNT+
                    "("+COLUM_ID_ACCOUNT+" INTEGER PRIMARY KEY,"+
                        COLUM_NAME_ACCOUNT+" VARCHAR(200),"+
                        COLUM_USERNAME+" VARCHAR(50),"+
                        COLUM_PASSOWRD+" VARCHAR(100),"+
                        COLUM_EMAIL+" VARCHAR(200),"+
                        COLUM_ADDRESS+" VARCHAR(200))");
        db.execSQL("CREATE TABLE "+TABLE_NAME_BOOK+
                    " ("+COLUM_ID_BOOK+ " INTEGER PRIMARY KEY,"+
                        COLUM_NAME_BOOK+" VARCHAR(200),"+
                        COLUM_TYPE+ " VARCHAR(50),"+
                        COLUM_AUTHOR+" VARCHAR(100),"+
                        COLUM_NXB+" VARCHAR(200)) ");
        db.execSQL("CREATE TABLE "+TABLE_NAME_MUON+
                    "("+COLUM_ID_MUON+"INTEGER PRIMARY KEY,"+
                        COLUM_BOOK_MUON+" VARCHAR(100),"+
                        COLUM_DATE+" VARCHAR(100),"+
                        COLUM_PERSON_MUON+" VARCHAR(100)," +
                        COLUM_TIME+" VARCHAR(100),"+
                        COLUM_STATUS_MUON+" VARCHAR(50))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_ACCOUNT);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_BOOK);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_MUON);
        onCreate(db);
    }

                //--------------ACCOUNT-----------------
    public long InsertAccount(Account account){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUM_ID_ACCOUNT, account.getId());
        contentValues.put(COLUM_NAME_ACCOUNT, account.getName());
        contentValues.put(COLUM_USERNAME, account.getUsername());
        contentValues.put(COLUM_PASSOWRD, account.getPassword());
        contentValues.put(COLUM_EMAIL, account.getEmail());
        contentValues.put(COLUM_ADDRESS, account.getAddress());

        long rs = sqLiteDatabase.insert(TABLE_NAME_ACCOUNT,null,contentValues);
        return rs;
    }
    public long UpdateAccount(Account account){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUM_ID_ACCOUNT, account.getId());
        contentValues.put(COLUM_NAME_ACCOUNT, account.getName());
        contentValues.put(COLUM_USERNAME, account.getUsername());
        contentValues.put(COLUM_PASSOWRD, account.getPassword());
        contentValues.put(COLUM_EMAIL, account.getEmail());
        contentValues.put(COLUM_ADDRESS, account.getAddress());

        long rs = sqLiteDatabase.update(TABLE_NAME_ACCOUNT,contentValues,COLUM_ID_ACCOUNT+"=?",new String[]{account.getId()});
        return rs;
    }
    public long DelAccount(Account account){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME_ACCOUNT,COLUM_ID_ACCOUNT+"=?",new String[]{account.getId()});
    }
    public List<Account> getAllAccount(){
        List<Account> accounts = new ArrayList<>();
        String SELECT = "SELECT * FROM "+ TABLE_NAME_ACCOUNT;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT,null);
        if(cursor.getCount() > 0){

            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                String id = cursor.getString(0);
                String name = cursor.getString(1);
                String username = cursor.getString(2);
                String password = cursor.getString(3);
                String email = cursor.getString(4);
                String address = cursor.getString(5);

                Account account = new Account();

                account.setId(id);
                account.setName(name);
                account.setUsername(username);
                account.setPassword(password);
                account.setEmail(email);
                account.setAddress(address);
                accounts.add(account);

                cursor.moveToNext();
            }
            cursor.close();
        }
        return accounts;
    }

        //------------------BOOK---------------

    public long InsertBook(Book book){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUM_ID_BOOK, book.getId());
        contentValues.put(COLUM_NAME_BOOK, book.getName());
        contentValues.put(COLUM_TYPE, book.getType());
        contentValues.put(COLUM_AUTHOR, book.getAuthor());
        contentValues.put(COLUM_NXB, book.getNXB());

        long rs = sqLiteDatabase.insert(TABLE_NAME_BOOK,null,contentValues);
        return rs;
    }
    public long UpdateBook(Book book){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUM_ID_BOOK, book.getId());
        contentValues.put(COLUM_NAME_BOOK, book.getName());
        contentValues.put(COLUM_TYPE, book.getType());
        contentValues.put(COLUM_AUTHOR, book.getAuthor());
        contentValues.put(COLUM_NXB, book.getNXB());

        long rs = sqLiteDatabase.update(TABLE_NAME_BOOK,contentValues,COLUM_ID_BOOK+"=?",new String[]{book.getId()});
        return rs;
    }
    public long DelBook(Book book){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME_BOOK,COLUM_ID_BOOK+"=?",new String[]{book.getId()});
    }
    public List<Book> getAllBook(){
        List<Book> books = new ArrayList<>();
        String SELECT = "SELECT * FROM "+ TABLE_NAME_BOOK;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT,null);
        if(cursor.getCount() > 0){

            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                String id = cursor.getString(0);
                String name = cursor.getString(1);
                String username = cursor.getString(2);
                String password = cursor.getString(3);
                String email = cursor.getString(4);

                Book book = new Book();

                book.setId(id);
                book.setName(name);
                book.setType(username);
                book.setAuthor(password);
                book.setNXB(email);

                books.add(book);

                cursor.moveToNext();
            }
            cursor.close();
        }
        return books;
    }
            //------------------------------MUON SACH------------------------
    public long InsertPhieuMuon(PhieuMuon pm){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUM_ID_MUON, pm.getId_Muon());
        contentValues.put(COLUM_BOOK_MUON, pm.getBook_Muon());
        contentValues.put(COLUM_DATE, pm.getDate());
        contentValues.put(COLUM_PERSON_MUON, pm.getPerson_Muon());
        contentValues.put(COLUM_TIME, pm.getTime());
        contentValues.put(COLUM_STATUS_MUON, pm.getTrangThai());


        long rs = sqLiteDatabase.insert(TABLE_NAME_MUON,null,contentValues);
        return rs;
    }
    public long UpdatePhieuMuon(PhieuMuon pm){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUM_ID_MUON, pm.getId_Muon());
        contentValues.put(COLUM_BOOK_MUON, pm.getBook_Muon());
        contentValues.put(COLUM_DATE, pm.getDate());
        contentValues.put(COLUM_PERSON_MUON, pm.getPerson_Muon());
        contentValues.put(COLUM_TIME, pm.getTime());
        contentValues.put(COLUM_STATUS_MUON, pm.getTrangThai());

        long rs = sqLiteDatabase.update(TABLE_NAME_MUON,contentValues,COLUM_ID_MUON+"=?",new String[]{pm.getId_Muon()});
        return rs;
    }
    public long DelPhieuMuon(PhieuMuon pm){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME_MUON,COLUM_ID_MUON+"=?",new String[]{pm.getId_Muon()});
    }
    public List<PhieuMuon> getAllPhieuMuon(){
        List<PhieuMuon> pm = new ArrayList<>();
        String SELECT = "SELECT * FROM "+ TABLE_NAME_MUON;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT,null);
        if(cursor.getCount() > 0){

            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                String id = cursor.getString(0);
                String book = cursor.getString(1);
                String date = cursor.getString(2);
                String person = cursor.getString(3);
                String time = cursor.getString(4);
                String status = cursor.getString(5);


                PhieuMuon phieuMuon = new PhieuMuon();

                phieuMuon.setId_Muon(id);
                phieuMuon.setBook_Muon(book);
                phieuMuon.setDate(date);
                phieuMuon.setPerson_Muon(person);
                phieuMuon.setTime(time);
                phieuMuon.setTrangThai(status);
                pm.add(phieuMuon);

                cursor.moveToNext();
            }
            cursor.close();
        }
        return pm;
    }
}
