package com.example.article;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

// Adapter cho RecyclerView hien thi danh sach bai viet.
// Theo cach hoc: dat lop ViewHolder ben trong Adapter.
public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {

    // Interface de truyen su kien click len MainActivity
    public interface OnArticleClickListener {
        void onArticleClick(int position);
    }

    private ArrayList<Article> articleList;
    private OnArticleClickListener listener;

    public ArticleAdapter(ArrayList<Article> articleList, OnArticleClickListener listener) {
        this.articleList = articleList;
        this.listener = listener;
    }

    // ===== ViewHolder: nam trong Adapter, giu cac View cua 1 item =====
    public static class ArticleViewHolder extends RecyclerView.ViewHolder {

        TextView textTitle;
        TextView textContent;
        TextView textViews;
        ImageView imageCover;

        public ArticleViewHolder(View itemView) {
            super(itemView);
            textTitle   = itemView.findViewById(R.id.text_title);
            textContent = itemView.findViewById(R.id.text_content);
            textViews   = itemView.findViewById(R.id.text_views);
            imageCover  = itemView.findViewById(R.id.image_cover);
        }
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate layout item_article.xml cho moi dong trong danh sach
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_article, parent, false);
        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        Article article = articleList.get(position);

        holder.textTitle.setText(article.getTitle());
        holder.textContent.setText(article.getContent());
        holder.textViews.setText("Views: " + article.getView());
        holder.imageCover.setImageResource(article.getImgCover());

        // Khi nguoi dung bam vao item, thong bao len MainActivity xu ly
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getAdapterPosition();
                if (pos != RecyclerView.NO_ID && listener != null) {
                    listener.onArticleClick(pos);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    // Goi khi view count cua 1 bai viet thay doi, de cap nhat dung 1 item do thoi
    public void notifyViewCountChanged(int position) {
        notifyItemChanged(position);
    }
}
