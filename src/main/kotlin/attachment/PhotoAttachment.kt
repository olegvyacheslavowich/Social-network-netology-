package attachment

data class PhotoAttachment(
    override val type: String,
    val photo: Photo = Photo()
) : Attachment {
}