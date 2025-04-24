package com.example.ac1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

public class BookAdapter extends ArrayAdapter<Book> {

    public BookAdapter(Context context, List<Book> books) {
        super(context, 0, books);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.book_item, parent, false);
        }

        TextView titleTextView = convertView.findViewById(R.id.titleTextView);
        TextView authorTextView = convertView.findViewById(R.id.authorTextView);
        TextView categoryTextView = convertView.findViewById(R.id.categoryTextView);
        CheckBox completedCheckBox = convertView.findViewById(R.id.completedCheckBox);

        Book book = getItem(position);

        titleTextView.setText(book.getTitle());
        authorTextView.setText(book.getAuthor());
        categoryTextView.setText(book.getCategory());
        completedCheckBox.setChecked(book.isCompleted());

        return convertView;
    }
}
