package com.example.pc.appamthuc;
import android.graphics.Bitmap;

import java.io.Serializable;

public class ThucUong implements Serializable{
    private int ID;
    private String Name;
    private String Note;
    private  Bitmap Image;
    private String Use;

    public String getUse() {
        return Use;
    }

    public void setUse(String use) {
        Use = use;
    }

    public ThucUong(String ten, String note, Bitmap anh, String use) {
        this.Name = ten;
        this.Note = note;
        this.Image = anh;
        this.Use = use;
    }

    public ThucUong() {
    }

    public int getId() {
        return ID;
    }

    public void setId(int id) {
        this.ID = id;
    }

    public String getTen() {
        return Name;
    }

    public void setTen(String ten) {
        this.Name = ten;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        this.Note = note;
    }

    public Bitmap getAnh() {
        return Image;
    }

    public void setAnh(Bitmap anh) {
        this.Image = anh;
    }


}
