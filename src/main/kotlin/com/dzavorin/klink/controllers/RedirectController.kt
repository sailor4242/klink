package com.dzavorin.klink.controllers

import com.dzavorin.klink.services.KeyMapperService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import javax.servlet.http.HttpServletResponse

@Controller
class RedirectController {

    @Autowired lateinit var service: KeyMapperService

    @GetMapping("/")
    fun home() = "home"

    @GetMapping("{key}")
    fun redirect(@PathVariable("key") key: String, response: HttpServletResponse) {
        val result = service.getLink(key)
        when (result) {
            is KeyMapperService.Get.Link -> {
                response.setHeader(HEADER_NAME, result.link)
                response.status = 302
            }
            is KeyMapperService.Get.NotFound -> {
                response.status = 404
            }
        }
    }

    companion object {
        private val HEADER_NAME = "Location"
    }
}