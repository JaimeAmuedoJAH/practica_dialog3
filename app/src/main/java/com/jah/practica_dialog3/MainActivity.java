package com.jah.practica_dialog3;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    Button btnName, btnSurnames, btnCourse, btnLanguages, btnOs;
    TextView lblName, lblSurnames, lblCourse, lblLanguages, lblOs;
    String[] arrLanguages, arrCourses;
    boolean[] checkedLanguages;
    int[] itemCourse;
    ArrayAdapter<CharSequence> adapter;
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        initComponents();

        btnName.setOnClickListener(view -> createDialogName());
        btnSurnames.setOnClickListener(view -> createDialogSurname());
        btnCourse.setOnClickListener(view -> createDialogCourse());
        btnOs.setOnClickListener(view -> createDialogOs());
        btnLanguages.setOnClickListener(view -> createDialogLanguages());

        if(savedInstanceState != null){
            lblName.setText(savedInstanceState.getString("name"));
            lblSurnames.setText(savedInstanceState.getString("surname"));
            lblCourse.setText(savedInstanceState.getString("course"));
            lblOs.setText(savedInstanceState.getString("os"));
            lblLanguages.setText(savedInstanceState.getString("languages"));
            arrLanguages = savedInstanceState.getStringArray("arrLanguages");
            arrCourses = savedInstanceState.getStringArray("arrCourse");
            checkedLanguages = savedInstanceState.getBooleanArray("checkedLanguages");
            checkedLanguages = savedInstanceState.getBooleanArray("checkedLanguages");
            itemCourse = savedInstanceState.getIntArray("itemCourse");
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("name", lblName.getText().toString());
        outState.putString("surname", lblSurnames.getText().toString());
        outState.putString("course", lblCourse.getText().toString());
        outState.putString("os", lblOs.getText().toString());
        outState.putString("languages", lblLanguages.getText().toString());
        outState.putStringArray("arrLanguages", arrLanguages);
        outState.putStringArray("arrCourse", arrCourses);
        outState.putBooleanArray("checkedLanguages", checkedLanguages);
        outState.putIntArray("itemCourse", itemCourse);
    }

    private void createDialogLanguages() {
        str = "";
        new AlertDialog.Builder(this)
                .setCancelable(false)
                .setIcon(R.drawable.ic_launcher_foreground)
                .setTitle(R.string.dialog_languages_title)
                .setMultiChoiceItems(arrLanguages, checkedLanguages, (dialogInterface, which, checked) -> checkedLanguages[which] = checked)
                .setNeutralButton(R.string.dialog_neutral, null)
                .setNegativeButton(R.string.dialog_negative, (dialogInterface, i) -> checkChecked())
                .setPositiveButton(R.string.dialog_positive, (dialogInterface, i) -> showSelectedItems())
                .create()
                .show();
    }

    private void showSelectedItems() {
        for(int ind = 0;ind < arrLanguages.length;ind++){
            if(checkedLanguages[ind]){
                str += arrLanguages[ind] + "\n";
            }
        }
        lblLanguages.setText(str);
    }

    private void checkChecked() {
        for(int ind = 0;ind < arrLanguages.length;ind++){
            if(lblLanguages.getText().toString().contains(arrLanguages[ind])){
                checkedLanguages[ind] = true;
            }else{
                checkedLanguages[ind] = false;
            }
        }
    }

    private void createDialogOs() {
        final View custom_layoutOs = getLayoutInflater().inflate(R.layout.custom_layoutos, null);
        Spinner spOs = custom_layoutOs.findViewById(R.id.spOs);
        adapter = ArrayAdapter.createFromResource(this, R.array.arr_os, android.R.layout.simple_spinner_dropdown_item);
        spOs.setAdapter(adapter);

        new AlertDialog.Builder(this)
                .setCancelable(false)
                .setIcon(R.drawable.ic_launcher_foreground)
                .setTitle(R.string.dialog_os_title)
                .setView(custom_layoutOs)
                .setNeutralButton(R.string.dialog_neutral, null)
                .setNegativeButton(R.string.dialog_negative, null)
                .setPositiveButton(R.string.dialog_positive, (dialogInterface, i) -> lblOs.setText(spOs.getSelectedItem().toString()))
                .create()
                .show();
    }

    private void createDialogCourse() {
        new AlertDialog.Builder(this)
                .setCancelable(false)
                .setIcon(R.drawable.ic_launcher_foreground)
                .setTitle(R.string.dialog_course_title)
                .setSingleChoiceItems(arrCourses, itemCourse[0], (dialogInterface, which) -> itemCourse[0] = which)
                .setNeutralButton(R.string.dialog_neutral, null)
                .setNegativeButton(R.string.dialog_negative, null)
                .setPositiveButton(R.string.dialog_positive, (dialogInterface, i) -> lblCourse.setText(arrCourses[itemCourse[0]]))
                .create()
                .show();
    }

    private void createDialogSurname() {
        final View custom_layoutsn = getLayoutInflater().inflate(R.layout.custom_layoutsn, null);
        EditText txtSurname = custom_layoutsn.findViewById(R.id.txtSurname);
        new AlertDialog.Builder(this)
                .setCancelable(false)
                .setIcon(R.drawable.ic_launcher_foreground)
                .setTitle(R.string.dialog_surname_title)
                .setView(custom_layoutsn)
                .setNeutralButton(R.string.dialog_neutral, null)
                .setNegativeButton(R.string.dialog_negative, null)
                .setPositiveButton(R.string.dialog_positive, (dialogInterface, i) -> lblSurnames.setText(txtSurname.getText().toString()))
                .create()
                .show();
    }

    private void createDialogName() {
        final View custom_layout = getLayoutInflater().inflate(R.layout.custom_layout, null);
        EditText txtName = custom_layout.findViewById(R.id.txtName);

        new AlertDialog.Builder(this).setCancelable(false)
                .setIcon(R.drawable.ic_launcher_foreground)
                .setTitle(R.string.dialog_name_title)
                .setView(custom_layout)
                .setNeutralButton(R.string.dialog_neutral, null)
                .setNegativeButton(R.string.dialog_negative, null)
                .setPositiveButton(R.string.dialog_positive, (dialogInterface, i) -> lblName.setText(txtName.getText().toString()))
                .create()
                .show();
    }

    private void initComponents() {
        btnName = findViewById(R.id.btnName);
        btnSurnames = findViewById(R.id.btnSurNames);
        btnCourse = findViewById(R.id.btnCourse);
        btnLanguages = findViewById(R.id.btnLanguages);
        btnOs = findViewById(R.id.btnOs);
        lblName = findViewById(R.id.lblName);
        lblSurnames = findViewById(R.id.lblSurNames);
        lblCourse = findViewById(R.id.lblCourse);
        lblLanguages = findViewById(R.id.lblLanguages);
        lblOs = findViewById(R.id.lblOs);
        arrLanguages = new String[5];
        arrLanguages = getResources().getStringArray(R.array.arr_languages);
        arrCourses = new String[3];
        arrCourses = getResources().getStringArray(R.array.arr_course);
        checkedLanguages = new boolean[5];
        Arrays.fill(checkedLanguages, false);
        itemCourse = new int[]{-1};
    }
}