package com.notetaking.database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
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
