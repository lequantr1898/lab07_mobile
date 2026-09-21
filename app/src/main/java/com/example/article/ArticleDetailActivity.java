package com.example.article;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

// Activity hien thi chi tiet 1 bai viet.
// Nhan nguyen object Article qua Intent bang Serializable, giong cach da duyet o lab truoc.
public class ArticleDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);

        Button buttonBack = findViewById(R.id.button_back);
        ImageView imageCover = findViewById(R.id.detail_image_cover);
        TextView textTitle = findViewById(R.id.detail_text_title);
        TextView textContent = findViewById(R.id.detail_text_content);

        Article article = (Article) getIntent().getSerializableExtra(MainActivity.EXTRA_ARTICLE);

        imageCover.setImageResource(article.getImgCover());
        textTitle.setText(article.getTitle());
        textContent.setText(article.getContent());

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}