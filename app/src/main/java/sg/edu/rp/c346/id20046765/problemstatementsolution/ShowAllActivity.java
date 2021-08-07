package sg.edu.rp.c346.id20046765.problemstatementsolution;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShowAllActivity extends AppCompatActivity {

    Button btnShowAll;
    ListView lv;
    Song data;
    ArrayList<Song> al;
    ArrayAdapter<Song> aa;
    String starsStr = "";
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all);

        btnShowAll = findViewById(R.id.btnShowAll);
        lv = findViewById(R.id.lv);

        DBHelper dbh = new DBHelper(ShowAllActivity.this);
        al = new ArrayList<Song>(dbh.getAllSongs());
//        aa = new ArrayAdapter<Song>(this, android.R.layout.simple_list_item_1, al);
//        lv.setAdapter(aa);

        adapter = new CustomAdapter(this, R.layout.row, al);
        lv.setAdapter(adapter);

        btnShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ShowAllActivity.this);
                al.clear();
                al.addAll(dbh.getAllNotes(5));
                adapter.notifyDataSetChanged();
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song data = al.get(position);
                Intent i = new Intent(ShowAllActivity.this, EditActivity.class);
                i.putExtra("data", data);
                startActivity(i);
            }
        });
    }
    @Override
    protected void onResume(){
        super.onResume();
        DBHelper dbh = new DBHelper(ShowAllActivity.this);
        al.clear();
        al.addAll(dbh.getAllSongs());
        adapter.notifyDataSetChanged();
    }
}