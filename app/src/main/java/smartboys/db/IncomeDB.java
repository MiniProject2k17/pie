package smartboys.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by kengeorge on 17/04/17.
 */

public class IncomeDB extends SQLiteOpenHelper {
    public static final String dbname="Smart Home Budget ";
    public static final String tbname="Incomegraph";
    public static final String amount="amount";
    public static final String cat="category";
    public IncomeDB(Context context) {
        super(context, dbname, null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Incomegraph(category text Primary key,amount integer default 0)");
        db.execSQL("insert into Incomegraph values('Gift',0)");
        db.execSQL("insert into Incomegraph values('Salary',0)");
        db.execSQL("insert into Incomegraph values('Other',0)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+tbname);
        onCreate(db);

    }
    public void addIncome(String catg,Integer amt){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues content=new ContentValues();
        content.put(amount,amt);
        db.update(tbname, content,"category ='"+catg+"'",null) ;
    }

    public Cursor displayInc()
    {
      SQLiteDatabase db=this.getWritableDatabase();
        Cursor result=db.rawQuery("select * from "+tbname,null);
        return result;
    }

    public Cursor dispInc(String cat)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        // Cursor res=db.rawQuery("select amount from Expensegraph where category="+"\""+cat+"\"",null);
         Cursor res=db.query(tbname,new String[]{"amount"},"category=?",new String[]{cat},null,null,null);
        return res;
    }

}

