fun main() {

    WallService.addPost(Post(ownerId = 1, fromId = 5, text ="First post"))
    WallService.addPost(Post(ownerId = 1, fromId = 3, text ="Second post"))
    WallService.addPost(Post(ownerId = 1, fromId = 3, text ="Third post"))

    WallService.showAll()

    WallService.update(Post(id = 2, ownerId = 1, fromId = 3, text ="This is new text of the post!"))

    println("=================================================")

    WallService.showAll()
}