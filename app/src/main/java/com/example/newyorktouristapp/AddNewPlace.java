package com.example.newyorktouristapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class AddNewPlace extends AppCompatActivity implements TextWatcher, AdapterView.OnItemSelectedListener {

    String placeType, title, description, number, address, url;
    Spinner spinner;
    EditText editTextTitle, editTextDescription, editTextNumber, editTextAddress, editTextURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_place);

        setReferences();
        setListeners();

        ArrayAdapter<CharSequence> adapter;
        adapter = ArrayAdapter.createFromResource(this,  R.array.place_type, R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void setListeners() {
        spinner.setOnItemSelectedListener(this);
        editTextTitle.addTextChangedListener(this);
        editTextDescription.addTextChangedListener(this);
        editTextNumber.addTextChangedListener(this);
        editTextAddress.addTextChangedListener(this);
        editTextURL.addTextChangedListener(this);
    }

    private void setReferences() {
        spinner = findViewById(R.id.spinner2);
        editTextTitle = findViewById(R.id.editTextTitle);
        editTextDescription = findViewById(R.id.editTextDescription);
        editTextNumber = findViewById(R.id.editTextNumber);
        editTextAddress = findViewById(R.id.editTextPostalAddress);
        editTextURL = findViewById(R.id.editTextWebsite);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        placeType = adapterView.getItemAtPosition(position).toString().toLowerCase();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}