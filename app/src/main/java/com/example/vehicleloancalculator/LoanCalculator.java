package com.example.vehicleloancalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class LoanCalculator extends AppCompatActivity {

    EditText vehiclePrice, downPayment, loanPeriod, interestRate;
    Button btnCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_loan_calculator);

        //connect toolbar
        Toolbar toolbar = findViewById(R.id.materialToolbar);
        setSupportActionBar(toolbar);

        //back button
        TextView backButton = findViewById(R.id.backBtn);

        // Set click listener
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go back to MainActivity
                Intent intent = new Intent(LoanCalculator.this, MainActivity.class);
                startActivity(intent);
                finish(); //remove AboutUs from back stack
            }
        });

        vehiclePrice = findViewById(R.id.vehiclePrice);
        downPayment = findViewById(R.id.downPayment);
        loanPeriod = findViewById(R.id.loanPeriod);
        interestRate = findViewById(R.id.interestRate);
        btnCalculate = findViewById(R.id.btnCalculate);

        btnCalculate.setOnClickListener(v -> calculateLoan());
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

         if (id == R.id.action_about) {
            Intent intent = new Intent(LoanCalculator.this, AboutUs.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void calculateLoan() {
        try {
            //read input
            double price = Double.parseDouble(vehiclePrice.getText().toString());
            double down = Double.parseDouble(downPayment.getText().toString());
            int years = Integer.parseInt(loanPeriod.getText().toString());
            double rate = Double.parseDouble(interestRate.getText().toString());

            //apply formula
            double loanAmount = price - down;
            double totalInterest = loanAmount * (rate / 100) * years;
            double totalPayment = loanAmount + totalInterest;
            double monthlyPayment = totalPayment / (years * 12);

            //update table rows
            TextView loanAmountText = findViewById(R.id.loanAmountText);
            TextView monthlyPaymentText = findViewById(R.id.monthlyPaymentText);
            TextView totalInterestText = findViewById(R.id.totalInterestText);
            TextView totalPaymentText = findViewById(R.id.totalPaymentText);

            loanAmountText.setText(String.format("RM %.2f", loanAmount));
            monthlyPaymentText.setText(String.format("RM %.2f", monthlyPayment));
            totalInterestText.setText(String.format("RM %.2f", totalInterest));
            totalPaymentText.setText(String.format("RM %.2f", totalPayment));
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter valid numbers in all fields", Toast.LENGTH_SHORT).show();
        }
    }
}