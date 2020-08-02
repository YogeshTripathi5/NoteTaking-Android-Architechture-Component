package com.notetaking.noteitem;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import android.os.AsyncTask;
import androidx.annotation.NonNull;

import com.notetaking.database.NoteDatabase;
import com.notetaking.database.NoteModel;

import java.util.List;

public class NotesListViewModel extends AndroidViewModel {
    private final LiveData<List<NoteModel>> noteList;
    private NoteDatabase noteDatabase;

    public NotesListViewModel(@NonNull Application application) {
        super(application);

        noteDatabase = NoteDatabase.getNoteDatabaseInstance(this.getApplication());
        noteList = noteDatabase.noteItemAndNoteModelDao().getAllNotes();
    }



    public LiveData<List<NoteModel>> getNoteList()
    {
        return noteList;
    }

    public void deleteNote(NoteModel  noteModel)
    {
        new deleteAsyncTask(noteDatabase).execute(noteModel);
    }


    private class deleteAsyncTask extends AsyncTask<NoteModel,Void,Void> {
        private NoteDatabase noteDb;

        public deleteAsyncTask(NoteDatabase noteDb) {

            this.noteDb = noteDb;
        }



        @Override
        protected Void doInBackground(NoteModel... noteModels) {

            noteDatabase.noteItemAndNoteModelDao().deleteNote(noteModels[0]);
            return null;
        }
    }
}
