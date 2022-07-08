package com.ronakJ.topnews

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var adapter: NewsAdapter
    private var articles = mutableListOf<Article>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = NewsAdapter(this@MainActivity,articles)
        newsList.adapter = adapter
        newsList.layoutManager = LinearLayoutManager(this@MainActivity)

        getNews()
    }

    private fun getNews() {
        val news = NewsService.newsInstance.getHeadlines("in", 1)

        news.enqueue(object: Callback<News>{
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<News>, response: Response<News>) {
                val news = response.body()
                if (news != null){
                    //Log.d("Hello", news.toString())

                    articles.addAll(news.articles)
                    adapter.notifyDataSetChanged()
                }
            }
            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("Error Occurred", news.toString())
            }
        })
    }
}