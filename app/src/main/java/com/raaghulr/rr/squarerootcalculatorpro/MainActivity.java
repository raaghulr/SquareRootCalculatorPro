package com.raaghulr.rr.squarerootcalculatorpro;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText et_getvalue;
    TextView tv_resultvalue;
    double var_store,result_store;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //intialise
        et_getvalue = (EditText)findViewById(R.id.et_getvalue);
        tv_resultvalue = (TextView)findViewById(R.id.tv_resultvalue);
        //
    }

    public void squareCalculator (View view)
    {
        var_store = new Double(String.valueOf(et_getvalue.getText()));
        result_store = Math.sqrt(var_store);
        tv_resultvalue.setText((new String(String.valueOf(result_store))));
        Toast.makeText(this, new String("Square Root Value For \nNumber : "+var_store+"  is "+String.valueOf(result_store)), Toast.LENGTH_LONG).show();
    }


}
