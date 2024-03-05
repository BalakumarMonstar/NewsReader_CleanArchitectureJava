package com.bala.newsreadercleanarchitecture.domain.repository;

import com.bala.newsreadercleanarchitecture.domain.model.Article;

import java.util.List;

public interface NewsRepository {
    List<Article> getNews();
}
