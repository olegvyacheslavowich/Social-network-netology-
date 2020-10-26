import java.time.LocalDateTime

data class Post(
    val id: Int = 0,
    val ownerId: Int,
    val fromId: Int,
    var text: String,
    val createdBy: Int = 0,
    val replyOwnerId: Int = 0,
    val replyPostId: Int = 0,
    val friendsOnly: Boolean = false,
    val comments: Comments = Comments(count = 0, canPost = true, groupsCanPost = true),
    val likes: Likes = Likes(0, userLikes = true, canLike = true, canPublish = true),
    val reposts: Reposts = Reposts(count = 0, userReposted = false),
    val postType: String = "post",
    val signerId: Int = 0,
    val canPin: Boolean = true,
    val canDelete: Boolean = false,
    val canEdit: Boolean = false,
    val isPinned: Boolean = false,
    val markedAsAds: Boolean = false,
    val isFavorite: Boolean = false,
    val date: LocalDateTime = LocalDateTime.now()
) {



}