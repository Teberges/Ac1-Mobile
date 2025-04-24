package com.example.ac1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private BookAdapter adapter;
    private List<Book> bookList;
    private BookDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        dbHelper = new BookDatabaseHelper(this);
        bookList = dbHelper.getAllBooks();
        adapter = new BookAdapter(this, bookList);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Book book = bookList.get(position);
            Intent intent = new Intent(MainActivity.this, AddEditBookActivity.class);
            intent.putExtra("book", book);
            startActivityForResult(intent, 1);
        });

        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            Book book = bookList.get(position);
            dbHelper.deleteBook(book.getId());
            bookList.remove(position);
            adapter.notifyDataSetChanged();
            return true;
        });

        findViewById(R.id.addButton).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddEditBookActivity.class);
            startActivityForResult(intent, 1);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            bookList.clear();
            bookList.addAll(dbHelper.getAllBooks());
            adapter.notifyDataSetChanged();
        }
    }
}