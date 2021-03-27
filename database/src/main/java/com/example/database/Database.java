package com.example.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "BookLibrary.db";

    private static String TABLE_NAME_Admin = "ADMINS";
    private static String TABLE_NAME_User = "USERS";
    private static String TABLE_NAME_Book = "BOOKS";


    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String table_admin = "CREATE TABLE " + TABLE_NAME_Admin + " (id INTEGER PRIMARY KEY AUTOINCREMENT, Username TEXT, Password TEXT, Email TEXT)";
        String table_user = "CREATE TABLE " + TABLE_NAME_User + " (id INTEGER PRIMARY KEY AUTOINCREMENT, Username TEXT, Password TEXT, Email TEXT)";
        String table_book = "CREATE TABLE " + TABLE_NAME_Book + " (id INTEGER PRIMARY KEY AUTOINCREMENT, Title TEXT, Author TEXT, Pages INTEGER, Issue_id INTEGER)";

        db.execSQL(table_admin);
        db.execSQL(table_user);
        db.execSQL(table_book);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_Admin);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_User);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_Book);
        onCreate(db);
    }

    public void deleteall() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_Admin);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_User);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_Book);
        onCreate(db);
    }

    public void on(SQLiteDatabase db) {
        String table_admin = "CREATE TABLE " + TABLE_NAME_Admin + " (id INTEGER PRIMARY KEY AUTOINCREMENT, Username TEXT, Password TEXT, Email TEXT)";
        String table_user = "CREATE TABLE " + TABLE_NAME_User + " (id INTEGER PRIMARY KEY AUTOINCREMENT, Username TEXT, Password TEXT, Email TEXT)";
        String table_book = "CREATE TABLE " + TABLE_NAME_Book + " (id INTEGER PRIMARY KEY AUTOINCREMENT, Title TEXT, Author TEXT, Pages INTEGER, Issue_id INTEGER)";

        db.execSQL(table_admin);
        db.execSQL(table_user);
        db.execSQL(table_book);
    }

    public void addBook(String title, String author, int pages) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("Title", title);
        cv.put("Author", author);
        cv.put("Pages", pages);
        cv.put("Issue_id", 0);
        long result = db.insert(TABLE_NAME_Book, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    public void addAdmin(String uname, String email, String pass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("Username", uname);
        cv.put("Email", email);
        cv.put("Password", pass);
        long result = db.insert(TABLE_NAME_Admin, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed!!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added Successfully!!", Toast.LENGTH_SHORT).show();
        }
    }

    public int findAdmin(String uname, String pass) {
        int id = -1;
        String query = "SELECT * FROM " + TABLE_NAME_Admin;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
            while (cursor.moveToNext()){
                if ((cursor.getString(1)).equals(uname)) {
                    if ((cursor.getString(2)).equals(pass)) {
                        id = cursor.getInt(0);
                        break;
                    }
                }
            }
        }
        return id;
    }
    public String[] findAdmin(int id)
    {
        String query =  "SELECT * FROM " + TABLE_NAME_Admin;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        String[] details = {"",""};
        if(db != null)
        {
            cursor = db.rawQuery(query, null);
            if(cursor != null)
                cursor.moveToFirst();
                do {
                    if (cursor.getInt(0) == id) {
                        details[0] = cursor.getString(1);
                        details[1] = cursor.getString(3);
                        return (details);
                    }
                } while (cursor.moveToNext());
        }
        return (details);
    }

    public void updateAdmin(int id,String uname, String email)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Username", uname);
        cv.put("Email", email);
        long result = db.update(TABLE_NAME_Admin, cv, "id=" + id, null);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    public void addUser(String uname, String email, String pass)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("Username", uname);
        cv.put("Email", email);
        cv.put("Password", pass);
        long result = db.insert(TABLE_NAME_User,null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    public int findUser(String uname,String pass) {
        int id = -1;
        String query =  "SELECT * FROM " + TABLE_NAME_User;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db != null) {
            cursor = db.rawQuery(query, null);
            while (cursor.moveToNext()){
                if ((cursor.getString(1)).equals(uname)) {
                    if ((cursor.getString(2)).equals(pass)) {
                        id = cursor.getInt(0);
                        break;
                    }
                }
            }
        }
        return id;
    }

    public String[] findUser(int id)
    {
        String query =  "SELECT * FROM " + TABLE_NAME_User;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        String[] details = {"",""};
        if(db != null)
        {
            cursor = db.rawQuery(query, null);
            if(cursor != null)
                cursor.moveToFirst();
            do {
                if( cursor.getInt(0) == id)
                {
                    details[0] = cursor.getString(1);
                    details[1] = cursor.getString(3);
                    return (details);
                }
            }while(cursor.moveToNext());
        }
        return (details);
    }

    public void updateUser(int id,String uname, String email)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Username", uname);
        cv.put("Email", email);
        long result = db.update(TABLE_NAME_User, cv, "id=" + id, null);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    public int canIssue(int bookid,int userid)
    {
        int flag = 0;
        String query =  "SELECT * FROM " + TABLE_NAME_Book;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db != null)
        {
            cursor = db.rawQuery(query, null);
            if(cursor != null)
                cursor.moveToFirst();
            do {
                if( cursor.getInt(0) == bookid)
                {
                    if( cursor.getInt(4) == userid) {
                        flag = 1;
                        break;
                    }
                }
            }while(cursor.moveToNext());
        }
        return flag;
    }

    public int issueBook(int bookid, int userid)
    {
        int flag = 0;
        String query =  "SELECT * FROM " + TABLE_NAME_Book;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db != null)
        {
            cursor = db.rawQuery(query, null);
            if(cursor != null)
                cursor.moveToFirst();
            do {
                if( cursor.getInt(0) == bookid)
                {
                    if( cursor.getInt(4) == 0) {
                        SQLiteDatabase db2 = this.getWritableDatabase();
                        ContentValues cv = new ContentValues();
                        cv.put("Issue_id", userid);
                        long result = db2.update(TABLE_NAME_Book, cv, "id=" + bookid, null);
                        if (result == -1) {
                            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
                        } else {
                            flag = 1;
                            Toast.makeText(context, "Book has been Issued", Toast.LENGTH_SHORT).show();
                            break;
                        }
                    }
                    else {
                        Toast.makeText(context, "Not Available", Toast.LENGTH_SHORT).show();
                    }
                }
            }while(cursor.moveToNext());
        }
        return flag;
    }

    public int returnBook(int bookid,int userid)
    {
        int flag = 0;
        String query =  "SELECT * FROM " + TABLE_NAME_Book;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db != null)
        {
            cursor = db.rawQuery(query, null);
            if(cursor != null)
                cursor.moveToFirst();
            do {
                if( cursor.getInt(0) == bookid)
                {
                    if( cursor.getInt(4) == userid) {
                        SQLiteDatabase db2 = this.getWritableDatabase();
                        ContentValues cv = new ContentValues();
                        cv.put("Issue_id", 0);
                        long result = db2.update(TABLE_NAME_Book, cv, "id=" + bookid, null);
                        if (result == -1) {
                            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
                        } else {
                            flag = 1;
                            Toast.makeText(context, "Book has been Returned", Toast.LENGTH_SHORT).show();
                            break;
                        }
                    }
                    else {
                        Toast.makeText(context, "Not Available", Toast.LENGTH_SHORT).show();
                    }
                }
            }while(cursor.moveToNext());
        }
        return flag;
    }

    public Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME_Book;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public Cursor readAllUserData(){
        String query = "SELECT * FROM " + TABLE_NAME_User;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateBook(int book_id, String title, String author, String pages){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Title", title);
        cv.put("Author", author);
        cv.put("Pages", pages);
        long result = db.update(TABLE_NAME_Book, cv, "id=" + book_id, null);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteBook(int book_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME_Book, "id=" + book_id, null);
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteUser(int user_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME_User, "id=" + user_id, null);
        if (result == -1) {
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }
}

