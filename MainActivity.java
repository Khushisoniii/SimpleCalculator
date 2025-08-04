package com.example.simplecalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

//import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView resultTv,solutionTv;
    MaterialButton buttonc,buttonAllclear,buttonDecimal,buttonOpenBracett,buttonCloseBracett;
    MaterialButton buttonDivide,buttonMultiply,buttonAdd,buttonMinus,buttonresult;
    MaterialButton button1,button2,button3,button4,button5,button6,button7,button8,button9,button0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        resultTv=findViewById(R.id.result_tv);
        solutionTv=findViewById(R.id.solution_tv);

        assignId(buttonc,R.id.button_C);
        assignId(buttonAllclear,R.id.button_all_clear);
        assignId(buttonDecimal,R.id.button_decimal);
        assignId(buttonOpenBracett,R.id.button_open_button);
        assignId(buttonCloseBracett,R.id.button_close_button);
        assignId(button1,R.id.button_one);
        assignId(button2,R.id.button_two);
        assignId(button3,R.id.button_three);
        assignId(button4,R.id.button_four);
        assignId(button5,R.id.button_five);
        assignId(button6,R.id.button_six);
        assignId(button7,R.id.button_seven);
        assignId(button8,R.id.button_eight);
        assignId(button9,R.id.button_nine);
        assignId(button0,R.id.button_zero);
        assignId(buttonMinus,R.id.button_subraction);
        assignId(buttonMultiply,R.id.button_multipy);
        assignId(buttonAdd,R.id.button_addition);
        assignId(buttonDivide,R.id.button_divide);
        assignId(buttonresult,R.id.button_result);

    }

    void assignId(MaterialButton btn,int id){
        btn=findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick (View view){
        MaterialButton button =(MaterialButton) view;
        String buttonText= button.getText().toString();
        String dataToCalculate=solutionTv.getText().toString();

        if(buttonText.equals("AC")){
            solutionTv.setText("");
            resultTv.setText("0");
            return;

        }
        if(buttonText.equals("=")){
            solutionTv.setText(resultTv.getText());
            return;

        }
        if(buttonText.equals("C")){
            dataToCalculate=dataToCalculate.substring(0,dataToCalculate.length()-1);

        }
        else{
            dataToCalculate=dataToCalculate+buttonText;
        }
        solutionTv.setText(dataToCalculate);

        String finalResult =getResult(dataToCalculate);

        if(!finalResult.equals("err")){
            resultTv.setText(finalResult);
        }

    }
    String getResult(String data){
        try{
            Context context=Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable=context.initStandardObjects();
            String finalResult=context.evaluateString(scriptable,data,"javascript",1,null).toString();
            return finalResult;

        }catch (Exception e){
            return "err";

        }
    }
}
