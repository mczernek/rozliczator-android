package pl.kap11.rozliczator;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pl.kap11.rozliczator.animation.BottomActionButtons;
import pl.kap11.rozliczator.animation.BottomButtonsFactory;
import pl.kap11.rozliczator.animation.BottomFragmentFactory;
import pl.kap11.rozliczator.animation.SingleBottomButton;
import pl.kap11.rozliczator.event.Event;
import pl.kap11.rozliczator.event.EventDisplay;

public class MainActivity extends Activity implements SingleBottomButton.VisibilityListener, EventDisplay.EventsDisplayStateCallbacks {

    View bottomButton;
    ViewGroup bottomFragment;
    View mainFragmentContainer;

    EventDisplay eventDisplay;

    View mainFragmentOverlay;

    BottomActionButtons expandableView;

    @Override
    public void onCreate(final Bundle savedState) {
        super.onCreate(savedState);
        setContentView(R.layout.activity_main);

        eventDisplay = new EventDisplay(this, R.id.main_fragment_container, this);

        bottomButton = findViewById(R.id.bottom_buttons);
        mainFragmentContainer = findViewById(R.id.main_fragment_container);
        mainFragmentContainer.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                expandableView.collapse();
                return false;
            }
        });
        bottomFragment = (ViewGroup)findViewById(R.id.hidden_fragment);

        BottomFragmentFactory.attachMainBottomFragmentToContainer(LayoutInflater.from(this), bottomFragment, eventDisplay);

        mainFragmentOverlay = findViewById(R.id.man_fragment_overlay);
        mainFragmentOverlay.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                expandableView.collapse();
                return true;
            }
        });

        TextView bottomText = (TextView)findViewById(R.id.bottom_text);
        expandableView = BottomButtonsFactory.getBottomButtons(bottomButton, bottomFragment, SingleBottomButton.State.COLLAPSED, bottomText);
        expandableView.addSizeListener(this);

    }

    @Override
    public void onDestroy() {
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
    public void onBackPressed() {
        if (expandableView.isExpanded()) {
            expandableView.collapse();
        } else {
            if (!eventDisplay.backPressed()) {
                super.onBackPressed();
            }
        }
    }

    @Override
    public void onEventDetailsDisplayed(Event event) {
        expandableView.setEventDetailsAction(this);
    }

    @Override
    public void onEventsListDisplayed() {
        expandableView.setEventListsAction(this);
    }

}
