package com.example.cmrdbandroid.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cmrdbandroid.viewmodel.FirstEvent
import com.example.cmrdbandroid.viewmodel.FirstViewModel

@Composable
fun FirstScreen(
    viewModel: FirstViewModel,
) {
    val state = viewModel.consumeState().collectAsState()
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn {
            val data = state.value.data
            items(data.size) {
                FirstItem(
                    title = data[it].title,
                    content = data[it].content,
                    onClick = {
                        viewModel.onEvent(FirstEvent.OnClick(data[it]))
                    },
                    onLongClick = {
                        viewModel.onEvent(FirstEvent.OnLongClick(data[it]))
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FirstItem(
    title: String,
    content: String,
    onClick: () -> Unit = {},
    onLongClick: () -> Unit = {},
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .combinedClickable(
                onClick = onClick,
                onLongClick = onLongClick
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = title, modifier = Modifier.padding(8.dp))
            Text(text = content)
        }
    }
}