package com.example.saicharan.sqlitedatabase;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;



public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }

    public void startTask(View view) {
        ArithmeticTask task = new ArithmeticTask();
        Integer[] data = new Integer[]{10, 20};
        task.execute(data);
    }

    class ArithmeticTask extends AsyncTask<Integer, Void, Integer> {

        private ProgressDialog p;

        @Override
        protected void onPreExecute() {
            p = new ProgressDialog(TestActivity.this);
            p.setMessage("Please wait");
            p.show();
        }

        @Override
        protected Integer doInBackground(Integer... params) {
            Integer firstValue = params[0];
            Integer secondValue = params[1];

            Integer result = firstValue * secondValue;
            return result;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            p.dismiss();

            Toast.makeText(TestActivity.this, String.valueOf(integer), Toast.LENGTH_LONG).show();
        }
    }
}
