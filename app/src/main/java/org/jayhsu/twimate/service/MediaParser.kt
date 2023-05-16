package org.jayhsu.twimate.service

import org.jayhsu.twimate.data.local.entity.Task
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jsoup.Jsoup

// https://twitter.com/InsaneRealitys/status/1658217817722724362?s=20

class MediaParser {
    fun parse(url: String) : List<Task>? {
        return null
    }

    fun sendGetRequestAndParseHtml(url: String) {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .build()

        val response = client.newCall(request).execute()
        val html = response.body?.string()

        val document = Jsoup.parse(html)

        // 在这里使用解析后的 HTML 文档
        // 例如，获取标题元素的文本内容
        val title = document.title()
        println(title)
    }
}