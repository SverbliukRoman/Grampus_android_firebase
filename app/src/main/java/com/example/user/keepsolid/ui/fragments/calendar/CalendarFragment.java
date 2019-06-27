package com.example.user.keepsolid.ui.fragments.calendar;

import android.content.Context;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alamkanak.weekview.MonthLoader;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;
import com.example.user.keepsolid.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by root on 05.09.17.
 */

public class CalendarFragment extends android.support.v4.app.Fragment implements WeekView.EventClickListener {
    private WeekView mWeekView;
    private Context context;
    private String param = "calendar=";
    private String KEY = "Calendar";
    ModelCalendar modelCalendar;
    CustomSwipeToRefresh swipeRefreshLayout;
    ArrayList<WeekViewEvent> events = new ArrayList<WeekViewEvent>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        context = container.getContext();
        mWeekView = view.findViewById(R.id.weekView);


        mWeekView.setMonthChangeListener(mMonthChangeListener);
        mWeekView.setOnEventClickListener(this);

        return view;
    }



    @Override
    public void onStart() {
        super.onStart();
    }


    MonthLoader.MonthChangeListener mMonthChangeListener = new MonthLoader.MonthChangeListener() {
        @Override
        public List<WeekViewEvent> onMonthChange(int newYear, int newMonth) {
            // Populate the week view with some events.
            //events = new ArrayList<WeekViewEvent>();


            ArrayList<WeekViewEvent> eventsMonth = new ArrayList<WeekViewEvent>();
            for (int i = 0; i < events.size(); i++) {
                Log.e("EventsCalendar", ""+(newMonth-1));
                if (((events.get(i).getStartTime().get(Calendar.MONTH)) == (newMonth-1))) {
                    eventsMonth.add(events.get(i));
                }
            }
            return eventsMonth;
        }
    };

    @Override
    public void onEventClick(WeekViewEvent event, RectF eventRect) {
        Log.i("onEventClick", event.getName());
        Toast.makeText(getContext(), event.getName() + " " + event.getStartTime().getTime().getDate(), Toast.LENGTH_LONG).show();
        Date date = new Date(event.getStartTime().getTime().getYear() + 1900, event.getStartTime().getTime().getMonth() + 1, event.getStartTime().getTime().getDay() + 3 + 14);

        //FixturesTabsFragment fixturesTabsFragment = new FixturesTabsFragment(date);
    }

    private ArrayList<WeekViewEvent> getData() {
        getDataFromConnection();
        return events;
    }

    private void getDataFromConnection() {

/*                        modelCalendar = new ModelCalendar();

                        Calendar startTime = Calendar.getInstance();
                        startTime.set(modelCalendar.getYear_start(), modelCalendar.getMonth_start()-1, modelCalendar.getDay_start(), modelCalendar.getHours_start(), modelCalendar.getMinut_start());
                        Calendar endTime = (Calendar) startTime.clone();
                        endTime.set(modelCalendar.getYear_end(), modelCalendar.getMonth_end()-1, modelCalendar.getDay_end(), modelCalendar.getHours_end(), modelCalendar.getMinut_end());
                        WeekViewEvent event = new WeekViewEvent(modelCalendar.getId(), modelCalendar.getName(), startTime, endTime);
                        event.setColor(Color.parseColor(modelCalendar.getColor()));
                        events.add(event);*/



                    }


}
