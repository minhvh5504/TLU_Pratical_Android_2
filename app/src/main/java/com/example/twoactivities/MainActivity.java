package com.example.twoactivities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";

    public static final int EXTRA_REQUEST = 1;

    private EditText msgEditText;

    private TextView textReplyHeader;
    private TextView textReply;

    private ActivityResultLauncher<Intent> activityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        msgEditText = findViewById(R.id.editText_main);
        textReplyHeader = findViewById(R.id.text_header_reply);
        textReply = findViewById(R.id.text_message_reply);

        if (savedInstanceState != null){
            boolean isVisible = savedInstanceState.getBoolean("reply_visible");

            if (isVisible){
                textReply.setText(savedInstanceState.getString("reply_text"));

                textReply.setVisibility(View.VISIBLE);

            }
        }

        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            String message = data.getStringExtra(SecondActivity.EXTRA_REPLY);
                            textReplyHeader.setVisibility(View.VISIBLE);
                            textReply.setText(message);
                            textReply.setVisibility(View.VISIBLE);
                        }
                    }
                }
        );

        Log.d(LOG_TAG,"---");
        Log.d(LOG_TAG,"onCreate");
    }

    public void launchSecondActivity(View view) {
        Log.d(LOG_TAG, "Button Clicked!");

        Intent intent = new Intent(this , SecondActivity.class);

        String msg = msgEditText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE,msg);

        activityResultLauncher.launch(intent);
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d(LOG_TAG,"onStart");
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);

        if (textReplyHeader.getVisibility() == View.VISIBLE) {
            outState.putBoolean("reply visible",true);

            outState.putString("reply_text",textReply.getText().toString());
        }
    }

}