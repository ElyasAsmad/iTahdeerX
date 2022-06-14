package com.group10.itahdeerx.components;

import com.group10.itahdeerx.utils.Theme;
import com.group10.itahdeerx.utils.TimetableData;
import com.group10.itahdeerx.utils.TimetableUtil;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

public class TimeTable extends VBox {

    VBox mainContainer;
    boolean isActionButtonEnabled = false;
    Map<String, Map<String, TimetableData>> timetableData = new LinkedHashMap<>();

    public TimeTable(double width, double height) {
        mainContainer = new VBox();
        mainContainer.setPrefWidth(width);
        mainContainer.setAlignment(Pos.TOP_CENTER);
        mainContainer.setPrefHeight(height);
        mainContainer.setSpacing(5);
        mainContainer.setStyle("-fx-background-color: #fafdfc;");

        TimetableData[] times = TimetableUtil.getTimetableTimes();

        for (String day: TimetableUtil.getDaysInWeek()) {

            Map<String, TimetableData> timeData = new LinkedHashMap<>();
            for (TimetableData time : times) {
                timeData.put(time.getTimeFrom() + " - " + time.getTimeTo(), new TimetableData());
            }

            timetableData.put(day, timeData);
        }

    }

    private VBox getTableCell(String title, String subtitle, HBox container) {

        VBox _cell = new VBox();
        _cell.setPadding(new Insets(5));
        _cell.setAlignment(Pos.CENTER);
        _cell.prefWidthProperty().bind(container.widthProperty());

        Text titleText = new Text(title);
        titleText.setStyle("-fx-font-weight: bold;");

        Text subtitleText = new Text(subtitle);
        subtitleText.setStyle("-fx-font-size: 12px;");

        _cell.getChildren().addAll(titleText, subtitleText);

        return _cell;
    }

    private VBox getTableHeaderCell(String cellContent, HBox container) {
        VBox dayContainer = new VBox();
        dayContainer.setAlignment(Pos.CENTER);
        dayContainer.setPadding(new Insets(12));
        dayContainer.setStyle("-fx-background-color: #dae5e3; -fx-background-radius: 10px;");
        dayContainer.prefWidthProperty().bind(container.widthProperty());

        Text dayText = new Text(cellContent);

        dayContainer.getChildren().add(dayText);
        return dayContainer;
    }

    public VBox render() {

        TimetableData[] timetableHeaders = TimetableUtil.getTimetableTimes();

        HBox tableHead = new HBox();
        tableHead.setSpacing(5);
        tableHead.setAlignment(Pos.CENTER_LEFT);
        tableHead.getChildren().add(getTableHeaderCell("Day/Time", tableHead));

        for (TimetableData time: timetableHeaders) {
            tableHead.getChildren().add(getTableHeaderCell(time.getTimeFrom() + " - " + time.getTimeTo(), tableHead));
        }

        mainContainer.getChildren().add(tableHead);

        for (Map.Entry<String, Map<String, TimetableData>> set : timetableData.entrySet()) {

            HBox tableRow = new HBox();
            tableRow.setAlignment(Pos.CENTER_LEFT);
            tableRow.setSpacing(5);
            tableRow.getChildren().add(getTableHeaderCell(set.getKey(), tableRow));

            ObservableList<Node> list = FXCollections.observableArrayList();
            Bindings.bindContentBidirectional(list, tableRow.getChildren());

            for (Map.Entry<String, TimetableData> timeSet : set.getValue().entrySet()) {
                list.add(getTableCell(timeSet.getValue().getCourseName(), timeSet.getValue().getCourseCode(), tableRow));
            }

            mainContainer.getChildren().add(tableRow);
        }

        if (isActionButtonEnabled) {
            Button addCourse = new MyButton("Add Course", Theme.LIGHT);
            addCourse.setOnMouseClicked(mouseEvent -> {
                final Stage dialog = new Stage();
                dialog.initModality(Modality.APPLICATION_MODAL);
                dialog.initOwner(mainContainer.getScene().getWindow());
                dialog.setMinWidth(500);

                Text dialogTitle = new Text("Add a course");
                dialogTitle.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

                TextField courseName = new MyTextField("Course Name");
                TextField courseCode = new MyTextField("Course Code");

                TimetableData[] timeDetails = TimetableUtil.getTimetableTimes();

                ObservableList<String> timesCombined = FXCollections.observableArrayList();

                for (TimetableData time : timeDetails) {
                    timesCombined.add(time.getTimeFrom() + " - " + time.getTimeTo());
                }

                ObservableList<String> timeFromOptions =
                        FXCollections.observableArrayList(TimetableUtil.getTimeFrom());

                ObservableList<String> timeToOptions =
                        FXCollections.observableArrayList(TimetableUtil.getTimeTo());

                ObservableList<String> daysOptions =
                        FXCollections.observableArrayList(TimetableUtil.getDaysInWeek());

                ComboBox<String> daysBox = new MyComboBox(daysOptions, "Day");
                daysBox.prefWidthProperty().bind(dialog.widthProperty());

                ComboBox<String> timesCombinedBox = new MyComboBox(timesCombined, "Course time");
                timesCombinedBox.prefWidthProperty().bind(dialog.widthProperty());

                Button submitBtn = new MyButton("Add Course", Theme.LIGHT);
                submitBtn.prefWidthProperty().bind(dialog.widthProperty());
                submitBtn.setOnMouseClicked(event -> {

                    TimetableData _newData = new TimetableData();
                    _newData.setCourseName(courseName.getText());
                    _newData.setCourseCode(courseCode.getText());

                    Map<String, TimetableData> dayRow = timetableData.get(daysBox.getValue());

                    dayRow.put(timesCombinedBox.getValue(), _newData);

                    timetableData.put(daysBox.getValue(), dayRow);

                    dialog.close();

                    // Re-render timetable
                    mainContainer.getChildren().clear();
                    this.render();

                });

                VBox dialogBox = new VBox(20);
                dialogBox.setAlignment(Pos.CENTER);
                dialogBox.getChildren().addAll(dialogTitle, courseName, courseCode, daysBox, timesCombinedBox, submitBtn);
                dialogBox.setPadding(new Insets(20));
                dialogBox.minWidth(500);

                Scene dialogScene = new Scene(dialogBox);
                dialog.setScene(dialogScene);
                dialog.setTitle("Add New Course");
                dialog.show();

            });
            VBox.setMargin(addCourse, new Insets(10, 0, 0, 0));

            mainContainer.getChildren().add(addCourse);

        }

        return mainContainer;

    }

    public static LocalDate stringToDate(String date) {

        Instant instant = Instant.parse(date);
        ZoneId zoneId = ZoneId.of("Asia/Kuala_Lumpur");
        ZonedDateTime zdt = instant.atZone(zoneId);
        return zdt.toLocalDate();

    }

    public void enableActionButton() {
        this.isActionButtonEnabled = true;
    }

}
