package dev.sanskar.featuretesteduco.core.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*


import androidx.compose.ui.Alignment
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import dev.sanskar.featuretesteduco.R
import dev.sanskar.featuretesteduco.core.domain.models.CateforyOverView
import dev.sanskar.featuretesteduco.core.domain.models.Category
import dev.sanskar.featuretesteduco.core.domain.models.CourseOverview
import dev.sanskar.featuretesteduco.core.util.Screen
import dev.sanskar.featuretesteduco.ui.theme.Grey400
import dev.sanskar.featuretesteduco.ui.theme.Grey500
import dev.sanskar.featuretesteduco.ui.theme.Grey900

@Composable
fun UserHeader(
    name: String,
    profilePictureUrl: String? = null
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(text = "Hola, $name!", fontSize = 20.sp, color = Grey900)
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = "What do you wanna learn today?", fontSize = 14.sp, color = Grey500)
        }
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(data = profilePictureUrl)
                .crossfade(durationMillis = 1000)
                .placeholder(drawableResId = R.drawable.ic_placeholder)
                .transformations(CircleCropTransformation())
                .build(),
            modifier = Modifier.size(44.dp),
            contentDescription = "Profile Photo"
        )
    }
}


@Composable
fun PopularCategory(
    onNavigate: (String) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Popular Category \nin our platform",
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold,
            color = Grey900
        )
        TextButton(
            onClick = {
                onNavigate(Screen.PopularCategoryListScreen.route)
            },
            modifier = Modifier.offset(y = 15.dp),
            contentPadding = PaddingValues(0.dp)
        ) {
            Text(text = "see more", color = Grey500)
        }
    }
}


@Composable
fun CategoryCard(
    category: CateforyOverView,
    imageLoader: ImageLoader,
    modifier: Modifier = Modifier,
    onNavigate: (String) -> Unit = {}
) {
    Card(
        modifier = modifier.width(150.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 5.dp
    ) {
        Box(
            modifier = modifier
                .height(140.dp)
                .clickable {
                    onNavigate(Screen.SearchScreen.route)
                }
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    model = category.categoryImageUrl,
                    imageLoader = imageLoader
                ),
                contentDescription = "Course Thumbnail",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = 200f
                        )
                    )
            )
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(18.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Text(
                    text = category.categoryName, style = TextStyle(
                        color = Color.Green,
                        fontSize = 20.sp
                    ), fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun randomcard(


){
    Card(
        modifier = Modifier.width(350.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 5.dp
    ){
        Text(
            text = "Delete Course",
            color = Color.Red,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            modifier = Modifier
                .padding(end = 10.dp, start = 20.dp)

                .clickable {

                }
        )

    }

}


@Composable
fun MostWatchedCourses(
    onNavigate: (String) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Most Watching \nCategory in month",
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold,
            color = Grey900
        )
        TextButton(
            onClick = {
                onNavigate(Screen.MostWatchedCourseListScreen.route)
            },
            modifier = Modifier.offset(y = 15.dp),
            contentPadding = PaddingValues(0.dp)
        ) {
            Text(text = "see more", color = Grey500)
        }
    }
}
@Composable
fun Courses(
    onNavigate: (String) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Your Courses",
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold,
            color = Grey900
        )
        TextButton(
            onClick = {
                onNavigate(Screen.TeacherCourseListScreen.route)
            },
            modifier = Modifier.offset(y = 15.dp),
            contentPadding = PaddingValues(0.dp)
        ) {
            Text(text = "see more", color = Grey500)
        }
    }
}
@Composable
fun CoursesRated(
    onNavigate: (String) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Your Highly Rated Courses",
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold,
            color = Grey900
        )
        TextButton(
            onClick = {
                onNavigate(Screen.TeacherCourseListScreen.route)
            },
            modifier = Modifier.offset(y = 15.dp),
            contentPadding = PaddingValues(0.dp)
        ) {
            Text(text = "see more", color = Grey500)
        }
    }
}
@Composable
fun CoursesEnrolled(
    onNavigate: (String) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Your Most Enrolled Courses",
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold,
            color = Grey900
        )
        TextButton(
            onClick = {
                onNavigate(Screen.TeacherCourseListScreen.route)
            },
            modifier = Modifier.offset(y = 15.dp),
            contentPadding = PaddingValues(0.dp)
        ) {
            Text(text = "see more", color = Grey500)
        }
    }
}


@Composable
fun PreviousWatchedCourses(
    onNavigate: (String) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Continue to watch \nprevious class",
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold,
            color = Grey900
        )
        TextButton(
            onClick = {
                onNavigate(Screen.MostWatchedCourseListScreen.route)
            },
            modifier = Modifier.offset(y = 15.dp),
            contentPadding = PaddingValues(0.dp)
        ) {
            Text(text = "see more", color = Grey500)
        }
    }
}

@Composable
fun OthersWatchedCourses(onNavigate: (String) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "What Others are \nwatching in a app",
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold,
            color = Grey900
        )
        TextButton(
            onClick = {
                onNavigate(Screen.MostWatchedCourseListScreen.route)
            },
            modifier = Modifier.offset(y = 15.dp),
            contentPadding = PaddingValues(0.dp)
        ) {
            Text(text = "see more", color = Grey500)
        }
    }
}

@Composable
fun CourseOverviewCard(
    course: CourseOverview,
    imageLoader: ImageLoader,
    onClick: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {



        Card(
            modifier = modifier.width(150.dp),
            shape = RoundedCornerShape(8.dp),
            elevation = 0.dp
        ) {
            Column(modifier = modifier.fillMaxWidth()) {
                Box(
                    modifier = modifier
                        .height(140.dp)
                        .clickable {
                            onClick(Screen.CourseDetailScreen.route + "/${course.courseId}")
                        }
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(
                            model = course.courseThumbnailUrl,
                            imageLoader = imageLoader
                        ),
                        contentDescription = "Course Thumbnail",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                    Box(
                        modifier = modifier
                            .fillMaxSize()
                            .padding(18.dp),
                        contentAlignment = Alignment.TopStart
                    ) {
                        course.tag?.let {
                            Text(
                                text = it,
                                style = TextStyle(
                                    color = Color.White,
                                    fontSize = 14.sp
                                ),
                                fontWeight = FontWeight.Bold,
                                modifier = modifier
                                    .background(Color(0XFFFD853A))
                                    .padding(horizontal = 15.dp, vertical = 4.dp)
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(15.dp))
                Column(
                    modifier = modifier.fillMaxWidth()
                ) {
                    Text(
                        text = course.courseName,
                        fontWeight = FontWeight.Bold,
                        color = Color(0XFF282F3E)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = course.courseTeacherName,
                        color = Color(0XFF585D69),
                        fontSize = 12.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            modifier = modifier.fillMaxHeight(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = course.rating.toString(), color = Grey400, fontSize = 11.sp)
                            Spacer(modifier = Modifier.width(6.dp))
                            RatingRow(rating = course.rating.toInt(), size = 15)
                        }
                        Text(
                            text = "(${course.noOfStudentRated.toString()})",
                            color = Grey400,
                            fontSize = 11.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
//                    course.price?.let {
//                        Text(
//                            text = it,
//                            color = Color(0XFF585D69),
//                            fontSize = 12.sp
//                        )
//                    }

                }
            }


        }



    }




@Composable
fun RatingRow(
    //modifier: Modifier = Modifier,
    rating: Int,
    size: Int
) {
    var rating by remember {
        mutableStateOf(rating)
    }
    Row(

        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        for (i in 1..5) {
            Icon(
                painter = painterResource(id = R.drawable.ic_star),
                contentDescription = "star",
                modifier = Modifier
                    .width(size.dp)
                    .height(size.dp),
                tint = if (i <= rating) Color(0xFFFFA927) else Color(0XFFA2ADB1)
            )

        }
    }
}