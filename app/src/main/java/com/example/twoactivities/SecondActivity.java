package com.example.twoactivities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    static final String EXTRA_REPLY = "EXTRA_REPLY";

    private EditText replyEditText;

    private static final String LOG_CAT =SecondActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        String msg = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        TextView textView = findViewById(R.id.text_message);
        textView.setText(msg);

        replyEditText = findViewById(R.id.editText_second);
    }

    public void returnReply(View view) {

        String msg = replyEditText.getText().toString();

        Intent intent = new Intent();
        intent.putExtra(EXTRA_REPLY,msg);

        setResult(RESULT_OK);

        Log.d(LOG_CAT,"End SecondActivity");
    }
}