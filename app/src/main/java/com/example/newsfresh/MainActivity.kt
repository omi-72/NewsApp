package com.example.newsfresh

import android.app.DownloadManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.textclassifier.TextClassification
import android.widget.Adapter
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject
import com.android.volley.VolleyError as VolleyError1

class MainActivity : AppCompatActivity(), NewsItemClicked {
    private lateinit var mAdapter: NewsListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


       recyclerView.layoutManager=LinearLayoutManager(this)
        fetchData()
        mAdapter = NewsListAdapter(this)
        recyclerView.adapter = mAdapter
    }
    private fun fetchData() {
        val url = "https://saurav.tech/NewsAPI/top-headlines/category/health/in.json"

        val JSONObjectRequest = JsonObjectRequest(
                Request.Method.GET, url, null,
                Response.Listener {
                   val newsJsonArray = it.getJSONArray("articles")
                    val newsArray= ArrayList<News>()
                    for (i in 0 until newsJsonArray.length()) {
                        val newsJSONObject = newsJsonArray.getJSONObject(i)
                        val news= News(
                                newsJSONObject.getString("title"),
                                newsJSONObject.getString("author"),
                                newsJSONObject.getString("url"),
                                newsJSONObject.getString("urlToImage")

                        )
                        newsArray.add(news)
                    }
                    mAdapter.updateNews(newsArray)

                },
                Response.ErrorListener {
                }
        )
       MySingleton.getInstance(this).addToRequestQueue(JSONObjectRequest)

    }


    override fun onItemClicked(item: News){


        val builder = CustomTabsIntent.Builder();
       val customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(item.url));
    }
}