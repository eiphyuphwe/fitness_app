package dme.systems.storeapp.ui.theme

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Badge
import androidx.compose.material.BadgedBox
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Fullscreen
import androidx.compose.material.icons.outlined.RadioButtonUnchecked
import androidx.compose.material.icons.outlined.Tune

import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dme.systems.storeapp.R
import dme.systems.storeapp.data.Category
import dme.systems.storeapp.ui.theme.theme.StoreAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Home()
        }
    }
}

val workOutCategories =
    mutableListOf(
        Category(1, "Full body", false),
        Category(2, "Cardio", false),
        Category(3, "Cross Fit", false),
        Category(4, "Cyclist", false),
        Category(5, "Glutes", false),
        Category(6, "Power", false)

    )


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Home() {
    StoreAppTheme {
        Scaffold(topBar = { TopBar() },
            bottomBar = { BottomBar() }) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 18.dp)
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 18.dp)
                ) {
                    Spacer(modifier = Modifier.size(15.dp))
                    TilesItems()
                    Spacer(modifier = Modifier.size(30.dp))
                    HorizontalPagerScreen()
                }

            }

        }
    }
}

@Composable
fun TilesItems() {

    val selectedStates = remember {
        mutableStateMapOf<String, Boolean>(
            "Full body" to false,
            "Cardio" to false,
            "Cross Fit" to false,
            "Cyclist" to false,
            "Glutes" to false,
            "Power" to false
        )
    }
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(
            15.dp
        )
    ) {
        items(workOutCategories) { workout ->
            val isSelected = selectedStates[workout.name] ?: false

            WorkOutType(
                workout,
                isSelected,
                onSelectedChange = { selectedKey, selected ->
                    selectedStates.keys.forEach { key ->
                        if (key == selectedKey) {
                            selectedStates[key] = selected
                        } else {
                            selectedStates[key] = false
                        }
                    }

                }
            )
        }
    }
}

@Composable
fun BottomBar() {

    BottomAppBar(
        modifier = Modifier.clip(
            RoundedCornerShape(
                topStart = 10.dp,
                topEnd = 10.dp
            )

        ),
        elevation = 5.dp,
        backgroundColor = Color.Black,
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {

            BottomNavigationItem(
                selectedContentColor = Color.White,
                unselectedContentColor = Color.LightGray,
                selected = true,
                onClick = { /*TODO*/ },
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "icon",
                        modifier = Modifier.size(30.dp)
                    )
                },
            )

            BottomNavigationItem(selectedContentColor = Color.Black,
                unselectedContentColor = Color.LightGray,
                selected = false,
                onClick = { /*TODO*/ },
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.Fullscreen,
                        contentDescription = "icon",
                        modifier = Modifier.size(30.dp)
                    )
                })

            BottomNavigationItem(selectedContentColor = Color.Black,
                unselectedContentColor = Color.LightGray,
                selected = false,
                onClick = { /*TODO*/ },
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.Tune,
                        contentDescription = "icon",
                        modifier = Modifier.size(30.dp)
                    )
                })

            BottomNavigationItem(
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.LightGray,
                selected = false,
                onClick = { /*TODO*/ },
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.RadioButtonUnchecked,
                        contentDescription = "icon",
                        modifier = Modifier.size(30.dp)
                    )
                })
        }
    }
}

@Composable
fun TopBar() {
    TopAppBar(
        modifier = Modifier.padding(horizontal = 18.dp),
        elevation = 0.dp,
        backgroundColor = Color.White,
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(65.dp)
                    .clip(shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = " Profile picture",
                    contentScale = ContentScale.Fit
                )
            }

            Text(buildAnnotatedString {
                append("Hello, ")
                withStyle(
                    style = SpanStyle(
                        color = colorResource(id = R.color.black),
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                ) {
                    append("Geraud")
                }
            }, modifier = Modifier.padding(start = 10.dp))

            Spacer(modifier = Modifier.weight(1f))

            BadgedBox(modifier = Modifier.padding(end = 10.dp),
                badge = {
                    Badge(
                        Modifier
                            .clip(CircleShape)
                            .background(Color.Red)
                            .align(Alignment.BottomEnd)
                    )
                }) {
                Icon(
                    Icons.Filled.Notifications,
                    contentDescription = "notification icon",
                    tint = Color.Black
                )
            }
        }
    }
}

@Composable
fun MenuItems(selectedStates: SnapshotStateMap<String, Boolean>) {

}

@Composable
fun WorkOutType(
    category: Category,
    selected: Boolean,
    onSelectedChange: (selectedKey: String, selected: Boolean) -> Unit
) {
    val backgroundColor = if (selected) Color.Black else Color.White
    val textColor = if (selected) Color.White else Color.Black

    OutlinedButton(
        shape = RoundedCornerShape(20.dp),
        contentPadding = PaddingValues(6.dp),
        border = BorderStroke(width = 1.dp, color = Color.Black),
        onClick = { onSelectedChange(category.name, !selected) },
        colors = ButtonDefaults.textButtonColors(
            backgroundColor = backgroundColor,
        )
    ) {
        Text(text = category.name, color = textColor, fontSize = 18.sp)
    }
}