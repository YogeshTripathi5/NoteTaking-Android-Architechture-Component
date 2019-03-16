package com.example.mindscroll.notetaking.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities =   NoteModel.class,version = 1)
public abstract class NoteDatabase extends RoomDatabase {
    private static  NoteDatabase noteDatabaseInstance;


    public static NoteDatabase getNoteDatabaseInstance(Context context)
    {
        if (noteDatabaseInstance==null){
            noteDatabaseInstance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class,"notes_db")
                    .build();
        }
        return noteDatabaseInstance;
    }

    public static void destroyInstance(){
        noteDatabaseInstance=null;
    }

    public abstract NoteModelDao  noteItemAndNoteModelDao();
}
