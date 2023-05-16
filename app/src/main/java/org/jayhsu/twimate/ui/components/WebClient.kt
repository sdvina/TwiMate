package org.jayhsu.twimate.ui.components

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.util.Log
import android.webkit.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.google.accompanist.web.AccompanistWebViewClient
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewNavigator
import com.google.accompanist.web.rememberWebViewState
import org.jayhsu.twimate.data.constant.LogConstant

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebClient(
    modifier: Modifier = Modifier,
    url: String
) {
    val urlState = remember { mutableStateOf(url) }
    val webViewState = rememberWebViewState(url = urlState.value)

    val webClient = remember {
        object : AccompanistWebViewClient() {

            override fun onPageStarted(
                view: WebView?,
                url: String?,
                favicon: Bitmap?
            ) {
                super.onPageStarted(view, url, favicon)
                urlState.value =  url!!
                Log.d(LogConstant.TAG_WEB_VIEW, "${LogConstant.MSG_PAGE_LOADING} $url")
            }

            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                super.onReceivedError(view, request, error)
                error?.errorCode?.let {
                    Log.d(LogConstant.TAG_WEB_VIEW, "${LogConstant.MSG_ERROR} $it")
                }
            }

            override fun shouldInterceptRequest(
                view: WebView?,
                request: WebResourceRequest?
            ): WebResourceResponse? {
                return super.shouldInterceptRequest(view, request)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
            }
        }
    }
    webClient.navigator.reload()
    WebView(
        state = webViewState,
        modifier = modifier,
        navigator = rememberWebViewNavigator(),
        onCreated = { webView ->
            webView.settings.javaScriptEnabled = true
            webView.settings.cacheMode = WebSettings.LOAD_DEFAULT
        },
        client = webClient
    )
}