package exceptions

class NoteCommentNotFountException(
    id: Int
) : RuntimeException(
    "Note comment with id $id not found"
) {
}