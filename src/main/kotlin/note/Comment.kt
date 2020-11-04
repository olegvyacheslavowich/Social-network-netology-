package note

import java.time.LocalDateTime

data class Comment(
    val id: Int = 0,
    val noteId: Int = 0,
    val message: String = "",
    val date: LocalDateTime = LocalDateTime.now()
) {
}