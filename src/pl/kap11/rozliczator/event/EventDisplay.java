package pl.kap11.rozliczator.event;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.View;

import pl.kap11.rozliczator.R;

/**
 * Created by mczernek on 12.01.15.
 */
public class EventDisplay implements EventsDisplayer {

    public interface EventsDisplayStateCallbacks {
        public void onEventDetailsDisplayed(Event event);
        public void onEventsListDisplayed();
    }

    private boolean detailsDisplayed = false;

    private EventsFragment mainFragment;
    private Activity activity;
    private EventsDisplayStateCallbacks callbacks;

    public EventDisplay(Activity activity, int rootViewId, EventsDisplayStateCallbacks callbacks) {
        this.activity = activity;
        this.callbacks = callbacks;

        FragmentManager manager = activity.getFragmentManager();
        mainFragment = new EventsFragment();
        mainFragment.setEventsDisplayer(this);

        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(rootViewId, mainFragment);
        transaction.commit();
    }

    @Override
    public void displayEvent(Event event) {
        EventDetailsFragment eventFragment = new EventDetailsFragment(event);
        FragmentManager manager = activity.getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
        transaction.replace(R.id.main_fragment_container, eventFragment);
        detailsDisplayed = true;
        transaction.commit();
        callbacks.onEventDetailsDisplayed(event);
    }

    @Override
    public void displayEventsList(){
        FragmentManager manager = activity.getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if(detailsDisplayed){
            transaction.setCustomAnimations(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
        }
        transaction.replace(R.id.main_fragment_container, mainFragment);
        detailsDisplayed = false;
        transaction.commit();
        callbacks.onEventsListDisplayed();
    }

    public boolean backPressed(){
        if(detailsDisplayed){
            displayEventsList();
            return true;
        }else{
            return false;
        }
    }

}
