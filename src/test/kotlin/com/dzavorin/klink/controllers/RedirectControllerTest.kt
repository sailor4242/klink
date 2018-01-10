package com.dzavorin.klink.controllers

import com.dzavorin.klink.KlinkApplication
import com.dzavorin.klink.services.KeyMapperService
import com.dzavorin.klink.whenever
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver

@TestPropertySource(locations = arrayOf("classpath:repositories-test.properties"))
@RunWith(SpringRunner::class)
@SpringBootTest(classes = arrayOf(KlinkApplication::class))
class RedirectControllerTest {

    @Autowired lateinit var webApplicationContext : WebApplicationContext

    lateinit var mockMvc : MockMvc

    @Mock lateinit var service : KeyMapperService

    @Autowired @InjectMocks lateinit var controller : RedirectController

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build()
        whenever(service.getLink(PATH)).thenReturn(KeyMapperService.Get.Link(HEADER_VALUE))
        whenever(service.getLink(BAD_PATH)).thenReturn(KeyMapperService.Get.NotFound(BAD_PATH))

    }

    private val PATH = "sdf"
    private val REDIRECT_STATUS : Int = 302
    private val HEADER_NAME = "Location"
    private val HEADER_VALUE = "http://www.eveonline.com"

    @Test
    fun controller_must_redirect_us_when_request_is_successfull() {
        mockMvc.perform(get("/$PATH"))
                .andExpect(status().`is`(REDIRECT_STATUS))
                .andExpect(header().string(HEADER_NAME, HEADER_VALUE))
    }

    private val BAD_PATH = "sdfsdf"
    private val NOT_FOUND : Int = 404

    @Test
    fun controller_must_return_404_if_bad_key() {
        mockMvc.perform(get("/$BAD_PATH"))
                .andExpect(status().`is`(NOT_FOUND))
    }

    @Ignore
    @Test
    fun home_works_fine() {
        mockMvc.perform(get("/")).andExpect(MockMvcResultMatchers.view().name("home"))
    }
}