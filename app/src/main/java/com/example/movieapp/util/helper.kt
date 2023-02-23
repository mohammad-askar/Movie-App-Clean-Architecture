package com.example.movieapp.util

fun String.removeHtmlTags(): String{
    return Regex(pattern = "<\\w+>|<\\w+>").replace(this, "")
}