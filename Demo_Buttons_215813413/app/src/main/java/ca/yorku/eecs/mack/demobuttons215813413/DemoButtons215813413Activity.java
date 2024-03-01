package ca.yorku.eecs.mack.demobuttons215813413;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.ToggleButton;

/**
 *
 * Demo_Android- with modifications by
 * Login ID - <kyongrok>
 * Student ID - <215813413>
 * Last Name - <Kim>
 * Frist name - <KyongRok>
 *
 * Edited main.xml in table layout to android:layout_width="wrap_content"
 * so that it will cover all the screen
 * Removed Exit button
 * when moving into landscape mode from portrait or visa versa, it saves all the attributes
 * and options that we have done, example, simple button's text view on off toggle button
 * radio buttons and it's text view and color
 * modified the landscape UI to make it look easier use.
 * The text color of button click status depending on the radio button pressed
 */
@SuppressWarnings("unused")
public class DemoButtons215813413Activity extends Activity {
    private final static String MYDEBUG = "MYDEBUG"; // for Log.i messages

    Button b;
    CheckBox cb;
    RadioButton rb1, rb2, rb3;
    ToggleButton tb;
    ImageButton backspaceButton;
    TextView buttonClickStatus, checkBoxClickStatus, radioButtonClickStatus, toggleButtonClickStatus,
            backspaceButtonClickStatus;
    Button exitButton;

    String buttonClickString, backspaceString;
    boolean checkStatus;
    static final String STATE_checkStatus="checkStatus";
    static final String STATE_Back="backStatus";
    static final String STATE_button="ButtonStatus";
    static final String STATE_OnOff = "OnOffStatus";
    static final String STATE_ColorText = "ColorText";
    static final String STATE_Color = "Color";
    //initialize 6 variables that needs to be saved into default setting
    //these are the keys for our saved values, if null, it will move on to init and
    //display will show the default values that are going to be set in init()

    boolean rb1Status, rb2Status, rb3Status;
    boolean tbStatus;

    // called when the activity is first created
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        init();
    }

    private void init() {
        b = (Button) findViewById(R.id.button);
        cb = (CheckBox) findViewById(R.id.checkbox);
        rb1 = (RadioButton) findViewById(R.id.radiobutton1);
        rb2 = (RadioButton) findViewById(R.id.radiobutton2);
        rb3 = (RadioButton) findViewById(R.id.radiobutton3);
        rb1.toggle();
        tb = (ToggleButton) findViewById(R.id.togglebutton);
        backspaceButton = (ImageButton) findViewById(R.id.backspacebutton);


        buttonClickStatus = (TextView) findViewById(R.id.buttonclickstatus);
        checkBoxClickStatus = (TextView) findViewById(R.id.checkboxclickstatus);
        radioButtonClickStatus = (TextView) findViewById(R.id.radiobuttonclickstatus);
        toggleButtonClickStatus = (TextView) findViewById(R.id.togglebuttonclickstatus);
        backspaceButtonClickStatus = (TextView) findViewById(R.id.backspacebuttonclickstatus);

        buttonClickString = "";
        backspaceString = "";

        buttonClickStatus.setText(buttonClickString);
        checkBoxClickStatus.setText(R.string.unchecked);
        radioButtonClickStatus.setText(R.string.red);
        radioButtonClickStatus.setTextColor(Color.RED);
        toggleButtonClickStatus.setText(R.string.off);
        buttonClickStatus.setTextColor(Color.RED);
        checkBoxClickStatus.setTextColor(Color.RED);
        toggleButtonClickStatus.setTextColor(Color.RED);
        backspaceButtonClickStatus.setTextColor(Color.RED);
    //set the color of the other texts the same color as the radio button's color

    }

    // handle button clicks
    public void buttonClick(View v) {
        // plain button
        if (v == b) {
            buttonClickString += ".";
            buttonClickStatus.setText(buttonClickString);
        }

        // checkbox
        else if (v == cb) {
            if (cb.isChecked()) {
                cb.setChecked(true);
                checkBoxClickStatus.setText(R.string.checked);
            } else {
                cb.setChecked(false);
                checkBoxClickStatus.setText(R.string.unchecked);
            }
        }

        // radio button #1 (RED)
        else if (v == rb1) {
            rb1.setChecked(true);
            radioButtonClickStatus.setText(R.string.red);
            radioButtonClickStatus.setTextColor(Color.RED);
            buttonClickStatus.setTextColor(Color.RED);
            checkBoxClickStatus.setTextColor(Color.RED);
            toggleButtonClickStatus.setTextColor(Color.RED);
            backspaceButtonClickStatus.setTextColor(Color.RED);
        }

        // radio button #2 (GREEN)
        else if (v == rb2) {
            rb2.setChecked(true);
            radioButtonClickStatus.setText(R.string.green);
            radioButtonClickStatus.setTextColor(Color.GREEN);
            buttonClickStatus.setTextColor(Color.GREEN);
            checkBoxClickStatus.setTextColor(Color.GREEN);
            toggleButtonClickStatus.setTextColor(Color.GREEN);
            backspaceButtonClickStatus.setTextColor(Color.GREEN);
        }

        // radio button #3 (BLUE)
        else if (v == rb3) {
            rb3.setChecked(true);
            radioButtonClickStatus.setText(R.string.blue);
            radioButtonClickStatus.setTextColor(Color.BLUE);
            buttonClickStatus.setTextColor(Color.BLUE);
            checkBoxClickStatus.setTextColor(Color.BLUE);
            toggleButtonClickStatus.setTextColor(Color.BLUE);
            backspaceButtonClickStatus.setTextColor(Color.BLUE);
        }

        // toggle button
        else if (v == tb) {
            tb.setActivated(tb.isChecked());
            if (tb.isChecked())
                toggleButtonClickStatus.setText(R.string.on);
            else
                toggleButtonClickStatus.setText(R.string.off);
        }

        // backspace button
        else if (v == backspaceButton) {
            backspaceString += "BK ";
            backspaceButtonClickStatus.setText(backspaceString);
        }

    }


    @Override
    //Override onSaveInstanceState inorder to get it back when activity is destroied
    public void onSaveInstanceState(Bundle savedInstanceState){
//      //getCurrentTextColor() returns integer value of the color, hence we convert to string
        savedInstanceState.putString(STATE_Color, String.valueOf((radioButtonClickStatus.getCurrentTextColor())));
        savedInstanceState.putString(STATE_ColorText,radioButtonClickStatus.getText().toString());
        savedInstanceState.putString(STATE_checkStatus,checkBoxClickStatus.getText().toString());
        savedInstanceState.putString(STATE_OnOff,toggleButtonClickStatus.getText().toString());
        savedInstanceState.putString(STATE_Back,backspaceButtonClickStatus.getText().toString());
        savedInstanceState.putString(STATE_button,buttonClickStatus.getText().toString());
        //get the values of textview and convert to string as it is in TextVeiw type
        //get values of the color(int) of the text and convert to string

        //always call this superclass inorder to save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    //we do not have to worry if savedInstanceState is null or not because it does it by it self
    public void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        //since STATE_Color is in Stringm, we have to convert into integer to set the color of the text
        radioButtonClickStatus.setTextColor(Integer.parseInt(savedInstanceState.getString(STATE_Color)));
        radioButtonClickStatus.setText(savedInstanceState.getString(STATE_ColorText));
        checkBoxClickStatus.setText(savedInstanceState.getString(STATE_checkStatus));
        toggleButtonClickStatus.setText(savedInstanceState.getString(STATE_OnOff));
        backspaceButtonClickStatus.setText(savedInstanceState.getString(STATE_Back));
        buttonClickStatus.setText(savedInstanceState.getString(STATE_button));
        //fetch all the string text views that were saved

        //for addtional feature, depending on which radio button pressed, changes all the
        //results of the button pressed.
        //save the current state button and state_back in the buttonclickString, bakcSpaceString so that
        //it can start adding to what it has left off.
        buttonClickString = savedInstanceState.getString(STATE_button);
        backspaceString = savedInstanceState.getString(STATE_Back);
        buttonClickStatus.setTextColor(Integer.parseInt(savedInstanceState.getString(STATE_Color)));
        checkBoxClickStatus.setTextColor(Integer.parseInt(savedInstanceState.getString(STATE_Color)));
        toggleButtonClickStatus.setTextColor(Integer.parseInt(savedInstanceState.getString(STATE_Color)));
        backspaceButtonClickStatus.setTextColor(Integer.parseInt(savedInstanceState.getString(STATE_Color)));
    }
}
