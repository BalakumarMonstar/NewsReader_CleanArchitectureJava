package com.bala.newsreadercleanarchitecture.data.repository;

import com.bala.newsreadercleanarchitecture.domain.model.Article;
import com.bala.newsreadercleanarchitecture.domain.repository.NewsRepository;


import android.content.Context;
import android.content.res.AssetManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;

public class NewsRepositoryImpl implements NewsRepository {

    private final Context context;

    public NewsRepositoryImpl(Context context) {
        this.context = context;
    }

    @Override
    public List<Article> getNews() {
        List<Article> articles = null;
        try {
            String json = loadJSONFromAsset("news.json");
            JSONObject jsonObject = new JSONObject(json);
            Type listType = new TypeToken<List<Article>>() {}.getType();
            articles = new Gson().fromJson(jsonObject.optString("articles"), listType);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return articles;
    }

    private String loadJSONFromAsset(String filename) throws IOException {
        AssetManager assetManager = context.getAssets();
        InputStream inputStream = assetManager.open(filename);
        byte[] buffer = new byte[inputStream.available()];
        inputStream.read(buffer);
        inputStream.close();
        return new String(buffer, "UTF-8");
    }
}

