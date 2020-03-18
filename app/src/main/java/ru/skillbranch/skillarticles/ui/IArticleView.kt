package ru.skillbranch.skillarticles.ui

interface IArticleView {

    /**
     * отрисовать все вхождения поискового запроса в контент
     */
    fun renderSearchResult(searchResult: List<Pair<Int, Int>>)

    /**
     * отрисовать текущее положение поиска и перевести фокус на него (spannable)
     */
    fun renderSearchPosition(searchPosition: Int)

    /**
     * очистить результаты поиска (удалять все spannable)
     */
    fun clearSearchResult()

    /**
     * показать search bar
     */
    fun showSearchBar()

    fun hideSearchBar()
}