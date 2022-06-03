package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy"); //Date format
    private static final String filename = "input.txt";                 // input data filename
    private static final String subDelimiter = "\\.";                   // delimiter between sub-parameters
    private static final String delimiter = " ";                        // delimiter between parameters
    private static final String ifOutputsNotDefined = "-";              // symbol for output in case of not defined query
    private static final String symbolForWaitingT = "C";                // how line started
    private static final String symbolForQuery = "D";                   // how query line started
    private static final int parametersInLine = 6;                      // limit q-ty of parameters per line for waiting timeline
    private static final int parametersInQueryLine = 5;                 // limit q-ty of parameters per line for query line
    private static final int countLimit = 100000;                       //count limit of all lines
    private static List <WaitingTimeline> list = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException, ParseException {
        readFile();
    }

    private static void readFile() throws FileNotFoundException, ParseException {
        Scanner scanner = new Scanner(new File(filename));
        parseFile(scanner);
    }


    private static void parseFile(Scanner scanner) throws ParseException {
        int lineLimit = 0;
        if (scanner.hasNextLine()){
            lineLimit = Integer.parseInt(scanner.nextLine());
            if (lineLimit>countLimit)
                throw new IndexOutOfBoundsException("Line count need to be less then " + countLimit);
        }

        for (int i = 0; i < lineLimit; i++) {
            String[]parameters = scanner.nextLine().split(delimiter);

            switch (parameters[0]){
                case symbolForWaitingT ->{
                    if (parameters.length!=parametersInLine)
                        throw new ParseException("Check q-ty of parameters in line. Parameters need to be "+parametersInLine,1);
                    else list.add(createWaitingTimeline(parameters));
                    break;
                }

                case symbolForQuery ->{
                    if (parameters.length!=parametersInQueryLine)
                        throw new ParseException("Check q-ty of parameters in Query line. Parameters need to be "+parametersInQueryLine,1);
                    else executeQuery(createQuery(parameters));
                    break;
                }
            }
        }
    }

    private static Query createQuery(String[] parameters) throws ParseException {
        Query query = new Query();
        query.setServiceID(parameters[1].split(subDelimiter)[0]);               // set serviceID
        query.setQuestionTypeID(parameters[2].split(subDelimiter)[0]);          // set QuestionTypeID
        query.setResponseType(parameters[3]);                                   // set ResponseType

        Calendar calfrom = Calendar.getInstance();                              // set Date From
        calfrom.setTime(sdf.parse(parameters[4].split("-")[0]));
        query.setDateFrom(calfrom);

        try {
            query.setServiceVariationID(Integer.parseInt(parameters[1].split(subDelimiter)[1])); // set ServiceVariationID
        } catch(ArrayIndexOutOfBoundsException exception) {}    //if sub-parameter not declared it will be processed by catch

        try {
            query.setQuestionCategoryID(Integer.parseInt(parameters[2].split(subDelimiter)[1])); // set questionCategoryID
        } catch(ArrayIndexOutOfBoundsException exception) {}

        try {
            query.setQuestionSubCategoryID(Integer.parseInt(parameters[2].split(subDelimiter)[2])); // set questionSubCategoryID
        } catch(ArrayIndexOutOfBoundsException exception) {}


        Calendar calTo = Calendar.getInstance(); //Set DateTo
        try {
            calTo.setTime(sdf.parse(parameters[4].split("-")[1]));
            query.setDateTo(calTo);
        } catch(ArrayIndexOutOfBoundsException exception) {}

        return query;
    }

    private static WaitingTimeline createWaitingTimeline(String[] parameters) throws ParseException {
        WaitingTimeline waitingTimeline = new WaitingTimeline();
        waitingTimeline.setServiceID(Integer.parseInt(parameters[1].split(subDelimiter)[0]));       //set serviceID
        waitingTimeline.setQuestionTypeID(Integer.parseInt(parameters[2].split(subDelimiter)[0]));  // set QuestionTypeID
        waitingTimeline.setResponseType(parameters[3]);     // set ResponseType

        Calendar cal = Calendar.getInstance();              //Set Date
        cal.setTime(sdf.parse(parameters[4]));
        waitingTimeline.setDate(cal);

        waitingTimeline.setMinutes(Integer.parseInt(parameters[5])); // set minutes

        try {
            waitingTimeline.setServiceVariationID(Integer.parseInt(parameters[1].split(subDelimiter)[1])); // set ServiceVariationID
        } catch(ArrayIndexOutOfBoundsException exception) {}

        try {
            waitingTimeline.setQuestionCategoryID(Integer.parseInt(parameters[2].split(subDelimiter)[1])); // set questionCategoryID
        } catch(ArrayIndexOutOfBoundsException exception) {}

        try {
            waitingTimeline.setQuestionSubCategoryID(Integer.parseInt(parameters[2].split(subDelimiter)[2])); // set questionSubCategoryID
        } catch(ArrayIndexOutOfBoundsException exception) {}

        return waitingTimeline;
    }

    public static void executeQuery(Query query){
        List <WaitingTimeline> filtered = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).compareTo(query)==0){
                filtered.add(list.get(i));
            }
        }

        if (filtered.size()==0){
            print(ifOutputsNotDefined);
        }else {
            int sum = 0;
            for (int i = 0; i < filtered.size(); i++) {
                sum = sum + filtered.get(i).getMinutes();
            }
            int avg = Math.round(sum/filtered.size());
            print(String.valueOf(avg));
        }
    }

    private static void print(String str) {
        System.out.println(str);
    }
}
