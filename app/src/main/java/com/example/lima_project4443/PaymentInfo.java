//Payment Info java

package com.example.lima_project4443;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class PaymentInfo extends AppCompatActivity {

    private Button pay;
    private EditText firstName, lastName, cardNum, expDate, cvv;

    private CheckBox sameAddressCB;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_activity);
        Intent intent = getIntent();
        type = intent.getStringExtra("type");

        pay = findViewById(R.id.buttonPay);
        firstName = findViewById(R.id.editfirstName);
        lastName = findViewById(R.id.editLastName);
        cardNum = findViewById(R.id.editCardNum);
        expDate = findViewById(R.id.editExpDate);
        cvv = findViewById(R.id.editCVV);
        sameAddressCB = findViewById(R.id.sameAddressCB);
        sameAddressCB.setChecked(true);

        //set onClickListener for the Pay button
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    String fName = firstName.getText().toString();
                    String lName = lastName.getText().toString();
                    String card = cardNum.getText().toString();
                    String expirationD = expDate.getText().toString();
                    String codeCVV = cvv.getText().toString();
                    Context context = v.getContext();
                    Intent intent = new Intent(context, OrderConfirmActivity.class);
                    Bundle b = new Bundle();
                    b.putString("type", type);
                    intent.putExtras(b);
                    context.startActivity(intent);

                    if (fName.trim().isEmpty() || lName.trim().isEmpty() || card.trim().isEmpty() || expirationD.trim().isEmpty() || codeCVV.trim().isEmpty())
                        throw new IllegalArgumentException("Please fill in all the fields");


                } catch (IllegalArgumentException e) {
                    e.printStackTrace();

                    Toast.makeText(PaymentInfo.this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();

                }
            }
        });

        //limit card number to 16 digits only
        cardNum.addTextChangedListener(new TextWatcher() {
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
                // Limit CVV to 3 characters
                if (s.length() > 16) {
                    s.delete(16, s.length());
                }
            }
        });

        //limit expiration date to 4 digits only
        expDate.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Not used
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Limit CVV to 3 characters

                if (s.length() > 4) {
                    s.delete(4, s.length());
                }
            }
        });

        //limit CVV characters to only 3
        cvv.addTextChangedListener(new TextWatcher() {
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
                // Limit CVV to 3 characters
                if (s.length() > 3) {
                    s.delete(3, s.length());
                }
            }
        });
    }

}