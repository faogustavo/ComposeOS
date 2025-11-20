package co.compose.dev.os

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
