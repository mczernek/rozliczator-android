package pl.kap11.rozliczator;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;


public class MainActivity extends Activity {

	private TextView mNewEventText;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mNewEventText = (TextView)findViewById(R.id.new_event_text);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Black.ttf");
        mNewEventText.setTypeface(font);
    }

}
