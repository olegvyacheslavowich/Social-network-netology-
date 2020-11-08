package chat

import exceptions.MessageNotFoundException

data class Chat(
    val id: Int = 0,
    val firstOwnerId: Int = 0,
    val secondOwnerId: Int = 0,
    val messages: MutableList<Message> = mutableListOf()
) {}

fun Chat.addMessage(message: Message): Int {
    val messageId = if (this.messages.size == 0) 1 else this.messages.last().id + 1
    this.messages.add(message.copy(id = messageId))
    return messageId
}

fun Chat.deleteMessage(messageId: Int): Boolean {
    return when (val message = this.messages.find { it.id == messageId }) {
        null -> throw MessageNotFoundException(messageId)
        else -> messages.remove(message)
    }
}

fun Chat.getMessages(fromMessageId: Int, count: Int = 1): List<Message> {
    val foundMessages =
        messages.filter { it.id >= fromMessageId }
            .take(count)

    messages.forEachIndexed {index, message ->
        if (foundMessages.findLast { it.id == message.id } != null) {
            messages[index] = messages[index].copy(read = true)
        }
    }

    return foundMessages
}

fun Chat.getUnreadMessages(): List<Message> = messages.filter { !it.read }

fun Chat.haveNewMessage(userId: Int): Boolean {
    val message = messages.last()
    return message.recipientId == userId && !message.read

}

fun Chat.readMessages(): Message {
    messages.forEach {
        if (!it.read) {
            val index = messages.indexOf(it)
            messages[index] = messages[index].copy(read = true)
        }
    }

    return messages.last()
}