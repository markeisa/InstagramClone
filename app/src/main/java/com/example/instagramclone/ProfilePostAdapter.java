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

import com.bumptech.glide.Glide;
import com..instagramclone.model.Post;

import java.io.Serializable;
import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class ProfilePostAdapter extends RecyclerView.Adapter<ProfilePostAdapter.ViewHolder> {

    List<Post> posts;
    Context context;


    //pass posts array to constructor
    public ProfilePostAdapter(List<Post> posts) {
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View postView = inflater.inflate(R.layout.item_profile_post, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(postView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        //get data according to position
        Post post = posts.get(i);

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
        public ImageView ivImage;

        public ViewHolder(View view) {
            super(view);

//            tvRelativeTimestamp = (TextView) view.findViewById(R.id.tvRelativeTimestamp);
            ivImage = (ImageView) view.findViewById(R.id.ivProfilePost);
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

