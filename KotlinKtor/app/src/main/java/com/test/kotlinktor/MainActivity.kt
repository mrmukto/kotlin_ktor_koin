package com.test.kotlinktor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.test.kotlinktor.data.model.Post
import com.test.kotlinktor.ui.PostViewModel
import com.test.kotlinktor.ui.theme.KotlinKtorTheme
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.test.kotlinktor.utils.Result

class MainActivity : ComponentActivity() {
    private val viewModel: PostViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        viewModel.loadPosts() // trigger API call

        setContent {
            KotlinKtorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PostScreen(
                        viewModel = viewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

}

@Composable
fun PostScreen(viewModel: PostViewModel, modifier: Modifier = Modifier) {
    val state by viewModel.posts.collectAsState()

    when (state) {
        is Result.Loading -> {
            Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is Result.Success -> {
            val posts = (state as Result.Success<List<Post>>).data
            LazyColumn(modifier = modifier.padding(16.dp)) {
                items(posts) { post ->
                    PostItem(post)
                }
            }
        }
        is Result.Error -> {
            val error = (state as Result.Error).exception.message ?: "Unknown Error"
            Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Error: $error", color = Color.Red)
            }
        }
    }
}
@Composable
fun PostItem(post: Post) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(text = post.title, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = post.body)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PostItemPreview() {
    KotlinKtorTheme {
        PostItem(Post(1, "Sample Title", "Sample body text", userId = 1))
    }
}

