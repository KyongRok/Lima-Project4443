package com.example.lima_project4443;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.lima_project4443.DAO.DataBaseParticipantsHelper;
import com.example.lima_project4443.Model.Login_Model;

/*
Group Members:
KyongRok Kim, 215813413
Brian Nguyen, 217233966
Seong Su Kim, 215481575
Alexis Estropia, 217146473
 */
public class MainActivity extends AppCompatActivity {

    private EditText editTextAge, hoursofUsage;
    private RadioGroup radioGroupGender;
    private RadioButton radioButtonMale, radioButtonFemale;
    private Button submitButton;

    //public DataBaseHelper(@Nullable Context context){

    //}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextAge = findViewById(R.id.editTextAge);
        radioGroupGender = findViewById(R.id.radioGroupGender);
        radioButtonMale = findViewById(R.id.radioButtonMale);
        radioButtonFemale = findViewById(R.id.radioButtonFemale);
        hoursofUsage = findViewById(R.id.editTextHours);
        submitButton = findViewById(R.id.buttonSubmit);
        DataBaseParticipantsHelper dbHelper = new DataBaseParticipantsHelper(MainActivity.this);
    //db works
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if(!editTextAge.getText().equals("")){
                  try{
                      Login_Model loginmodel = new Login_Model();
                      loginmodel.setAge(Integer.parseInt(editTextAge.getText().toString()));
                      int genderId = radioGroupGender.getCheckedRadioButtonId();
                      int gender = (genderId == R.id.radioButtonMale) ? 1 : 2;
                      loginmodel.setGender(gender);
                      loginmodel.setHours_Phone(Integer.parseInt(hoursofUsage.getText().toString()));
                      dbHelper.addParticipants(loginmodel);
                      Toast.makeText(MainActivity.this, loginmodel.toString(), Toast.LENGTH_LONG).show();
                  }
                  catch(Exception e){
                      Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();


                  }


                //}

            }
        });
    }
}