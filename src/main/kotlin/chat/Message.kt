package chat

import java.time.LocalDateTime

data class Message(
    val id: Int = 0,
    val chatId: Int = 0,
    val senderId: Int = 0,
    val recipientId: Int = 0,
    val text: String = "",
    val date: LocalDateTime = LocalDateTime.now(),
    val read: Boolean = false
) {
}