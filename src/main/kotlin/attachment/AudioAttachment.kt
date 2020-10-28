package attachment

data class AudioAttachment(
    override val type: String = "audio",
    val audio: Audio
) : Attachment {
}