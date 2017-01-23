package com.joaquinalan.contacts;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mNextButton;
    private EditText mNameEditText;
    private EditText mBirthdayEditText;
    private EditText mTelephoneEditText;
    private EditText mEmailEditText;
    private EditText mContactDescriptionEditText;
    private DatePickerDialog mDateEditTextDialog;
    private SimpleDateFormat mDateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        findViewsById();
        setDateTimeField();
        mNextButton.setOnClickListener(this);
    }

    private void findViewsById() {
        mNextButton = (Button) findViewById(R.id.nextButton);
        mNameEditText = (EditText) findViewById(R.id.nameEditText);
        mBirthdayEditText = (EditText) findViewById(R.id.birthdayEditText);
        mTelephoneEditText = (EditText) findViewById(R.id.telephoneEditText);
        mEmailEditText = (EditText) findViewById(R.id.emailEditText);
        mContactDescriptionEditText = (EditText) findViewById(R.id.contactDescriptionEditText);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mNameEditText.setText(extras.getString(getResources().getString(R.string.intent_name)));
            mBirthdayEditText.setText(extras.getString(getResources().getString(R.string.intent_birthday)));
            mTelephoneEditText.setText(extras.getString(getResources().getString(R.string.intent_telephone)));
            mEmailEditText.setText(extras.getString(getResources().getString(R.string.intent_email)));
            mContactDescriptionEditText.setText(extras.getString(getResources().getString(R.string.intent_contact_description)));
        }

        mBirthdayEditText.setInputType(InputType.TYPE_NULL);
    }

    private void setDateTimeField() {
        mBirthdayEditText.setOnClickListener(this);
        Calendar newCalendar = Calendar.getInstance();
        mDateEditTextDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                mBirthdayEditText.setText(mDateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.birthdayEditText:
                mDateEditTextDialog.show();
                break;
            case R.id.nextButton:
                Intent intent = new Intent(MainActivity.this, DataConfirmationActivity.class);
                intent.putExtra(getResources().getString(R.string.intent_name), mNameEditText.getText().toString());
                intent.putExtra(getResources().getString(R.string.intent_birthday), mBirthdayEditText.getText().toString());
                intent.putExtra(getResources().getString(R.string.intent_telephone), mTelephoneEditText.getText().toString());
                intent.putExtra(getResources().getString(R.string.intent_email), mEmailEditText.getText().toString());
                intent.putExtra(getResources().getString(R.string.intent_contact_description), mContactDescriptionEditText.getText().toString());
                startActivity(intent);
                break;
        }
    }
}
