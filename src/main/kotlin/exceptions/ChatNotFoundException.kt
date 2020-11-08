package exceptions

class ChatNotFoundException(
    chatId: Int
) : RuntimeException(
    "Chat with id $chatId not found"
) {
}