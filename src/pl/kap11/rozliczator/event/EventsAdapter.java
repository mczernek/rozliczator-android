package pl.kap11.rozliczator.event;

import java.util.List;

import pl.kap11.rozliczator.R;
import pl.kap11.rozliczator.event.storage.EventStorage;
import pl.kap11.rozliczator.event.storage.EventStorage.ContentListener;
import android.app.Activity;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

public class EventsAdapter implements ListAdapter, ContentListener{

	private List<Event> eventsList;
	private Activity activity;

	public EventsAdapter(Activity activity, EventStorage storage){
		this.eventsList = storage.getEvents();
		storage.registerContentListener(this);
		this.activity = activity;
	}
	
	@Override
	public void registerDataSetObserver(DataSetObserver observer) {
	}

	@Override
	public void unregisterDataSetObserver(DataSetObserver observer) {
	}

	@Override
	public int getCount() {
		return eventsList.size();
	}

	@Override
	public Object getItem(int position) {
		return eventsList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null){
			convertView = activity.getLayoutInflater().inflate(R.layout.event_row, null);
		}
		TextView title = (TextView)convertView.findViewById(R.id.event_title);
		title.setText(eventsList.get(position).getName());
		return convertView;
	}

	@Override
	public int getItemViewType(int position) {
		return 1;
	}

	@Override
	public int getViewTypeCount() {
		return 1;
	}

	@Override
	public boolean isEmpty() {
		return eventsList.isEmpty();
	}

	@Override
	public boolean areAllItemsEnabled() {
		return true;
	}

	@Override
	public boolean isEnabled(int position) {
		return true;
	}

	@Override
	public void onItemAdded(Event item, int position) {
	}

	@Override
	public void onItemRemoved(Event item, int position) {
	}

	@Override
	public void onStorageCleared() {
	}
	
	

}
