package com.example.saicharan.sqlitedatabase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class StudentsAdapter extends BaseAdapter {

    Context context;
    ArrayList<Student> students;

    public StudentsAdapter(Context context, ArrayList<Student> students){
        this.context = context;
        this.students = students;
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int position) {
        return students.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_item, parent, false);

        TextView textName = (TextView) view.findViewById(R.id.textName);
        TextView textNumber = (TextView) view.findViewById(R.id.textNumber);
        TextView textMarks1 = (TextView) view.findViewById(R.id.textMarks1);
        TextView textMarks2 = (TextView) view.findViewById(R.id.textMarks2);

        Student student = students.get(position);

        textName.setText(student.getName());
        textNumber.setText(String.valueOf(student.getRollNumber()));
        textMarks1.setText(String.valueOf(student.getMarks1()));
        textMarks2.setText(String.valueOf(student.getMarks2()));

        return view;
    }
}
