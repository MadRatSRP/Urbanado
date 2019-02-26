package ui.form

import android.Manifest
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView

import java.io.FileNotFoundException
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

import idd.urbanido.R

import android.app.Activity.RESULT_OK
import kotlinx.android.synthetic.main.fragment_form.*

class FormFragment : Fragment() {

    //internal var bitmapImage: Bitmap

    //val bitmap:Bitmap

    //private val dateAndTime = Calendar.getInstance()
    //internal val newCalendar = Calendar.getInstance()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        show_address.text = address

        val adapter = ArrayAdapter.createFromResource(context!!,
                R.array.events_values, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        events_spinner.adapter = adapter

        load_image.setOnClickListener {
            if (ActivityCompat.checkSelfPermission(activity!!,
                            Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(
                        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                        2000)
            } else {
                startGallery()
            }
        }

        upload.setOnClickListener{
            //uploadData();
        }

        showDate.setOnClickListener {
            val mcurrentDate = Calendar.getInstance()
            var mYear = mcurrentDate.get(Calendar.YEAR)
            var mMonth = mcurrentDate.get(Calendar.MONTH)
            var mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH)

            val mDatePicker = DatePickerDialog(context!!,
                    { _, selectedyear, selectedmonth, selectedday ->

                        val myCalendar = Calendar.getInstance()
                        myCalendar.set(Calendar.YEAR, selectedyear)
                        myCalendar.set(Calendar.MONTH, selectedmonth)
                        myCalendar.set(Calendar.DAY_OF_MONTH, selectedday)
                        val myFormat = "dd/MM/yy" //Change as you need
                        val sdf = SimpleDateFormat(myFormat, Locale.FRANCE)
                        showDate.text = sdf.format(myCalendar.time)

                        mDay = selectedday
                        mMonth = selectedmonth
                        mYear = selectedyear
                    }, mYear, mMonth, mDay)
            mDatePicker.show()
        }

        showTime.setOnClickListener {
            val mcurrentTime = Calendar.getInstance()
            val hour = mcurrentTime.get(Calendar.HOUR_OF_DAY)
            val minute = mcurrentTime.get(Calendar.MINUTE)
            val mTimePicker: TimePickerDialog
            mTimePicker = TimePickerDialog(context,
                    { _, selectedHour, selectedMinute ->

                        showTime.text = selectedHour.toString() + ":" + selectedMinute
                    },
                    hour, minute, true)
            mTimePicker.setTitle("Select Time")
            mTimePicker.show()
        }
    }

    private fun startGallery() {
        val cameraIntent = Intent(Intent.ACTION_GET_CONTENT)
        cameraIntent.type = "image/*"
        if (cameraIntent.resolveActivity(activity!!.packageManager) != null) {
            startActivityForResult(cameraIntent, 1000)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == RESULT_OK) {
            if (requestCode == 1000) {
                val returnUri = data!!.data
                var bitmap:Bitmap? = null
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(activity!!
                            .applicationContext.contentResolver, returnUri)
                } catch (ex: FileNotFoundException) {
                    ex.printStackTrace()
                } catch (e: IOException) {
                    e.printStackTrace()
                } finally {
                    image_container.setImageBitmap(bitmap)
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        (activity as AppCompatActivity).supportActionBar!!.setTitle(R.string.form)
        val view = inflater.inflate(R.layout.fragment_form, container, false)

        /*final TextView coordinatesView = (TextView)findViewById(R.id.coordinator);
        coordinatesView.setText(koord);*/

        return view
    }

    companion object {

        var koord: String? = null
        var address: String? = null

        fun newInstance(): FormFragment {
            val args = Bundle()
            val fragment = FormFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
