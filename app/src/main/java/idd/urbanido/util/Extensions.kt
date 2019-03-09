package ui.util

import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import idd.urbanido.BuildConfig

fun Any.logd(message: String){
    if (BuildConfig.DEBUG) Log.d(this::class.java.simpleName, message)
}
fun Fragment.snack(text: String) {
    this.view?.let { Snackbar.make(it, text, Snackbar.LENGTH_SHORT).show() }
}

//RecyclerView extensions
fun RecyclerView.linearManager() {
    this.layoutManager = LinearLayoutManager(context)
}

