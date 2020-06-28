package com.digikeep.noteitem;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import androidx.annotation.Nullable;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.digikeep.R;
import com.digikeep.addnote.AddNoteActivity;
import com.digikeep.database.NoteModel;

import java.util.ArrayList;
import java.util.List;

public class NoteActivity extends AppCompatActivity implements View.OnLongClickListener {

    private NotesListViewModel viewModel;
    private RecyclerView mNotesRecyclerView;
    private NoteAdapter mAdapter;
    private BottomAppBar bottom_app_bar;
    private TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottom_app_bar = findViewById(R.id.bottom_app_bar);
        name = findViewById(R.id.name);
        name.setText(getIntent().getStringExtra("name"));
        setSupportActionBar(bottom_app_bar);

        FloatingActionButton note = findViewById(R.id.fab);

        note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addnote = new Intent(NoteActivity.this, AddNoteActivity.class);
                startActivity(addnote);
            }
        });


        mNotesRecyclerView = findViewById(R.id.recyclerView);
        mAdapter = new NoteAdapter(new ArrayList<NoteModel>(), this);
        mNotesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mNotesRecyclerView.setAdapter(mAdapter);


        viewModel = ViewModelProviders.of(this).get(NotesListViewModel.class);
        viewModel.getNoteList().observe(NoteActivity.this, new Observer<List<NoteModel>>() {
            @Override
            public void onChanged(@Nullable List<NoteModel> noteModels) {
                mAdapter.addNote(noteModels);
            }
        });
    }

    @Override
    public boolean onLongClick(View view) {
        NoteModel noteModel = (NoteModel) view.getTag();
        viewModel.deleteNote(noteModel);

        Toast.makeText(this, noteModel.getNoteTitle() + "->Just deleted", Toast.LENGTH_SHORT).show();
        return true;
    }
}
