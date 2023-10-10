package com.example.noticias.webview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.webkit.WebViewClient
import com.example.noticias.R
import com.example.noticias.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityWebViewBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        val url = intent.getStringExtra("URL")

        binding.web.loadUrl(url ?: "")

        binding.web.settings.javaScriptEnabled = true

        binding.web.webViewClient = WebViewClient()

        binding.containerBack.setOnClickListener {
            finish()
        }

    }

    companion object {
        fun newInstance(activity: AppCompatActivity, url: String): Intent {
            val intent = Intent(
                activity,
                WebViewActivity::class.java
            )

            intent.putExtra("URL", url)

            return intent
        }
    }
}