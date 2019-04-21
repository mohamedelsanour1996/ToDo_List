package com.example.todo_list;

import android.app.Application;
import android.arch.lifecycle.LiveData;



import java.util.List;


public class NoteRepository {

    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;

    public NoteRepository(Application application) {
        NoteDatabase database = NoteDatabase.getInstance(application);
        noteDao = database.noteDao();
        allNotes = noteDao.getAllNotes();
    }

    public void insert(final Note note) {
       AppExecutors.getInstance().diskIO().execute(new Runnable() {
           @Override
           public void run() {
               noteDao.insert(note);
           }
       });
    }

    public void update(final Note note) {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                noteDao.update(note);
            }
        });
    }

    public void delete(final Note note) {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                noteDao.delete(note);
            }
        });
    }

    public void deleteAllNotes() {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                noteDao.deleteAllNotes();
            }
        });
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }


}