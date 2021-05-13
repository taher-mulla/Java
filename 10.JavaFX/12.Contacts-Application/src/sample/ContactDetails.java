package sample;

public class ContactDetails {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String note;

    public ContactDetails(String firstName, String lastName, String phoneNumber, String note) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.note = note;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getNote() {
        return note;
    }
}
