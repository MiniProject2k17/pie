package smartboys.db;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
    }


        public void buttonClick(View view)
        {
            Intent i= new Intent(this,Main5Activity.class);
            startActivity(i);
        }
        public void buttonClick2(View view)
        {
            Intent i = new Intent(this,Main2Activity.class);
            startActivity(i);
        }
    }


