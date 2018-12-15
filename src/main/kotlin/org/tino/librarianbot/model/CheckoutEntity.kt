package org.tino.librarianbot.model

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "checkout")
data class CheckoutEntity(

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = -1,

        @Column(name = "user_id", nullable = false)
        var userId: Long = -1,

        @Column(name = "book_id", nullable = false)
        var bookId: Long = -1,

        @Column(name = "date", nullable = false)
        var date: Date = Date()

//        @Enumerated(EnumType.STRING)
//        @Column(name = "status", length = 30, nullable = false)
//        var status: Enum<Status> = Status.WITHDRAWN
)