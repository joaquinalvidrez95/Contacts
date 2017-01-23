package com.joaquinalan.contacts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DataConfirmationActivity extends AppCompatActivity implements View.OnClickListener {
    TextView mNameTextView;
    TextView mBirthdayTextView;
    TextView mTelephoneTextView;
    TextView mEmailTextView;
    TextView mContactDescriptionTextView;
    Button mEditDataButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_confirmation);

        mNameTextView = (TextView) findViewById(R.id.nameTextView);
        mBirthdayTextView = (TextView) findViewById(R.id.birthdayTextView);
        mTelephoneTextView = (TextView) findViewById(R.id.telephoneTextView);
        mEmailTextView = (TextView) findViewById(R.id.emailTextView);
        mContactDescriptionTextView = (TextView) findViewById(R.id.contactDescriptionTextView);
        mEditDataButton = (Button) findViewById(R.id.editDataButton);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mNameTextView.setText(extras.getString(getResources().getString(R.string.intent_name)));
            mBirthdayTextView.setText(extras.getString(getResources().getString(R.string.intent_birthday)));
            mTelephoneTextView.setText(extras.getString(getResources().getString(R.string.intent_telephone)));
            mEmailTextView.setText(extras.getString(getResources().getString(R.string.intent_email)));
            mContactDescriptionTextView.setText(extras.getString(getResources().getString(R.string.intent_contact_description)));
        }
        mEditDataButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.editDataButton){
            Intent intent = new Intent(DataConfirmationActivity.this, MainActivity.class);
            intent.putExtra(getResources().getString(R.string.intent_name), mNameTextView.getText().toString());
            intent.putExtra(getResources().getString(R.string.intent_birthday), mBirthdayTextView.getText().toString());
            intent.putExtra(getResources().getString(R.string.intent_telephone), mTelephoneTextView.getText().toString());
            intent.putExtra(getResources().getString(R.string.intent_email), mEmailTextView.getText().toString());
            intent.putExtra(getResources().getString(R.string.intent_contact_description), mContactDescriptionTextView.getText().toString());
            startActivity(intent);
        }
    }
}
