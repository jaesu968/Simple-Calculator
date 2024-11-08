package com.codecademy.simplecalculator;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        // Reference UI elements
        // reference to the first number box in a variable called firstNumber
        EditText firstNumber = findViewById(R.id.number1);
        // reference to the second number box in a variable called secondNumber
        EditText secondNumber = findViewById(R.id.number2);
        // store the radio group in a RadioGroup object called operators
        RadioGroup operators = findViewById(R.id.operators);
        // store the addition and subtraction radio buttons
        // in RadioButton objects called add and subtract
        RadioButton add = findViewById(R.id.add);
        RadioButton subtract = findViewById(R.id.subtract);
        RadioButton multiply = findViewById(R.id.multiply);
        RadioButton divide = findViewById(R.id.divide);

        // Store the equals button in a Button object called equals
        Button equals = findViewById(R.id.equals);
        // Store the answer text view in a TextView object
        // called result
        TextView result = findViewById(R.id.result);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // set the up equals button
        equals.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // parse firstNumber for integer
                try{
                double firstNumberValue = Double.parseDouble(firstNumber.getText().toString());
                double secondNumberValue = Double.parseDouble(secondNumber.getText().toString());
                double answer;

                // get the operator
                   double operatorButtonId = operators.getCheckedRadioButtonId();

                // conditional logic to make sure answer displays correctly
                    if(operatorButtonId == add.getId()){
                        answer = firstNumberValue + secondNumberValue;
                    } else if (operatorButtonId == subtract.getId()){
                        answer = firstNumberValue - secondNumberValue;
                    } else if (operatorButtonId == multiply.getId()){
                        answer = firstNumberValue * secondNumberValue;
                    } else if(operatorButtonId == divide.getId()){
                        if(secondNumberValue == 0){
                            answer = 0;
                        } else {
                            answer = firstNumberValue / secondNumberValue;
                        }
                    }else {
                        Toast.makeText(MainActivity.this, "Select an operator", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    // set the result text to answer
                    result.setText(String.valueOf(answer));
            } catch (NumberFormatException e){
                    // handle the exception
                    Toast.makeText(MainActivity.this, "Invalid input", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}