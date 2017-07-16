package com.example.saicharan.sqlitedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


class MyDatabase extends SQLiteOpenHelper {

    private static final String DB_NAME = "student.db";
    private static final int VERSION = 1;

    public MyDatabase(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String studentTable = "CREATE TABLE STUDENT(ID INTEGER " +
                "PRIMARY KEY AUTOINCREMENT, NAME TEXT, ROLLNUMBER " +
                "INTEGER, MARKS1 REAL, MARKS2 REAL);";

        db.execSQL(studentTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertStudent(Student student) {
        SQLiteDatabase database = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("NAME", student.getName());
        cv.put("ROLLNUMBER", student.getRollNumber());
        cv.put("MARKS1", student.getMarks1());
        cv.put("MARKS2", student.getMarks2());

        database.insert("STUDENT", null, cv);
    }

    public ArrayList<Student> getAllStudents() {
        SQLiteDatabase database = getReadableDatabase();

        ArrayList<Student> students = new ArrayList<>();

        Cursor cursor = database.query("STUDENT", null, null, null, null, null, null);
        //        Cursor cursor = database.rawQuery("SELECT * FROM STUDENT", null);
        boolean first = cursor.moveToFirst();
        if (!first) {
            return null;
        }

        int nameIndex = cursor.getColumnIndex("NAME");
        int numberIndex = cursor.getColumnIndex("ROLLNUMBER");
        int marks1Index = cursor.getColumnIndex("MARKS1");
        int marks2Index = cursor.getColumnIndex("MARKS2");

        while (!cursor.isAfterLast()) {
            String name = cursor.getString(nameIndex);
            int number = cursor.getInt(numberIndex);
            float marks1 = cursor.getFloat(marks1Index);
            float marks2 = cursor.getFloat(marks2Index);

            Student s = new Student();
            s.setName(name);
            s.setRollNumber(number);
            s.setMarks1(marks1);
            s.setMarks2(marks2);

            students.add(s);

            cursor.moveToNext();
        }
        return students;
    }
}
