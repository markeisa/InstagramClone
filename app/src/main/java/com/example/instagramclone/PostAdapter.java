package com.example.instagramclone;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.instagram.model.Post;
import com.parse.ParseUser;

import java.io.Serializable;
import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    List<Post> posts;
    Context context;


    //pass posts array to constructor
    public PostAdapter(List<Post> posts) {
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View postView = inflater.inflate(R.layout.item_post, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(postView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        //get data according to position
        final Post post = posts.get(i);
        //populate views according to data
        viewHolder.tvUsername.setText(post.getUser().getUsername());
        viewHolder.tvUser.setText(post.getUser().getUsername());
        viewHolder.tvDescription.setText(post.getDescription());
        viewHolder.tvNumLikes.setText(Integer.toString(post.getNumLikes()));
        viewHolder.tvRelativeTimestamp.setText(post.getRelativeTimeAgo(post.getCreatedAt().toString()));

        if(post.isLiked()) {
            viewHolder.ivHeart.setImageResource(R.drawable.ufi_heart_active);
        }

        viewHolder.ivHeart.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!post.isLiked()) {
                    post.likePost(ParseUser.getCurrentUser());
                    viewHolder.ivHeart.setImageResource(R.drawable.ufi_heart_active);
                    viewHolder.tvNumLikes.setText(Integer.toString(post.getNumLikes()));

                    post.saveInBackground();

                } else {
                    post.unlikePost(ParseUser.getCurrentUser());
                    viewHolder.ivHeart.setImageResource(R.drawable.ufi_heart);
                    viewHolder.ivHeart.setColorFilter(R.color.black);
                    viewHolder.tvNumLikes.setText(Integer.toString(post.getNumLikes()));

                    post.saveInBackground();
                }
            }
        });


//        Glide.with(context)
//                .load(post.getUser()
//                .bitmapTransform(new RoundedCornersTransformation(context, 100, 0))
//                .into(viewHolder.ivProfileImage);

        Glide.with(context)
                .load(post.getImage().getUrl())
                .bitmapTransform(new RoundedCornersTransformation(context, 10, 0))
                .into(viewHolder.ivImage);

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }



    //for each row pass ViewHolder class

    //bind values based on position of element

    //create ViewHolder class
    public class ViewHolder extends RecyclerView.ViewHolder implements OnClickListener {
        public ImageView ivProfileImage;
        public TextView tvUsername;
        public TextView tvUser;
        public TextView tvDescription;
        public TextView tvRelativeTimestamp;
        public ImageView ivImage;
        public ImageView ivHeart;
        public ImageView ivComment;
        public TextView tvNumLikes;

        public ViewHolder(View view) {
            super(view);

            //perform findViewById lookups
            ivProfileImage = (ImageView) view.findViewById(R.id.ivUserProfile);
            tvUsername = (TextView) view.findViewById(R.id.tvUser);
            tvUser = (TextView) view.findViewById(R.id.tvPostUser);
            tvDescription = (TextView) view.findViewById(R.id.tvPostDescription);
            tvRelativeTimestamp = (TextView) view.findViewById(R.id.tvDate);
            ivImage = (ImageView) view.findViewById(R.id.ivImage);
            ivHeart = (ImageView) view.findViewById(R.id.ivHeart);
            ivComment = (ImageView) view.findViewById(R.id.ivComment);
            tvNumLikes = (TextView) view.findViewById(R.id.tvNumLikes);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //get position of movie clicked
            int position = getAdapterPosition();
            //get movie at the position
            Post post = posts.get(position);
            //create new intent
            Intent intent = new Intent(context, PostDetailActivity.class);
            //pass movie
            intent.putExtra(Post.class.getSimpleName(), (Serializable) post);
            //show the activity
            context.startActivity(intent);
        }

    }

    // Clean all elements of the recycler
    public void clear() {
        posts.clear();
        notifyDataSetChanged();
    }

}

