package service

import exceptions.NoteCommentNotFountException
import exceptions.NoteNotFountException
import note.Comment
import note.Note

object NoteService {
    private var noteId = 1
    private val notes: MutableList<Note> = mutableListOf()
    private var commentId = 1
    private val comments: MutableList<Comment> = mutableListOf()
    private val deletedComments: MutableList<Comment> = mutableListOf()

    fun add(title: String, text: String): Int {
        notes += Note(noteId++, title, text)
        return notes.last().id
    }

    fun createComment(noteId: Int, message: String): Int {
        comments += Comment(commentId++, noteId, message)
        return comments.last().id
    }

    fun delete(noteId: Int): Boolean {
        val note = notes.find { it.id == noteId }
        return notes.remove(note)
    }

    fun deleteComment(commentId: Int): Boolean {
        when (val comment = comments.find { it.id == commentId }) {
            null -> throw NoteCommentNotFountException(commentId)
            else -> {
                comments.remove(comment)
                deletedComments.add(comment)
            }
        }

        return true
    }

    fun edit(noteId: Int, title: String, text: String): Note {
        when (val note = notes.find { it.id == noteId }) {
            null -> throw NoteNotFountException(noteId)
            else -> {
                val index = notes.indexOf(note)
                notes[index] = note.copy(title = title, text = text)
                return notes[index]
            }
        }
    }

    fun editComment(commentId: Int, message: String): Comment {

        when (val comment = comments.find { it.id == commentId }) {
            null -> throw NoteCommentNotFountException(commentId)
            else -> {
                val index = comments.indexOf(comment)
                comments[index] = comment.copy(message = message)
                return comments[index]
            }
        }
    }

    fun get(noteIds: List<Int>, count: Int = 20): List<Note> {
        val foundNotes = notes.takeWhile { it.id in noteIds }
        return if (foundNotes.size > count) {
            foundNotes.subList(0, count - 1)
        } else {
            foundNotes
        }
    }

    fun getById(noteId: Int): Note {
        return when (val note = notes.find { it.id == noteId }) {
            null -> throw NoteNotFountException(noteId)
            else -> note
        }
    }

    fun getComments(noteId: Int, count: Int = 20): List<Comment> {

        val commentsByNote = comments.filter { it.noteId == noteId }
        return if (commentsByNote.size > count) {
            commentsByNote.subList(0, count - 1)
        } else {
            commentsByNote
        }
    }

    fun restoreComment(commentId: Int): Boolean {

        when (val comment = deletedComments.find { it.id == commentId }) {
            null -> throw NoteCommentNotFountException(commentId)
            else -> {
                deletedComments.remove(comment)
                comments.add(comment)
            }
        }

        return true
    }

    fun clearWall() {
        noteId = 1
        notes.clear()
        commentId = 1
        comments.clear()
        deletedComments.clear()
    }
}