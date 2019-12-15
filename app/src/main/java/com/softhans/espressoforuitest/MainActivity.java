package com.softhans.espressoforuitest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.LogPrinter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**To use Espresso UI test to test your apps:
 * Your project usually be default, has the library for espresso ui test in your build.gradle.(Check it out)
 * for androidx add:
 * -->androidTestImplementation 'androidx.test:rules:1.1.1'
 * -->androidTestImplementation 'androidx.test:runner:1.1.1'
 * Android Studio also adds testInstrumentationRunner statement to the end of the defaultConfig section of a new project:
 *These are the basic steps:
 * -->Match a View: Find a View.
 * -->Perform an action: Perform a click or other action that triggers an event with the View.
 * -->Assert and verify the result: Check the state of the View to see if it reflects the expected state or behavior defined by the assertion.
 *
 * The following shows how all three expressions work together:
 *
 * Use a ViewMatcher to find a View: onView(withId(R.id.my_view))
 * Use a ViewAction to perform an action: .perform(click())
 * Use a ViewAssertion to check if the result of the action matches an assertion: .check(matches(isDisplayed()))
 * Example:
 *
 * onView(withId(R.id.my_view))
 *         .perform(click())
 *         .check(matches(isDisplayed()));
 *
 * Rename the class from ExampleInstrumentedTest to ActivityInputOutputTest
 *
 */


public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    TextView mTextView, texviewR;
    EditText mEditText;
    Button mButton;
    public static final String EXTRA_MESSAGE ="ccom.softhans.espressoforuitest.extra.MESSAGE";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditText =findViewById(R.id.edit_txt);
        mButton =findViewById(R.id.button1);
        mTextView =findViewById(R.id.messagetv);
        texviewR =findViewById(R.id.text_message);



        Log.d(LOG_TAG, "-------");
        Log.d(LOG_TAG, "onCreate");

        Intent intent = getIntent();
        String message = intent.getStringExtra(SecondActivity.EXTRA_REPLY);
        texviewR.setText(message);


        if(texviewR.getText() != "")
        {
            mButton.setText(R.string.reply_message);
            mTextView.setVisibility(View.VISIBLE);
        }

// Restore the saved state.
        // See onSaveInstanceState() for what gets saved.
        if (savedInstanceState != null) {
            boolean isVisible =
                    savedInstanceState.getBoolean("reply_visible");
            // Show both the header and the message views. If isVisible is
            // false or missing from the bundle, use the default layout.
            if (isVisible) {
                texviewR.setVisibility(View.VISIBLE);
                mTextView.setText(savedInstanceState
                        .getString("reply_text"));
                mTextView.setVisibility(View.VISIBLE);
            }
        }




    }




    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "onPause");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.d(LOG_TAG, "onResume");
    }

    public void launchSecondActivity(View view) {

        Log.d (LOG_TAG, "Button clicked");

        Intent intent = new Intent(this, SecondActivity.class);
        String message = mEditText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);

    }

    //TODO see if you can adapt a screen to have both activities see each their chats like messaging apps.
}

