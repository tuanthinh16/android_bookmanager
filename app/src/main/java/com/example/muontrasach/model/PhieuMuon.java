package com.example.muontrasach.model;

public class PhieuMuon {
    String Id_Muon;
    String book_Muon;
    String Date;
    String Person_Muon;
    String Time;
    int TrangThai;

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int trangThai) {
        TrangThai = trangThai;
    }

    public String getId_Muon() {
        return Id_Muon;
    }

    public void setId_Muon(String id_Muon) {
        Id_Muon = id_Muon;
    }

    public String getBook_Muon() {
        return book_Muon;
    }

    public void setBook_Muon(String book_Muon) {
        this.book_Muon = book_Muon;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getPerson_Muon() {
        return Person_Muon;
    }

    public void setPerson_Muon(String person_Muon) {
        Person_Muon = person_Muon;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }
}
