package org.tino.librarianbot.model

import javax.persistence.*

@Entity
@Table(name = "user")
data class UserEntity(

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = -1,

        @Column(name = "name", length = 50, nullable = false)
        var name: String = "",

        @Column(name = "surname", length = 70, nullable = false)
        var surname: String = "",

        @Column(name = "user_name", unique = true, length = 70, nullable = false)
        var userName: String = "",

        @Column(name = "is_admin", nullable = false)
        var isAdmin: Boolean = false
)