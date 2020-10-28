package attachment

data class VideoAttachment(
    override val type: String = "video",
    val video: Video = Video()
) : Attachment{
}