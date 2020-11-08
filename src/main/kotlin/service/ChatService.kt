package service

import chat.*
import exceptions.ChatNotFoundException
import exceptions.NewMessageNotFoundException

object ChatService {

    private val chats: MutableList<Chat> = mutableListOf()

    fun sendMessage(senderId: Int, recipientId: Int, message: String): Message {

        var chatId = if (chats.size == 0) 1 else chats[chats.lastIndex].id + 1
        val currentChat =
            when (val chat = chats.find { it.firstOwnerId == recipientId || it.secondOwnerId == recipientId }) {
                null -> {
                    val newChat = Chat(chatId, senderId, recipientId)
                    chats.add(newChat)
                    newChat
                }
                else -> {
                    chatId = chat.id
                    chat
                }
            }
        val newMessage = Message(chatId = chatId, senderId = senderId, recipientId = recipientId, text = message)
        currentChat.addMessage(newMessage)
        return newMessage
    }

    fun readMessages(chatId: Int): Message  = getChat(chatId).readMessages()

    fun deleteMessage(chatId: Int, messageId: Int): Boolean {
        val chat = getChat(chatId)
        return if (chat.messages.size == 1) deleteChat(chat.id) else chat.deleteMessage(messageId)
    }

    fun getMessages(chatId: Int, fromMessageId: Int, count: Int = 1) = getChat(chatId).getMessages(fromMessageId, count)

    fun deleteChat(chatId: Int): Boolean = chats.remove(getChat(chatId))

    fun getUnreadChatsCount() = chats.count { it.getUnreadMessages().isNotEmpty() }

    fun getChat(chatId: Int): Chat {
        return when (val chat = chats.find { it.id == chatId }) {
            null -> throw ChatNotFoundException(chatId)
            else -> chat
        }
    }

    fun clearChats() {
        chats.clear()
    }

    fun getChatsCount() = chats.size - 1

    fun getChats(userId: Int): List<Chat> {
        val chatsWithMessage = chats.filter { it.haveNewMessage(userId) }
        if (chatsWithMessage.isEmpty()) {
            throw NewMessageNotFoundException()
        }
        return chatsWithMessage
    }

}