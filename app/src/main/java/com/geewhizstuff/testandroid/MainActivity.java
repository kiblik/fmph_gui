package com.geewhizstuff.testandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.MenuItem;
import android.widget.TextView;
import com.geewhizstuff.testandroid.DBAccess;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public String[] currentAnswers;
    public DBAccess db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentAnswers = new String[]{"bird1", "bird2", "bird3", "bird4", "bird4", "bird5", "bird6", "bird7"};
        db = new DBAccess(getApplicationContext());
        db.ping();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void submit(View view) {
        TextView responseView = (TextView) findViewById(R.id.response);
        String response = responseView.getText().toString();
        boolean found=false;
        int i;
        for(i=0;i<currentAnswers.length;i++) {
            if (currentAnswers[i].equals(response)) {
                found = true;
                break;
            }
        }

        if (found) {
            int id;
            id = getResources().getIdentifier("answer"+(i+1), "id", getPackageName());
            TextView answerView = (TextView) findViewById(id);
            answerView.setText(response);
        }
    }

    public void loadNew(View view){
        String q = db.getNewQuestion();
        TextView questionView = (TextView) findViewById(R.id.question);
        questionView.setText(q);

        String[] a = db.getNewAnswers(q);
        currentAnswers = a;
        int i;
        for(i=0;i<a.length;i++) {
            int id;
            id = getResources().getIdentifier("answer"+(i+1), "id", getPackageName());
            TextView answerView = (TextView) findViewById(id);
            answerView.setText("give me answer");
        }
        for(i=i;i<6;i++) {
            int id;
            id = getResources().getIdentifier("answer"+(i+1), "id", getPackageName());
            TextView answerView = (TextView) findViewById(id);
            answerView.setText("");
        }


    }

}
