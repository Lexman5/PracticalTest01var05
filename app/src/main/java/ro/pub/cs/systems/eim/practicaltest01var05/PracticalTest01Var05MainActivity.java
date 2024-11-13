package ro.pub.cs.systems.eim.practicaltest01var05;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class PracticalTest01Var05MainActivity extends AppCompatActivity {

    private TextView textViewOutput;
    private int buttonPressCount = 0; // Contor pentru apăsările butoanelor

    // Cheile pentru salvarea stării
    private static final String TEXT_VIEW_OUTPUT_KEY = "textViewOutputKey";
    private static final String BUTTON_PRESS_COUNT_KEY = "buttonPressCountKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var05_main);

        textViewOutput = findViewById(R.id.text_view_output);

        // Inițializarea butoanelor
        Button buttonTopLeft = findViewById(R.id.button_top_left);
        Button buttonTopRight = findViewById(R.id.button_top_right);
        Button buttonCenter = findViewById(R.id.button_center);
        Button buttonBottomLeft = findViewById(R.id.button_bottom_left);
        Button buttonBottomRight = findViewById(R.id.button_bottom_right);
        Button buttonNavigateSecondary = findViewById(R.id.button_navigate_secondary);

        // Setarea OnClickListener-urilor pentru fiecare buton
        View.OnClickListener buttonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = ((Button) v).getText().toString();
                updateTextView(text);
            }
        };

        buttonTopLeft.setOnClickListener(buttonClickListener);
        buttonTopRight.setOnClickListener(buttonClickListener);
        buttonCenter.setOnClickListener(buttonClickListener);
        buttonBottomLeft.setOnClickListener(buttonClickListener);
        buttonBottomRight.setOnClickListener(buttonClickListener);

        // Setează un OnClickListener pentru a lansa activitatea secundară la apăsarea butonului de navigare
        buttonNavigateSecondary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToSecondaryActivity();
            }
        });

        // Restaurarea stării dacă există
        if (savedInstanceState != null) {
            String savedText = savedInstanceState.getString(TEXT_VIEW_OUTPUT_KEY);
            buttonPressCount = savedInstanceState.getInt(BUTTON_PRESS_COUNT_KEY, 0);
            textViewOutput.setText(savedText);
            Toast.makeText(this, "Button Press Count: " + buttonPressCount, Toast.LENGTH_SHORT).show();
        }
    }

    // Salvarea stării
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(TEXT_VIEW_OUTPUT_KEY, textViewOutput.getText().toString());
        outState.putInt(BUTTON_PRESS_COUNT_KEY, buttonPressCount);
    }

    // Funcție pentru a actualiza TextView și a incrementa contorul
    private void updateTextView(String text) {
        buttonPressCount++;
        String currentText = textViewOutput.getText().toString();
        if (currentText.equals("Select a position") || currentText.isEmpty()) {
            textViewOutput.setText(text);
        } else {
            textViewOutput.setText(currentText + ", " + text);
        }
    }

    // Funcție pentru a naviga la SecondaryActivity
    private void navigateToSecondaryActivity() {
        Intent intent = new Intent(this, PracticalTest01Var05SecondaryActivity.class);
        intent.putExtra("total_button_presses", buttonPressCount);
        startActivityForResult(intent, 1); // 1 este codul cererii
    }

    // Suprascrierea onActivityResult pentru a primi rezultatul
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && data != null) {
            String result = data.getStringExtra("result");
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Result from SecondaryActivity: " + result, Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Result from SecondaryActivity: " + result, Toast.LENGTH_SHORT).show();
            }

            // Resetăm TextView și contorul de apăsări
            textViewOutput.setText("");
            buttonPressCount = 0;
        }
    }
}
