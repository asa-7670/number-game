package com.asa.numbergame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvHistory;
    private EditText edNumber;
    private Button btnCompare;
    private int score ;
    private int searchValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvHistory = (TextView) findViewById(R.id.tvHistory);
        edNumber = (EditText) findViewById(R.id.edNumber);
        btnCompare = (Button) findViewById(R.id.btnCompare);

        btnCompare.setOnClickListener(onClickCompareListener);

        init();

    }
    private void init() {
        tvHistory.setText("");
        edNumber.setText("");
        edNumber.requestFocus();
        score = 0;
        searchValue = 1 + (int)( Math.random() * 100);
    }

    private void congratulations() {
        AlertDialog retryAlert = new AlertDialog.Builder( this ).create();
        retryAlert.setTitle(getString(R.string.app_name));
        retryAlert.setMessage(String.format(getString(R.string.str_congratulations), score));

        retryAlert.setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.str_btn_yes), new AlertDialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                init();
            }
        });
        retryAlert.setButton(AlertDialog.BUTTON_NEGATIVE, getString(R.string.str_btn_no), new AlertDialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               finish();
            }
        });

        retryAlert.show();
    }

    private View.OnClickListener onClickCompareListener = new View.OnClickListener() {
        @Override
        public void  onClick(View v) {
            String strNumber = edNumber.getText().toString();
            if(strNumber.isEmpty()) return;
            tvHistory.append(strNumber + "\n\r");
            score++;

            int numberEntred = Integer.valueOf(strNumber).intValue();
            if( numberEntred==(searchValue)){
                congratulations();
            } else if(numberEntred < searchValue) {
                tvHistory.append(getString(R.string.str_greater) +"\n\r");
            } else {
                tvHistory.append(getString(R.string.str_lower)+"\n\r");
            }
            edNumber.setText("");
            edNumber.requestFocus();
        }
    };
}
