package com.dzavorin.klink.model.repositories

import com.dzavorin.klink.model.Link
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface LinkRepository : JpaRepository<Link, Long> {
    fun getById(id: Long?): Optional<Link>
    fun getByText(text: String?): Optional<Link>
}