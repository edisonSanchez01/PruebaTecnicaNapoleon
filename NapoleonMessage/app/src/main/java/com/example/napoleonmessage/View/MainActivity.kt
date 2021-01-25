package com.example.napoleonmessage.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.napoleonmessage.R
import com.example.napoleonmessage.ViewModel.ViewModelMainActivity
import com.example.napoleonmessage.model.Post
import com.example.napoleonmessage.model.PostAdapter

const val EXTRA_USER_ID = "id user"
const val EXTRA_POST_TITLE = "title post"
const val EXTRA_POST_MESSAGE = "message post"

class MainActivity : AppCompatActivity() {
    
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: ViewModelMainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)
        var swipeRefresh: SwipeRefreshLayout = findViewById(R.id.swipeRefresh)
        swipeRefresh.setOnRefreshListener {
            viewModel.getPosts()
        }
        recyclerView.hasFixedSize()
        var manager = LinearLayoutManager(this)
        manager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = manager
        var adapter: PostAdapter = PostAdapter(this, listOf())
        recyclerView.adapter = adapter
        viewModel = ViewModelProvider(this).get(ViewModelMainActivity::class.java)
        viewModel.posts.observe(this, Observer {
            adapter = PostAdapter(this, it)
            adapter.setOnItemClickListener(object: PostAdapter.OnItemClickListener{
                override fun onItemClick(post: Post?) {
                    var detailsActivity = Intent(this@MainActivity, DetailsPostActivity::class.java)
                    detailsActivity.putExtra(EXTRA_USER_ID, post?.userId)
                    detailsActivity.putExtra(EXTRA_POST_TITLE, post?.title)
                    detailsActivity.putExtra(EXTRA_POST_MESSAGE, post?.body)
                    startActivity(detailsActivity)
                }
            })
            recyclerView.adapter = adapter
            swipeRefresh.isRefreshing = false
        })
        viewModel.getPosts()
    }


}