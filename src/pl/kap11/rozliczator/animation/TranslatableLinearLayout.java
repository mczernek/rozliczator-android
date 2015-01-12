package pl.kap11.rozliczator.animation;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * A simple {@link android.app.Fragment} subclass.
 */
public class TranslatableLinearLayout extends LinearLayout {

    public TranslatableLinearLayout(Context context) {
        super(context);
        setVisibility(GONE);
    }

    public TranslatableLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setVisibility(GONE);
    }

    public TranslatableLinearLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setVisibility(GONE);
    }

    public void setXFraction(float xFraction) {
        setVisibility(VISIBLE);
        int width = getWidth();
        setX( width > 0 ? xFraction * width : Integer.MAX_VALUE);
    }

    public float gerXFraction() {
        return getX() / getWidth();
    }

}
