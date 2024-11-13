package ro.pub.cs.systems.eim.practicaltest01var05;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.SystemClock;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PracticalTest01Var05Service extends Service {

    public static final String ACTION_BROADCAST = "ro.pub.cs.systems.eim.practicaltest01var05.SERVICE_BROADCAST";
    private Handler handler = new Handler();
    private int receivedNumber = 0;

    // Formatter pentru data și ora
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            // Obținem data și ora curentă
            String currentDateAndTime = dateFormatter.format(new Date());

            // Creăm mesajul de difuzare
            Intent broadcastIntent = new Intent(ACTION_BROADCAST);
            broadcastIntent.putExtra("date_time", currentDateAndTime);
            broadcastIntent.putExtra("received_number", receivedNumber);

            // Trimiterea mesajului
            sendBroadcast(broadcastIntent);

            // Reprogramarea pentru a trimite din nou peste 5 secunde
            handler.postDelayed(this, 5000);
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "Service Created", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Service Started", Toast.LENGTH_SHORT).show();

        // Obținem numărul din intenție, dacă există
        if (intent != null && intent.hasExtra("number")) {
            receivedNumber = intent.getIntExtra("number", 0);
        }

        // Pornim difuzarea mesajelor la fiecare 5 secunde
        handler.post(runnable);

        // Specificăm că serviciul ar trebui să fie repornit în caz că este oprit
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service Stopped", Toast.LENGTH_SHORT).show();
        handler.removeCallbacks(runnable); // Oprim difuzarea mesajelor
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null; // Serviciul nu permite legături
    }
}
