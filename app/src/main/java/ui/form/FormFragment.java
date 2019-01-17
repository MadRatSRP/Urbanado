package ui.form;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import idd.urbanido.R;

import static android.app.Activity.RESULT_OK;

public class FormFragment extends Fragment {

    Spinner spinner;
    EditText edit_title;
    EditText edit_description;
    TextView show_address;
    TextView showDate;
    TextView showTime;
    Button load_image;
    Button upload;
    ImageView imageView;
    Bitmap bitmapImage;

    Button setTime;
    Button setDate;

    int mYear, mMonth, mDay;

    public static String koord;
    public static String address;

    private Calendar dateAndTime = Calendar.getInstance();
    final Calendar newCalendar = Calendar.getInstance();

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        load_image.setOnClickListener((View v) -> {
            if(ActivityCompat.checkSelfPermission(getActivity(),
                    Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            {
                requestPermissions(
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        2000);
            }
            else {
                startGallery();
            }
        });
        showDate.setOnClickListener((View v) -> {
            Calendar mcurrentDate = Calendar.getInstance();
            mYear = mcurrentDate.get(Calendar.YEAR);
            mMonth = mcurrentDate.get(Calendar.MONTH);
            mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog mDatePicker = new DatePickerDialog(getContext(),
                    (datepicker, selectedyear, selectedmonth, selectedday) -> {

                Calendar myCalendar = Calendar.getInstance();
                myCalendar.set(Calendar.YEAR, selectedyear);
                myCalendar.set(Calendar.MONTH, selectedmonth);
                myCalendar.set(Calendar.DAY_OF_MONTH, selectedday);
                String myFormat = "dd/MM/yy"; //Change as you need
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.FRANCE);
                showDate.setText(sdf.format(myCalendar.getTime()));

                mDay = selectedday;
                mMonth = selectedmonth;
                mYear = selectedyear;
            }, mYear, mMonth, mDay);
            mDatePicker.show();
        });
        showTime.setOnClickListener((View v) -> {
            Calendar mcurrentTime = Calendar.getInstance();
            int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
            int minute = mcurrentTime.get(Calendar.MINUTE);
            TimePickerDialog mTimePicker;
            mTimePicker = new TimePickerDialog(getContext(),
                    (timePicker, selectedHour, selectedMinute) ->

                    showTime.setText( selectedHour + ":" + selectedMinute),
                    hour, minute, true);
            mTimePicker.setTitle("Select Time");
            mTimePicker.show();
        });


    }

    private void startGallery() {
        Intent cameraIntent = new Intent(Intent.ACTION_GET_CONTENT);
        cameraIntent.setType("image/*");
        if (cameraIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(cameraIntent, 1000);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super method removed
        if (resultCode == RESULT_OK) {
            if (requestCode == 1000) {
                Uri returnUri = data.getData();
                try {
                    bitmapImage = MediaStore.Images.Media.getBitmap(getActivity()
                            .getApplicationContext().getContentResolver(), returnUri);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    imageView.setImageBitmap(bitmapImage);
                }
            }
        }
    }

    public static FormFragment newInstance() {
        Bundle args = new Bundle();
        FormFragment fragment = new FormFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.form);
        View view = inflater.inflate(R.layout.fragment_form, container, false);

        /*final TextView coordinatesView = (TextView)findViewById(R.id.coordinator);
        coordinatesView.setText(koord);*/

        edit_title = view.findViewById(R.id.edit_title);
        edit_description = view.findViewById(R.id.edit_description);
        show_address = view.findViewById(R.id.show_address);
        showDate = view.findViewById(R.id.showDate);
        showTime = view.findViewById(R.id.showTime);
        imageView = view.findViewById(R.id.image_container);
        spinner = view.findViewById(R.id.events_spinner);


        load_image = view.findViewById(R.id.load_image);
        upload = view.findViewById(R.id.upload);

        show_address.setText(address);
        // setInitialDateTime();


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.events_values, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        /*upload.setOnClickListener((View v) -> {
            //uploadData();
        });*/
        return view;
    }
}
