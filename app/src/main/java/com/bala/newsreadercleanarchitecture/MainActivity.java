package com.bala.newsreadercleanarchitecture;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bala.newsreadercleanarchitecture.data.repository.NewsRepositoryImpl;
import com.bala.newsreadercleanarchitecture.domain.model.Article;
import com.bala.newsreadercleanarchitecture.domain.repository.NewsRepository;
import com.bala.newsreadercleanarchitecture.ui.NewsAdapter;
import com.bala.newsreadercleanarchitecture.utils.DateTimeUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements NewsAdapter.OnItemClickListener {

    private NewsAdapter adapter;
    private List<Article> articles;
    private List<Article> filteredArticles;
    private NewsRepository newsRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        articles = new ArrayList<>();
        filteredArticles = new ArrayList<>();
        adapter = new NewsAdapter(filteredArticles, this);
        recyclerView.setAdapter(adapter);

        newsRepository = new NewsRepositoryImpl(this);

        loadNewsData(); // Load news data from repository

        SearchView searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchNews(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchNews(newText);
                return true;
            }
        });
    }

    private void loadNewsData() {
        articles = newsRepository.getNews();
        this.articles.sort((article1, article2) -> {
            SimpleDateFormat format = new SimpleDateFormat(DateTimeUtil.inputPattern, Locale.getDefault());
            try {
                Date date1 = format.parse(article1.getPublishedAt());
                Date date2 = format.parse(article2.getPublishedAt());
                assert date2 != null;
                return date2.compareTo(date1); // Descending order
            } catch (ParseException e) {
                e.printStackTrace();
                return 0;
            }
        });
        filteredArticles.addAll(articles);
        adapter.notifyDataSetChanged();
    }

    private void searchNews(String query) {
        filteredArticles.clear();
        if (TextUtils.isEmpty(query)) {
            filteredArticles.addAll(articles);
        } else {
            for (Article article : articles) {
                if (article.getTitle().toLowerCase().contains(query.toLowerCase())) {
                    filteredArticles.add(article);
                }
            }
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(Article article) {

    }
}

