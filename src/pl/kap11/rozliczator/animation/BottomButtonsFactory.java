package pl.kap11.rozliczator.animation;

import android.view.View;
import android.widget.TextView;

public class BottomButtonsFactory {

    public static BottomActionButtons getBottomButtons(View onClickView, View expandableView, SingleBottomButton.State initialState, TextView textView){
        return new SingleBottomButton(onClickView, expandableView, initialState, textView);
    }

}
