package com.digikeep.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

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
