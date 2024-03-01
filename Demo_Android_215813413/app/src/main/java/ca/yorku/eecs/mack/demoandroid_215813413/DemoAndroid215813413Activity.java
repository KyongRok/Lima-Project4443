package ca.yorku.eecs.mack.demoandroid_215813413;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import java.util.Locale;

/**
 * Demo_Android - with modifications by KyongRok Kim
 * Provides clicker that will increment/decrement the counter when pressed
 * resets the counter when "Reset" button is pressed
 * addition of new button in XML code
 * Login ID - <kyongrok>
 * Student ID - <215813413>
 * Last Name - <Kim>
 * First Name - <KyongRok>
 */
public class DemoAndroid215813413Activity extends Activity implements OnClickListener {
    private final static String MYDEBUG = "MYDEBUG"; // for Log.i messages

    private Button incrementButton, decrementButton, exitButton,resetButton;
    private TextView textview;
    private int clickCount;

    // called when the activity is first created
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initialize();
        Log.i(MYDEBUG, "Initialization done. Application running.");
    }

    private void initialize() {
        // get references to buttons and text view from the layout manager (rather than instantiate them)
        incrementButton = (Button) findViewById(R.id.incbutton);
        decrementButton = (Button) findViewById(R.id.decbutton);
        exitButton = (Button) findViewById(R.id.exitbutton);
        textview = (TextView) findViewById(R.id.textview);
        resetButton = (Button) findViewById(R.id.resetbutton);
        // some code is missing here
       incrementButton.setOnClickListener(this);//initialize button for increment
       decrementButton.setOnClickListener(this); //initialize button for decrement
       exitButton.setOnClickListener(this); //initialize button for exit
       resetButton.setOnClickListener(this); //initialize button for rest
        // initialize the click count
        clickCount = 0;

        // initialize the text field with the click count
        textview.setText(String.format(Locale.CANADA, "Count: %d", clickCount));
    }

    // this code executes when a button is clicked (i.e., tapped with user's finger)
    @Override
    public void onClick(View v) {
        if (v == incrementButton) {
            Log.i(MYDEBUG, "Increment button clicked!");
            ++clickCount;
            //if button is increment, increase the click count

        } else if (v == decrementButton) {
            Log.i(MYDEBUG, "Decrement button clicked!");
            --clickCount;
            //if button is decrement, decrease the click count
        } else if (v == exitButton) {
            Log.i(MYDEBUG, "Good bye!");
            this.finish();
            //end the app if button is exit button
        }else if(v == resetButton){
                Log.i(MYDEBUG,"Reset Button clicked!");
                clickCount = 0;
                //set the click count to 0 if reset button is pressed
            }
         else{
            Log.i(MYDEBUG, "Oops: Invalid Click Event!");
        }

        // update click count
        textview.setText(String.format(Locale.CANADA, "Count: %d", clickCount));
    }
}