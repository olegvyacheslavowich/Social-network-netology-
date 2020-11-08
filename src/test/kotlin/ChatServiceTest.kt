import exceptions.ChatNotFoundException
import exceptions.MessageNotFoundException
import exceptions.NewMessageNotFoundException
import org.junit.Assert
import org.junit.Test
import service.ChatService

class ChatServiceTest {

    @Test
    fun sendMessage() {

        ChatService.clearChats()

        ChatService.sendMessage(1, 3, "Hello, world!")
        val secondMessage = ChatService.sendMessage(1, 3, "How are you?")

        Assert.assertEquals(1, secondMessage.chatId)
    }

    @Test(expected = ChatNotFoundException::class)
    fun deleteChat_ChatNotFound() {

        ChatService.clearChats()

        ChatService.sendMessage(1, 3, "Hello, world!")
        ChatService.sendMessage(1, 2, "How are you?")

        ChatService.deleteChat(5)

    }

    @Test
    fun deleteChat() {

        ChatService.clearChats()

        ChatService.sendMessage(1, 3, "Hello, world!")
        ChatService.sendMessage(1, 2, "How are you?")

        val chatsCount = ChatService.getChatsCount()
        ChatService.deleteChat(1)

        Assert.assertEquals(chatsCount - 1, ChatService.getChatsCount())

    }

    @Test
    fun deleteMessage() {

        ChatService.clearChats()

        ChatService.sendMessage(1, 3, "Hello, world!")
        ChatService.sendMessage(1, 3, "How are you!")

        val chat = ChatService.getChat(1)

        val messageCount = chat.messages.size
        ChatService.deleteMessage(1, 1)

        Assert.assertEquals(messageCount - 1, chat.messages.size)
    }

    @Test(expected = ChatNotFoundException::class)
    fun deleteMessage_ChatNotFound() {

        ChatService.clearChats()

        ChatService.sendMessage(1, 3, "Hello, world!")
        ChatService.sendMessage(1, 2, "How are you?")

        ChatService.deleteMessage(50, 1)

    }

    @Test(expected = MessageNotFoundException::class)
    fun deleteMessage_MessageNotFound() {

        ChatService.clearChats()

        ChatService.sendMessage(1, 3, "Hello, world!")
        ChatService.sendMessage(1, 3, "How are you?")

        ChatService.deleteMessage(1, 50)
    }

    @Test
    fun getUnreadChatsCount() {

        ChatService.clearChats()

        ChatService.sendMessage(1, 3, "Hello, world!")
        ChatService.sendMessage(1, 2, "How are you?")

        val unreadChatCount = ChatService.getUnreadChatsCount()
        Assert.assertEquals(2, unreadChatCount)
    }

    @Test
    fun getChats() {

        ChatService.clearChats()

        ChatService.sendMessage(1, 3, "Hello, world!")
        ChatService.sendMessage(1, 2, "How are you?")

        val chats = ChatService.getChats(2)

        Assert.assertEquals(ChatService.getChat(2), chats.get(0))
    }

    @Test(expected = NewMessageNotFoundException::class)
    fun getChats_NewMessageNotFound() {

        ChatService.clearChats()

        ChatService.sendMessage(1, 3, "Hello, world!")
        ChatService.sendMessage(1, 2, "How are you?")

        ChatService.readMessages(2)
        ChatService.getChats(2)
    }

    @Test
    fun getMessages() {

        ChatService.clearChats()

        ChatService.sendMessage(1, 2, "Hello, world!")
        ChatService.sendMessage(1, 2, "How are you?")
        ChatService.sendMessage(1, 2, "Where are you?")
        ChatService.sendMessage(1, 2, "What do you do?")
        ChatService.sendMessage(1, 2, "What is your name?")

        val messages = ChatService.getMessages(1, 3, 2 )

        Assert.assertEquals(2, messages.size)

    }
}