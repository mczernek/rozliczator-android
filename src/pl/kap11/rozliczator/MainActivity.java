package pl.kap11.rozliczator;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import pl.kap11.rozliczator.animation.ExpandableOnClickView;

public class MainActivity extends Activity implements ExpandableOnClickView.VisibilityListener{

    View bottomButton;
    View bottomFragment;
    View mainFragmentContainer;
    Fragment mainFragment;
    View mainFragmentOverlay;

    ExpandableOnClickView expandableView;

    @Override
	public void onCreate(final Bundle savedState){
		super.onCreate(savedState);
		setContentView(R.layout.activity_main);

		FragmentManager manager = getFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.main_fragment);

        bottomButton = findViewById(R.id.bottom_buttons);
        mainFragmentContainer = findViewById(R.id.main_fragment_container);
        bottomFragment = findViewById(R.id.bottom_fragment);
        mainFragmentOverlay = findViewById(R.id.man_fragment_overlay);

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
        mainFragmentContainer.setClickable(false);
    }

    @Override
    public void onCollapseStarted() {
        mainFragmentOverlay.setVisibility(View.VISIBLE);
    }

    @Override
    public void onViewCollapsed() {
        mainFragmentOverlay.setVisibility(View.GONE);
        mainFragmentContainer.setClickable(true);
    }

    @Override
    public void onViewExpanded() {
        mainFragmentOverlay.setVisibility(View.VISIBLE);
    }

    @Override
    public void onViewSizeUpdated(float fraction) {
        float maxAlpha = 0.75f;
        Log.d("FLOAT", "Overlay alpha " + fraction);
        mainFragmentOverlay.setAlpha(fraction * maxAlpha / 100);
    }
}
