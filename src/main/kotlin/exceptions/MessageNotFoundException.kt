package exceptions

import java.lang.RuntimeException

class MessageNotFoundException(
    messageId: Int
) : RuntimeException(
    "Chat with id $messageId not found"

) {
}