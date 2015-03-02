package pl.kap11.rozliczator.animation;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import pl.kap11.rozliczator.R;
import pl.kap11.rozliczator.event.EventAdderView;
import pl.kap11.rozliczator.event.EventsDisplayer;

public class BottomFragmentFactory {

    public static void attachMainBottomFragmentToContainer(LayoutInflater inflater, ViewGroup parent, EventsDisplayer displayer){
        new EventAdderView(inflater, parent, displayer);
    }

}
