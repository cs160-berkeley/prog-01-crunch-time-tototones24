package com.example.tototones24.crunchtime2;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    String[] workOuts;
    Spinner sp;
    public RadioButton minutes;
    public RadioButton reps;
    public TextView viewJumpingJacks;
    public TextView viewPushUps;
    public TextView viewJogging;
    public TextView viewSittUps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bn;
        bn = (Button)findViewById(R.id.button);
        Boolean jumpingJacks;
        final Boolean jogging;
        Boolean pushUps;
        Boolean sitUps;

        minutes = (RadioButton)findViewById(R.id.radioButton);
        reps = (RadioButton)findViewById(R.id.radioButton2);

        //these represent the actual boxes where the the numbers can be viewed.
        viewJumpingJacks = (TextView)findViewById(R.id.textView4);
        viewPushUps = (TextView)findViewById(R.id.textView);
        viewJogging = (TextView)findViewById(R.id.textView3);
        viewSittUps = (TextView)findViewById(R.id.textView2);


        sp = (Spinner) findViewById(R.id.sp);
        workOuts = getResources().getStringArray(R.array.workouts);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this ,android.R.layout.simple_spinner_item, workOuts);
        sp.setAdapter(adapter);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int index = parent.getSelectedItemPosition();
                //Toast.makeText(getBaseContext(), "You selected " + workOuts[index] , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


//        setUpMainButton();
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float pushUpRep = .286f;
                float sitUpRep = .5f;
                float jumpingJackMinute = 10f;
                float jogginMinute = 8.33f;

                //this will return the number that you inputted into the edit text
                EditText numberInput = (EditText)findViewById(R.id.editText);
                float number = Float.valueOf(numberInput.getText().toString());


//                Toast.makeText(getBaseContext(), "Time to convert", Toast.LENGTH_SHORT).show();
                if (!minutes.isChecked() && !reps.isChecked()) {
                    Toast.makeText(getBaseContext(), "Select minutes or reps", Toast.LENGTH_LONG).show();
                }

                //This is where the main logic will go when it comes to converting the calories
                if (sp.getSelectedItem().toString().equals("Pushups")) {
//                    Toast.makeText(getBaseContext(), "was pushups", Toast.LENGTH_SHORT).show();

                    //this is where I will check which option was pressed minutes or reps
                    if (minutes.isChecked()) {
                        Toast.makeText(getBaseContext(), "These are pushups, you have to choose reps then run again", Toast.LENGTH_LONG).show();
                    }
                    if (reps.isChecked()) {
                        float outPut = number * pushUpRep;
                        float roundOutput = Math.round(outPut);
                        viewPushUps.setText("" + roundOutput + " calories"); //t

                        //this will be the conversions for the other excerzises
                        float sitUpNum = outPut / sitUpRep;
                        float joggingNum = outPut / jogginMinute;
                        float jjackNum = outPut / jumpingJackMinute;

                        float roundSitUp = Math.round(sitUpNum);
                        float roundJogging = Math.round(joggingNum);
                        float roundJumping = Math.round(jjackNum);

                        viewJogging.setText("" + roundJogging + " minutes");
                        viewSittUps.setText("" + roundSitUp + " reps");
                        viewJumpingJacks.setText("" + roundJumping + " minutes");
                    }

                } else if (sp.getSelectedItem().toString().equals("Situps")){
                    if (minutes.isChecked()) {
                        Toast.makeText(getBaseContext(), "Situps are counted by reps, not minutes, change it.", Toast.LENGTH_LONG).show();
                    }
                    if (reps.isChecked()) {
                        float outPut = number * sitUpRep;
                        float roundOutput = Math.round(outPut);
                        viewSittUps.setText("" + roundOutput + " calories");

                        //this will be the conversions for the other excerzises
                        float jjackNum = outPut / jumpingJackMinute;
                        float joggingNum = outPut / jogginMinute;
                        float pushupNum = outPut / pushUpRep;

                        float roundPushUp = Math.round(pushupNum);
                        float roundJogging = Math.round(joggingNum);
                        float roundJumping = Math.round(jjackNum);

                        viewJogging.setText("" + roundJogging + " minutes");
                        viewPushUps.setText("" + roundPushUp + " reps");
                        viewJumpingJacks.setText("" + roundJumping + " minutes");

                    }
                } else if (sp.getSelectedItem().toString().equals("JumpingJacks")) {
//                    Toast.makeText(getBaseContext(), "was Jumping Jacks", Toast.LENGTH_LONG).show();

                    if (reps.isChecked()) {
                        Toast.makeText(getBaseContext(), "You cannot check minutes for jumping jacks, change it then run it", Toast.LENGTH_LONG).show();
                    }

                    if (minutes.isChecked()) {

                        float outPut = number * jumpingJackMinute;
                        float roundOutput = Math.round(outPut);
                        viewJumpingJacks.setText("" + roundOutput + " calories");

                        float joggingNum = outPut / jogginMinute;
                        float pushupNum = outPut / pushUpRep;
                        float sitUpNum = outPut / sitUpRep;

                        float roundPushUp = Math.round(pushupNum);
                        float roundJogging = Math.round(joggingNum);
                        float roundSitUp = Math.round(sitUpNum);


                        viewJogging.setText("" + roundJogging + " minutes");
                        viewPushUps.setText("" + roundPushUp + " reps");
                        viewSittUps.setText("" + roundSitUp + " reps");
                    }

                } else {
                    if (reps.isChecked()) {
                        Toast.makeText(getBaseContext(), "you cannot check reps for jogging, change it then run it again", Toast.LENGTH_LONG).show();
                    }
                    if (minutes.isChecked()) {
                        float outPut = number * jogginMinute;
                        float roundOutput = Math.round(outPut);
                        viewJogging.setText("" + roundOutput + " calories");

                        float jjackNum = outPut / jumpingJackMinute;
                        float pushupNum = outPut / pushUpRep;
                        float sitUpNum = outPut / sitUpRep;


                        float roundPushUp = Math.round(pushupNum);
                        float roundJumping = Math.round(jjackNum);
                        float roundSitUp = Math.round(sitUpNum);

                        viewJumpingJacks.setText("" + roundJumping + " minutes");
                        viewPushUps.setText("" + roundPushUp + " reps");
                        viewSittUps.setText("" + roundSitUp + " reps");
                    }
                }
            }
        });
    }
}
