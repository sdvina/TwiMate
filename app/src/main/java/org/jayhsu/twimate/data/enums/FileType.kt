package org.jayhsu.twimate.data.enums

enum class FileType (val id: Int, val suffix: List<String>){
    BITMAP(1, listOf("bmp", "jpg", "jpeg", "png")),
    VECTOR(2, listOf()),
    GIF(3, listOf("gif")),
    VIDEO(4, listOf(".mp4"))
}