package com.bala.newsreadercleanarchitecture;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.bala.newsreadercleanarchitecture.domain.model.Article;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.lang.reflect.Type;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class NewsRepositoryTest {


    @Test
    public void testLoadNewsData() {

        // Mock JSON response
        JSONObject jsonObject = mock(JSONObject.class);
        when(jsonObject.optString("articles")).thenReturn("[{\"publishedAt\":\"2024-01-10T22:41:25Z\",\"title\":\"Title 1\",\"url\":\"url1\",\"urlToImage\":\"image1\"},{\"publishedAt\":\"2024-01-11T18:18:13Z\",\"title\":\"Title 2\",\"url\":\"url2\",\"urlToImage\":\"image2\"}]");

        Type listType = new TypeToken<List<Article>>() {
        }.getType();
        List<Article> articles = new Gson().fromJson(jsonObject.optString("articles"), listType);

        // Verify that the list is not empty
        assertNotNull(articles);

        // Verify that the list contains expected number of articles
        assertEquals(2, articles.size());

    }

}
