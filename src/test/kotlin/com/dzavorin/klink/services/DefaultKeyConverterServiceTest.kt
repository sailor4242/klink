package com.dzavorin.klink.services

import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

class DefaultKeyConverterServiceTest {

    val service : KeyConverterService = DefaultKeyConverterService()

    @Test
    fun given_id_must_be_convertable_both_ways() {
        val rand = Random()
        for (i in 0..1000L) {
            val initialId = Math.abs(rand.nextLong())
            val key = service.idToKey(initialId)
            val id = service.keyToId(key)
            assertEquals(initialId, id)
        }
    }
}