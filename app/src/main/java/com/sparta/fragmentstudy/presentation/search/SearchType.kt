package com.sparta.fragmentstudy.presentation.search

enum class SearchType(val viewType: Int) {
    IMAGE(0),
    VIDEO(1),
    DEFAULT(-1)
}
//서버에서 내려오는 값을 enum으로 변경할 때 사용
fun findSearchType(viewType: Int): SearchType {
    return SearchType.entries.find { viewType == it.viewType } ?: SearchType.DEFAULT
}