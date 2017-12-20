package com.denie.mediashortcuts2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTitleView, editNoteView;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        saveButton = (Button)findViewById(R.id.save_button);
        editTitleView = (EditText)findViewById(R.id.edit_title);
        editNoteView = (EditText)findViewById(R.id.edit_note);

        saveButton.setOnClickListener(this);

        Paper.init(this);
    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.save_button:
                Paper.book().write("title", editTitleView.getText().toString());
                Paper.book().write("note", editNoteView.getText().toString());
                break;
        }
    }
}
