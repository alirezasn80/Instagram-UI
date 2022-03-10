package com.example.instagramui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.example.instagramui.ui.theme.InstagramUITheme

private val normalFontSize = 24.sp


@ExperimentalFoundationApi
@ExperimentalUnitApi
@Preview(showBackground = true)
@Composable
fun ProfileScreen() {
    var selectedTabItem by remember {
        mutableStateOf(0)
    }
    InstagramUITheme {
        LazyColumn(
            Modifier
                .fillMaxSize()
        ) {

            item {
                TopBar(name = "Azin dev", Modifier.padding(16.dp))
                InfoBar(Modifier.padding(16.dp))
                ProfileDescription(
                    modifier = Modifier.padding(16.dp),
                    name = "Azin",
                    description = "Android Developer",
                    url = "...",
                    followedBy = listOf("Ronaldo", "Messi", "Selena gomez"),
                    otherCount = 3
                )
                ButtonSection()
                Spacer(modifier = Modifier.height(16.dp))
                HighLightSection(
                    listOf(
                        ImageWithText(
                            painter = painterResource(id = R.drawable.album),
                            text = "Album"
                        ),
                        ImageWithText(
                            painter = painterResource(id = R.drawable.camera),
                            text = "Camera"
                        ),
                        ImageWithText(
                            painter = painterResource(id = R.drawable.flower),
                            text = "Flower"
                        ),
                        ImageWithText(
                            painter = painterResource(id = R.drawable.food),
                            text = "Food"
                        ),
                        ImageWithText(
                            painter = painterResource(id = R.drawable.messages),
                            text = "Message"
                        ),
                        ImageWithText(
                            painter = painterResource(id = R.drawable.music),
                            text = "Music"
                        ),
                    ),
                    modifier = Modifier.offset(5.dp)
                )

                PostTabView(
                    imageWithTexts = listOf(
                        ImageWithText(
                            painter = painterResource(id = R.drawable.ic_airdrop),
                            text = "air"
                        ),
                        ImageWithText(
                            painter = painterResource(id = R.drawable.ic_folder),
                            text = "folder"
                        ),
                        ImageWithText(
                            painter = painterResource(id = R.drawable.ic_one_drive),
                            text = "one drive"
                        ),
                        ImageWithText(
                            painter = painterResource(id = R.drawable.ic_music_tab),
                            text = "music"
                        ),
                    )
                ) {
                    selectedTabItem = it
                }
            }

            val posts: MutableList<Int> = mutableListOf()

              var flag = false
              repeat((1..50).count()) {
                  flag = if (flag) {
                      posts.add(R.drawable.my_pic)
                      !flag
                  } else {
                      posts.add(R.drawable.ic_launcher_background)
                      !flag
                  }
              }



            when (selectedTabItem) {
                0 -> {
                    this.gridColumn(
                        posts = posts,
                        modifier = Modifier
                            .aspectRatio(1f)
                            .border(width = 1.dp, color = Color.White),
                        cells = 3
                    )
                }
            }
        }
    }
}

@Composable
fun PostTabView(
    modifier: Modifier = Modifier,
    imageWithTexts: List<ImageWithText>,
    onTabSelected: (selectedIndex: Int) -> Unit
) {
    val inactiveColor = Color.Gray
    var selectedTabIndex by remember {
        mutableStateOf(0)
    }

    TabRow(
        selectedTabIndex = selectedTabIndex,
        modifier = modifier,
        backgroundColor = Color.Transparent,
        contentColor = Color.Black
    ) {
        imageWithTexts.forEachIndexed { index, item ->
            Tab(
                selected = selectedTabIndex == index,
                selectedContentColor = Color.Black,
                unselectedContentColor = inactiveColor,
                onClick = {
                    selectedTabIndex = index
                    onTabSelected(index)
                }
            ) {
                Icon(
                    painter = item.painter,
                    contentDescription = item.text
                )
            }
        }

    }
}

@ExperimentalFoundationApi
fun LazyListScope.gridColumn(
    posts: List<Int>,
    modifier: Modifier = Modifier,
    cells: Int = 3
) {
    val size = posts.size
    val remaining = size % cells
    val itemCount = if (remaining == 0) size / cells else (size + (cells - remaining)) / cells

    items(count = itemCount) {
        Row(
            Modifier
                .fillMaxWidth()
                .scale(scaleX = 1.01f, 1f)
        ) {
            for (i in 0 until cells) {
                if (it * cells + i < posts.size) {
                    Image(
                        painter = painterResource(id = posts[it * cells + i]),
                        contentDescription = "post",
                        contentScale = ContentScale.Crop,
                        modifier = modifier.weight(1f, fill = true)
                    )
                } else {
                    repeat(cells - remaining) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                    break
                }
            }
        }

    }

}

@Composable
fun HighLightSection(
    highlights: List<ImageWithText>,
    modifier: Modifier = Modifier
) {
    LazyRow(modifier = modifier) {
        items(count = highlights.size) {
            Column(
                Modifier
                    .padding(end = 15.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                RoundedImage(painter = highlights[it].painter,
                    modifier
                        .size(70.dp)
                        .clickable {})
                Text(text = highlights[it].text, textAlign = TextAlign.Center)
            }
        }

    }
}

@Composable
fun ButtonSection(
) {
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ActionButton(icon = Icons.Default.ArrowDropDown, text = "Following")
        ActionButton(text = "Following")
        ActionButton(text = "Email")
        ActionButton(icon = Icons.Default.ArrowDropDown)
    }

}

@Composable
fun ActionButton(
    icon: ImageVector? = null,
    text: String? = null,
) {

    Row(
        modifier = Modifier
            .defaultMinSize(minWidth = 30.dp)
            .border(
                width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(5.dp)
            )
            .padding(5.dp)
            .clickable {},
        horizontalArrangement = Arrangement.Center
    ) {
        if (text != null) {
            Text(text = text, fontWeight = FontWeight.SemiBold)
        }
        if (icon != null) {
            Icon(imageVector = icon, contentDescription = "icon")
        }

    }

}

@Composable
fun TopBar(
    name: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "icon arrow",
        )
        Text(
            text = name,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            overflow = TextOverflow.Ellipsis

        )
        Icon(
            imageVector = Icons.Outlined.Notifications,
            contentDescription = "Icon notification"
        )

        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = "Icon notification"
        )


    }
}

@Composable
fun InfoBar(
    modifier: Modifier = Modifier
) {
    Row(
        modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        RoundedImage(
            painterResource(id = R.drawable.my_pic)
        )

        CustomColumn("313", "posts")
        CustomColumn("1.3M", "Followers")
        CustomColumn("1", "Following")
    }
}

@Composable
fun RoundedImage(
    painter: Painter,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painter,
        contentDescription = "My img",
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(100.dp)
            .aspectRatio(1f, matchHeightConstraintsFirst = true)
            .border(1.dp, color = Color.Gray, CircleShape)
            .padding(2.dp)
            .clip(CircleShape)
    )

}

@ExperimentalUnitApi
@Composable
fun ProfileDescription(
    modifier: Modifier = Modifier,
    name: String,
    description: String = "",
    url: String,
    followedBy: List<String>,
    otherCount: Int,
) {
    Column(
        modifier
            .fillMaxWidth()
    ) {

        val textStyle = TextStyle(
            fontSize = 16.sp,
            letterSpacing = 0.5.sp,
            lineHeight = 20.sp
        )
        Text(
            text = name,
            style = textStyle,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = description,
            style = textStyle
        )
        Text(
            text = url,
            style = textStyle,
            color = Color.Blue
        )
        var scaledTextStyle by remember { mutableStateOf(textStyle) }
        var readyToDraw by remember { mutableStateOf(false) }

        if (followedBy.isNotEmpty()) {
            Text(
                text = buildAnnotatedString {
                    val boldStyle = SpanStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )

                    append("Followed by ")
                    followedBy.forEachIndexed { index, name ->
                        pushStyle(boldStyle)
                        append(name)
                        pop()

                        if (index < followedBy.size - 1)
                            append(", ")
                    }

                    if (otherCount > 2) {
                        append(" and ")
                        pushStyle(boldStyle)
                        append("$otherCount other")
                        pop()
                    }

                },
                style = textStyle,
                softWrap = true,
                onTextLayout = { textLayoutResult ->
                    if (textLayoutResult.didOverflowWidth) {
                        scaledTextStyle =
                            scaledTextStyle.copy(fontSize = scaledTextStyle.fontSize * 0.9)
                    } else {
                        readyToDraw = true
                    }

                })
        }
    }
}

@Composable
fun CustomColumn(number: String, text: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = if (number.length > 5) number.dropLast(number.length - 5) else number,
            fontSize = normalFontSize,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Clip
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = text, color = Color.Gray)
    }
}
