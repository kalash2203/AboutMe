package android.example.aboutme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class LoginActivity extends AppCompatActivity {
    EditText phoneNumber;
    String userPhoneNumber;
    FloatingActionButton sendOTPBtn;
    String countryCode;

    CountryCodePicker ccp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //countrycode
        ccp = (CountryCodePicker) findViewById(R.id.ccp);
        countryCode = ccp.getSelectedCountryCodeWithPlus();
        ccp.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected() {
                countryCode = ccp.getSelectedCountryCodeWithPlus();
            }
        });

        phoneNumber = findViewById(R.id.number_edt);
        sendOTPBtn = findViewById(R.id.sendOtpBtn);


        sendOTPBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (phoneNumber.getText().toString().isEmpty()) {
                    phoneNumber.setError("required");
                    return;
                } else if (phoneNumber.getText().toString().length() != 10) {
                    phoneNumber.setError("invalid phone number");
                    return;
                } else {
                    userPhoneNumber = countryCode + phoneNumber.getText().toString();
                    Intent intent = new Intent(getApplicationContext(), OtpActivity.class);
                    intent.putExtra("phoneNumber", userPhoneNumber);
                    startActivity(intent);
                    finish();

                }
            }
        });
    }}
