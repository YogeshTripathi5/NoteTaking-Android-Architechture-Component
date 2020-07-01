package com.digikeep.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class NoteModel {
    @PrimaryKey(autoGenerate = true)
    public int id;
    private String noteTitle;
    private String noteDesc;

    public NoteModel()
    {

    }
    public NoteModel( String noteTitle, String noteDesc) {

        this.noteTitle = noteTitle;

        this.noteDesc = noteDesc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteDesc() {
        return noteDesc;
    }

    public void setNoteDesc(String noteDesc) {
        this.noteDesc = noteDesc;
    }


}
