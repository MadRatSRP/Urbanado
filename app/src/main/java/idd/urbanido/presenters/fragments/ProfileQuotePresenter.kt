package idd.urbanido.presenters.fragments

import android.content.Context
import android.widget.TextView
import com.github.mikephil.charting.data.BarEntry
import idd.urbanido.interfaces.fragments.ProfileQuoteMVP
import ui.util.logd
import java.io.IOException


class ProfileQuotePresenter(private var pv: ProfileQuoteMVP.View,
                            private var pr: ProfileQuoteMVP.Repository): ProfileQuoteMVP.Presenter {
    override fun updateProfileQuote(entries: ArrayList<BarEntry>, labels: ArrayList<String>) {
        pv.drawChart(entries, labels)
    }

    fun getPercent(context: Context, token: String, id: String,
                   start_date: TextView, finish_date: TextView) {
        /*var percentResponse = PercentResponse(start_date.text.toString(),
                                              finish_date.text.toString())*/
        val kek = start_date.text.toString()
        logd("Здаровеньки булы$kek")


        getPercentStatistic(context, token, id,
                            start_date.text.toString(),
                            finish_date.text.toString())
    }

    override fun getData(context: Context, id: String, token: String) {
        pr.getProfileQuoteObservable(context, id, token)?.subscribe ({ response->
            pv.showSnack("Данные для графика успешно получены и обработаны")
            //updateProfileQuote(response, response.date)
            //updateProfileQuote(response)
            //logd("Запрос успешно получен")
            //sv.showSnack("Успешно получены данные")

            //val avprice: Double,
            //val date: String

            val entries = ArrayList<BarEntry>()
            var labels = ArrayList<String>()

            for (i in 0 until response.size) {
                entries.add(BarEntry(response[i].avprice.toFloat(), i))
                labels.add(response[i].date)
            }

            updateProfileQuote(entries, labels)


            /*if (response) {
                updateShares(response.shares)
                logd("Запрос успешно получен")
                sv.showSnack("Успешно получены данные")
            } else sv.showSnack("Произошла ошибка при получении запроса")*/
        }, { error ->
            error.printStackTrace()
            pv.showSnack("Проверьте подключение к интернету.")
        })
    }

    fun getPercentStatistic(context: Context, token: String, id: String,
                            start_date: String, finish_date: String) {
        pr.getQuotePercentObservable(context, token, id, start_date, finish_date)?.subscribe ({ response->
            pv.showSnack("Ваши данные были успешно отправлены")
            val entries = ArrayList<BarEntry>()
            var labels = ArrayList<String>()

            var array = response.date_instruments

            for (i in 0 until array.size) {
                entries.add(BarEntry(array[i].avprice.toFloat(), i))
                labels.add(array[i].date)
            }

            updateProfileQuote(entries, labels)

            pv.returnPercent(response.result_str)
        }, {error->
            if (error is IOException) {
                pv.showSnack("Проверьте, включен ли интернет")
            } else {
                pv.showSnack("Произошла ошибка при отправке данных")
                error.printStackTrace()
            }

        })
    }
}