package pl.kap11.rozliczator;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import pl.kap11.rozliczator.animation.ExpandableOnClickView;
import pl.kap11.rozliczator.event.Event;
import pl.kap11.rozliczator.event.EventFragment;
import pl.kap11.rozliczator.event.EventsFragment;

import static pl.kap11.rozliczator.event.EventsFragment.*;

public class MainActivity extends Activity implements ExpandableOnClickView.VisibilityListener, EventsDisplayer{

    View bottomButton;
    View bottomFragment;
    View mainFragmentContainer;
    EventsFragment mainFragment;
    View mainFragmentOverlay;

    ExpandableOnClickView expandableView;

    @Override
	public void onCreate(final Bundle savedState){
		super.onCreate(savedState);
		setContentView(R.layout.activity_main);

		FragmentManager manager = getFragmentManager();
        mainFragment = new EventsFragment();
        mainFragment.setEventsDisplayer(this);
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.main_fragment_container, mainFragment);
        transaction.commit();

        bottomButton = findViewById(R.id.bottom_buttons);
        mainFragmentContainer = findViewById(R.id.main_fragment_container);
        mainFragmentContainer.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                expandableView.collapse();
                return false;
            }
        });
        bottomFragment = findViewById(R.id.bottom_fragment);
        mainFragmentOverlay = findViewById(R.id.man_fragment_overlay);
        mainFragmentOverlay.setOnTouchListener( new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                expandableView.collapse();
                return true;
            }
        });

        expandableView = new ExpandableOnClickView(bottomButton, bottomFragment, ExpandableOnClickView.State.COLLAPSED);
        expandableView.addSizeListener(this);

	}

    @Override
    public void onDestroy(){
        super.onDestroy();
        expandableView.removeSizeListener(this);
    }


    @Override
    public void onExpansionStarted() {
        mainFragmentOverlay.setVisibility(View.VISIBLE);
    }

    @Override
    public void onCollapseStarted() {
        mainFragmentOverlay.setVisibility(View.VISIBLE);
    }

    @Override
    public void onViewCollapsed() {
        mainFragmentOverlay.setVisibility(View.GONE);
    }

    @Override
    public void onViewExpanded() {
        mainFragmentOverlay.setVisibility(View.VISIBLE);
    }

    @Override
    public void onViewSizeUpdated(float fraction) {
        float maxAlpha = 0.75f;
        mainFragmentOverlay.setAlpha(fraction * maxAlpha / 100);
    }

    @Override
    public void displayEvent(Event event) {
        EventFragment eventFragment = new EventFragment(event);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in_from_right , R.anim.slide_out_to_left, R.anim.slide_in_from_left, R.anim.slide_out_to_right); //TODO: Create own animators for each slide in/out and set custom animations, and pop custom animations
        transaction.replace(R.id.main_fragment_container, eventFragment);
        transaction.addToBackStack("displayEvent");
        transaction.commit();
        expandableView.collapse();
    }

    @Override
    public void onBackPressed(){
        if(expandableView.isExpanded()){
            expandableView.collapse();
        }else{
            super.onBackPressed();
        }
    }

}
