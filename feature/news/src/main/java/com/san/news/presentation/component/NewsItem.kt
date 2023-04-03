package com.san.news.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.san.news.data.entity.NewsBaseResponse
import com.san.news.presentation.ui.theme.background
import com.san.news.presentation.ui.theme.colorSubText
import com.san.news.presentation.ui.theme.colorText

@Composable
fun NewsItem(news: NewsBaseResponse.Data.Newslist) {

    Column(
        Modifier
            .fillMaxWidth()
            .background(background)
            .padding(4.dp)
    ) {
        Row {
            AsyncImage(
                modifier = Modifier.size(70.dp, 75.dp),
                model = news.playerImageUrl,
                contentDescription = news.playerImageUrl
            )

            Column(Modifier.fillMaxWidth()) {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.padding(end = 4.dp),
                        text = news.pname ?: "Not found",
                        maxLines = 1,
                        style = MaterialTheme.typography.titleMedium,
                        color = colorText
                    )
                    Text(
                        modifier = Modifier.padding(end = 4.dp),
                        text = news.position ?: "Not found",
                        style = MaterialTheme.typography.titleMedium,
                        color = colorSubText

                    )
                }
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.weight(.9f),
                        text = news.title ?: "Not found",
                        maxLines = 2,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White
                    )
                    AsyncImage(
                        modifier = Modifier
                            .size(15.dp)
                            .weight(.1f),
                        model = news.sourceLogo,
                        contentDescription = news.sourceName
                    )
                }
                Text(
                    text = news.details ?: "Not found",
                    maxLines = 3,
                    style = MaterialTheme.typography.bodySmall,
                    color = colorSubText
                )
            }
        }
        Text(
            modifier = Modifier.padding(4.dp),
            text = news.id ?: "0",
            maxLines = 1,
            style = MaterialTheme.typography.bodySmall,
            color = colorSubText
        )
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 70.dp, top = 4.dp, bottom = 1.dp)
                .height(0.5.dp), color = colorSubText
        )
    }


}

@Preview
@Composable
fun NewsItemPreview() {
    NewsItem(
        news = NewsBaseResponse.Data.Newslist(
            pname = "santhanam",
            position = "D | IND",
            title = "Kwity Paye (ankle) out on Wednesday",
            details = "Colts EDGE Kwity Paye (ankle) did not practice Wednesday,",
            sourceLogo = "https://www.playerline.org/test/static-endpoint/images/sources/square_logos/39px/rotoworld.png"
        )
    )
}

