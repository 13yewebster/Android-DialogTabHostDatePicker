package com.bye.project.helper;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.Date;

/**
 * Created by Bye Webster on 24/02/17.
 * Website : http://bye-webster.blogspot.com
 */
public class SimpleDateTimePicker {

    private CharSequence mDialogTitle;
    private Date mInitDate;
    private DateTimePicker.OnDateFromSetListener mOnDateFromSetListener;
    private DateTimePicker.OnDateToSetListener mOnDateToSetListener;
    private FragmentManager mFragmentManager;

    /**
     * Private constructor that can only be access using the make method
     * @param dialogTitle Title of the Date Time Picker Dialog
     * @param initDate Date object to use to set the initial Date and Time
     * @param onDateFromSetListener OnDateTimeSetListener interface
     * @param onDateToSetListener onDateToSetListener interface
     * @param fragmentManager Fragment Manager from the activity
     */
    private SimpleDateTimePicker(CharSequence dialogTitle, Date initDate,
                                 DateTimePicker.OnDateFromSetListener onDateFromSetListener,
                                 DateTimePicker.OnDateToSetListener onDateToSetListener,
                                 FragmentManager fragmentManager) {

        // Find if there are any DialogFragments from the FragmentManager
        FragmentTransaction mFragmentTransaction = fragmentManager.beginTransaction();
        Fragment mDateTimeDialogFrag = fragmentManager.findFragmentByTag(
                DateTimePicker.TAG_FRAG_DATE_TIME
        );

        // Remove it if found
        if(mDateTimeDialogFrag != null) {
            mFragmentTransaction.remove(mDateTimeDialogFrag);
        }
        mFragmentTransaction.addToBackStack(null);

        mDialogTitle = dialogTitle;
        mInitDate = initDate;
        mOnDateFromSetListener = onDateFromSetListener;
        mOnDateToSetListener = onDateToSetListener;
        mFragmentManager = fragmentManager;

    }

    /**
     * Creates a new instance of the SimpleDateTimePicker
     * @param dialogTitle Title of the Date Time Picker Dialog
     * @param initDate Date object to use to set the initial Date and Time
     * @param onDateFromSetListener OnDateFromSetListener interface
     * @param onDateToSetListener OnDateToSetListener interface
     * @param fragmentManager Fragment Manager from the activity
     * @return Returns a SimpleDateTimePicker object
     */
    public static SimpleDateTimePicker make(CharSequence dialogTitle, Date initDate,
                                            DateTimePicker.OnDateFromSetListener onDateFromSetListener,
                                            DateTimePicker.OnDateToSetListener onDateToSetListener,
                                            FragmentManager fragmentManager) {

        return new SimpleDateTimePicker(dialogTitle, initDate,
                onDateFromSetListener,onDateToSetListener, fragmentManager);

    }

    /**
     * Shows the DateTimePicker Dialog
     */
    public void show() {

        // Create new DateTimePicker
        DateTimePicker mDateTimePicker = DateTimePicker.newInstance(mDialogTitle,mInitDate);
        mDateTimePicker.setOnDateFromSetListener(mOnDateFromSetListener);
        mDateTimePicker.setOnDateToSetListener(mOnDateToSetListener);
        mDateTimePicker.show(mFragmentManager, DateTimePicker.TAG_FRAG_DATE_TIME);

    }
}