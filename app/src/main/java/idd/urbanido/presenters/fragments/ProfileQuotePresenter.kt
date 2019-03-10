package idd.urbanido.presenters.fragments

import android.content.Context
import android.widget.EditText
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import idd.urbanido.interfaces.fragments.ProfileQuoteMVP
import idd.urbanido.model.profile_quote.PercentResponse


class ProfileQuotePresenter(private var pv: ProfileQuoteMVP.View,
                            private var pr: ProfileQuoteMVP.Repository): ProfileQuoteMVP.Presenter {
    override fun updateProfileQuote(entries: ArrayList<BarEntry>, labels: ArrayList<String>) {
        pv.showProfileQuote(entries, labels)
    }

    fun getPercent(context: Context, token: String, id: String,
                   start_date: EditText, finish_date: EditText) {
        /*var percentResponse = PercentResponse(start_date.text.toString(),
                                              finish_date.text.toString())*/
        getPercentStatistic(context, token, id,
                            start_date.text.toString(),
                            finish_date.text.toString())
    }

    override fun getData(context: Context, id: String, token: String) {
        pr.getProfileQuoteObservable(context, id, token)?.subscribe ({ response->
            //updateProfileQuote(response, response.date)
            //updateProfileQuote(response)
            //logd("Запрос успешно получен")
            //sv.showSnack("Успешно получены данные")

            //val avprice: Double,
            //val date: String

            val entries = ArrayList<BarEntry>()
            var labels = ArrayList<String>()

            /*for (i in 0..response.size) {
                arrayListString?.add(response[i].date)

                //logd("HI" + response[i].date)
                //arrayListString?.get(i)?.let { logd(it) }
            }*/

            for (i in 0 until response.size) {
                entries.add(BarEntry(response[i].avprice.toFloat(), i))
                labels.add(response[i].date)
            }


            //var barDataSet = BarDataSet(entries, "Cells")
            //val data = BarData(labels, barDataSet)

            updateProfileQuote(entries, labels)

            var i: Int? = null
            var value: Double? = null

            /*var jsonArray: JSONArray = JSONArray(response)
            for(i in 1..jsonArray.length()){
                var quote: JSONObject = jsonArray.getJSONObject(i)
                var date: String = quote.getString("date")
                logd(date)
                arrayListString?.add(date)
            }*/

            /*val jsonarray = JSONArray(response)
            for (i in 3 until jsonarray.length()) {
                val jsonobject = jsonarray.getJSONObject(i)
                //val name = jsonobject.getString("name")
                //val url = jsonobject.getString("url")
                var date: String = jsonobject.getString("date")
                logd(date)
                arrayListString?.add(date)
            }*/

            /*var json = Gson().toJson(response)
            logd("Its JSON, baby!$json")

            var jsonArray = JSONArray(json)
            for (jsonIndex in 0..(jsonArray.length() - 1 )){

                arrayListString?.add(jsonArray.getJSONObject(jsonIndex).getString("date").toString())
                logd(jsonArray.getJSONObject(jsonIndex).getString("date").toString())
                //Log.d("JSON", jsonArray.getJSONObject(jsonIndex).getString("date"))
                //arrayListString?.add(jsonArray.getJSONObject(jsonIndex).getString("date"))
                //logd(arrayListString?.get(0).toString())
                //Log.d("JSON", jsonArray.getJSONObject(jsonIndex).toString())
            }*/


            /*if (response) {
                updateShares(response.shares)
                logd("Запрос успешно получен")
                sv.showSnack("Успешно получены данные")
            } else sv.showSnack("Произошла ошибка при получении запроса")*/
        }, { error ->
            error.printStackTrace()
            //sv.showSnack("Проверьте подключение к интернету.")
        })
    }

    fun getPercentStatistic(context: Context, token: String, id: String,
                            start_date: String, finish_date: String) {
        pr.getQuotePercentObservable(context, token, id, start_date, finish_date)?.subscribe ({ response->

        }, {error->
            error.printStackTrace()
        })
    }
}