package pl.kap11.rozliczator.event;

import pl.kap11.rozliczator.R;
import pl.kap11.rozliczator.event.storage.EventStorage;
import pl.kap11.rozliczator.event.storage.TemporaryEventStorage;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

public class EventsFragment extends Fragment {
	
	
	public EventsFragment(){
	}
	
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		EventStorage storage = new TemporaryEventStorage();
		ListAdapter adapter = new EventsAdapter(getActivity(), storage);
		((ListView)getView().findViewById(R.id.events_list)).setAdapter(adapter);
	}
	
	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View rootView = inflater.inflate(R.layout.events_list, container, false);
		return rootView;
	}

}
