package com.affinipay.com;


public class CalculateTime {

    private int calculateHours(int addMin) {
        int time;
        int day = 24;

        // clock goes forward by default
        int hours = addMin / 60;

        // Strip out days
        int days = hours / day;
        if (days > 0) {
            int hoursMinusDays = hours - (days * day);
            return hoursMinusDays;
        }

        // clock goes back if we are adding more than 12 hours
        if (hours > 12) {
            int hoursMinusDays = hours - (days * day);
            time = hoursMinusDays - day;
            return time;
        }
      return hours;

    }

    private String calculatePeriod(int nHours, int hours,  boolean periodBool) {
        if (nHours + hours >= 12){
            if (periodBool){
               return  "PM";
            } else {
               return "AM";
            }
        }
        return "";
    }

    private int calculateMins(int addMin) {
        int mins;
        mins = addMin%60;

        return mins;

    }

    private int findHours(String time) {
        String[] hString = time.split(":(.*)");
        String cleanH = hString[0];

        return Integer.parseInt(cleanH);
    }

    private int findMinutes(String time) {
        String[] mString = time.split("(.*):");
        String cleanM = mString[1];
        cleanM = cleanM.replaceAll("[A-Z\\s+]","");

        return  Integer.parseInt(cleanM);
    }

    private String findPeriod(String time) {
        String[] findPeriod = time.split("[\\s+]");
        String period = findPeriod[1];

        return period;

    }

    public String addMinutes(String time, int addMins) {
        /* Java does not seem to support unsigned int declaration in the way something like C or Go does.
        In the spirit of using an unsigned int i'm checking to make sure that the value is not negative
         */

        if (addMins < 0) {
            throw new RuntimeException("addMin must be greater than zero");
        }

        // parse string
        String period = findPeriod(time);
        int hours = findHours(time);
        int minutes = findMinutes(time);

        // track which period
        Boolean periodBool = false;
        if (period.contains("AM"))
            periodBool = true;

        // calculate minutes
        int addHours = calculateHours(addMins);
        int addMinutes = calculateMins(addMins);
        int nHours = hours + addHours;
        int nMins = minutes + addMinutes;

        // check if hours can be made from added minutes
        if (nMins > 59) {
           int addHFromM = nMins/60;
            nHours += addHFromM;
            nMins = nMins - 60;
        }

        // flip period
        if (nHours >= 12 && addHours > 1) {
            period =  calculatePeriod(nHours, hours, periodBool);
        }

        // normalize nHours
        if (nHours > 12) {
            nHours = nHours - 12;
        }

        // prep nMins string
       String minsString = Integer.toString(nMins);
        if (nMins < 10) {
            minsString = '0' + minsString;
        }

        // prep nHours string
        String hoursString = Integer.toString(nHours);
        if (nHours < 10) {
            hoursString = '0' + hoursString;
        }

        return hoursString + ":" + minsString +  " " + period;

    }

}
