package attachment

sealed class Attachment() {
    abstract val type: String
}

data class PhotoAttachment(
    override val type: String = "photo",
    val photo: Photo = Photo()
) : Attachment() {
}

data class VideoAttachment(
    override val type: String = "video",
    val video: String = ""

) : Attachment() {
}

data class AppAttachment(
    override val type: String = "app",
    val app: App = App()
) : Attachment() {
}

data class AudioAttachment(
    override val type: String = "audio",
    val audio: Audio = Audio()
) : Attachment() {
}

data class GraffitiAttachment(
    override val type: String = "graffiti",
    val graffiti: Graffiti = Graffiti()
) : Attachment() {
}