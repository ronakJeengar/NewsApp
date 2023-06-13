package com.ronakJ.topnews

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import com.ronakJ.topnews.databinding.ActivityDetailWebViewBinding

class DetailWebView : AppCompatActivity() {

    private var binding: ActivityDetailWebViewBinding? = null

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailWebViewBinding.inflate(layoutInflater)
        val view = binding?.root
        setContentView(view)

        val url: String? = intent.getStringExtra("URL")
        if (url != null) {

            binding?.detailWebView?.settings!!.javaScriptEnabled = true
            binding?.detailWebView?.settings!!.userAgentString =
                "Mozilla/5.0 (iPhone; U; CPU like Mac OS X; en) AppleWebKit/420+ (KHTML, like Gecko) Version/3.0 Mobile/1A543a Safari/419.3"

            binding?.detailWebView?.webViewClient = object : WebViewClient() {

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    binding?.progressBar?.visibility = View.GONE
                    binding?.detailWebView?.visibility = View.VISIBLE
                }
            }
            binding?.detailWebView?.loadUrl(url)
        }
    }
}