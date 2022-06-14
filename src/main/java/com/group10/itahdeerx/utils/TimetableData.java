package com.group10.itahdeerx.utils;

public class TimetableData {

    private String timeFrom, timeTo;
    private long timestampFrom, timestampTo;
    private String courseCode, courseName;

    public TimetableData() {
    }

    public TimetableData(String timeFrom, String timeTo) {
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
    }

    public String getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(String timeFrom) {
        this.timeFrom = timeFrom;
    }

    public String getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(String timeTo) {
        this.timeTo = timeTo;
    }

    public long getTimestampFrom() {
        return timestampFrom;
    }

    public void setTimestampFrom(long timestampFrom) {
        this.timestampFrom = timestampFrom;
    }

    public long getTimestampTo() {
        return timestampTo;
    }

    public void setTimestampTo(long timestampTo) {
        this.timestampTo = timestampTo;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
