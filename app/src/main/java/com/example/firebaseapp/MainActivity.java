package com.example.firebaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private Button saveDataButton,loadDataButton;
    private EditText nameEditText,ageEditText;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseReference= FirebaseDatabase.getInstance().getReference("students");

        saveDataButton=findViewById(R.id.saveDataButtonId);
        nameEditText=findViewById(R.id.nameEditTextId);
        ageEditText=findViewById(R.id.ageEditTextId);
        loadDataButton=findViewById(R.id.loadDataButtonId);

        loadDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,DetailsActivity.class);
                startActivity(intent);
            }
        });

        saveDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
    }
    public void saveData(){
        String name=nameEditText.getText().toString().trim();
        String age=ageEditText.getText().toString().trim();

        String key=databaseReference.push().getKey();

        Student student=new Student(name,age);

        databaseReference.child(key).setValue(student);

        Toast.makeText(getApplicationContext(),"Student Added.",Toast.LENGTH_LONG).show();

        nameEditText.setText("");
        ageEditText.setText("");

    }
}
