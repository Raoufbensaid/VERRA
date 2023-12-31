package fr.raouf.verra

class ArticleModel(
    val description: String = "Petite description",
    val name: String = "Lacoste",
    val imageUrl: String = "https://example.com/your-image.jpg",
    var liked: Boolean = false,
    val price: Double = 0.0
)