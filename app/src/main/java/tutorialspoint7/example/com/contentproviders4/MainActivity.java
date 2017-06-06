package tutorialspoint7.example.com.contentproviders4;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // 寫入一筆資料
    public void onClickAddName(View view) {
        // Add a new student record
        ContentValues values = new ContentValues();
        values.put(StudentsProvider.NAME, ((EditText)findViewById(R.id.editText2)).getText().toString());
        values.put(StudentsProvider.GRADE, ((EditText)findViewById(R.id.editText3)).getText().toString());
        Uri uri = getContentResolver().insert( StudentsProvider.CONTENT_URI, values);
        Toast.makeText(getBaseContext(), uri.toString(), Toast.LENGTH_SHORT).show();
    }

    // 查詢資料
    public void onClickRetrieveStudents(View view) {
        // Retrieve student records
        String URL = "content://tutorialspoint7.example.com.contentproviders4.StudentsProvider";
        Uri students = Uri.parse(URL);
        Cursor c = managedQuery(students, null, null, null, "name");
        // 移動cursor，一筆一筆顯示在畫面上
        if (c.moveToFirst()) {
            do{
                Toast.makeText(this, c.getString(c.getColumnIndex(StudentsProvider._ID)) + ", " + c.getString(c.getColumnIndex( StudentsProvider.NAME)) + ", " + c.getString(c.getColumnIndex( StudentsProvider.GRADE)), Toast.LENGTH_SHORT).show();
            } while (c.moveToNext());
        }
    }



}
