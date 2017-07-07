package com.codepath.apps.twitter;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.codepath.apps.twitter.fragments.ProgressListener;
import com.codepath.apps.twitter.fragments.TweetsListFragment;
import com.codepath.apps.twitter.fragments.TweetsPagerAdapter;
import com.codepath.apps.twitter.models.Tweet;

import org.parceler.Parcels;

public class TimelineActivity extends AppCompatActivity implements ProgressListener, TweetsListFragment.TweetSelectedListener {


    //MentionsTimeLineFragment fragmentMentionsTimeline;
    MenuItem miActionProgressItem;
    SwipeRefreshLayout swipeContainer;


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // Store instance of the menu item containing progress
        miActionProgressItem = menu.findItem(R.id.miActionProgress);
        // Extract the action-view from the menu item
        ProgressBar v =  (ProgressBar) MenuItemCompat.getActionView(miActionProgressItem);
        // Return to finish
        showProgressBar();
        //fragmentMentionsTimeline.populateTimeline();
        hideProgressBar();
        return super.onPrepareOptionsMenu(menu);


    }

    @Override
    public void showProgressBar() {
        // Show progress item
        miActionProgressItem.setVisible(true);
    }

    @Override
    public void hideProgressBar() {
        // Hide progress item
        miActionProgressItem.setVisible(false);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        ViewPager vpPager = (ViewPager) findViewById(R.id.viewpager);
        vpPager.setAdapter(new TweetsPagerAdapter(getSupportFragmentManager(), this));
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(vpPager);


        //fragmentHomeTimeline = (HomeTimelineFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_timeline);
        //fragmentMentionsTimeline = (MentionsTimeLineFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_timeline);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.timeline_menu, menu);
        return true;
    }

    // TODO: This is the code for the compose activity menu
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle presses on the action bar items
//        switch (item.getItemId()) {
//            case R.id.miCompose:
//                composeMessage();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }


    public void startCompose(View view) {
        Intent i = new Intent(TimelineActivity.this, ComposeActivity.class);
        startActivityForResult(i,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // REQUEST_CODE is defined above
        if (resultCode == RESULT_OK && requestCode == 1) {
            Tweet tweet = Parcels.unwrap(data.getParcelableExtra(Tweet.class.getName()));
            //fragmentMentionsTimeline.tweets.add(0, tweet);
            //fragmentMentionsTimeline.tweetAdapter.notifyItemInserted(0);
            //fragmentMentionsTimeline.rvTweets.scrollToPosition(0);

        }
    }

    public void onProfileView(MenuItem item) {
        Intent i = new Intent(TimelineActivity.this, ProfileActivity.class);
        startActivity(i);
    }

    @Override
    public void onTweetSelected(Tweet tweet) {
        Toast.makeText(this, tweet.body, Toast.LENGTH_SHORT).show();

    }


}
