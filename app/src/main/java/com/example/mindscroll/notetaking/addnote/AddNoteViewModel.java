package com.example.mindscroll.notetaking.addnote;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.mindscroll.notetaking.database.NoteDatabase;
import com.example.mindscroll.notetaking.database.NoteModel;

public class AddNoteViewModel extends AndroidViewModel {
    private NoteDatabase noteDatabase;


    public AddNoteViewModel(@NonNull Application application) {
        super(application);

        noteDatabase = NoteDatabase.getNoteDatabaseInstance(this.getApplication());
    }


    public void  addNote(final NoteModel  noteModel)
    {
        new addAsycTask(noteDatabase).execute(noteModel);
    }

    private class addAsycTask extends AsyncTask<NoteModel,Void,Void> {
        private    NoteDatabase noteDatabase;

        public addAsycTask(NoteDatabase noteDatabase) {
this.noteDatabase = noteDatabase;

        }

        @Override
        protected Void doInBackground(NoteModel... noteModels) {

            noteDatabase.noteItemAndNoteModelDao().insertNote(noteModels[0]);
            return null;
        }
    }
}
