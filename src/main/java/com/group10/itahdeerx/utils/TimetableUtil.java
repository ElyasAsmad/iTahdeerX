package com.group10.itahdeerx.utils;

public class TimetableUtil {

    public static TimetableData[] getTimetableTimes() {

        return new TimetableData[] {
                new TimetableData("8:30 AM", "9:50 AM"),
                new TimetableData("10:00 AM", "11:20 AM"),
                new TimetableData("11:30 AM", "12:50 PM"),
                new TimetableData("1:00 PM", "2:00 PM"),
                new TimetableData("2:00 PM", "3:20 PM"),
                new TimetableData("3:30 PM", "4:50 PM"),
                new TimetableData("5:00 PM", "6:50 PM")
        };
    }

    public static String[] getDaysInWeek() {

        return new String[] {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};

    }

    public static String[] getTimeFrom() {
        return new String[]{
                "8:30 AM",
                "10:00 AM",
                "11:30 AM",
                "1:00 PM",
                "2:00 PM",
                "3:30 PM",
                "5:00 PM"
        };
    }

    public static String[] getTimeTo() {
        return new String[] {
            "9:50 AM",
            "11:20 AM",
            "12:50 PM",
            "2:00 PM",
            "3:20 PM",
            "4:50 PM",
            "6:50 PM"
        };
    }

}
