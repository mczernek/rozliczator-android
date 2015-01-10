package pl.kap11.rozliczator.animation;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.ActionBar;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by mczernek on 09.01.15.
 */
public class ExpandableOnClickView {

    private static final int COLLAPSE_ANIMATION_DURATION = 500;
    private static final int EXPAND_ANIMATION_DURATION = 500;

    private State currentState = State.COLLAPSED;
    private View clickView;
    private View expandableView;
    private List<VisibilityListener> listeners = new LinkedList<VisibilityListener>();

    private int expandedHeightParam = ActionBar.LayoutParams.WRAP_CONTENT;
    private int expandedWidthParam = ActionBar.LayoutParams.MATCH_PARENT;

    public ExpandableOnClickView(View onClickView, View expandableView, State initialState) {
        setOnClickView(onClickView);
        this.expandableView = expandableView;
        this.currentState = initialState;
    }


    public enum State {
        EXPANDED, COLLAPSED;
    }

    public interface VisibilityListener {
        public void onExpansionStarted();

        public void onCollapseStarted();

        public void onViewCollapsed();

        public void onViewExpanded();

        /**
         * @param fraction part of view visible expressed in percentage
         */
        public void onViewSizeUpdated(float fraction);

    }


    private void setOnClickView(View onClick) {
        this.clickView = onClick;
        onClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeState();
            }
        });
    }

    public void setExpandableView(View expandable) {
        this.expandableView = expandable;
    }

    public void addSizeListener(VisibilityListener listener) {
        listeners.add(listener);
    }

    public void removeSizeListener(VisibilityListener listener) {
        listeners.remove(listener);
    }

    public void setExpandedParams(int widthParam, int heightParam) {
        expandedWidthParam = widthParam;
        expandedHeightParam = heightParam;
    }

    private void changeState() {
        if (currentState == State.COLLAPSED) {
            setState(State.EXPANDED);
        } else {
            setState(State.COLLAPSED);
        }
    }

    private void setState(State state) {
        this.currentState = state;
        if (currentState == State.COLLAPSED) {
            collapse();
        } else {
            expand();
        }
    }

    private void collapse() {
        ValueAnimator collapse = new ValueAnimator();
        collapse.setInterpolator(new AccelerateDecelerateInterpolator());
        collapse.setDuration(COLLAPSE_ANIMATION_DURATION);
        final int viewHeight = expandableView.getMeasuredHeight();
        collapse.setIntValues(viewHeight, 0);
        collapse.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer value = (Integer) animation.getAnimatedValue();
                expandableView.getLayoutParams().height = value.intValue();
                expandableView.requestLayout();
                notifyOnViewSizeUpdated((((float) value.intValue()) / viewHeight) * 100);
            }
        });
        collapse.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                notifyOnViewCollapseStarted();
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                expandableView.setVisibility(View.GONE);
                notifyOnViewCollapsed();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                expandableView.setVisibility(View.GONE);
                notifyOnViewCollapsed();
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        collapse.start();

    }

    private void expand() {
        ValueAnimator expand = new ValueAnimator();
        expandableView.measure(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
        final int viewHeight = expandableView.getMeasuredHeight();
        expand.setInterpolator(new AccelerateDecelerateInterpolator());
        expand.setDuration(EXPAND_ANIMATION_DURATION);
        expand.setIntValues(viewHeight, 0);
        expand.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer value = (Integer) animation.getAnimatedValue();
                expandableView.getLayoutParams().height = viewHeight - value.intValue();
                expandableView.requestLayout();
                notifyOnViewSizeUpdated(100 - (((float) value.intValue()) / viewHeight) * 100);
            }
        });
        expand.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                expandableView.setVisibility(View.VISIBLE);
                notifyOnViewExpansionStarted();
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                expandableView.setVisibility(View.VISIBLE);
                notifyOnViewExpanded();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                expandableView.setVisibility(View.VISIBLE);
                notifyOnViewExpanded();
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        expand.start();
    }

    private void notifyOnViewSizeUpdated(float percentage) {
        for (VisibilityListener lis : listeners) {
            lis.onViewSizeUpdated(percentage);
        }
    }

    private void notifyOnViewCollapsed() {
        for (VisibilityListener lis : listeners) {
            lis.onViewCollapsed();
        }
    }

    private void notifyOnViewExpanded() {
        for (VisibilityListener lis : listeners) {
            lis.onViewExpanded();
        }
    }

    private void notifyOnViewCollapseStarted() {
        for (VisibilityListener lis : listeners) {
            lis.onCollapseStarted();
        }
    }

    private void notifyOnViewExpansionStarted() {
        for (VisibilityListener lis : listeners) {
            lis.onExpansionStarted();
        }
    }

}
