package pl.kap11.rozliczator;

import pl.kap11.rozliczator.event.EventsFragment;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

public class MainActivity extends Activity {

	@Override
	public void onCreate(final Bundle savedState){
		super.onCreate(savedState);
		setContentView(R.layout.activity_main);
		
		Fragment fragment = new EventsFragment();
		
//		FragmentManager manager = getFragmentManager();
//		FragmentTransaction transaction = manager.beginTransaction();
//		transaction.add(R.id.main_fragment, fragment);
//		transaction.commit();
	}
	
	
}
