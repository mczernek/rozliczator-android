package pl.kap11.rozliczator.event.storage;

import java.util.List;

import pl.kap11.rozliczator.event.Event;

public interface EventStorage {
	
	public void saveEvent(Event event);
	
	public void refresh();
	
	public List<Event> getEvents();
	
	public void registerContentListener(ContentListener listener);
	
	public void unregisterContentListener(ContentListener listener);
	
	public static interface ContentListener{
		
		public void onItemAdded(Event item, int position);
		
		public void onItemRemoved(Event item, int position);
		
		public void onStorageCleared();
		
	}
	
}
