package note

import java.time.LocalDateTime

data class Note(
    val id: Int = 0,
    val title: String = "",
    val text: String = "",
    val date: LocalDateTime = LocalDateTime.now()
) {
}