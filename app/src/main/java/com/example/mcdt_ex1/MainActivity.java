package com.example.mcdt_ex1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    static ArrayList<String> carList = new ArrayList<String>();
    EditText line;
    ListView listView;
    ArrayAdapter<String> aa;
    int position = 0;

    private static void getCars(){
        carList.add("Alfa Romeo");
        carList.add("BMW");
        carList.add("Corvette");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getCars();

        listView = (ListView)findViewById(R.id.list_view);
        aa = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,carList);
        listView.setAdapter(aa);

        findViewById(R.id.add_button).setOnClickListener(this);
        findViewById(R.id.remove_button).setOnClickListener(this);
        findViewById(R.id.edit_button).setOnClickListener(this);
        findViewById(R.id.second_activity_button).setOnClickListener(this);
        line = (EditText) findViewById(R.id.text_editor);
    }

    @Override
    public void onClick(View v) {
        int ID = v.getId();

        if(ID == R.id.add_button){
            String mark = line.getText().toString();
            carList.add(mark);
            listView.setAdapter(aa);
        }
        if(ID == R.id.edit_button){
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String selelctedCar = carList.get(position);
                    Toast.makeText(MainActivity.this, "Car Selected: " + selelctedCar, Toast.LENGTH_LONG).show();
                    line.setText(selelctedCar);
                }
            });
        }
        if(ID == R.id.remove_button){
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String selected = carList.get(position);
                    line.setText(selected);
                    carList.remove(selected);
                    line = null;
                    Toast.makeText(MainActivity.this, "Car removed", Toast.LENGTH_LONG).show();

                }
            });
        }
        if(ID == R.id.second_activity_button){
            Toast.makeText(getApplicationContext(), "Second activity selected!", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
            startActivity(intent);
        }
    }
}
