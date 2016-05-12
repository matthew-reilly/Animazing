package com.weddingwire.animazing;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.ChangeBounds;
import android.transition.ChangeImageTransform;
import android.transition.ChangeTransform;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private BlankFragment mContentFragment;

    private ImageView mImageView;
    private ContentFragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    setFragment();
                }

            });
        }


        getSupportFragmentManager().beginTransaction().add(R.id.content, ContentFragment.newInstance("asdf", "asdf"), "TransitionTestFragment1").commit();

        mFragment = ContentFragment.newInstance("test", "test");

        mImageView = (ImageView) findViewById(R.id.profileImage);
    }

    private void setFragment() {
        mContentFragment = BlankFragment.newInstance("test", "test");

        final TransitionSet transitionSet = new TransitionSet();
        transitionSet.addTransition(new ChangeImageTransform());
        transitionSet.addTransition(new ChangeBounds());
        transitionSet.addTransition(new ChangeTransform());
        transitionSet.setDuration(300);


//        Transition changeTransform = TransitionInflater.from(this).
//                inflateTransition(R.transition.change_image_transform);
//        Transition explodeTransform = TransitionInflater.from(this).
//                inflateTransition(android.R.transition.explode);
//
//        mContentFragment.setSharedElementEnterTransition(changeTransform);
//        mContentFragment.setEnterTransition(explodeTransform);
//
//        mFragment.setSharedElementReturnTransition(changeTransform);
//        mFragment.setExitTransition(explodeTransform);

        mFragment.setSharedElementEnterTransition(transitionSet);
        mFragment.setSharedElementReturnTransition(transitionSet);
        mContentFragment.setSharedElementEnterTransition(transitionSet);

//        getSupportFragmentManager().beginTransaction()
//                .setCustomAnimations(R.anim.slide_up, R.anim.fade_out, R.anim.slide_up, R.anim.fade_out)
//                .replace(R.id.content, mContentFragment)
//                .addSharedElement(mImageView, getString(R.string.fragment_image_trans))
//                .addToBackStack(null)
//                .commit();
//
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.content, mContentFragment)
//                .addSharedElement(mImageView, getString(R.string.fragment_image_trans))
//                .addToBackStack(null)
//                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
