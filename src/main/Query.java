package main;

import java.util.Calendar;

public class Query {
    private String serviceID; //can be *
    private int serviceVariationID;

    private String questionTypeID; //can be *
    private int questionCategoryID;
    private int questionSubCategoryID;

    private String responseType;

    private Calendar dateFrom;
    private Calendar dateTo;

    public String getServiceID() {
        return serviceID;
    }

    public void setServiceID(String serviceID) {
        this.serviceID = serviceID;
    }

    public int getServiceVariationID() {
        return serviceVariationID;
    }

    public void setServiceVariationID(int serviceVariationID) {
        this.serviceVariationID = serviceVariationID;
    }

    public String getQuestionTypeID() {
        return questionTypeID;
    }

    public Query() {
    }

    public void setQuestionTypeID(String questionTypeID) {
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

    public Calendar getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Calendar dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Calendar getDateTo() {
        return dateTo;
    }

    public void setDateTo(Calendar dateTo) {
        this.dateTo = dateTo;
    }
}
