package com.example.deliverable1test.organizer;

public class ClubOwnerProfile {
    private String socialMediaLink;
    private String phoneNumber;
    private String contactName;
    private String id;

    // Default constructor required for calls to DataSnapshot.getValue(Profile.class)
    public ClubOwnerProfile() {
    }

    public ClubOwnerProfile(String socialMediaLink, String phoneNumber, String contactName) {
        this.socialMediaLink = socialMediaLink;
        this.phoneNumber = phoneNumber;
        this.contactName = contactName;

    }

    public String getSocialMediaLink() {
        return socialMediaLink;
    }

    public void setSocialMediaLink(String socialMediaLink) {
        this.socialMediaLink = socialMediaLink;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
}
