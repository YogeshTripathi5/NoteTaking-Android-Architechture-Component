package com.digikeep.noteitem;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import androidx.annotation.Nullable;

import com.digikeep.login.LoginActivity;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.digikeep.R;
import com.digikeep.addnote.AddNoteActivity;
import com.digikeep.database.NoteModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NoteActivity extends AppCompatActivity implements View.OnLongClickListener {

    private NotesListViewModel viewModel;
    private RecyclerView mNotesRecyclerView;
    private NoteAdapter mAdapter;
    private BottomAppBar bottom_app_bar;
    private TextView name;
    private Button button2;
    private String uniqueid;
    private DatabaseReference myRef,myRef2;
    List<NoteModel> noteModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottom_app_bar = findViewById(R.id.bottom_app_bar);
        name = findViewById(R.id.name);
        name.setText(getIntent().getStringExtra("name"));
        uniqueid = getIntent().getStringExtra("uniqueid");
        setSupportActionBar(bottom_app_bar);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //FloatingActionButton note = findViewById(R.id.fab);

        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 // signOut();
            }
        });
      /*  note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               *//* Intent addnote = new Intent(NoteActivity.this, AddNoteActivity.class);
                startActivity(addnote);*//*



            }
        });
*/

        mNotesRecyclerView = findViewById(R.id.recyclerView);
        mAdapter = new NoteAdapter(new ArrayList<NoteModel>(), this);
        mNotesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mNotesRecyclerView.setAdapter(mAdapter);


     /*   viewModel = ViewModelProviders.of(this).get(NotesListViewModel.class);
        viewModel.getNoteList().observe(NoteActivity.this, new Observer<List<NoteModel>>() {
            @Override
            public void onChanged(@Nullable List<NoteModel> noteModels) {
               mAdapter.addNote(noteModels);
                randomshit(noteModels);
            }
        });
*/
        // Write a message to the database



        myRef = database.getReference("message");
        myRef2 = myRef.child(uniqueid);
        // Read from the database
        myRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                noteModels.clear();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    NoteModel university = postSnapshot.getValue(NoteModel.class);
                    noteModels.add(university);
                }

                mAdapter.addNote(noteModels);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //  Log.w(TAG, "Failed to read value.", error.toException());
            }
        });


    }




    private void randomshit(List<NoteModel> noteModels) {

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
         myRef = database.getReference("message");
        myRef.child(uniqueid).setValue(noteModels);

    }

    private void signOut() {
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        Intent intent = new Intent(NoteActivity.this, LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
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
