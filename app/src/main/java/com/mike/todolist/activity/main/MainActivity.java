package com.mike.todolist.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mike.todolist.App;
import com.mike.todolist.R;
import com.mike.todolist.context.ApplicationContext;
import com.mike.todolist.context.DefaultContext;
import com.mike.todolist.context.DefaultSecurityContext;
import com.mike.todolist.context.SecurityContext;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        final Adapter adapter = new Adapter();
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> NoteDetailsActivity.start(MainActivity.this, null));

        Button logoutButton = findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            MainActivity.this.startActivity(intent);
        });

        MainViewModel mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        ApplicationContext context = App.getInstance().getLocalApplicationContext();
        String username = "";

        if (context instanceof DefaultContext) {
            DefaultContext defaultContext = (DefaultContext) context;
            SecurityContext securityContext = defaultContext.getSecurityContext();

            if (securityContext instanceof DefaultSecurityContext) {
                DefaultSecurityContext defaultSecurityContext = (DefaultSecurityContext) securityContext;
                username = defaultSecurityContext.getUserRepresentation().getUsername();
            }
        }

        mainViewModel.getByUsername(username).observe(this, adapter::setItems);
    }
}
