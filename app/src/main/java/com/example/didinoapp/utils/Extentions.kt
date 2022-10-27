package com.example.didinoapp.utils

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import com.example.didinoapp.R
import com.example.didinoapp.view.fragments.detail.DetailFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


fun ProgressBar.show() {
    visibility = View.VISIBLE
}

fun ProgressBar.hide() {
    visibility = View.VISIBLE
}

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Context.toastShort(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.toastLong(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

val handler = android.os.Handler()
fun setPostDelayed(doing: Runnable, time: Long) {
    handler.postDelayed(doing, time)
}

fun setStatusColorSplashScreen(window: Window, context: Context) {
    window.statusBarColor = ContextCompat.getColor(context, R.color.black)
}

fun setStatusColorHomeScreen(window: Window, context: Context) {
    window.statusBarColor = ContextCompat.getColor(context, R.color.main_Color)
}

fun viewGoneAnimationForBottomNav(context: Context, activity: Activity) {
    val view = activity.findViewById<BottomNavigationView>(R.id.bottom_Navigation)
    val animationUtils = AnimationUtils.loadAnimation(context, R.anim.slide_down)
    view.visibility = View.GONE
    view.startAnimation(animationUtils)
}

fun viewVisibleAnimationForBottomNav(context: Context, activity: Activity) {
    val view = activity.findViewById<BottomNavigationView>(R.id.bottom_Navigation)
    val animationUtils = AnimationUtils.loadAnimation(context, R.anim.slide_up)
    view.visibility = View.VISIBLE
    view.startAnimation(animationUtils)
}

fun showDialogAccountForFragments(context: Context) {
    val dialog = Dialog(context)
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dialog.setCancelable(false)
    dialog.setCanceledOnTouchOutside(true)
    dialog.setContentView(R.layout.slide_account_item)
    dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    dialog.window!!.attributes.windowAnimations = R.style.dialog_animation
    dialog.window!!.setGravity(Gravity.BOTTOM)

    dialog.show()
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}