package pl.kap11.rozliczator;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.View;

import pl.kap11.rozliczator.event.EventsFragment;

public class MainActivity extends Activity {

    View bottomButton;
    View bottomFragment;
    View mainFragmentContainer;
    Fragment mainFragment;

	@Override
	public void onCreate(final Bundle savedState){
		super.onCreate(savedState);
		setContentView(R.layout.activity_main);

		FragmentManager manager = getFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.main_fragment);

        bottomButton = findViewById(R.id.bottom_buttons);
        mainFragmentContainer = findViewById(R.id.main_fragment_container);
        bottomFragment = findViewById(R.id.bottom_fragment);

        bottomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomFragment.setVisibility(View.VISIBLE);
                mainFragmentContainer.setVisibility(View.GONE);
            }
        });


	}
	
	
}
