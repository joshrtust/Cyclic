/**
 * Adapter class for a RecyclerView to display a list of UserEvent objects.
 * It provides filtering capabilities to filter the events based on certain criteria.
 */
package com.example.deliverable1test.participant;

// Import statements for required classes and interfaces
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.deliverable1test.R;

import java.util.ArrayList;
import java.util.List;

public class participantAdapter extends RecyclerView.Adapter<participantAdapter.myviewholder> implements Filterable {

    // List to hold the events and a copy for filtering purposes
    List<UserEvent> eventList;
    List<UserEvent> originaleventList;
    Context context;

    /**
     * Constructor for the participantAdapter.
     *
     * @param activity The context of the activity where the RecyclerView is used.
     * @param eventList The list of UserEvents to display.
     * @param originaleventList A copy of the original list of UserEvents, used for filtering.
     */
    public participantAdapter(Activity activity, List<UserEvent> eventList, List<UserEvent> originaleventList) {
        this.context = activity;
        this.eventList = eventList;
        this.originaleventList = originaleventList;
    }

    @NonNull
    @Override
    /**
     * Called when RecyclerView needs a new ViewHolder of the given type to represent an item.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     */
    public participantAdapter.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for each item of the RecyclerView
        View view = LayoutInflater.from(context).inflate(R.layout.user_events_list, parent, false);
        return new myviewholder(view);
    }

    @Override
    /**
     * Called by RecyclerView to display the data at the specified position.
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the item at the given position.
     * @param position The position of the item within the adapter's data set.
     */
    public void onBindViewHolder(@NonNull participantAdapter.myviewholder holder, int position) {
        // Setting the event details to the TextViews in the ViewHolder
        holder.distance.setText("Distance: " + eventList.get(position).getDistance());
        holder.date.setText("Event Date: " + eventList.get(position).getDate());
        holder.type.setText("Event Type: " + eventList.get(position).getType());
        holder.particpets.setText("Participant: " + eventList.get(position).getParticipants());
        holder.route.setText("Route: " + eventList.get(position).getRoute());
        holder.clubname.setText("Club Name: " + eventList.get(position).getClubname());
        holder.eventname.setText("Event Name: " + eventList.get(position).getEventname());

        // Set OnClickListener for the layout to start a new activity
        holder.layout.setOnClickListener(view -> {
            context.startActivity(new Intent(context, JoinFormActivity.class));
        });

        // Set OnClickListener for the rating button to show a dialog
        holder.buttonratting.setOnClickListener(view -> {
            Dialog dialog = new Dialog(context);
            // Inflate the custom layout for the dialog
            dialog.setContentView(R.layout.rattingdialog);
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setBackgroundDrawableResource(R.color.trans);
            Button button=dialog.findViewById(R.id.submittt);
            // Set OnClickListener for the submit button in the dialog
            button.setOnClickListener(view1 -> {
                Toast.makeText(context, "Thank you for your feedback", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            });
            // Show the dialog
            dialog.show();
        });
    }

    @Override
    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    public int getItemCount() {
        return eventList.size();
    }

    @Override
    /**
     * Returns a filter that can be used to constrain data with a filtering pattern.
     *
     * @return A Filter used for filtering data.
     */
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String query = charSequence.toString().toLowerCase();

                FilterResults results = new FilterResults();
                List<UserEvent> filteredList = new ArrayList<>();

                // Filtering logic based on the query
                for (UserEvent item : originaleventList) {
                    if (item.getType().toLowerCase().contains(query)) {
                        filteredList.add(item);
                    } else if (item.getEventname().toLowerCase().contains(query)) {
                        filteredList.add(item);
                    } else if (item.getClubname().toLowerCase().contains(query)) {
                        filteredList.add(item);
                    }
                }

                results.count = filteredList.size();
                results.values = filteredList;
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                // Update the list of events with the results of filtering
                eventList = (List<UserEvent>) filterResults.values;
                // Notify the adapter to refresh the RecyclerView
                notifyDataSetChanged();
            }
        };
    }

    /**
     * ViewHolder class that defines the views for each item in the RecyclerView.
     */
    public static class myviewholder extends RecyclerView.ViewHolder {
        TextView distance, type, date, route, particpets, clubname, eventname;
        LinearLayout layout;
        AppCompatImageButton buttonratting;

        /**
         * Constructor for the ViewHolder, initializes the views.
         *
         * @param itemView The View that you inflated in onCreateViewHolder.
         */
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            // Initializing the views by finding them in the itemView
            clubname = itemView.findViewById(R.id.userevents_clubname);
            eventname = itemView.findViewById(R.id.userevents_eventname);
            distance = itemView.findViewById(R.id.userevents_distance);
            date = itemView.findViewById(R.id.userevents_date);
            type = itemView.findViewById(R.id.userevents_type);
            particpets = itemView.findViewById(R.id.userevents_particpant);
            route = itemView.findViewById(R.id.userevents_route);
            layout = itemView.findViewById(R.id.usereventlist);
            buttonratting = itemView.findViewById(R.id.userevents_clubratting);
        }
    }
}
