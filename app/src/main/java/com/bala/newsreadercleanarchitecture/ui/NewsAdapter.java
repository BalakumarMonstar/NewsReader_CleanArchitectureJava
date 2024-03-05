package com.bala.newsreadercleanarchitecture.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bala.newsreadercleanarchitecture.R;
import com.bala.newsreadercleanarchitecture.domain.model.Article;
import com.bala.newsreadercleanarchitecture.utils.DateTimeUtil;
import com.bumptech.glide.Glide;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private final List<Article> articles;
    private final OnItemClickListener listener;

    public NewsAdapter(List<Article> articles, OnItemClickListener listener) {
        this.articles = articles;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Article article = articles.get(position);
        holder.bind(article, listener);
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;
        private final TextView titleTextView;
        private final TextView publishedAtTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            titleTextView = itemView.findViewById(R.id.title);
            publishedAtTextView = itemView.findViewById(R.id.publishedAt);
        }

        public void bind(final Article article, final OnItemClickListener listener) {
            titleTextView.setText(article.getTitle());
            publishedAtTextView.setText(DateTimeUtil.parseEventDate(article.getPublishedAt()));

            // Load image using Glide library
            Glide.with(itemView.getContext())
                    .load(article.getUrlToImage())
                    .placeholder(R.drawable.article)
                    .into(imageView);

            itemView.setOnClickListener(v -> listener.onItemClick(article));
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Article article);
    }
}

