package dme.systems.storeapp.ui.theme


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import dme.systems.storeapp.data.Advertisement
import kotlin.math.absoluteValue

val advertisements = listOf(
    Advertisement(
        title = "Premium Home Gym",
        imageUrl = "https://img.freepik.com/premium-photo/charming-young-lady-sportswear-smiling-sitting-modern-exercise-bike-near-mirror-stylish-gym_184353-728.jpg",
        description = "Transform your home with our top-of-the-line home gym equipment. Build strength and stay fit without leaving the house!"
    ),
    Advertisement(
        title = "All-in-One Treadmill",
        imageUrl = "https://res.cloudinary.com/iconfitness/image/upload/dpr_auto,f_auto,q_auto,w_auto/v1/site--1/NTL19124_galleryb.png",
        description = "Upgrade your home workout with our high-quality all-in-one treadmill – featuring adjustable incline, speeds, and built-in workout programs!"
    ),
    Advertisement(
        title = "Yoga Essentials Kit",
        imageUrl = "https://sanskritiyogpeeth.org/blog/wp-content/uploads/2020/09/importance-of-yoga-mat.png",
        description = "Find balance and serenity with our Yoga Essentials Kit, complete with a premium yoga mat, blocks, and strap. Perfect for beginners and advanced yogis alike."
    ),
    Advertisement(
        title = "Nutrition Coaching",
        imageUrl = "https://twobrainbusiness.com/wp-content/uploads/elementor/thumbs/Podcast-1-57-q6mx4aybzqpe0wy8688bbxytmplrt2scjnin45n4by.jpg",
        description = "Our expert nutrition coaches are ready to guide you towards your health and fitness goals with personalized meal plans, support, and advice."
    ),
    Advertisement(
        title = "High-Intensity Interval Training",
        imageUrl = "https://i.ytimg.com/vi/zr08J6wB53Y/maxresdefault.jpg",
        description = "Push your limits and achieve faster results with our high-intensity interval training program, designed to burn fat and increase muscle strength."
    )
)

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HorizontalPagerScreen() {
    val pagerState = rememberPagerState()
    HorizontalPager(
        count = advertisements.size,
        state = pagerState,
    ) { page ->
        Card(
            Modifier
                .fillMaxWidth()
                .height(250.dp)
                .graphicsLayer {
                    val pageOffset = (
                            (pagerState.currentPage - page) + pagerState.currentPageOffset
                            ).absoluteValue
                    // translate the contents by the size of the page, to prevent the pages from sliding in from left or right and stays in the center
                    translationX = pageOffset * size.width
                    // apply an alpha to fade the current page in and the old page out
                    alpha = 1 - pageOffset.absoluteValue
                }) {

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(advertisements[page].imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = advertisements[page].description,
                contentScale = ContentScale.Crop,
                modifier = Modifier.clip(
                    RoundedCornerShape(
                        8.dp
                    )
                )
            )
        }

    }
}

@Composable
fun AdvItem() {


}