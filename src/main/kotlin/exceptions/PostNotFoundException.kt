package exceptions

import java.lang.RuntimeException

class PostNotFoundException (
    postId: Int
): RuntimeException("Post with ID $postId is not found") {
}