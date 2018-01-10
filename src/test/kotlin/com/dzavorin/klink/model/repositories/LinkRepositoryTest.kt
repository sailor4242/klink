package com.dzavorin.klink.model.repositories

import com.dzavorin.klink.model.AbstractRepositoryTest
import com.dzavorin.klink.model.Link
import com.github.springtestdbunit.annotation.DatabaseOperation
import com.github.springtestdbunit.annotation.DatabaseSetup
import com.github.springtestdbunit.annotation.DatabaseTearDown
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import java.util.*
import org.hamcrest.Matchers.*
import org.hamcrest.MatcherAssert.assertThat

@DatabaseSetup(LinkRepositoryTest.DATASET)
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = LinkRepositoryTest.DATASET)
class LinkRepositoryTest : AbstractRepositoryTest() {

    @Autowired lateinit var repository : LinkRepository

    @Test
    fun find_one_existing() {
        val got : Optional<Link> = repository.getById(LINK_1_ID)
        assertThat(got.isPresent, equalTo(true))
        val link = got.get()
        assertThat(link, equalTo(Link(LINK_1_TEXT, LINK_1_ID)))
    }

    @Test
    fun find_one_not_existing() {
        val got : Optional<Link> = repository.getById(LINK_NOT_FOUND)
        assertThat(got.isPresent, equalTo(false))
    }

    @Test
    fun save_new() {
        val toBeSaved : Link = Link(LINK_TBS_TEXT)
        val got : Link = repository.save(toBeSaved)
        val list : List<Link> = repository.findAll()
        assertThat(list, hasSize(4))
        assertThat(got.text, equalTo(LINK_TBS_TEXT))
    }

    companion object {
        const val DATASET = "classpath:datasets/link-table.xml"
        private val LINK_1_ID : Long = 100500
        private val LINK_1_TEXT : String = "http://www.eveonline.com"
        private val LINK_TBS_TEXT : String = "http://www.ru"
        private val LINK_NOT_FOUND : Long = 1L
    }
}