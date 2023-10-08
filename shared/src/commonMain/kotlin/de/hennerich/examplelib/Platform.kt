package de.hennerich.examplelib

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform