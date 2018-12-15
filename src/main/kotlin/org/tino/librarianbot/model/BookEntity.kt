package org.tino.librarianbot.model

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "book")
data class BookEntity(

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = -1,

        @Column(name = "name", length = 100, nullable = false)
        var name: String = "",

        @Column(name = "author", length = 100, nullable = false)
        var author: String = "",

        @Column(name = "description", nullable = false)
        var description: String = "",

        @Column(name = "editorial", length = 100, nullable = false)
        var editorial: String = "",

        @Column(name = "imprint", length = 100, nullable = false)
        var imprint: String = "",

        @Column(name = "date", nullable = false)
        var date: Date = Date(),

        @Column(name = "isbn13", length = 13, nullable = false)
        var isbn13: String = "",

        @Column(name = "url", nullable = false)
        var url: String = "",

        @Column(name = "isDigital", nullable = false)
        var isDigital: Boolean = false
)