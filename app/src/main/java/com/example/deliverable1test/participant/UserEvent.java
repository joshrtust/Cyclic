/**
 * Represents an event for a user in a club or organization.
 * This class encapsulates details about the event including its distance, date, type, participants, route, club name, and event name.
 */
package com.example.deliverable1test.participant;

public class UserEvent {
    // Instance variables to hold event details.
    String distance, date, type, participants, route, clubname, eventname;

    /**
     * Constructor to create a new UserEvent.
     *
     * @param distance The distance of the event.
     * @param date The date of the event.
     * @param type The type of the event.
     * @param participants The number of participants in the event.
     * @param route The route of the event.
     * @param clubname The name of the club organizing the event.
     * @param eventname The name of the event.
     */
    public UserEvent(String distance, String date, String type, String participants, String route, String clubname, String eventname) {
        this.distance = distance;
        this.date = date;
        this.type = type;
        this.participants = participants;
        this.route = route;
        this.clubname = clubname;
        this.eventname = eventname;
    }

    // Getter methods to access the properties of the event.

    /**
     * Returns the distance of the event.
     * @return The event's distance.
     */
    public String getDistance() {
        return distance;
    }

    /**
     * Returns the date of the event.
     * @return The event's date.
     */
    public String getDate() {
        return date;
    }

    /**
     * Returns the type of the event.
     * @return The event's type.
     */
    public String getType() {
        return type;
    }

    /**
     * Returns the number of participants in the event.
     * @return The number of participants.
     */
    public String getParticipants() {
        return participants;
    }

    /**
     * Returns the route of the event.
     * @return The event's route.
     */
    public String getRoute() {
        return route;
    }

    /**
     * Returns the name of the club organizing the event.
     * @return The organizing club's name.
     */
    public String getClubname() {
        return clubname;
    }

    /**
     * Returns the name of the event.
     * @return The event's name.
     */
    public String getEventname() {
        return eventname;
    }
}
