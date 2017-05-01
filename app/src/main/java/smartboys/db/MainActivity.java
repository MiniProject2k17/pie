package smartboys.db;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RadioGroup rg;
    Spinner spin;
    ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rg = (RadioGroup) findViewById(R.id.rg);
        spin = (Spinner) findViewById(R.id.spinner);
        if (rg.getCheckedRadioButtonId() == R.id.expense) {
            adapter = ArrayAdapter.createFromResource(this, R.array.expense, android.R.layout.simple_spinner_item);
            spin.setAdapter(adapter);
        }
        if (rg.getCheckedRadioButtonId() == R.id.income) {
            adapter = ArrayAdapter.createFromResource(this, R.array.income, android.R.layout.simple_spinner_item);
            spin.setAdapter(adapter);
        }
    }

    public void catlist(View v) {

        if (rg.getCheckedRadioButtonId() == R.id.expense) {
            adapter = ArrayAdapter.createFromResource(this, R.array.expense, android.R.layout.simple_spinner_item);
            spin.setAdapter(adapter);
        }
        if (rg.getCheckedRadioButtonId() == R.id.income) {
            adapter = ArrayAdapter.createFromResource(this, R.array.income, android.R.layout.simple_spinner_item);
            spin.setAdapter(adapter);
        }
    }

    public void saves(View v) {
        transactDB db = new transactDB(this);
        IncomeDB db2 = new IncomeDB(this);
        TextView amt = (TextView) findViewById(R.id.amount);
        if (amt.getText().toString().equals("")) {

            Toast.makeText(getApplicationContext(), "Nothing to add", Toast.LENGTH_SHORT).show();
        }
        else {
            int ammt = Integer.parseInt(amt.getText().toString());

            int amount;
            //Cursor c = db.displayExp(spin.getSelectedItem().toString());
            //Cursor c2=db2.dispInc(spin.getSelectedItem().toString());
            //amount = Integer.parseInt(c.getString(1));
            //int amnt =Integer.parseInt(c2.getString(1));


            if (rg.getCheckedRadioButtonId() == R.id.expense) {
                Cursor c = db.displayExp(spin.getSelectedItem().toString());
                c.moveToFirst();
                amount = Integer.parseInt(c.getString(c.getColumnIndex("amount")));
                amount = amount + ammt;
                db.addExpense(spin.getSelectedItem().toString(), amount);
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
            } else {
                Cursor c2 = db2.dispInc(spin.getSelectedItem().toString());
                c2.moveToFirst();
                int amnt = Integer.parseInt(c2.getString(c2.getColumnIndex("amount")));
                amnt = amnt + ammt;
                db2.addIncome(spin.getSelectedItem().toString(), amnt);
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
