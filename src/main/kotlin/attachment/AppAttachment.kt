package attachment

data class AppAttachment(
    override val type: String = "app",
    val app: App = App()
) : Attachment {
}