package pl.kap11.rozliczator.event;

import pl.kap11.rozliczator.R;
import pl.kap11.rozliczator.event.storage.EventStorage;
import pl.kap11.rozliczator.event.storage.TemporaryEventStorage;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

public class EventsFragment extends Fragment {

    private EventsDisplayer displayer;

	public void setEventsDisplayer(EventsDisplayer displayer){
        this.displayer = displayer;
    }

	
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		EventStorage storage = new TemporaryEventStorage();
		final ListAdapter adapter = new EventsAdapter(getActivity(), storage);
        ListView list = ((ListView)getView().findViewById(R.id.events_list));
		list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object item = adapter.getItem(position);
                if(item instanceof Event){
                    displayEvent((Event)item);
                }
            }
        });

	}
	
	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View rootView = inflater.inflate(R.layout.events_list, container, false);
		return rootView;
	}

    public void displayEvent(Event event){
        if(displayer != null){
            displayer.displayEvent(event);
        }
    }

}
