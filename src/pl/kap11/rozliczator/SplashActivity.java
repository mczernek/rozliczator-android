package pl.kap11.rozliczator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


public class SplashActivity extends Activity {

	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        
        try{
            Thread.sleep(500);
        }catch(InterruptedException ex){}
        finally{
        	Intent intent = new Intent(this, MainActivity.class);
        	startActivity(intent);
        	overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        	finish();
        }
    }

}
