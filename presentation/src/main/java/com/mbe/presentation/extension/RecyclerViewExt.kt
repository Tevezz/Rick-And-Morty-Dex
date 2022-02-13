package com.mbe.presentation.extension

import androidx.recyclerview.widget.RecyclerView

internal inline fun <reified T> RecyclerView.getTypedAdapter(): T? = (adapter as? T)
