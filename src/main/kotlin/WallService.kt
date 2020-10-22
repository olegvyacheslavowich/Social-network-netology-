object WallService {

    private var posts = emptyArray<Post>()
    private var newPostId: Int = 1

    fun addPost(post: Post): Post {
        post.id = newPostId++
        posts += post
        return posts.last()
    }

    fun update(post: Post): Boolean {
        val oldPost = posts.find { it.id == post.id} ?: return false
        val oldPostIndex = posts.indexOf(oldPost)
        val newPost = post.copy(date = oldPost.date, ownerId = oldPost.ownerId)
        posts[oldPostIndex] = newPost

        return true
    }

    fun showAll() {
        posts.forEach {
            println(
                """Post: id = ${it.id}
    text = ${it.text}"""
            )
        }
    }

    fun clearWall() {
        posts = emptyArray<Post>()
        newPostId = 1
    }

}