package com.dzavorin.klink.services

import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

@Component
class DefaultKeyMapperService : KeyMapperService {

    private val map : MutableMap<String, String> = ConcurrentHashMap()

    override fun add(key: String, link: String): KeyMapperService.Add {
        return if (map.containsKey(key)) {
            KeyMapperService.Add.AlreadyExist(key)
        } else {
            map.put(key, link)
            KeyMapperService.Add.Success(key, link)
        }
    }

    override fun getLink(key: String): KeyMapperService.Get {
        return if (map.containsKey(key)) {
            KeyMapperService.Get.Link(map.get(key)!!)
        } else {
            KeyMapperService.Get.NotFound(key)
        }
    }
}