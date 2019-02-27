package ui.form

import android.Manifest
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter

import java.io.FileNotFoundException
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

import idd.urbanido.R

import android.app.Activity.RESULT_OK
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import idd.urbanido.model.EventResponse
import idd.urbanido.network.APIService
import idd.urbanido.network.ApiUtils
import kotlinx.android.synthetic.main.fragment_form.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FormView : Fragment() {

    var apiService: APIService? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        apiService = ApiUtils.apiService

        show_address.text = address

        val adapter = ArrayAdapter.createFromResource(context!!,
                R.array.events_values, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        events_spinner.adapter = adapter

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

        showTime.setOnClickListener {v->
            val mcurrentTime = Calendar.getInstance()
            val hour = mcurrentTime.get(Calendar.HOUR_OF_DAY)
            val minute = mcurrentTime.get(Calendar.MINUTE)
            val mTimePicker: TimePickerDialog
            mTimePicker = TimePickerDialog(context,
                    { _, selectedHour, selectedMinute ->

                        showTime.text = v.context.getString(R.string.showTime,
                                                            selectedHour,
                                                            selectedMinute)
                    },
                    hour, minute, true)
            mTimePicker.setTitle("Select Time")
            mTimePicker.show()
        }

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

        upload.setOnClickListener{v->

            apiService?.postEvent(edit_description.text.toString(),
                                 edit_title.text.toString(),
                                 show_address.text.toString(),
                                 koord,
                                 showDate.text.toString(),
                                 events_spinner.toString(),
                                 "picture")?.enqueue(object : Callback<EventResponse> {

                override fun onResponse(call: Call<EventResponse>, response: Response<EventResponse>) {

                    Log.i("", "post submitted to API." + response.body())

                    if (response.isSuccessful) {

                        Log.i("", "post registration to API" + response.body().toString())
                        Snackbar.make(v, "Форма была успешно отправлена", Snackbar.LENGTH_LONG)
                                .show()
                    }
                    else Snackbar.make(v, "Произошла ошибка", Snackbar.LENGTH_SHORT)
                                 .show()
                }

                override fun onFailure(call: Call<EventResponse>, t: Throwable) {
                    t.printStackTrace()
                    /*val snackbar = Snackbar.make(v, "Произошла ошибка",
                                   Snackbar.LENGTH_LONG).show()*/
                }
            })
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
    }
}