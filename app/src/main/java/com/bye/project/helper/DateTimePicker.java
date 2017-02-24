package com.bye.project.helper;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TabHost;

import com.bye.project.R;

import java.util.Date;

/**
 * Created by Bye Webster on 24/02/17.
 * Website : http://bye-webster.blogspot.com
 */
public class DateTimePicker extends DialogFragment {
    public static final String TAG_FRAG_DATE_TIME = "fragDateTime";
    private static final String KEY_DIALOG_TITLE = "dialogTitle";
    private static final String KEY_INIT_DATE = "initDate";
    private static final String TAG_DATE = "date";
    private static final String TAG_TIME = "time";
    private Context mContext;
    private ButtonClickListener mButtonClickListener;
    private OnDateFromSetListener mOnDateFromSetListener;
    private OnDateToSetListener mOnDateToSetListener;
    private Bundle mArgument;
    private DatePicker mDatePickerFrom;
    private DatePicker mDatePickerTo;
    // DialogFragment constructor must be empty
    public DateTimePicker() {
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity;
        mButtonClickListener = new ButtonClickListener();
    }
    /**
     *
     * @param dialogTitle Title of the DateTimePicker DialogFragment
     * @param initDate Initial Date and Time set to the Date and Time Picker
     * @return Instance of the DateTimePicker DialogFragment
     */
    public static DateTimePicker newInstance(CharSequence dialogTitle, Date initDate) {
        // Create a new instance of DateTimePicker
        DateTimePicker mDateTimePicker = new DateTimePicker();
        // Setup the constructor parameters as arguments
        Bundle mBundle = new Bundle();
        mBundle.putCharSequence(KEY_DIALOG_TITLE, dialogTitle);
        mBundle.putSerializable(KEY_INIT_DATE, initDate);
        mDateTimePicker.setArguments(mBundle);
        // Return instance with arguments
        return mDateTimePicker;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Retrieve Argument passed to the constructor
        mArgument = getArguments();
        // Use an AlertDialog Builder to initially create the Dialog
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(mContext);
        // Setup the Dialog
        mBuilder.setTitle(mArgument.getCharSequence(KEY_DIALOG_TITLE));
        mBuilder.setNegativeButton(android.R.string.no,mButtonClickListener);
        mBuilder.setPositiveButton(android.R.string.yes,mButtonClickListener);
        // Create the Alert Dialog
        AlertDialog mDialog = mBuilder.create();
        // Set the View to the Dialog
        mDialog.setView(
                createDateTimeView(mDialog.getLayoutInflater())
        );
        // Return the Dialog created
        return mDialog;
    }
    /**
     * Inflates the XML Layout and setups the tabs
     * @param layoutInflater Layout inflater from the Dialog
     * @return Returns a view that will be set to the Dialog
     */
    private View createDateTimeView(LayoutInflater layoutInflater) {
        // Inflate the XML Layout using the inflater from the created Dialog
        View mView = layoutInflater.inflate(R.layout.date_time_picker,null);
        // Extract the TabHost
        TabHost mTabHost = (TabHost) mView.findViewById(R.id.tab_host);
        mTabHost.setup();
        // Create Date From Tab and add to TabHost
        TabHost.TabSpec mDateFromTab = mTabHost.newTabSpec(TAG_DATE);
        mDateFromTab.setIndicator("Date From");
        mDateFromTab.setContent(R.id.date_from_content);
        mTabHost.addTab(mDateFromTab);
        // Create Date To Tab and add to TabHost
        TabHost.TabSpec mDateToTab = mTabHost.newTabSpec(TAG_DATE);
        mDateToTab.setIndicator("Date To");
        mDateToTab.setContent(R.id.date_to_content);
        mTabHost.addTab(mDateToTab);
        // Retrieve Date from Arguments sent to the Dialog
        DateTime mDateTime = new DateTime((Date) mArgument.getSerializable(KEY_INIT_DATE));
        // Initialize Date and Time Pickers
        mDatePickerFrom = (DatePicker) mView.findViewById(R.id.date_picker_from);
        mDatePickerFrom.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        mDatePickerTo = (DatePicker) mView.findViewById(R.id.date_picker_to);
        mDatePickerTo.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        // Return created view
        return mView;
    }
    /**
     * Sets the OnDateFromSetListener interface
     * @param onDateFromSetListener Interface that is used to send the Date and Time
     *               to the calling object
     */
    public void setOnDateFromSetListener(OnDateFromSetListener onDateFromSetListener) {
        mOnDateFromSetListener = onDateFromSetListener;
    }

    /**
     * Sets the OnDateToSetListener interface
     * @param onDateToSetListener Interface that is used to send the Date and Time
     *               to the calling object
     */
    public void setOnDateToSetListener(OnDateToSetListener onDateToSetListener) {
        mOnDateToSetListener = onDateToSetListener;
    }
    private class ButtonClickListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialogInterface, int result) {
            // Determine if the user selected Ok
            if(DialogInterface.BUTTON_POSITIVE == result) {
                /* Example to get day,month and year
                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth() + 1;
                int year = datePicker.getYear();
                */
                String from = String.valueOf(mDatePickerFrom.getYear()+"-"+(mDatePickerFrom.getMonth() + 1)+"-"+mDatePickerFrom.getDayOfMonth());
                String to = String.valueOf(mDatePickerTo.getYear()+"-"+(mDatePickerTo.getMonth() + 1)+"-"+mDatePickerTo.getDayOfMonth());

                    System.out.println("FROM DATE : " + from);
                    System.out.println("TO DATE : " + to);

                if(from!=null)
                    mOnDateFromSetListener.DateFromSet(from);
                if(to!=null)
                    mOnDateToSetListener.DateToSet(to);

            }
        }
    }
    /**
     * Interface for sending the Date From to the calling object
     */
    public interface OnDateFromSetListener {
        public void DateFromSet(String dateFrom);
    }
    /**
     * Interface for sending the Date To to the calling object
     */
    public interface OnDateToSetListener {
        public void DateToSet(String dateTo);
    }
}