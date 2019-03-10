package idd.urbanido.presenters.fragments

import android.content.Context
import idd.urbanido.interfaces.fragments.ProfileQuoteMVP
import idd.urbanido.model.ProfileQuoteResponse
import ui.util.logd

class ProfileQuotePresenter(private var pv: ProfileQuoteMVP.View,
                            private var pr: ProfileQuoteMVP.Repository): ProfileQuoteMVP.Presenter {
    override fun updateProfileQuote(list: List<ProfileQuoteResponse>) {
        //pv.showProfileQuote(avprice.toString(), date)
        //logd(list.toString())
    }

    override fun getData(context: Context, id: String, token: String) {
        pr.getProfileQuoteObservable(context, id, token)?.subscribe ({ response->
            //updateProfileQuote(response, response.date)
            updateProfileQuote(response)
            //logd("Запрос успешно получен")
            //sv.showSnack("Успешно получены данные")

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
}