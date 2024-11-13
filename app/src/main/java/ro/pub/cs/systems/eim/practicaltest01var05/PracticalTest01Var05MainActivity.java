package ro.pub.cs.systems.eim.practicaltest01var05;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PracticalTest01Var05MainActivity extends AppCompatActivity {

    private TextView textViewOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var05_main);

        // Inițializarea TextView-ului
        textViewOutput = findViewById(R.id.text_view_output);

        // Configurarea fiecărui buton
        Button buttonTopLeft = findViewById(R.id.button_top_left);
        Button buttonTopRight = findViewById(R.id.button_top_right);
        Button buttonCenter = findViewById(R.id.button_center);
        Button buttonBottomLeft = findViewById(R.id.button_bottom_left);
        Button buttonBottomRight = findViewById(R.id.button_bottom_right);
        Button buttonNavigate = findViewById(R.id.button_navigate);

        // Setarea OnClickListener-urilor pentru fiecare buton
        buttonTopLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTextView("Top Left");
            }
        });

        buttonTopRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTextView("Top Right");
            }
        });

        buttonCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTextView("Center");
            }
        });

        buttonBottomLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTextView("Bottom Left");
            }
        });

        buttonBottomRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTextView("Bottom Right");
            }
        });

        // Setarea OnClickListener pentru butonul de navigare
        buttonNavigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToSecondaryActivity();
            }
        });
    }

    // Funcție pentru a actualiza textul din TextView
    private void updateTextView(String text) {
        // Obține textul existent și adaugă noul text cu virgulă ca delimitator
        String currentText = textViewOutput.getText().toString();
        if (currentText.equals("Select a position") || currentText.isEmpty()) {
            textViewOutput.setText(text);
        } else {
            textViewOutput.setText(currentText + ", " + text);
        }
    }

    // Funcție pentru a naviga la SecondaryActivity
    private void navigateToSecondaryActivity() {
        Intent intent = new Intent(this, SecondaryActivity.class);
        intent.putExtra("selected_positions", textViewOutput.getText().toString());
        startActivity(intent);
    }
}
