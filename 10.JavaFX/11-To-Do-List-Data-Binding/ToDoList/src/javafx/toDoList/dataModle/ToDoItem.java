package javafx.toDoList.dataModle;

import java.time.LocalDate;

public class ToDoItem {
    private String shortDiscription;
    private String details;
    private LocalDate dealLine;

    public ToDoItem(String shortDiscription, String details, LocalDate dealLine) {
        this.shortDiscription = shortDiscription;
        this.details = details;
        this.dealLine = dealLine;
    }
//          CUSTOM SELF FACTORY IS BEEING USED IN CONTROLLER initialize()
//    @Override
//    public String toString() {
//        return shortDiscription;
//    }

    public String getShortDiscription() {
        return shortDiscription;
    }

    public void setShortDiscription(String shortDiscription) {
        this.shortDiscription = shortDiscription;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public LocalDate getDealLine() {
        return dealLine;
    }

    public void setDealLine(LocalDate dealLine) {
        this.dealLine = dealLine;
    }
}
