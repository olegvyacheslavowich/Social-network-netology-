package exceptions

class CommentNotFountException(
    id: Int,
    ownerId: Int
) : RuntimeException(
    "Comment with id $id from owner with id $ownerId not found"
) {
}