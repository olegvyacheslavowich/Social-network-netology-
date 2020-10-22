import org.junit.Assert.*
import org.junit.Test

class WallServiceTest {

    @Test
    fun addPost() {
        WallService.clearWall()

        val post = Post(ownerId = 1, fromId = 5, text ="First post")

        val addedPost = WallService.addPost(post)

        assertTrue(addedPost.id != 0)
    }

    @Test
    fun updateExisting() {

        WallService.clearWall()

        WallService.addPost(Post(ownerId = 1, fromId = 5, text ="First post"))
        WallService.addPost(Post(ownerId = 55, fromId = 3, text ="Second post"))
        WallService.addPost(Post(ownerId = 32, fromId = 3, text ="Third post"))

        var result = WallService.update(Post(id = 1, ownerId = 1, fromId = 3, text ="This is new text of the post!"))

        assertTrue(result)
    }

    @Test
    fun updateNotExist() {

        WallService.clearWall()

        WallService.addPost(Post(ownerId = 1, fromId = 5, text ="First post"))
        WallService.addPost(Post(ownerId = 55, fromId = 3, text ="Second post"))
        WallService.addPost(Post(ownerId = 32, fromId = 3, text ="Third post"))

        var result = WallService.update(Post(id = 90, ownerId = 1, fromId = 3, text ="This is new text of the post!"))

        assertFalse(result)
    }
}