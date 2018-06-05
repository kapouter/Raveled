package com.kapouter.raveled.search.filter

import android.os.Parcel
import android.os.Parcelable
import com.kapouter.raveled.R

fun List<FilterItem>.getQuery(): String? {
    return if (isEmpty()) null
    else joinToString("|") { it.value }
}

data class Filter(var sort: FilterItem = FilterItem.BEST, var craft: List<FilterItem> = ArrayList(), var category: List<FilterItem> = ArrayList()) : Parcelable {
    constructor(parcel: Parcel) : this(
            sort = parcel.readParcelable(FilterItem::class.java.classLoader) as FilterItem,
            craft = parcel.readParcelableArray(FilterItem::class.java.classLoader).toList() as List<FilterItem>,
            category = parcel.readParcelableArray(FilterItem::class.java.classLoader).toList() as List<FilterItem>)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(sort, flags)
        parcel.writeParcelableArray(craft.toTypedArray(), flags)
        parcel.writeParcelableArray(category.toTypedArray(), flags)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Filter> {
        override fun createFromParcel(parcel: Parcel): Filter = Filter(parcel)

        override fun newArray(size: Int): Array<Filter?> = arrayOfNulls(size)
    }
}

enum class FilterItem(val value: String, val label: Int, val icon: Int? = null) : Parcelable {
    // SORT
    BEST("best", R.string.sort_best, R.drawable.icon_sort_best),
    HOT("recently-popular", R.string.sort_hot, R.drawable.icon_sort_hot),
    NAME("name", R.string.sort_name, R.drawable.icon_sort_name),
    POPULAR("popularity", R.string.sort_popularity, R.drawable.icon_sort_popular),
    PROJECTS("projects", R.string.sort_projects, R.drawable.icon_sort_projects),
    FAVORITES("favorites", R.string.sort_favorites, R.drawable.icon_sort_favorites),
    QUEUES("queues", R.string.sort_queues, R.drawable.icon_sort_queues),
    PUBLICATION("date", R.string.sort_publication, R.drawable.icon_sort_publication),
    NEW("created", R.string.sort_new, R.drawable.icon_sort_new),
    RATING("rating", R.string.sort_rating, R.drawable.icon_sort_rating),
    DIFFICULTY("difficulty", R.string.sort_difficulty, R.drawable.icon_sort_difficulty),
    YARN("yarn", R.string.sort_yarn, R.drawable.icon_sort_yarn),
    // CRAFT
    CROCHET("crochet", R.string.filter_crochet),
    KNITTING("knitting", R.string.filter_knitting),
    // CATEGORY
    PULLOVER("pullover", R.string.filter_pullover),
    CARDIGAN("cardigan", R.string.filter_cardigan),
    TOP("tops", R.string.filter_top),
    HAT("hat", R.string.filter_hat),
    HAND("hands", R.string.filter_hat),
    COWL("cowl", R.string.filter_cowl),
    SCARF("scarf", R.string.filter_scarf),
    SHAWL("shawl-wrap", R.string.filter_shawl),
    SOCKS("socks", R.string.filter_socks),
    TOYS("toysandhobbies", R.string.filter_toys);

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(ordinal)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<FilterItem> {
        override fun createFromParcel(parcel: Parcel): FilterItem =
                FilterItem.values()[parcel.readInt()]

        override fun newArray(size: Int): Array<FilterItem?> = arrayOfNulls(size)
    }
}