package ro.pub.cs.systems.eim.practicaltest01var05;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var05_secondary);

        // Inițializarea TextView-ului pentru a afișa textul primit
        TextView textViewReceived = findViewById(R.id.text_view_received);

        // Obține textul trimis din activitatea principală
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("selected_positions")) {
            String receivedText = intent.getStringExtra("selected_positions");
            textViewReceived.setText(receivedText);
        }

        // Configurarea butonului Verify
        Button buttonVerify = findViewById(R.id.button_verify);
        buttonVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Setează rezultatul ca "VERIFY" și trimite-l înapoi la activitatea principală
                Intent resultIntent = new Intent();
                resultIntent.putExtra("result", "VERIFY");
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });

        // Configurarea butonului Cancel
        Button buttonCancel = findViewById(R.id.button_cancel);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Setează rezultatul ca "CANCEL" și trimite-l înapoi la activitatea principală
                Intent resultIntent = new Intent();
                resultIntent.putExtra("result", "CANCEL");
                setResult(RESULT_CANCELED, resultIntent);
                finish();
            }
        });
    }
}
