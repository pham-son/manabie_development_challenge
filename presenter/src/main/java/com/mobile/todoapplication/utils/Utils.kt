package com.mobile.todoapplication.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.mobile.todoapplication.R
import com.mobile.todoapplication.utils.debounce.DebouncedOnClickListener
import java.text.SimpleDateFormat
import java.util.*

/***
 * Created by Phạm Sơn on 6/12/2022
 * Copyright (c) 2022 Propzy. All rights reserved.
 * Email: pson2900@gmail.com
 */
/**
 * View Action Click Extension
 */
fun View.onDebounceClick(callback: (view: View) -> Unit) = this.setOnClickListener(DebouncedOnClickListener { callback.invoke(it) })

/**
 * List Extension
 */
fun <T> List<T>.toArrayList(): ArrayList<T> = ArrayList<T>().apply { addAll(this@toArrayList) }

/**
 * Pattern: HH:mm dd/MM/yyyy
 */
fun Calendar.formatTimeDate(): String {
    return this.toString("HH:mm - dd/MM/yyyy")
}

/**
 * Convert Long to Calendar
 */
fun Long.toCalendar() = Calendar.getInstance().also {
    it.timeInMillis = this
}

/**
 * Format: HH:mm dd/MM/yyyy
 */
fun Calendar.toString(pattern: String): String {
    val dateFormat = SimpleDateFormat(pattern, Locale.getDefault())
    return dateFormat.format(time)
}

fun Context.showToast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun Context.showToast(resource: Int) = Toast.makeText(this, getString(resource), Toast.LENGTH_SHORT).show()

fun Context.showDialog(
    title: String, message: String,
    titleConfirmButton: String = getString(R.string.common_ok), titleCancelButton: String = getString(R.string.common_cancel),
    onConfirmed: (() -> Unit)? = null, onCanceled: (() -> Unit)? = null
) {
    val dialog = AlertDialog.Builder(this).apply {
        setTitle(title)
        setMessage(message)
        setPositiveButton(titleConfirmButton) { _, _ -> onConfirmed?.invoke() }
        setNegativeButton(titleCancelButton) { _, _ ->
            onCanceled?.invoke()
            this.setOnDismissListener { p0 -> p0.dismiss() }
        }
    }
    dialog.show()
}