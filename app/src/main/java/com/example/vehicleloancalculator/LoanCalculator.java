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


}