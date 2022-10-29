package com.example.fifa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.fifa.adapter.FixtureAdapter;
import com.example.fifa.databinding.ActivityMainBinding;
import com.example.fifa.manager.RequestManager;
import com.example.fifa.models.FixtureResponse;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ActivityMainBinding binding;
    //private ProgressBar dialog; ??
    private ProgressDialog dialog;
    private RequestManager manager;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading");

        manager = new RequestManager(this);
        manager.getFixtures(listener, 1331);
        dialog.show();

    }

    private final ResponseListener listener = new ResponseListener() {
        @Override
        public void didFetch(FixtureResponse response, String message) {
            dialog.dismiss();
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
            FixtureAdapter adapter = new FixtureAdapter(MainActivity.this, response.data);
            recyclerView.setAdapter(adapter);

        }

        @Override
        public void didError(String message) {
            dialog.dismiss();
            Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();

        }
    };
}