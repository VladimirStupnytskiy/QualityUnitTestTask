package main;

import java.util.Calendar;

public class WaitingTimeline implements Comparable<Query>{
    private int serviceID;
    private int serviceVariationID;
    private int questionTypeID;
    private int questionCategoryID;
    private int questionSubCategoryID;
    private String responseType;
    private Calendar date;
    private int minutes;

    public WaitingTimeline() {
    }

    @Override
    public int compareTo(Query query) {
        boolean part = false;
        int value = -1;
        if ((query.getServiceID().equals("*")||(this.getServiceID()==Integer.parseInt(query.getServiceID())))&&
                (query.getQuestionTypeID().equals("*")||(Integer.parseInt(query.getQuestionTypeID())==this.getQuestionTypeID()))&&
                (query.getResponseType().equals(this.getResponseType()))&&
                (this.getDate().after(query.getDateFrom()))
        ){  value = 0;
            part = true;
        }
        if (query.getDateTo()!=null && part){
            if (this.getDate().before(query.getDateTo()))value = 0;
            else value = -1;
        }

        return value;
    }

    public int getServiceID() {
        return serviceID;
    }

    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }

    public int getServiceVariationID() {
        return serviceVariationID;
    }

    public void setServiceVariationID(int serviceVariationID) {
        this.serviceVariationID = serviceVariationID;
    }

    public int getQuestionTypeID() {
        return questionTypeID;
    }

    public void setQuestionTypeID(int questionTypeID) {
        this.questionTypeID = questionTypeID;
    }

    public int getQuestionCategoryID() {
        return questionCategoryID;
    }

    public void setQuestionCategoryID(int questionCategoryID) {
        this.questionCategoryID = questionCategoryID;
    }

    public int getQuestionSubCategoryID() {
        return questionSubCategoryID;
    }

    public void setQuestionSubCategoryID(int questionSubCategoryID) {
        this.questionSubCategoryID = questionSubCategoryID;
    }

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }


}
