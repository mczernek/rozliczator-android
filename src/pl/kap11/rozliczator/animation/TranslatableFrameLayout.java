package pl.kap11.rozliczator.animation;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
/**
 * A simple {@link FrameLayout} subclass.
 */
public class TranslatableFrameLayout extends FrameLayout {

    public TranslatableFrameLayout(Context context) {
        super(context);
    }

    public TranslatableFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TranslatableFrameLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setXFraction(float xFraction){
        Log.d("FRACTION", "Fraction set to: " + xFraction);
        int width = getWidth();
        setX( width > 0 ? xFraction * width : Integer.MAX_VALUE);
    }

    public float gerXFraction(){
        return getX() / getWidth();
    }

}
