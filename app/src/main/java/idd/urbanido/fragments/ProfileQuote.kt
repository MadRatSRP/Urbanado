package idd.urbanido.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import idd.urbanido.R
import idd.urbanido.interfaces.fragments.ProfileQuoteMVP
import idd.urbanido.presenters.fragments.ProfileQuotePresenter
import idd.urbanido.repositories.ProfileQuoteRepository
import idd.urbanido.util.MyApplication
import kotlinx.android.synthetic.main.fragment_profile_quote.*
import ui.util.logd

class ProfileQuote: Fragment(), ProfileQuoteMVP.View {
    private var profileQuotePresenter: ProfileQuotePresenter? = null

    //var arrayListEntry: ArrayList<Entry>? = null
    //var arrayListString: ArrayList<String>? = null

    private var mChart: LineChart? = null

    //companion object { val instance = ProfileQuote() }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupMVP()

        val quoteId = arguments?.let { ProfileQuoteArgs.fromBundle(it).id }
        logd("ID акции получен: $quoteId")

        val myApplication = MyApplication.instance
        val token = myApplication.releaseToken()
        logd("Токен пользователя получен: $token")

        context?.let { quoteId?.let { it1 ->
            token?.let { it2 ->
                profileQuotePresenter?.getData(it, it1, it2) } } }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val title = arguments?.let { ProfileQuoteArgs.fromBundle(it).title }
        logd("Title акции получен: $title")

        (activity as AppCompatActivity).supportActionBar?.title = title
        val view = inflater.inflate(R.layout.fragment_profile_quote, container, false)

        /*mChart?.findViewById<LineChart>(idd.urbanido.R.id.chart)
        mChart?.setDrawGridBackground(false)
        //mChart?.setDescription("")
        mChart?.setTouchEnabled(true)
        mChart?.isDragEnabled = true
        mChart?.setScaleEnabled(true)
        mChart?.setPinchZoom(true)
        mChart?.marker
        val xl = mChart?.xAxis
        xl?.setAvoidFirstLastClipping(true)
        val leftAxis = mChart?.axisLeft
        leftAxis?.isInverted = true
        val rightAxis = mChart?.axisRight
        rightAxis?.isEnabled = false
        val l = mChart?.legend
        l?.form = Legend.LegendForm.LINE*/

        return view
    }

    override fun setupMVP() {
        profileQuotePresenter = ProfileQuotePresenter(this, ProfileQuoteRepository())
    }

    override fun showProfileQuote(entries: ArrayList<BarEntry>, labels: ArrayList<String>) {
        /*val set1 = LineDataSet(arrayListEntry, "NAV Data Value")
        set1.lineWidth = 1.5f
        set1.circleRadius = 4f
        val data = LineData(/*arrayListString,*/ set1)
        mChart?.data = data
        mChart?.invalidate()*/

        var barDataSet = BarDataSet(entries, "Cells")
        val data = BarData(labels, barDataSet)


        //var barDataSet = BarDataSet(entries, "Cells")
        //val data = BarData(labels, barDataSet)

        barChart.data = data




        barChart.setDescription("Set Bar Chart Description")  // set the description

        //barDataSet.setColors(ColorTemplate.COLORFUL_COLORS)
        barDataSet.color = context?.let { ContextCompat.getColor(it, R.color.colorAccent) }!!;

        barChart.animateY(5000)
    }
}