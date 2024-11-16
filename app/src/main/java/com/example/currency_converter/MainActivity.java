package com.example.currency_converter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private RadioGroup conversionOptionsGroup;
    private EditText inputAmount;
    private TextView resultText;

    private static final double DINAR_TO_EURO_RATE = 0.3;
    private static final double EURO_TO_DINAR_RATE = 3.3;

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.top_app_bar);
        setSupportActionBar(toolbar);

        // Initialize views
        conversionOptionsGroup = findViewById(R.id.conversion_options_group);
        inputAmount = findViewById(R.id.input_amount);
        Button convertButton = findViewById(R.id.convert_button);
        resultText = findViewById(R.id.result_text);

        // Set click listener for the button
        convertButton.setOnClickListener(v -> {
            String amountStr = inputAmount.getText().toString();

            if (amountStr.isEmpty()) {
                Toast.makeText(this, "Please enter an amount", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                double amount = Double.parseDouble(amountStr);
                double result;

                // Check which radio button is selected
                int selectedId = conversionOptionsGroup.getCheckedRadioButtonId();
                if (selectedId == R.id.radio_button_dinar_to_euro) {
                    result = amount * DINAR_TO_EURO_RATE;
                    resultText.setText(String.format("%.2f Dinar = %.2f Euro", amount, result));
                } else if (selectedId == R.id.radio_button_euro_to_dinar) {
                    result = amount * EURO_TO_DINAR_RATE;
                    resultText.setText(String.format("%.2f Euro = %.2f Dinar", amount, result));
                } else {
                    Toast.makeText(this, "Please select a conversion option", Toast.LENGTH_SHORT).show();
                }
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Invalid amount entered", Toast.LENGTH_SHORT).show();
            }
        });
    }
}