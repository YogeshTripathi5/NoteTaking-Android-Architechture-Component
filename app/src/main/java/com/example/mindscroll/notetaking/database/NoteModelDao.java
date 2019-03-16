package com.example.mindscroll.notetaking.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface NoteModelDao {
    @Query("SELECT * FROM NoteModel")
    LiveData<List<NoteModel>> getAllNotes();

    @Query("SELECT * FROM NoteModel WHERE id = :id")
    NoteModel getNotesById(String  id);

    @Insert(onConflict = REPLACE)
    void insertNote(NoteModel noteModel);

    @Delete
    void deleteNote(NoteModel noteModel);

}
