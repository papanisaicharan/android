package com.example.saicharan.sqlitedatabase;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText textName, textNumber, textMarks1, textMarks2;
    ListView studentsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textName = (EditText) findViewById(R.id.textName);
        textNumber = (EditText) findViewById(R.id.textNumber);
        textMarks1 = (EditText) findViewById(R.id.textMarks1);
        textMarks2 = (EditText) findViewById(R.id.textMarks2);

        studentsList = (ListView) findViewById(R.id.studentsList);
    }

    public void insertIntoDatabase(View view) {
        String name = textName.getText().toString();
        int number = Integer.parseInt(textNumber.getText().toString());
        float marks1 = Float.parseFloat(textMarks1.getText().toString());
        float marks2 = Float.parseFloat(textMarks2.getText().toString());

        Student student = new Student();
        student.setName(name);
        student.setRollNumber(number);
        student.setMarks1(marks1);
        student.setMarks2(marks2);

        MyDatabase database = new MyDatabase(this);
        database.insertStudent(student);
        Toast.makeText(this, "Data inserted", Toast.LENGTH_SHORT).show();

        textName.setText("");
        textNumber.setText("");
        textMarks1.setText("");
        textMarks2.setText("");
    }

    public void showInList(View view) {
        DataTask task = new DataTask();
        task.execute();
    }

    class DataTask extends AsyncTask<Integer, Void, ArrayList<Student>> {

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Please wait");
            progressDialog.show();
        }

        @Override
        protected ArrayList<Student> doInBackground(Integer... params) {
            MyDatabase database = new MyDatabase(MainActivity.this);
            ArrayList<Student> allStudents = database.getAllStudents();
            return allStudents;
        }

        @Override
        protected void onPostExecute(ArrayList<Student> students) {
            progressDialog.dismiss();

            StudentsAdapter adapter = new StudentsAdapter(MainActivity.this, students);
            studentsList.setAdapter(adapter);
        }
    }
}
