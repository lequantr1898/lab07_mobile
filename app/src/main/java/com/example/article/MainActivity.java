package com.example.article;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ArticleAdapter.OnArticleClickListener {

    public static final String EXTRA_ARTICLE = "com.example.article.extra.ARTICLE";

    private RecyclerView recyclerArticles;
    private ArticleAdapter adapter;

    // Danh sach bai viet, Activity nay tu quan ly
    private ArrayList<Article> articleList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerArticles = findViewById(R.id.recycler_articles);

        createSampleArticles();

        // Khoi tao RecyclerView voi LinearLayoutManager (danh sach doc)
        adapter = new ArticleAdapter(articleList, this);
        recyclerArticles.setLayoutManager(new LinearLayoutManager(this));
        recyclerArticles.setAdapter(adapter);
    }

    // Tao san 3 bai viet mau, du lieu chi de demo
    private void createSampleArticles() {
        articleList.add(new Article(
                "Gioi thieu Android Studio",
                "Android Studio la moi truong phat trien chinh thuc cho ung dung Android, " +
                        "ho tro viet code Java/Kotlin, thiet ke giao dien va chay thu tren may ao.",
                R.drawable.article_cover_1
        ));

        articleList.add(new Article(
                "Vong doi cua Activity",
                "Moi Activity trong Android deu trai qua cac trang thai onCreate, onStart, " +
                        "onResume, onPause, onStop va onDestroy trong suot vong doi cua no.",
                R.drawable.article_cover_2
        ));

        articleList.add(new Article(
                "Cach dung Intent de chuyen man hinh",
                "Intent la thanh phan dung de chuyen tu Activity nay sang Activity khac, " +
                        "co the mang theo du lieu di kem bang cach dung extras hoac Serializable.",
                R.drawable.article_cover_3
        ));
    }

    // Callback tu ArticleAdapter khi nguoi dung bam vao 1 bai viet
    @Override
    public void onArticleClick(int position) {
        Article article = articleList.get(position);

        // Tang luot xem len 1
        article.increaseView();

        // Cap nhat lai dung item do tren RecyclerView
        adapter.notifyViewCountChanged(position);

        // Mo man hinh chi tiet bai viet qua Intent (Serializable)
        Intent intent = new Intent(MainActivity.this, ArticleDetailActivity.class);
        intent.putExtra(EXTRA_ARTICLE, article);
        startActivity(intent);
    }
}