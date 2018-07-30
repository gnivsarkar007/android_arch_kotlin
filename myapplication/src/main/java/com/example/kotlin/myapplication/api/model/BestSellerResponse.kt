package com.example.kotlin.myapplication.api.model


import com.google.gson.annotations.SerializedName

data class Response(@SerializedName("copyright")
                    val copyright: String = "",
                    @SerializedName("results")
                    val results: List<ResultsItem>?,
                    @SerializedName("num_results")
                    val numResults: Int = 0,
                    @SerializedName("status")
                    val status: String = "")


data class ReviewsItem(@SerializedName("article_chapter_link")
                       val articleChapterLink: String = "",
                       @SerializedName("book_review_link")
                       val bookReviewLink: String = "",
                       @SerializedName("first_chapter_link")
                       val firstChapterLink: String = "",
                       @SerializedName("sunday_review_link")
                       val sundayReviewLink: String = "")


data class ResultsItem(@SerializedName("isbns")
                       val isbns: List<IsbnsItem>?,
                       @SerializedName("contributor_note")
                       val contributorNote: String = "",
                       @SerializedName("ranks_history")
                       val ranksHistory: List<RanksHistoryItem>?,
                       @SerializedName("contributor")
                       val contributor: String = "",
                       @SerializedName("reviews")
                       val reviews: List<ReviewsItem>?,
                       @SerializedName("author")
                       val author: String = "",
                       @SerializedName("price")
                       val price: Float = 0.0f,
                       @SerializedName("age_group")
                       val ageGroup: String = "",
                       @SerializedName("description")
                       val description: String = "",
                       @SerializedName("publisher")
                       val publisher: String = "",
                       @SerializedName("title")
                       val title: String = "")


data class IsbnsItem(@SerializedName("isbn13")
                     val isbn13: String = "",
                     @SerializedName("isbn10")
                     val isbn10: String = "")


data class RanksHistoryItem(@SerializedName("weeks_on_list")
                            val weeksOnList: Int = 0,
                            @SerializedName("dagger")
                            val dagger: Int = 0,
                            @SerializedName("bestsellers_date")
                            val bestsellersDate: String = "",
                            @SerializedName("asterisk")
                            val asterisk: Int = 0,
                            @SerializedName("rank")
                            val rank: Int = 0,
                            @SerializedName("primary_isbn10")
                            val primaryIsbn10: String = "",
                            @SerializedName("list_name")
                            val listName: String = "",
                            @SerializedName("primary_isbn13")
                            val primaryIsbn13: String = "",
                            @SerializedName("display_name")
                            val displayName: String = "",
                            @SerializedName("published_date")
                            val publishedDate: String = "",
                            @SerializedName("ranks_last_week")
                            val ranksLastWeek: String? = null)


