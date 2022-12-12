package com.mike.todolist.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.mike.todolist.model.Note;

import java.util.List;

@Dao
public interface NoteDao {

    @Query("SELECT * FROM Note WHERE username = :username")
    LiveData<List<Note>> findByUsername(String username);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

}
