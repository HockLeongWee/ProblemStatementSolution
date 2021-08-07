package sg.edu.rp.c346.id20046765.problemstatementsolution;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.io.*;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Rating;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;

public class EditActivity extends AppCompatActivity {

    EditText etID, etEditTitle, etEditSingers, etEditYear;
    RadioGroup RG;
    RadioButton radioButton1, radioButton2, radioButton3, radioButton4, radioButton5;
    Button btnUpdate, btnDelete, btnCancel;
    Song data;
    RatingBar ratingStar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        etID = findViewById(R.id.etID);
        etEditTitle = findViewById(R.id.etEditTitle);
        etEditSingers = findViewById(R.id.etEditSingers);
        etEditYear = findViewById(R.id.etEditYear);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);
        ratingStar = findViewById(R.id.ratingStar);

        Intent i = getIntent();
        data = (Song) i.getSerializableExtra("data");
        etID.setEnabled(false);

        int dataInt = data.get_id();
        String dataStr = String.valueOf(dataInt);
        etID.setText(dataStr);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(EditActivity.this);
                myBuilder.setTitle("Are you sure?");
                myBuilder.setMessage("Are you sure you want to update it?");
                myBuilder.setCancelable(false);
                myBuilder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        myBuilder.setTitle("Update Failed");
                        myBuilder.setMessage("Updated Failed due to you press no");
                        myBuilder.setCancelable(true);
                    }
                });


                myBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AlertDialog.Builder myBuilder2 = new AlertDialog.Builder(EditActivity.this);
                        // After a few hours of research, finally found what I want
                        myBuilder2.setOnCancelListener(new DialogInterface.OnCancelListener() {
                            public void onCancel(DialogInterface dialog) {
                                finish();
                            }
                        });

                        String oldName = data.getTitle();

                        DBHelper dbh = new DBHelper(EditActivity.this);
                        data.setTitle(etEditTitle.getText().toString());
                        data.setSingers(etEditSingers.getText().toString());
                        String yearStr = etEditYear.getText().toString();
                        int yearInt = Integer.parseInt(yearStr);
                        data.setStars(ratingStar.getRating());
                        data.setYear(yearInt);
                        dbh.updateNote(data);
                        dbh.close();

                        myBuilder2.setTitle("Successfully Updated!");
                        myBuilder2.setMessage("You have successfully updated " + oldName + " to " + data.getTitle());
                        myBuilder2.setCancelable(true);
                        AlertDialog myDialog = myBuilder2.create();
                        myDialog.show();


                    }
                });

                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder myBuilder = new AlertDialog.Builder(EditActivity.this);
                myBuilder.setTitle("Are you sure?");
                myBuilder.setMessage("Are you sure you want to delete?");
                myBuilder.setCancelable(false);
                myBuilder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        myBuilder.setTitle("Deletion Failed");
                        myBuilder.setMessage("The song won't be deleted");
                        myBuilder.setCancelable(true);
                    }
                });
                myBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AlertDialog.Builder myBuilder2 = new AlertDialog.Builder(EditActivity.this);
                        // After a few hours of research, finally found what I want
                        myBuilder2.setOnCancelListener(new DialogInterface.OnCancelListener() {
                            public void onCancel(DialogInterface dialog) {
                                finish();
                            }
                        });

                        myBuilder2.setTitle("Deletion Success!");
                        myBuilder2.setMessage("You have successfully deleted " + data.getTitle() + "!");
                        myBuilder2.setCancelable(true);
                        AlertDialog myDialog = myBuilder2.create();
                        myDialog.show();
// I tried researching for the countDownTimer, but too difficult for me, I'll research more some other time
                                DBHelper dbh = new DBHelper(EditActivity.this);
                                dbh.deleteNote(data.get_id());
                                dbh.close();
                    }
                });
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditActivity.this, ShowAllActivity.class);
                startActivity(intent);
            }
        });

    }
}