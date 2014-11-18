package pl.kap11.rozliczator.event.storage;

import pl.kap11.rozliczator.event.Event;
import android.widget.ListView;

public interface EventStorage {

	public void saveEvent(Event event);
	
	public void setEventDisplay(ListView list);
	
	public void refresh();
	
}
