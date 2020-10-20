package com.example.instagramclone;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.instagramclone.model.Post;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class PostDetailActivity extends AppCompatActivity {

    Post post;
    ImageView ivUserProfile;
    TextView tvUserName;
    TextView tvDescription;
    TextView tvDate;
    ImageView ivImage;
    TextView tvUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);

        ivUserProfile = (ImageView) findViewById(R.id.ivProfile);
        tvUserName = (TextView) findViewById(R.id.tvUsername);
        tvDescription = (TextView) findViewById(R.id.tvDescription);
        ivImage = (ImageView) findViewById(R.id.ivPostImage);
        tvDate = (TextView) findViewById(R.id.tvTimestamp);
        tvUser = (TextView) findViewById(R.id.tvPostUsername);

        post = (Post) getIntent().getParcelableExtra(Post.class.getSimpleName());

        tvUserName.setText(post.getUser().getUsername());
        tvUser.setText(post.getUser().getUsername());
        tvDescription.setText(post.getDescription());
        String date = post.getRelativeTimeAgo(post.getCreatedAt().toString());
        tvDate.setText(date);


//        Glide.with(this)
//                .load(post.getUser())
//                .bitmapTransform(new RoundedCornersTransformation(this, 100, 0))
//                .into(ivUserProfile);

        String mediaUrl = post.getImage().getUrl();
        Glide.with(this)
                .load(mediaUrl)
                .bitmapTransform(new RoundedCornersTransformation(this, 10, 0))
                .into(ivImage);
    }
}

