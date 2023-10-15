package com.example.bmi_g07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources res = getResources();
        final String[]  arr = res.getStringArray(R.array.result_bmi);
        final TextView output = findViewById(R.id.outputNumber);
        final TextView output2 = findViewById(R.id.outputText);
        final EditText weight = findViewById(R.id.editTextNumberDecimal);
        final EditText height = findViewById(R.id.editTextNumberDecimal2);
        final Button calculate_btn = findViewById(R.id.button);


        int color_green = getResources().getColor(R.color.green);
        int color_yellow = getResources().getColor(R.color.yellow);
        int color_lightred = getResources().getColor(R.color.lightred);
        int color_red = getResources().getColor(R.color.red);
        int color_darkred = getResources().getColor(R.color.darkred);


        weight.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(8, 2)});

        calculate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double readyNumber = 0.0;
                double weight_str = 0.0;
                double height_str = 0.0;
                final DecimalFormat df = new DecimalFormat("0.00");

                output.setText("");
                if(weight.getText().toString().isEmpty() && height.getText().toString().isEmpty()){
                    readyNumber = 0.0;
                }else{
                    weight_str = Float.parseFloat(weight.getText().toString());
                    height_str = Float.parseFloat(height.getText().toString());
                    readyNumber = weight_str / (height_str * height_str / 10000);
                }
                output.setText(df.format(readyNumber )+ "");
                if(readyNumber < 16){
                    output2.setText(arr[0]);
                    output2.setBackgroundColor(color_red);
                } else if (readyNumber >= 16 && readyNumber < 17) {
                    output2.setText(arr[1]);
                    output2.setBackgroundColor(color_lightred);
                }else if (readyNumber >= 17 && readyNumber < 18.5) {
                    output2.setText(arr[2]);
                    output2.setBackgroundColor(color_yellow);
                }else if (readyNumber >= 18.5 && readyNumber < 25) {
                    output2.setText(arr[3]);
                    output2.setBackgroundColor(color_green);
                }else if (readyNumber >= 25 && readyNumber < 30) {
                    output2.setText(arr[4]);
                    output2.setBackgroundColor(color_yellow);
                }else if (readyNumber >= 30 && readyNumber < 35) {
                    output2.setText(arr[5]);
                    output2.setBackgroundColor(color_lightred);
                }else if (readyNumber >= 35 && readyNumber < 40) {
                    output2.setText(arr[6]);
                    output2.setBackgroundColor(color_red);
                }else {
                    output2.setText(arr[7]);
                    output2.setBackgroundColor(color_darkred);
                }
            }
        });
    }


}
class DecimalDigitsInputFilter implements InputFilter {
    private Pattern mPattern;

    DecimalDigitsInputFilter(int digits, int digitsAfterZero) {
        mPattern = Pattern.compile("[0-9]{0," + (digits - 1) + "}+((\\.[0-9]{0," + (digitsAfterZero - 1) +
                "})?)||(\\.)?");
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        Matcher matcher = mPattern.matcher(dest);
        if (!matcher.matches())
            return "";
        return null;
    }
}