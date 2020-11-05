package bruno.koster.myeleventhapp.extensions

import android.app.Activity
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.toast(message: String) {
    Toast.makeText(activity!!.applicationContext, message, Toast.LENGTH_LONG).show()
}

fun Activity.toast(message: String) {
    Toast.makeText(baseContext, message, Toast.LENGTH_LONG).show()
}

fun Activity.toast(message: Int) {
    Toast.makeText(baseContext, getText(message), Toast.LENGTH_LONG).show()
}