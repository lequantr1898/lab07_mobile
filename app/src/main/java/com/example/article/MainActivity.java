package com.example.article;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_ARTICLE = "com.example.article.extra.ARTICLE";

    private LinearLayout articleListContainer;

    // Danh sach bai viet, Activity nay tu quan ly
    private ArrayList<Article> articleList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        articleListContainer = findViewById(R.id.article_list_container);

        createSampleArticles();

        for (int i = 0; i < articleList.size(); i++) {
            addArticleCard(articleList.get(i));
        }
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

    // Tao 1 "card" cho 1 bai viet: anh cover + title + content + view, roi addView vao container
    private void addArticleCard(final Article article) {
        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setBackgroundResource(R.drawable.card_border);
        card.setPadding(16, 16, 16, 16);

        LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        cardParams.setMargins(0, 0, 0, 16);
        card.setLayoutParams(cardParams);

        ImageView imageCover = new ImageView(this);
        imageCover.setImageResource(article.getImgCover());
        imageCover.setScaleType(ImageView.ScaleType.CENTER_CROP);
        LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 400);
        imageParams.setMargins(0, 0, 0, 12);
        imageCover.setLayoutParams(imageParams);
        card.addView(imageCover);

        TextView textTitle = new TextView(this);
        textTitle.setText(article.getTitle());
        textTitle.setTextSize(20);
        textTitle.setTypeface(null, android.graphics.Typeface.BOLD);
        card.addView(textTitle);

        TextView textContent = new TextView(this);
        textContent.setText(article.getContent());
        textContent.setTextSize(15);
        textContent.setPadding(0, 8, 0, 8);
        card.addView(textContent);

        final TextView textView = new TextView(this);
        textView.setText("Views: " + article.getView());
        textView.setTextSize(13);
        textView.setTextColor(0xFF888888);
        card.addView(textView);

        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onArticleClicked(article, textView);
            }
        });

        articleListContainer.addView(card);
    }

    // Bam vao 1 bai viet: tang view, cap nhat lai text tren card, roi mo man hinh chi tiet
    private void onArticleClicked(Article article, TextView viewCountText) {
        article.increaseView();
        viewCountText.setText("Views: " + article.getView());

        Intent intent = new Intent(MainActivity.this, ArticleDetailActivity.class);
        intent.putExtra(EXTRA_ARTICLE, article);
        startActivity(intent);
    }
}