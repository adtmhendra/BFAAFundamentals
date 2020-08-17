package com.hendra.mybook

object BooksData {
    private val bookNames = arrayOf(
        "Clean Architecture",
        "Clean Code",
        "Cracking the Coding Interview",
        "Cracking the Tech Career",
        "Kotlin Android Developer Expert",
        "The Mythical Man Month",
        "The Pragmatic Programmer"
    )

    private val bookPublishers = arrayOf(
        "William T",
        "Gramedia",
        "Addison Wesley",
        "Addison Wesley",
        "Dicoding Indonesia",
        "Joseph Vincent",
        "George Brush"
    )

    private val bookImages = intArrayOf(
        R.drawable.clean_architecture,
        R.drawable.clean_code,
        R.drawable.cracking_the_coding_interview,
        R.drawable.cracking_the_tech_career,
        R.drawable.kade,
        R.drawable.the_mythical_man_month,
        R.drawable.the_pragmatic_programmer
    )

    val listBook: ArrayList<Book>
    get() {
        val list = arrayListOf<Book>()
        for (position in bookNames.indices) {
            val book = Book()
            book.bookName = bookNames[position]
            book.bookPublisher = bookPublishers[position]
            book.bookImage = bookImages[position]
            list.add(book)
        }
        return list
    }
}