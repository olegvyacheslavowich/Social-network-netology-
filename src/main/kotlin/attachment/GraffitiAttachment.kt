package attachment

data class GraffitiAttachment(
    override val type: String = "graffiti",
    val graffiti: Graffiti = Graffiti()
) : Attachment {
}