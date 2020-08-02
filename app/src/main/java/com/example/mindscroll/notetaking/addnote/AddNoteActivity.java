package com.example.mindscroll.notetaking.addnote;

import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;

import com.example.mindscroll.notetaking.R;
import com.google.android.material.button.MaterialButton;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.notetaking.database.NoteModel;

public class AddNoteActivity extends AppCompatActivity {
    private EditText mTitle;
    private EditText mDescription;
    private com.notetaking.addnote.AddNoteViewModel addNoteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
       // Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        mTitle = findViewById(R.id.noteTitle);
        mDescription = findViewById(R.id.noteDescription);

        addNoteViewModel = ViewModelProviders.of(this).get(com.notetaking.addnote.AddNoteViewModel.class);

        //FloatingActionButton note = findViewById(R.id.fab);

        MaterialButton note = findViewById(R.id.subit);
        note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mTitle.getText() == null || mDescription.getText() == null){
                    Toast.makeText(AddNoteActivity.this, "Hi there you missed something", Toast.LENGTH_SHORT).show();
                }else{
                    addNoteViewModel.addNote(new NoteModel(mTitle.getText().toString(),
                            mDescription.getText().toString()
                    ));
                }
                finish();
            }
        });
    }
}
