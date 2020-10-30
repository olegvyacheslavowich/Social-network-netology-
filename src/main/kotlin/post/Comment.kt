package post

import attachment.Attachment
import java.time.LocalDateTime

data class Comment(
    val id: Int = 0,
    val fromId: Int = 0,
    val postId: Int = 0,
    val date: LocalDateTime = LocalDateTime.now(),
    val text: String = "",
    val replyToUser: Int? = null,
    val replyToComment: Int? = null,
    val attachments: Array<Attachment> = emptyArray(),
    val parentStack: Array<Int> = emptyArray(),
    val thread: Thread = Thread()

) {
}