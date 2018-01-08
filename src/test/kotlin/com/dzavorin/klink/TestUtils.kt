package com.dzavorin.klink

import org.mockito.Mockito

fun <T> whenever(call:T) = Mockito.`when`(call)