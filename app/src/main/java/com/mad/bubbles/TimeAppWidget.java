package com.mad.bubbles;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Implementation of App Widget functionality.
 * Sources: https://stackoverflow.com/questions/17187471/setting-transparency-for-widget-background
 */
public class TimeAppWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = getNearestHour();
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.time_app_widget);
        views.setTextViewText(R.id.appwidget_text, widgetText);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    /**
     * Get the current time and round to nearest hour.
     * Sources used: https://stackoverflow.com/questions/25474033/getting-hours-minutes-and-seconds-from-date,
     * https://stackoverflow.com/questions/8233236/in-java-how-do-we-round-off-time-to-nearest-hour-and-minute
     * https://stackoverflow.com/questions/6976922/problem-with-calendar-getcalendar-hour
     * @return
     */
    private static String getNearestHour() {
        Calendar calendar = new GregorianCalendar();
        Date date = calendar.getTime();
        calendar.setTime(date);

        if (calendar.get(Calendar.MINUTE) >= 30)
            calendar.add(Calendar.HOUR, 1);

        date = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("K a");
        String formattedTime = sdf.format(date);

        return formattedTime;
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}