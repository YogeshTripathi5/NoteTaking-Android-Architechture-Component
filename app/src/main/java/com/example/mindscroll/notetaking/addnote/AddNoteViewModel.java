package com.notetaking.addnote;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import android.os.AsyncTask;
import androidx.annotation.NonNull;

import com.notetaking.database.NoteDatabase;
import com.notetaking.database.NoteModel;

public class AddNoteViewModel extends AndroidViewModel {
    private NoteDatabase noteDatabase;


    public AddNoteViewModel(@NonNull Application application) {
        super(application);

        noteDatabase = NoteDatabase.getNoteDatabaseInstance(this.getApplication());
    }


    public void  addNote(final NoteModel  noteModel)
    {if (noteModel!=null)
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
