package sg.edu.rp.c346.id20046765.problemstatementsolution;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etTitle, etSingers, etYear;
    Button btnAdd, btnRetrieve;
    ImageView imageViewNews;
    RatingBar ratingBarStars;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTitle = findViewById(R.id.etTitle);
        etSingers = findViewById(R.id.etSingers);
        etYear = findViewById(R.id.etYear);
        btnAdd = findViewById(R.id.btnAdd);
        btnRetrieve = findViewById(R.id.btnRetrieve);
        imageViewNews = findViewById(R.id.imageViewNews);
        ratingBarStars = findViewById(R.id.ratingBarStars);

        long inserted_id = 0;

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = etTitle.getText().toString();
                String singer = etSingers.getText().toString();
                String yearStr = etYear.getText().toString();
                int yearInt = Integer.parseInt(yearStr);
                Float stars = ratingBarStars.getRating();

                DBHelper dbh = new DBHelper(MainActivity.this);
                dbh.insertSong(title, singer, yearInt, stars);
                dbh.close();

                AlertDialog.Builder myBuilder = new AlertDialog.Builder(MainActivity.this);
                myBuilder.setTitle("Are you sure?");
                myBuilder.setMessage("You are about to insert: \n" + "\n" +
                        "Title: " + title + "\n" +
                        "Singer: " + singer + "\n" +
                        "Year: " + yearStr + "\n" +
                        "Rating / Stars: " + stars + "\n");
                myBuilder.setCancelable(false);
                myBuilder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        myBuilder.setTitle("Insertion Failed");
                        myBuilder.setMessage("Insertion Failed due to pressing NO");
                        myBuilder.setCancelable(true);
                    }
                });

                myBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AlertDialog.Builder myBuilder2 = new AlertDialog.Builder(MainActivity.this);
                        // After a few hours of research, finally found what I want

                        myBuilder2.setTitle("Successfully Inserted!");
                        myBuilder2.setMessage("You have successfully added " + title + " to the list.");
                        myBuilder2.setCancelable(true);
                        AlertDialog myDialog = myBuilder2.create();
                        myDialog.show();
// I tried researching for the countDownTimer, but too difficult for me, I'll research more some other time
                    }
                });
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();



            }
        });

        btnRetrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShowAllActivity.class);
                startActivity(intent);
            }
        });

    }
}