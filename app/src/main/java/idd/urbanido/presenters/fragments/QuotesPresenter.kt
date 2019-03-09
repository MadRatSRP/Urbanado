package idd.urbanido.presenters.fragments

import android.content.Context
import idd.urbanido.interfaces.fragments.QuotesMVP
import idd.urbanido.model.QuotesResponse

class QuotesPresenter(private var qv: QuotesMVP.View,
                     private var qr: QuotesMVP.Repository): QuotesMVP.Presenter {

    override fun updateQuotes(quotes: List<QuotesResponse>) {
        qv.showQuotes(quotes)
    }

    override fun getData(context: Context, token: String) {
        qr.getQuotesListObservable(context, token)?.subscribe ({ response->
            updateQuotes(response)
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