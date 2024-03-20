package com.example.lima_project4443;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
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
    private Switch switchToggle;
    private TextView selectedOption;
    private String selectedVersion;


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
        Switch switchToggle = findViewById(R.id.typeToggle);
        selectedOption = findViewById(R.id.selectedOption);
        switchToggle.setChecked(false);
        selectedOption.setText("Selected Option: A");
        selectedVersion = "A";

        //@@@@@@DELETE LATER!!
        boolean dontmakedata = true;

        DataBaseParticipantsHelper dbHelper = new DataBaseParticipantsHelper(MainActivity.this);
        //db works
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if(!editTextAge.getText().equals("")){
                try{
                    Login_Model loginmodel = new Login_Model();
                    loginmodel.setAge(Integer.parseInt(editTextAge.getText().toString()));
                    //we need to set ID for the participant mannually.
                    int genderId = radioGroupGender.getCheckedRadioButtonId();
                    int gender = (genderId == R.id.radioButtonMale) ? 1 : 2;
                    loginmodel.setGender(gender);
                    loginmodel.setHours_Phone(Integer.parseInt(hoursofUsage.getText().toString()));
                    loginmodel.setType(selectedVersion);
                    dbHelper.addParticipants(loginmodel);
                    Toast.makeText(MainActivity.this, Long.toString(loginmodel.getId()),Toast.LENGTH_LONG).show();
                    Bundle b = new Bundle();

                    b.putString("type",selectedVersion);
                    Intent i = new Intent(MainActivity.this,LoginActivity.class);
                  
                    i.putExtras(b);
                    startActivity(i);

                }
                catch(Exception e){
                    Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();


                }


                //}

            }
        });

        /**
         * A listener for toggle that determines which version will be tested
         */
        switchToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Switch is in the "B" position
                    selectedOption.setText("Selected Option: B");
                    selectedVersion = "B";
                    Toast.makeText(MainActivity.this, "Switch is in B position", Toast.LENGTH_SHORT).show();
                } else {
                    // Switch is in the "A" position
                    selectedOption.setText("Selected Option: A");
                    selectedVersion = "A";
                    Toast.makeText(MainActivity.this, "Switch is in A position", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    }
