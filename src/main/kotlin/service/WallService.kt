package service

import exceptions.CommentNotFountException
import exceptions.PostNotFoundException
import exceptions.ReasonNotFoundException
import post.Comment
import post.Post

object WallService {

    private var reports = emptyArray<Comment>()
    private var comments = emptyArray<Comment>()
    private var newCommentId: Int = 1
    private var posts = emptyArray<Post>()
    private var newPostId: Int = 1

    fun addPost(post: Post): Post {
        posts += post.copy(id = newPostId++)
        return posts.last()
    }

    fun update(post: Post): Boolean {
        val oldPost = posts.find { it.id == post.id } ?: return false
        val oldPostIndex = posts.indexOf(oldPost)
        val newPost = post.copy(date = oldPost.date, ownerId = oldPost.ownerId)
        posts[oldPostIndex] = newPost

        return true
    }

    fun createComment(comment: Comment): Int {
        when (posts.find { it.id == comment.postId }) {
            null -> throw PostNotFoundException(comment.postId)
            else -> {
                comments += comment.copy(id = newCommentId++)
                return comments.last().id
            }
        }
    }

    fun reportComment(ownerId: Int, commentId: Int, reason: Int) {
        when (val comment = comments.find { it.id == commentId}) {
            null -> throw CommentNotFountException(commentId, ownerId)
            else -> {
                when (reason) {
                    !in 0..6 -> throw ReasonNotFoundException(reason)
                    else -> reports += comment
                }
            }
        }
    }

    fun showAll() {
        posts.forEach {
            println(
                """post.Post: id = ${it.id}
    text = ${it.text}"""
            )
        }
    }

    fun clearWall() {
        reports = emptyArray()
        comments = emptyArray()
        newCommentId = 1
        posts = emptyArray()
        newPostId = 1
    }

}