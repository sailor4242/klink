package com.dzavorin.klink.services

import org.junit.Assert.assertEquals
import org.junit.Test

class DefaultKeyMapperServiceTest {

    val service: KeyMapperService = DefaultKeyMapperService()

    private val KEY : String = "sdf"
    private val LINK : String = "http://www.eveonline.com"
    private val LINK_NEW : String = "http://www.wow.com"

    @Test
    fun clinet_can_add_new_key_with_link() {
        assertEquals(KeyMapperService.Add.Success(KEY, LINK), service.add(KEY, LINK))
        assertEquals(KeyMapperService.Get.Link(LINK), service.getLink(KEY))
    }

    @Test
    fun client_can_not_add_existing_key() {
        service.add(KEY, LINK)
        assertEquals(KeyMapperService.Add.AlreadyExist(KEY), service.add(KEY, LINK))
        assertEquals(KeyMapperService.Get.Link(LINK), service.getLink(KEY))
    }

    @Test
    fun client_can_not_take_key_if_link_is_not_found_in_service() {
        assertEquals(KeyMapperService.Get.NotFound(KEY), service.getLink(KEY))
    }
}