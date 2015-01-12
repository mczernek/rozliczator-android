package pl.kap11.rozliczator.animation;

import android.view.View;

/**
 * Created by mczernek on 13.01.15.
 */
public interface ExpandableOnClickView {
    void setExpandableView(View expandable);

    void addSizeListener(SingleBottomButton.VisibilityListener listener);

    void removeSizeListener(SingleBottomButton.VisibilityListener listener);

    void setExpandedParams(int widthParam, int heightParam);

    void collapse();

    void expand();

    boolean isExpanded();

    boolean isCollapsed();
}
