package com.dzavorin.klink.services

import org.springframework.stereotype.Component

@Component
class DefaultKeyConverterService : KeyConverterService {

    private val chars = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890-_".toCharArray()

    private val charToLong = (0 until chars.size).map { i -> Pair(chars[i], i.toLong()) }.toMap()

    override fun idToKey(id: Long): String {
        var n = id
        val builder = StringBuilder()
        while (n != 0L) {
            builder.append(chars[(n % chars.size).toInt()])
            n /= chars.size
        }
        return builder.reverse().toString()
    }

    override fun keyToId(key: String): Long = key
            .map { c -> charToLong[c]!! }
            .fold(0L, { a,b -> a * chars.size + b })

}