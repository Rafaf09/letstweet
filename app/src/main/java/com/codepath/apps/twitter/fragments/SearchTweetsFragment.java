package com.codepath.apps.twitter.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.codepath.apps.twitter.TwitterApp;
import com.codepath.apps.twitter.TwitterClient;

/**
 * Created by rafelix on 7/7/17.
 */

public class SearchTweetsFragment extends TweetsListFragment {
    TwitterClient client;
    String recentQuery;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        client = TwitterApp.getRestClient();
        recentQuery = "";

        setHasOptionsMenu(true);

        
    }
}
