import exceptions.NoteCommentNotFountException
import exceptions.NoteNotFountException
import note.Comment
import note.Note
import org.junit.Assert
import org.junit.Test
import service.NoteService

class NoteServiceTest {

    @Test
    fun add() {
        NoteService.clearWall()

        val noteId = NoteService.add("Hello", "world!")
        Assert.assertEquals(1, noteId)
    }

    @Test
    fun createComment() {

        NoteService.clearWall()

        val noteId = NoteService.add("Hello", "world!")
        val commentId = NoteService.createComment(noteId, "Simple comment to note")

        Assert.assertEquals(1, commentId)
    }

    @Test
    fun delete() {
        NoteService.clearWall()

        val noteId = NoteService.add("Hello", "world!")
        val result = NoteService.delete(noteId)
        assert(result)
    }

    @Test
    fun delete_fail() {
        NoteService.clearWall()

        val result = NoteService.delete(18)
        Assert.assertFalse(result)
    }

    @Test
    fun deleteComment_success() {

        NoteService.clearWall()

        val noteId = NoteService.add("Hello", "world!")
        val commentId = NoteService.createComment(noteId, "Simple comment to note")

        val result = NoteService.deleteComment(commentId)

        assert(result)
    }

    @Test(expected = NoteCommentNotFountException::class)
    fun deleteComment_fail() {

        NoteService.clearWall()

        val noteId = NoteService.add("Hello", "world!")
        NoteService.createComment(noteId, "Simple comment to note")

        NoteService.deleteComment(18)
    }

    @Test
    fun edit_success() {

        NoteService.clearWall()

        val noteId = NoteService.add("Hello", "world!")

        val newText = "this is new text!"
        val result = NoteService.edit(noteId, "Hello", newText)

        Assert.assertEquals(newText, result.text)
    }

    @Test(expected = NoteNotFountException::class)
    fun edit_fail() {

        NoteService.clearWall()

        val newText = "this is new text!"
        NoteService.edit(18, "Hello", newText)

    }

    @Test
    fun editComment_success() {

        NoteService.clearWall()

        val noteId = NoteService.add("Hello", "world!")
        val commentId = NoteService.createComment(noteId, "Simple comment to note")
        val newText = "this is new text!"
        val result = NoteService.editComment(commentId, newText)

        Assert.assertEquals(newText, result.message)
    }

    @Test(expected = NoteCommentNotFountException::class)
    fun editComment_fail() {

        NoteService.clearWall()

        val newText = "this is new text!"
        NoteService.editComment(80, newText)

    }

    @Test
    fun get() {

        NoteService.clearWall()

        val noteIds: List<Int> = listOf(
            NoteService.add("Hello1", "world!"),
            NoteService.add("Hello2", "world!"),
            NoteService.add("Hello3", "world!")
            )

        val result = NoteService.get(noteIds)

        Assert.assertEquals(3, result.size)
    }

    @Test
    fun getById_success() {
        NoteService.clearWall()

        val noteId = NoteService.add("Hello", "world!")
        val result = NoteService.getById(noteId)

        Assert.assertEquals(noteId, result.id)
    }

    @Test(expected = NoteNotFountException::class)
    fun getById_fail() {
        NoteService.clearWall()
        NoteService.getById(80)
    }

    @Test
    fun getComments() {

        NoteService.clearWall()

        val noteId = NoteService.add("Hello", "world!")
        NoteService.createComment(noteId, "Simple comment to note1 #1")
        NoteService.createComment(noteId, "Simple comment to note1 #2")

        val noteId_2 = NoteService.add("Hello2", "world!")
        NoteService.createComment(noteId_2, "Simple comment to note2 #1")

        val result = NoteService.getComments(noteId)

        Assert.assertEquals(2, result.size)
    }

    @Test
    fun restoreComment_success() {

        NoteService.clearWall()

        val noteId = NoteService.add("Hello", "world!")
        val commentId = NoteService.createComment(noteId, "Simple comment to note")
        NoteService.deleteComment(commentId)

        val result = NoteService.restoreComment(commentId)

        assert(result)

    }

    @Test(expected = NoteCommentNotFountException::class)
    fun restoreComment_fail() {
        NoteService.clearWall()
        NoteService.restoreComment(99)
    }
}