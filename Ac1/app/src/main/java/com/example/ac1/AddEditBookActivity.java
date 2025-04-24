package com.example.ac1;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class AddEditBookActivity extends AppCompatActivity {

    private EditText titleEditText, authorEditText;
    private Spinner categorySpinner;
    private CheckBox completedCheckBox;
    private BookDatabaseHelper dbHelper;
    private Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_book);

        titleEditText = findViewById(R.id.titleEditText);
        authorEditText = findViewById(R.id.authorEditText);
        categorySpinner = findViewById(R.id.categorySpinnen);
        completedCheckBox = findViewById(R.id.completedCheckBox);
        dbHelper = new BookDatabaseHelper(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.book_categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);

        book = (Book) getIntent().getSerializableExtra("book");
        if (book != null) {
            titleEditText.setText(book.getTitle());
            authorEditText.setText(book.getAuthor());
            categorySpinner.setSelection(adapter.getPosition(book.getCategory()));
            completedCheckBox.setChecked(book.isCompleted());
        }

        findViewById(R.id.saveButton).setOnClickListener(v -> {
            String title = titleEditText.getText().toString();
            String author = authorEditText.getText().toString();
            String category = categorySpinner.getSelectedItem().toString();
            boolean completed = completedCheckBox.isChecked();

            if (book == null) {
                book = new Book(title, author, category, completed);
                dbHelper.insertBook(book);
            } else {
                book.setTitle(title);
                book.setAuthor(author);
                book.setCategory(category);
                book.setCompleted(completed);
                dbHelper.updateBook(book);
            }
            setResult(RESULT_OK);
            finish();
        });
    }
}