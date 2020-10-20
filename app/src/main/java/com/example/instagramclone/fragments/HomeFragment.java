package com.example.instagramclone.fragments;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.instagramclone.PostAdapter;
import com.instagramclone.R;
import com.instagramclone.model.Post;
import com.parse.FindCallback;
import com.parse.ParseException;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    //swipe to refresh
    private SwipeRefreshLayout swipeRefreshLayout;
    //for intent when composing tweet
    private com.instagramclone.EndlessRecyclerViewScrollListener scrollListener;
    PostAdapter postAdapter;
    ArrayList<Post> posts;
    RecyclerView rvPosts;
    long maxId = 0;
    MenuItem miActionProgressItem;

    public void showProgressBar() {
        // Show progress item
        miActionProgressItem.setVisible(true);
    }

    public void hideProgressBar() {
        // Hide progress item
        miActionProgressItem.setVisible(false);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        // Store instance of the menu item containing progress
        miActionProgressItem = menu.findItem(R.id.miActionProgress);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.fragment_home, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        loadTopPosts();
        //find RecyclerView
        rvPosts = (RecyclerView) view.findViewById(R.id.rvPost);
        //init arraylist
        posts = new ArrayList<>();
        //construct adapter
        postAdapter = new PostAdapter(posts);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvPosts.setLayoutManager(linearLayoutManager);
        scrollListener = new com.instagramclone.EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list
                loadNextData(page);
            }
        };
        rvPosts.addOnScrollListener(scrollListener);
        //set the adapter
        rvPosts.setAdapter(postAdapter);
        //set swipe refresh layout



        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadTopPosts();
            }
        });

        // Configure the refreshing colors
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    public void loadNextData(int page) {
        loadTopPosts();
    }

    public void loadTopPosts() {

        final Post.Query postQuery = new Post.Query();
        postQuery.getTop().withUser();
        postQuery.addDescendingOrder(Post.KEY_DATE);

        postQuery.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> objects, ParseException e) {
                if(e == null) {
                    postAdapter.clear();
                    for(int i = 0; i < objects.size(); i++) {
                        posts.add(objects.get(i));
                        postAdapter.notifyItemInserted(posts.size() - 1);
//                        Log.i("HomeFragment", "Post " + i + " " + objects.get(i).getDescription());
                    }
                } else {
                    Toast.makeText(getContext(), "Failed to query posts", Toast.LENGTH_SHORT).show();
                }
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}
