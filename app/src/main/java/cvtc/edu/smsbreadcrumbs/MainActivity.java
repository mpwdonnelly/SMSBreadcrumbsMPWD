package cvtc.edu.smsbreadcrumbs;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity  {

    // objects needed for the messaging functions
    private EditText targetNumber, smsMessage;
    private Button sendButton;

    // objects needed for the location functions


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        targetNumber = findViewById(R.id.target_number_text);
        smsMessage = findViewById(R.id.message_text);
        sendButton = findViewById(R.id.send_button);

        sendButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                        sendSMSmessage();
                    } else {
                        requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 1);
                    }
                }
            }
    });

    }

    private void sendSMSmessage() {
        String targetPhoneNumber = targetNumber.getText().toString().trim();
        String smsText = smsMessage.getText().toString().trim();


        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(targetPhoneNumber, null, smsText, null, null);
            Toast.makeText(this, "Message sent", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Send Message Failed", Toast.LENGTH_SHORT).show();
        }
    }


}