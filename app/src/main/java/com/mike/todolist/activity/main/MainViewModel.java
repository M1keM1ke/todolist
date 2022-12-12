package com.mike.todolist.activity.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.mike.todolist.App;
import com.mike.todolist.model.Note;

import java.util.List;

public class MainViewModel extends ViewModel {

    public LiveData<List<Note>> getByUsername(String username) {
        return App.getInstance().getNoteDao().findByUsername(username);
    }
}
