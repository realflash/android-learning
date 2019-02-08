package net.attono.countdown;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CountdownActivity extends Activity
{

    private static final int MILLIS_PER_SECOND = 1000;
    
	private TextView countdownDisplay;
	private CountDownTimer timer; 

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

		countdownDisplay = (TextView) findViewById(R.id.countdownDisplay);
		final EditText timeEntry = (EditText) findViewById(R.id.timeentrybox);
		Button startButton = (Button) findViewById(R.id.startbutton);

		startButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View view) {
				String timeStringSeconds = timeEntry.getText().toString();
				try {
					int countdownMillis = Integer.parseInt(timeStringSeconds.trim()) * MILLIS_PER_SECOND;
					showTimer(countdownMillis);
				} catch (NumberFormatException e) {
					//method ignores invalid input
				}
			}
		});
    }

	private void showTimer(int countdownMillis) {
		if(timer != null) {timer.cancel(); }
		timer = new CountDownTimer(countdownMillis, MILLIS_PER_SECOND) {
			
			@Override
			public void onTick(long millisUntilFinished) {
				countdownDisplay.setText("counting down: " + 
					millisUntilFinished / MILLIS_PER_SECOND);
			}

			@Override
			public void onFinish() {
				countdownDisplay.setText("KABOOM!");
			}
		}.start();
	}


}
