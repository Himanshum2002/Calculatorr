package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    TextView resultTv,solutionTv;
    MaterialButton buttonC,buttonBackOpen,buttonBackClose;
    MaterialButton buttonDivided,buttonMultiply,buttonMinus,buttonPlus,buttonEquals;
    MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;
    MaterialButton buttonAC,buttonDot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTv = findViewById(R.id.hellohello);
        solutionTv = findViewById(R.id.titu);

        assignId(buttonC,R.id.hello1);
        assignId(buttonBackOpen,R.id.hello2);
        assignId(buttonBackClose,R.id.hello3);
        assignId(buttonDivided,R.id.hello4);
        assignId(buttonMultiply,R.id.hello4ab);
        assignId(buttonMinus,R.id.hello4b);
        assignId(buttonPlus,R.id.hello4ad);
        assignId(buttonEquals,R.id.hello4ah);
        assignId(button0,R.id.hello2ah);
        assignId(button1,R.id.hello3ad);
        assignId(button2,R.id.hello2ad);
        assignId(button3,R.id.hello1ad);
        assignId(button4,R.id.hello3b);
        assignId(button5,R.id.hello2b);
        assignId(button6,R.id.hello1c);
        assignId(button7,R.id.hello3ab);
        assignId(button8,R.id.hello2ab);
        assignId(button9,R.id.hello1b);
        assignId(buttonAC,R.id.hello1ah);
        assignId(buttonDot,R.id.hello3a);




    }

    void assignId(MaterialButton btn,int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solutionTv.getText().toString();

        if (buttonText.equals("AC")){
            solutionTv.setText("");
            resultTv.setText("0");
            return;
        }
        if (buttonText.equals("=")){
            solutionTv.setText(resultTv.getText());
            return;
        }
        if (buttonText.equals("C")){
            dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
        }else {
            dataToCalculate = dataToCalculate+buttonText;
        }

        solutionTv.setText(dataToCalculate);
        String finalResult=getResult(dataToCalculate);
        if(!finalResult.equals("Error")){
            resultTv.setText(finalResult);
        }

    }

    String getResult(String data){
        try{
            Context context=Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable=context.initStandardObjects();
            String finalResult= context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult=finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e){
            return "Error";
        }
    }
}