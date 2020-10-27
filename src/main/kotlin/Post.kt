import attachment.Attachment
import java.time.LocalDateTime

data class Post(
    val id: Int = 0,
    val ownerId: Int,
    val fromId: Int,
    val createdBy: Int = 0,
    val date: LocalDateTime = LocalDateTime.now(),
    val text: String,
    val replyOwnerId: Int = 0,
    val replyPostId: Int = 0,
    val friendsOnly: Boolean = false,
    val comments: Comments = Comments(),
    val copyright: String = "",
    val likes: Likes = Likes(),
    val reposts: Reposts = Reposts(),
    val views: Views = Views(),
    val postType: String = "post",
    val postSource: PostSource = PostSource(),
    val geo: Geo = Geo(),
    val signerId: Int? = null,
    val copyHistory: Array<Post>? = null,
    val canPin: Boolean = true,
    val canDelete: Boolean = false,
    val canEdit: Boolean = false,
    val isPinned: Boolean = false,
    val markedAsAds: Boolean = false,
    val isFavorite: Boolean = false,
    val attachment: Attachment? = null
) {

}