import exceptions.CommentNotFountException
import exceptions.PostNotFoundException
import exceptions.ReasonNotFoundException
import org.junit.Assert.*
import org.junit.Test
import post.Comment
import post.Post
import service.WallService

class WallServiceTest {

    @Test
    fun addPost() {
        WallService.clearWall()

        val post = Post(ownerId = 1, fromId = 5, text = "First post")

        val addedPost = WallService.addPost(post)

        assertTrue(addedPost.id != 0)
    }

    @Test
    fun updateExisting() {

        WallService.clearWall()

        WallService.addPost(Post(ownerId = 1, fromId = 5, text = "First post"))
        WallService.addPost(Post(ownerId = 55, fromId = 3, text = "Second post"))
        WallService.addPost(Post(ownerId = 32, fromId = 3, text = "Third post"))

        val result = WallService.update(
            Post(
                id = 1,
                ownerId = 1,
                fromId = 3,
                text = "This is new text of the post!"
            )
        )

        assertTrue(result)
    }

    @Test
    fun updateNotExist() {

        WallService.clearWall()

        WallService.addPost(Post(ownerId = 1, fromId = 5, text = "First post"))
        WallService.addPost(Post(ownerId = 55, fromId = 3, text = "Second post"))
        WallService.addPost(Post(ownerId = 32, fromId = 3, text = "Third post"))

        val result = WallService.update(
            Post(
                id = 90,
                ownerId = 1,
                fromId = 3,
                text = "This is new text of the post!"
            )
        )

        assertFalse(result)
    }

    @Test(expected = PostNotFoundException::class)
    fun createCommentToNotExistingPost() {

        WallService.clearWall()

        WallService.addPost(Post(ownerId = 1, fromId = 5, text = "First post"))
        WallService.addPost(Post(ownerId = 55, fromId = 3, text = "Second post"))
        WallService.addPost(Post(ownerId = 32, fromId = 3, text = "Third post"))

        WallService.createComment(Comment(postId = 8, text = "This's comment from Kotlin test function to not existing post"))
    }

    @Test()
    fun createCommentToExistingPost() {

        WallService.clearWall()

        WallService.addPost(Post(ownerId = 1, fromId = 5, text = "First post"))
        WallService.addPost(Post(ownerId = 55, fromId = 3, text = "Second post"))
        WallService.addPost(Post(ownerId = 32, fromId = 3, text = "Third post"))

        val commentId = WallService.createComment(Comment(postId = 3, text = "This's comment from Kotlin test function to existing post"))

        assertEquals(commentId, 1)
    }

    @Test(expected = CommentNotFountException::class)
    fun reportCommentNotExistingComment() {

        WallService.clearWall()

        WallService.addPost(Post(ownerId = 1, fromId = 5, text = "First post"))
        WallService.addPost(Post(ownerId = 55, fromId = 3, text = "Second post"))
        WallService.addPost(Post(ownerId = 32, fromId = 3, text = "Third post"))

        WallService.createComment(Comment(postId = 3, text = "This's comment from Kotlin test function to existing post"))

        WallService.reportComment(1, 88, 3)
    }

    @Test(expected = ReasonNotFoundException::class)
    fun reportCommentReasonNotFound() {

        WallService.clearWall()

        WallService.addPost(Post(ownerId = 1, fromId = 5, text = "First post"))
        WallService.addPost(Post(ownerId = 55, fromId = 3, text = "Second post"))
        WallService.addPost(Post(ownerId = 32, fromId = 3, text = "Third post"))

        val commentId = WallService.createComment(Comment(postId = 3, text = "This's comment from Kotlin test function to existing post"))

        WallService.reportComment(3, commentId, 10)
    }
}