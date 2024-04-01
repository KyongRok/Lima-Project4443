package com.example.lima_project4443;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Checkout extends AppCompatActivity {

    private Button buttonPay;
    private EditText editTextFirstName, editTextLastName, editTextContactPhone, editTextStreetAdd, editTextOptional, editTextCity, editTextPostalCode;
    private Spinner spinnerProvince;
    private String type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkout_activity);
        Intent intent = getIntent();
        type = intent.getStringExtra("type");

        //UI elements initialize
        buttonPay = findViewById(R.id.buttonPay);
        editTextFirstName = findViewById(R.id.editfirstName);
        editTextLastName = findViewById(R.id.editLastName);
        editTextContactPhone = findViewById(R.id.editContactPhone);
        editTextStreetAdd = findViewById(R.id.editStreetAdd);
        editTextOptional = findViewById(R.id.editOptional);
        editTextCity = findViewById(R.id.editCity);
        editTextPostalCode = findViewById(R.id.editPostalCode);
        spinnerProvince = findViewById(R.id.editProvince);

        //define array of Canada provinces
        String[] canadaProvinces = {"Alberta", "British Columbia", "Manitoba", "New Brunswick", /* Add more provinces as needed */};

        String[] provinces = getResources().getStringArray(R.array.canadaProvinces); //retrieve array of provinces from resource
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, provinces); //create array adapter to populate the spinner with the list of provinces
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProvince.setAdapter(adapter);

        //set onClickListener for the Proceed to pay button
        buttonPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String firstName = editTextFirstName.getText().toString();
                    String lastName = editTextLastName.getText().toString();
                    String contactPhone = editTextContactPhone.getText().toString();
                    String streetAdd = editTextStreetAdd.getText().toString();
                    String optional = editTextOptional.getText().toString();
                    String city = editTextCity.getText().toString();
                    String postalCode = editTextPostalCode.getText().toString();
                    String province = spinnerProvince.getSelectedItem().toString();

                    //check if textfield are all filled in except to optional
                    if (firstName.trim().isEmpty() || lastName.trim().isEmpty() || contactPhone.trim().isEmpty() || streetAdd.trim().isEmpty() || city.trim().isEmpty() || postalCode.trim().isEmpty() || province.trim().isEmpty())
                        throw new IllegalArgumentException("Please fill in all the fields");

                    //for test purposes:
                    String checkoutInfo = "First Name: " + firstName + "\n" +
                            "Last Name: " + lastName + "\n" +
                            "Contact Phone: " + contactPhone + "\n" +
                            "Street Address: " + streetAdd + "\n" +
                            "Optional: " + optional + "\n" +
                            "City: " + city + "\n" +
                            "Postal Code: " + postalCode + "\n" +
                            "Province: " + province;
                    // Log the checkout information
                    System.out.println(checkoutInfo);

                    Intent intent = new Intent(Checkout.this, PaymentInfo.class);
                    Bundle b = new Bundle();
                    b.putString("type",type);
                    intent.putExtras(b);
                    startActivity(intent);

                } catch (IllegalArgumentException e) {
                    e.printStackTrace();

                    Toast.makeText(Checkout.this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //limit mobile number characters to only 10
        editTextContactPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Not used
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Not used
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Limit CVV to 10 characters
                if (s.length() > 10) {
                    s.delete(10, s.length());
                }
            }
        });

        //limit postal code characters to only 6
        editTextPostalCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Not used
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Not used
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Limit CVV to 6 characters
                if (s.length() > 6) {
                    s.delete(6, s.length());
                }
            }
        });
    }
}