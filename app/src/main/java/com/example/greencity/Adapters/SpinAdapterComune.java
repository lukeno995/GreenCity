package com.example.greencity.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class SpinAdapterComune extends ArrayAdapter<String> {

    // Your sent context
    private Context context;
    // Your custom values for the spinner (Utente)
    private ArrayList<String> values;

    public SpinAdapterComune(Context context, int textViewResourceId,
                             ArrayList<String> values) {
        super(context, textViewResourceId, values);
        this.context = context;
        this.values = values;
    }



    // And the "magic" goes here
    // This is for the "passive" state of the spinner
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // I created a dynamic TextView here, but you can reference your own  custom layout for each spinner item
        TextView label = (TextView) super.getView(position, convertView, parent);
        label.setTextColor(Color.WHITE);
        label.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        // Then you can get the current item using the values array (Users array) and the current position
        // You can NOW reference each method you has created in your bean object (Utente class)
        label.setText(values.get(position));

        // And finally return your dynamic (or custom) view for each spinner item
        return label;
    }

    // And here is when the "chooser" is popped up
    // Normally is the same view, but you can customize it if you want
    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        TextView label = (TextView) super.getDropDownView(position, convertView, parent);
        label.setTextColor(Color.BLACK);
        label.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        label.setText(values.get(position));
        return label;
    }
}