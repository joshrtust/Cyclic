package com.example.deliverable1test.admin;



import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.deliverable1test.R;
import com.example.deliverable1test.admin.Event;

import java.util.List;

public class EventList extends ArrayAdapter<Event> {
    private Activity context;
    List<Event> events;

    public EventList(Activity context, List<Event> events) {
        super(context, R.layout.event_list, events);
        this.context = context;
        this.events = events;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.event_list, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewAge = (TextView) listViewItem.findViewById(R.id.textViewAge);
        TextView textViewPace = (TextView) listViewItem.findViewById(R.id.textViewLevel);
        TextView textViewLevel = (TextView) listViewItem.findViewById(R.id.textViewPace);

        Event event = events.get(position);
        textViewName.setText(event.getEventname());
        textViewAge.setText("Age: " + event.getAge().toString());
        textViewPace.setText("Pace: " + event.getPace().toString());
        textViewLevel.setText("Level: " + event.getLevel().toString());
        return listViewItem;
    }
}