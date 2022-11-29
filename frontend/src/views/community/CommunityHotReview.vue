<template>
  <div class="container pt-60">
    <HeaderView/>
    <h1 class="hot-review-title">모든 핫 리뷰</h1>
      <div class="review-body bs-card-03" v-for="review in reviewsData.dto?.hotreview">
          <div class="review-header">
            <div class="profile">
              <div class="profile-box">
                <img class="profile-img" :src="review.user_img" alt="">
              </div>
              <div class="review-writer">
                <div class="review-nickname">{{review.user_nickname}}</div>
                <div class="review-level">{{review?.gosu}}</div>
              </div>
            </div>
            <span class="review-date">{{review.time}}</span> 
          </div>
          <!-- <img src="@/assets/images/pic3.jpg"/> -->
          <div class="block text-center" m="t-4">
            <el-carousel trigger="click" height="250px" indicator-position="outside" :autoplay="false">
              <el-carousel-item v-for="review_pic in review.img" :key="i">
                <!-- <h3 class="small justify-center" text="2xl">{{ item }}</h3>
                <img :src="`@/assets/images/pic${i}.jpg`" alt="">
                <img src="@/assets/images/pic1.jpg" alt="pic"> -->
                <img :src="review_pic" alt="">
              </el-carousel-item>
              <span class="like">
                <i class="fas fa-heart icon" @click="likeReview(review.id)"></i> {{review.like}}
              </span>              
            </el-carousel>
          </div>          
          <div class="review-content">
            <div class="wooju">
              <span class="wooju-name">제목 : </span>
               {{review.title}}
            </div>
            <div class="wooju">
              <span class="wooju-name">술 이름 : </span>
               {{review.product_name}}
              <i class="fas fa-chevron-right"></i>
            </div>
            <div class="rate">
              <span class="wooju-star">별점  </span>
              <i class="fas fa-star star"></i> {{review.star}}
            </div>
            <p class="review-text">
              {{review.content}}
            </p>
          </div>
      </div>
  </div>
</template>

<script setup>
import HeaderView from "@/views/common/HeaderView.vue"
import { ref, computed, onMounted } from "vue";
import { useStore } from "vuex";

const store = useStore();
const reviewsData = computed(() => store.getters.reviews);
onMounted(() => {
  store.dispatch("fetchAllReviews")
})

// const isLikeReview = computed(() => store.getters.isLikeReview);
// function likeReview(ReviewId) {
//   console.log(store.getters.isLikeReview)
//   if (store.getters.isLikeReview === false) {
//     console.log('좋아요 누름')
//     store.dispatch('likeReview', reviewId)
//     router.go()
//   } else if (store.getters.isLikeReview === true) {
//     console.log('싫어요')
//     store.dispatch('unlikeReview', reviewId)
//     router.go()
//   }
// }

</script>

<style>

</style>
