package com.bala.newsreadercleanarchitecture;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bala.newsreadercleanarchitecture.data.repository.NewsRepositoryImpl;
import com.bala.newsreadercleanarchitecture.domain.model.Article;
import com.bala.newsreadercleanarchitecture.domain.repository.NewsRepository;
import com.bala.newsreadercleanarchitecture.ui.NewsAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NewsAdapter.OnItemClickListener {

    private NewsAdapter adapter;
    private List<Article> articles;
    private NewsRepository newsRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        articles = new ArrayList<>();
        adapter = new NewsAdapter(articles, this);
        recyclerView.setAdapter(adapter);

        newsRepository = new NewsRepositoryImpl(this);

        loadNewsData(); // Load news data from repository

    }

    private void loadNewsData() {
        articles.addAll(newsRepository.getNews());
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onItemClick(Article article) {

    }
}

