package com.example.git.service.search

class SearchResponse(val items: List<Item>) {
    class Item(
        val id: Long,
        val login: String,
        val avatar_url: String = "",
        val score: Int
    )
}

