package com.example.password_manager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class EntryInfo extends AppCompatActivity {
    Button canc; // кнопка "Отмена"
    Button add;
    DBHelper dbHelper;
    TextView url;
    TextView password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_info);
        canc = (Button) findViewById(R.id.cancel);
        add = (Button) findViewById(R.id.add);
        url = (TextView) findViewById(R.id.editText);
        password = (TextView) findViewById(R.id.editText2);
        dbHelper = new DBHelper(this);
        View.OnClickListener oclBtnOk = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues cv = new ContentValues();
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                switch(v.getId()) {
                    case R.id.cancel: {
                        Intent mainintentbck = new Intent(EntryInfo.this, List_of_passwords.class);
                        finish();
                        startActivity(mainintentbck);
                        break;
                    }
                    case R.id.add: {//напиши тут закидывание в бд плиз
                        cv.put("url_site", url.getText().toString());
                        cv.put("password", password.getText().toString());
                        // вставляем запись и получаем ее ID
                        long rowId = db.insert("mytable", null, cv);
                        Intent mainintentbck = new Intent(EntryInfo.this, List_of_passwords.class);
                        finish();
                        startActivity(mainintentbck);
                    }

                }
            }
        };
        add.setOnClickListener(oclBtnOk);
        canc.setOnClickListener(oclBtnOk);
        //необходимо прописать логику кнопки добавить
    }
    private static long back_pressed;
    @Override
    public void onBackPressed() { //событик двойного даблклика назад
        if (back_pressed + 2000 > System.currentTimeMillis()) {

            Intent mainintentbck = new Intent(this, List_of_passwords.class);
            finish();
            startActivity(mainintentbck);
        } else {
        }
        back_pressed = System.currentTimeMillis();
    }
}
