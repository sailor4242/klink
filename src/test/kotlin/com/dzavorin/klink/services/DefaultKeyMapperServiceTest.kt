package com.dzavorin.klink.services

import com.dzavorin.klink.model.Link
import com.dzavorin.klink.model.repositories.LinkRepository
import com.dzavorin.klink.whenever
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.util.*

class DefaultKeyMapperServiceTest {

    @InjectMocks val service: KeyMapperService = DefaultKeyMapperService()

    @Mock lateinit var converter : KeyConverterService

    @Mock lateinit var repo : LinkRepository

    private val KEY_A: String = "abc"
    private val ID_A: Long = 10000000L
    private val KEY_B: String = "cde"
    private val ID_B: Long = 10000001L
    private val KEY : String = "sdf"
    private val LINK_A: String = "http://google.com"
    private val LINK_B: String = "http://apple.com"
    private val LINK_OBJ_A: Link = Link(LINK_A, ID_A)
    private val LINK_OBJ_B: Link = Link(LINK_B, ID_B)

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        whenever(converter.keyToId(KEY_A)).thenReturn(ID_A)
        whenever(converter.idToKey(ID_A)).thenReturn(KEY_A)
        whenever(converter.keyToId(KEY_B)).thenReturn(ID_B)
        whenever(converter.idToKey(ID_B)).thenReturn(KEY_B)

        whenever(repo.getById(Mockito.anyObject())).thenReturn(Optional.empty())
        whenever(repo.getById(ID_A)).thenReturn(Optional.of(LINK_OBJ_A))
        whenever(repo.getById(ID_B)).thenReturn(Optional.of(LINK_OBJ_B))
        whenever(repo.getByText(LINK_A)).thenReturn(Optional.of(LINK_OBJ_A))
        whenever(repo.getByText(LINK_B)).thenReturn(Optional.of(LINK_OBJ_B))
        whenever(repo.save(Link(LINK_A))).thenReturn(LINK_OBJ_A)
        whenever(repo.save(Link(LINK_B))).thenReturn(LINK_OBJ_B)
    }

    @Test
    fun client_can_add_links() {
        val keyA = service.add(LINK_A)
        assertEquals(KeyMapperService.Get.Link(LINK_A), service.getLink(keyA))
        val keyB = service.add(LINK_B)
        assertEquals(KeyMapperService.Get.Link(LINK_B), service.getLink(keyB))
        assertNotEquals(keyA, keyB)
    }

    @Test
    fun client_can_not_take_key_if_link_is_not_found_in_service() {
        assertEquals(KeyMapperService.Get.NotFound(KEY), service.getLink(KEY))
    }
}