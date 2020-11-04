package exceptions

class NoteNotFountException(
    id: Int
) : RuntimeException(
    "Note with id $id not found"
) {
}