package com.example.locationspinner;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
public class LocationActivity extends AppCompatActivity implements 
AdapterView.OnItemSelectedListener {
 @Override
 protected void onCreate(Bundle savedInstanceState) {
 super.onCreate(savedInstanceState);
 setContentView(R.layout.activity_main);
 Spinner spinner = findViewById(R.id.spinner1);
 ArrayAdapter<CharSequence> adapter = 
ArrayAdapter.createFromResource(this,
 R.array.villages, android.R.layout.simple_spinner_item);
 
adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_it
em);
 spinner.setAdapter(adapter);
 spinner.setOnItemSelectedListener(this);
 }
 @Override
 public void onItemSelected(AdapterView<?> parent, View view, int
position, long id) {
 String text = parent.getItemAtPosition(position).toString();
 Toast.makeText(parent.getContext(), text, 
Toast.LENGTH_SHORT).show();
 }
 @Override
 public void onNothingSelected(AdapterView<?> parent) {
 }
