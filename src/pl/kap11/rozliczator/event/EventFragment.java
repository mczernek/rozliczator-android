package pl.kap11.rozliczator.event;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pl.kap11.rozliczator.R;

/**
 * Created by mczernek on 10.01.15.
 */
public class EventFragment extends Fragment {

    private Event event;

    public EventFragment(){}

    public EventFragment(Event event){
        this.event = event;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedStateInstane){
        View mainView = inflater.inflate(R.layout.event_layout, container, false);
        TextView titleText = (TextView)mainView.findViewById(R.id.titleText);
        titleText.setText(event.getName());
        return mainView;
    }
}