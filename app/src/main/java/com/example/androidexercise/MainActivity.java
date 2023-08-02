package com.example.androidexercise;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;

public class MainActivity extends AppCompatActivity {

    RadioButton rdbY,rdbN;
    TextView result,dobControl;
    EditText nameInput;
    EditText destiInput;
    EditText descripInput;
    static LocalDate dob;
    String str;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Get a reference from the toolbar
        Toolbar myToolBar = findViewById(R.id.toolbar);

// Set toolbar as actionbar for the activity
        setSupportActionBar(myToolBar);


        dobControl = findViewById(R.id.dob_control);

        dobControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });
        //Set value form radiobutton
        rdbY=findViewById(R.id.rdbYes);
        rdbN=findViewById(R.id.rdbNo);
        btn=findViewById(R.id.btn);
       // result=findViewById(R.id.textResult);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getInputs();
//                String selectedRequire="Selected require: ";
//                if (rdbY.isChecked()){
//                    selectedRequire+=" Yes";
//                }
//                if (rdbN.isChecked()){
//                    selectedRequire+="No";
//                }
//                //result.setText(selectedRequire);
            }
        });
    }

    public void onRadioButtonClicked(View view) {
         str= "";
        if (view.getId()== R.id.rdbYes){
            str="Yes";
        }
        if (view.getId()== R.id.rdbNo){
            str="No";
        }
        Toast.makeText(getApplicationContext(),str,Toast.LENGTH_SHORT).show();
    }
    public static class DatePickerFragment extends DialogFragment implements
            DatePickerDialog.OnDateSetListener {
        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState)
        {
            LocalDate d = LocalDate.now();
            int year = d.getYear();
            int month = d.getMonthValue();
            int day = d.getDayOfMonth();
            return new DatePickerDialog(getActivity(), this, year, --month, day);}
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day){
             dob = LocalDate.of(year, ++month, day);
            ((MainActivity)getActivity()).updateDOB(dob);
        }
    }
    public void updateDOB(LocalDate dob){
        TextView dobControl = findViewById(R.id.dob_control);
        dobControl.setText(dob.toString());
    }
    private  void getInputs(){
        nameInput=findViewById(R.id.nameInput);
        destiInput=findViewById(R.id.destination_input);
        descripInput=findViewById(R.id.description_input);
        String name=nameInput.getText().toString();
        String destination=destiInput.getText().toString();
        String description=descripInput.getText().toString();
        String datePicker= dob.toString();
        if (checkEmpty(name,destination,datePicker,str,description))
        {
        displayNextAlert(name,destination,datePicker,str,description);
        }
        else {
            checkEmptyAlert();
        }
    }

    private boolean checkEmpty(String name, String destination, String datePicker, String str, String description) {
        if (name.trim().isEmpty()||destination.trim().isEmpty()||datePicker.trim().isEmpty()||str.trim().isEmpty()||description.trim().isEmpty()){
           return false;
        }
        else return true;
    }

    private void displayNextAlert(String name, String destination, String datePicker, String str, String description) {
        new AlertDialog.Builder(this)
                .setTitle("Details Entered")
                .setMessage(
                          "Name: "+ name + "\n" +
                          "Destination: "+destination + "\n" +
                          "Date of the trip: "+ datePicker + "\n" +
                          "Risk Assessment: "+ str+"\n"+
                          "Description: "+description
                )
                .setNeutralButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .show();
    }
    private void checkEmptyAlert() {
        new AlertDialog.Builder(this)
                .setTitle("")
                .setMessage("You need to fill all required fields ")
                .setNeutralButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .show();
    }

}