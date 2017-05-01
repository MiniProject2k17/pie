package smartboys.db;

/**
 * Created by kengeorge on 17/04/17.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.Date;

/**
 * Created by deva on 07/03/17.
 */

public class transactDB extends SQLiteOpenHelper {
    public static final String dbname="Smart Home ";
    public static final String tbname="Expensegraph";
    public static final String amount="amount";
    public static final String cat="category";
    public transactDB(Context context) {
        super(context, dbname, null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Expensegraph(category text Primary key,amount integer )");
        //db=this.getWritableDatabase();
        db.execSQL("insert into Expensegraph values('Food',0)");
        db.execSQL("insert into Expensegraph values('Transport',0)");
        db.execSQL("insert into Expensegraph values('Petrol',0)");
        db.execSQL("insert into Expensegraph values('Entertainment',0)");
        db.execSQL("insert into Expensegraph values('Shopping',0)");
        db.execSQL("insert into Expensegraph values('Other',0)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+tbname);
        onCreate(db);

    }
    public void addExpense(String catg,Integer amt){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues content=new ContentValues();
        content.put(amount,amt);
        db.update(tbname, content,"category ='"+catg+"'",null) ;
    }

    public Cursor dispExp()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor result=db.rawQuery("select * from "+tbname,null);
        return result;
    }

    public Cursor displayExp(String cat)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        //Cursor res=db.rawQuery("select amount from Expensegraph where category = " + "\""+cat+"\"",null);
        Cursor res=db.query(tbname,new String[]{"amount"},"category=?",new String[]{cat},null,null,null);
        return res;
    }

}

