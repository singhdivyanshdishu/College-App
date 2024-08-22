//package com.example.collegeapp.screens
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material3.Card
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.livedata.observeAsState
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.lifecycle.viewmodel.compose.viewModel
//import coil.compose.AsyncImagePainter
//import coil.compose.rememberAsyncImagePainter
//import com.example.collegeapp.itemview.NoticeItemView
//import com.example.collegeapp.viewmodel.BannerViewModel
//import com.example.collegeapp.viewmodel.CollegeInfoViewModel
//import com.example.collegeapp.viewmodel.NoticeViewModel
//import com.google.accompanist.pager.ExperimentalPagerApi
//import com.google.accompanist.pager.HorizontalPager
//import com.google.accompanist.pager.HorizontalPagerIndicator
//import com.google.accompanist.pager.rememberPagerState
//import kotlinx.coroutines.delay
//import kotlinx.coroutines.yield
//
//@OptIn(ExperimentalPagerApi::class)
//@Composable
//fun Home() {
//
//    val bannerViewModel: BannerViewModel = viewModel()
//    val noticeViewModel: NoticeViewModel = viewModel()
//    val collegeInfoViewModel: CollegeInfoViewModel = viewModel()
//
//
//
//
//    val bannerList by bannerViewModel.bannerList.observeAsState(emptyList())
//    val noticeList by noticeViewModel.noticeList.observeAsState(emptyList())
//    val collegeInfo by collegeInfoViewModel.collegeInfo.observeAsState(null)
//
//
//
//    bannerViewModel.getBanner()
//    noticeViewModel.getNotice()
//    collegeInfoViewModel.getCollegeInfo()
//
//
//    val pagerState = rememberPagerState(initialPage = 0)
//    val imageSlider =ArrayList<AsyncImagePainter>()
//
//
//    if(bannerList!=null) {
//        bannerList.forEach{
//            imageSlider.add(rememberAsyncImagePainter(model = it.url))
//        }
//    }
//
//    LaunchedEffect(key1 = Unit) {
//        try {
//            while (true){
//                yield()
//                delay(2600)
//                pagerState.animateScrollToPage(page =(pagerState.currentPage + 1) % pagerState.pageCount)
//            }
//        } catch (e: Exception) {
//            TODO("Not yet implemented")
//        }
//    }
//
//    LazyColumn(modifier = Modifier.padding(8.dp)) {
//        item {
//            HorizontalPager(
//                count = imageSlider.size,
//                state = pagerState,
//
//                ) { pager ->
//                Card(modifier = Modifier.height(250.dp)) {
//                    Image(
//                        painter = imageSlider[pager],
//                        contentDescription = "banner",
//                        modifier = Modifier
//                            .height(250.dp)
//                            .fillMaxWidth()
//                    )
//                }
//            }
//        }
//        item {
//            Row(
//                horizontalArrangement = Arrangement.Center,
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                HorizontalPagerIndicator(
//                    pagerState = pagerState,
//                    modifier = Modifier.padding(8.dp)
//                )
//            }
//        }
//        item {
//            if (collegeInfo != null)
//                Text(
//                    text = collegeInfo!!.name!!,
//                    //color = Color.White,
//                    fontWeight = FontWeight.Bold,
//                    fontSize = 18.sp,
//
//
//                    )
//            Spacer(modifier = Modifier.height(8.dp))
//
//            Text(
//                text = collegeInfo!!.desc!!,
//                //color = Color.White,
//
//                fontSize = 16.sp
//
//            )
//
//            Spacer(modifier = Modifier.height(8.dp))
//
//            Text(
//                text = collegeInfo!!.address!!,
//                //color = Color.White,
//
//                fontSize = 16.sp,
//
//
//                )
//
//            Spacer(modifier = Modifier.height(8.dp))
//
//            Text(
//                text = collegeInfo!!.websiteLink!!,
//                //color = Color.White,
//
//                fontSize = 16.sp,
//                color = Color.Blue
//
//            )
//            Spacer(modifier = Modifier.height(12.dp))
//
//            Text(
//                text = "Notices",
//                //color = Color.White,
//                fontWeight = FontWeight.Bold,
//                fontSize = 18.sp,
//
//                )
//
//            Spacer(modifier = Modifier.height(8.dp))
//        }
//
//
////        items(noticeList ?: emptyList()) { notice ->
////            NoticeItemView(notice, delete = { noticeToDelete ->
////                noticeViewModel.deleteNotice(noticeToDelete)
////
////            })
////        }
//
//        items(noticeList ?: emptyList()) { notice -> // Notice is now of type NoticeModel
//            NoticeItemView(notice, delete = { noticeToDelete -> // Pass the entire NoticeModel
//                noticeViewModel.deleteNotice(noticeToDelete)
//            })
//        }
//    }
//
//
//    }
//


//CHATGPT

package com.example.collegeapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.collegeapp.itemview.NoticeItemView
import com.example.collegeapp.viewmodel.BannerViewModel
import com.example.collegeapp.viewmodel.CollegeInfoViewModel
import com.example.collegeapp.viewmodel.NoticeViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Home() {

    val bannerViewModel: BannerViewModel = viewModel()
    val noticeViewModel: NoticeViewModel = viewModel()
    val collegeInfoViewModel: CollegeInfoViewModel = viewModel()

    val bannerList by bannerViewModel.bannerList.observeAsState(emptyList())
    val noticeList by noticeViewModel.noticeList.observeAsState(emptyList())
    val collegeInfo by collegeInfoViewModel.collegeInfo.observeAsState(null)

    bannerViewModel.getBanner()
    noticeViewModel.getNotice()
    collegeInfoViewModel.getCollegeInfo()

    val pagerState = rememberPagerState(initialPage = 0)

    LaunchedEffect(key1 = Unit) {
        try {
            while (true) {
                yield()
                delay(2600)
                pagerState.animateScrollToPage(page = (pagerState.currentPage + 1) % pagerState.pageCount)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            // Log error or handle it gracefully
        }
    }

    LazyColumn(modifier = Modifier.padding(8.dp)) {
        item {
            HorizontalPager(
                count = bannerList.size,
                state = pagerState,
            ) { page ->
                Card(modifier = Modifier.height(250.dp)) {
                    Image(
                        painter = rememberAsyncImagePainter(model = bannerList[page].url),
                        contentDescription = "banner",
                        modifier = Modifier
                            .height(250.dp)
                            .fillMaxWidth()
                    )
                }
            }
        }

        item {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                HorizontalPagerIndicator(
                    pagerState = pagerState,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }

        item {
            collegeInfo?.let { info ->
                Text(
                    text = info.name ?: "No Name",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = info.desc ?: "No Description",
                    fontSize = 16.sp,
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = info.address ?: "No Address",
                    fontSize = 16.sp,
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = info.websiteLink ?: "No Website",
                    fontSize = 16.sp,
                    color = Color.Blue
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Notices",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
            )

            Spacer(modifier = Modifier.height(8.dp))
        }

        if (noticeList.isNullOrEmpty()) {
            item {
                Text(text = "No notices available")
            }
        } else {
            items(noticeList) { notice ->
                notice?.let {
                    NoticeItemView(it, delete = { noticeToDelete ->
                        noticeViewModel.deleteNotice(noticeToDelete)
                    })
                }
            }
        }
    }
}
