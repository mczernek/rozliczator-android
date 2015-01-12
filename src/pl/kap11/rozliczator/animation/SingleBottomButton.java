package pl.kap11.rozliczator.animation;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

import pl.kap11.rozliczator.R;
import pl.kap11.rozliczator.event.editor.EventEditorActivity;

/**
 * Created by mczernek on 09.01.15.
 */
public class SingleBottomButton implements BottomActionButtons {

    private static final int COLLAPSE_ANIMATION_DURATION = 500;
    private static final int EXPAND_ANIMATION_DURATION = 500;

    private State currentState = State.COLLAPSED;
    private View clickView;
    private View expandableView;
    private TextView actionText;
    private List<VisibilityListener> listeners = new LinkedList<VisibilityListener>();

    private int expandedHeightParam = ActionBar.LayoutParams.WRAP_CONTENT;
    private int expandedWidthParam = ActionBar.LayoutParams.MATCH_PARENT;

    public SingleBottomButton(View onClickView, View expandableView, State initialState, TextView textView) {
        setOnClickView(onClickView);
        this.expandableView = expandableView;
        this.currentState = initialState;
        this.actionText = textView;
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
        setExpandAction(onClick);
    }

    private void setExpandAction(View onClick) {
        if (onClick != null) {
            this.clickView = onClick;
            onClick.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    changeState();
                }
            });
        }
    }

    @Override
    public void setExpandableView(View expandable) {
        this.expandableView = expandable;
    }

    @Override
    public void addSizeListener(VisibilityListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeSizeListener(VisibilityListener listener) {
        listeners.remove(listener);
    }

    @Override
    public void setExpandedParams(int widthParam, int heightParam) {
        expandedWidthParam = widthParam;
        expandedHeightParam = heightParam;
    }


    @Override
    public void collapse() {
        setState(State.COLLAPSED);
    }

    @Override
    public void expand() {
        setExpandedState();
    }

    @Override
    public boolean isExpanded() {
        return currentState == State.EXPANDED;
    }

    @Override
    public boolean isCollapsed() {
        return currentState == State.COLLAPSED;
    }

    private void changeState() {
        if (currentState == State.COLLAPSED) {
            setState(State.EXPANDED);
        } else {
            setState(State.COLLAPSED);
        }
    }

    private void setState(State state) {
        if (currentState != state) {
            this.currentState = state;
            if (currentState == State.COLLAPSED) {
                setCollapsedState();
            } else {
                setExpandedState();
            }
        }
    }

    private void setCollapsedState() {
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

    private void setExpandedState() {
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

    @Override
    public void setEventListsAction(Context context) {
        actionText.setText(context.getResources().getText(R.string.new_event));
        collapse();
        setExpandAction(clickView);
    }

    @Override
    public void setEventDetailsAction(Context context) {
        actionText.setText(context.getResources().getText(R.string.edit_event));
        collapse();
        setEventEditAction(context);
    }

    private void setEventEditAction(final Context context) {
        clickView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EventEditorActivity.class);
                context.startActivity(intent);
            }
        });

    }

}