package smartboys.db;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
    }

    public void buttonclick(View view){
        Intent i= new Intent(this,MainActivity.class);
        startActivity(i);
    }
    public void buttonclick2(View view){
        Intent i= new Intent(this,Main3Activity.class);
        startActivity(i);
    }
}
