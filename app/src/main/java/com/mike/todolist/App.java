package com.mike.todolist;

import android.app.Application;

import androidx.room.Room;

import com.mike.todolist.context.ApplicationContext;
import com.mike.todolist.context.DefaultContext;
import com.mike.todolist.context.DefaultSecurityContext;
import com.mike.todolist.data.AppDatabase;
import com.mike.todolist.data.NoteDao;

public class App extends Application {

    private AppDatabase database;
    private NoteDao noteDao;

    private static App instance;

    private ApplicationContext context;

    public App() {
        context = new DefaultContext(new DefaultSecurityContext(null));
    }

    public static App getInstance() {
        return instance;
    }

    public ApplicationContext getLocalApplicationContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        database = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, getString(R.string.database_name))
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        noteDao = database.noteDao();
    }

    public AppDatabase getDatabase() {
        return database;
    }

    public void setDatabase(AppDatabase database) {
        this.database = database;
    }

    public NoteDao getNoteDao() {
        return noteDao;
    }

    public void setNoteDao(NoteDao noteDao) {
        this.noteDao = noteDao;
    }
}
