package com.dzavorin.klink.controllers

import com.dzavorin.klink.services.KeyMapperService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
class AddController {

    @Value("\${klink.prefix}")
    private lateinit var prefix: String

    @Autowired lateinit var service : KeyMapperService

    @PostMapping("add")
    @ResponseBody
    fun addRest(@RequestBody request : AddRequest) =
            ResponseEntity.ok(AddResponse(request.link, service.add(request.link)))!!

    @PostMapping("addhtml")
    fun addHtml(model: Model, @RequestParam(value = "link", required = true) link: String) : String {
        val result = add(link)
        model.addAttribute("link", result.link)
        model.addAttribute("keyed", prefix + result.key)
        return "result"
    }

    private fun add(link: String) = AddResponse(link, service.add(link))

    data class AddRequest(val link : String)
    data class AddResponse(val link : String, val key : String)
}