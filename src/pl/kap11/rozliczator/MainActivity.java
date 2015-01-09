package pl.kap11.rozliczator;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

import pl.kap11.rozliczator.event.EventsFragment;

public class MainActivity extends Activity {

    View bottomButton;
    View bottomFragment;
    View mainFragmentContainer;
    Fragment mainFragment;

	@Override
	public void onCreate(final Bundle savedState){
		super.onCreate(savedState);
		setContentView(R.layout.activity_main);

		FragmentManager manager = getFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.main_fragment);

        bottomButton = findViewById(R.id.bottom_buttons);
        mainFragmentContainer = findViewById(R.id.main_fragment_container);
        bottomFragment = findViewById(R.id.bottom_fragment);

        bottomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(bottomFragment.getVisibility() != View.VISIBLE) {
                    expandBottomFragment();
                }else{
                    collapseBottomFragment();
                }
            }
        });

	}

    private void collapseBottomFragment() {
        ValueAnimator collapse = new ValueAnimator();
        collapse.setInterpolator(new AccelerateDecelerateInterpolator());
        collapse.setDuration(200);
        int viewHeight = bottomFragment.getMeasuredHeight();
        collapse.setIntValues(viewHeight, 0);
        collapse.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer value = (Integer) animation.getAnimatedValue();
                bottomFragment.getLayoutParams().height = value.intValue();
                bottomFragment.requestLayout();
            }
        });
        collapse.addListener( new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {}

            @Override
            public void onAnimationEnd(Animator animation) {
                bottomFragment.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                bottomFragment.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animator animation) {}
        });
        collapse.start();
    }

    private void expandBottomFragment() {
        ScaleAnimation expand = new ScaleAnimation(1,1,0,1);
        expand.setDuration(200);
        bottomFragment.startAnimation(expand);
    }


}
