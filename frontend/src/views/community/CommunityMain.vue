<template>
  <div class="tab-template container">
    <div class="tab-header">
      <div class="tab-title">
        커뮤니티
      </div>
      <div class="mode-toggle">
        <ModeToggle/>
      </div>
    </div>
    <div class="header-content">
      <img class="content-img" src="@/assets/images/community.png" alt="communityMainPic">
      <p class="header-intro">더 많은 사람들과 더 많은 우주를 나눠보세요</p>
    </div>
    
    <div class="inner-container">
      <div class="inner-section">
        <div class="mt"></div>
        
        <!-- HOT review -->
        <div class="community-category">
          <div class="category-header">
            <div class="category-title">
              핫 리뷰
            </div>
          </div>
          <div class="category-intro">
            지금 하트를 많이 받은 리뷰를 만나보세요
          </div>
          <div class="category-carousel">
            <el-scrollbar>
              <div class="carousel-container">
                <div class="review-card" v-for="review, idx in reviewsData.dto?.hotreview">
                  <div @click="linkTo('hotreview', idx + 1)">
                    <div class="image">
                      <img :src="review.img[0]" alt="reviewThumbnail" v-if="review.img[0]">
                      <span class="like">
                        <i class="fas fa-heart heart"></i> {{ review.like }}
                      </span>
                    </div>
                    <div class="card-content">
                      <div class="wooju cl-ellipsis">
                        {{ review.product_name }}
                      </div>
                      <div class="rate">
                        <i class="fas fa-star star"></i> {{ review.star }}
                      </div>
                      <div class="preview">
                        {{ review.content }}
                      </div>
                    </div>
                  </div>
                </div>
                <div class="more-card review-card bs-none" @click="linkTo('hotreview', 0)">
                  <div class="more-btn">
                    <span>
                      더보기 <i class="fas fa-arrow-right"></i>
                    </span>
                  </div>
                </div>
              </div>
            </el-scrollbar>
          </div>
        </div>

        <!-- HOT PRODUCTS review -->
        <div class="community-category">
          <div class="category-header">
            <div class="category-title">
              핫한 술 리뷰
            </div>
          </div>
          <div class="category-intro">
            지금 가장 많이 리뷰가 달린 술을 구경하세요
          </div>
          <div class="category-carousel">
            <el-scrollbar>
              <div class="carousel-container">
                <div class="review-card" v-for="review, idx in reviewsData.dto?.hotproductsreview">
                  <div @click="linkTo('Hotproductsreview', idx + 1)">
                    <div class="image">
                      <img :src="review.img[0]" alt="reviewThumbnail" v-if="review.img[0]">
                      <span class="like">
                        <i class="fas fa-heart heart"></i> {{ review.like }}
                      </span>
                    </div>
                    <div class="card-content">
                      <div class="wooju cl-ellipsis">
                        {{ review.product_name }}
                      </div>
                      <div class="rate">
                        <i class="fas fa-star star"></i> {{ review.star }}
                      </div>
                      <div class="preview">
                        {{ review.content }}
                      </div>
                    </div>
                  </div>
                </div>
                <div class="more-card review-card bs-none" @click="linkTo('Hotproductsreview', 0)">
                  <div class="more-btn">
                    <span>
                      더보기 <i class="fas fa-arrow-right"></i>
                    </span>
                  </div>
                </div>
              </div>
            </el-scrollbar>
          </div>
        </div>

        <!-- GOSU review -->
        <div class="community-category">
          <div class="category-header">
            <div class="category-title">
              맛 고수 리뷰
            </div>
          </div>
          <div class="category-intro">
            전통주를 사랑하는 맛 고수들의 풍부한 리뷰
          </div>
          <div class="category-carousel">
            <el-scrollbar>
              <div class="carousel-container">
                <div class="review-card" v-for="review, idx in reviewsData.dto?.gosureview">
                  <div @click="linkTo('gosureview', idx + 1)">
                    <div class="image">
                      <img :src="review.img[0]" alt="reviewThumbnail" v-if="review.img[0]">
                      <span class="like">
                        <i class="fas fa-heart heart"></i> {{ review.like }}
                      </span>
                    </div>
                    <div class="card-content">
                      <div class="wooju cl-ellipsis">
                        {{ review.product_name }}
                      </div>
                      <div class="rate">
                        <i class="fas fa-star star"></i> {{ review.star }}
                      </div>
                      <div class="preview">
                        {{ review.content }}
                      </div>
                    </div>
                  </div>
                </div>
                <div class="more-card review-card bs-none" @click="linkTo('gosureview', 0)">
                  <div class="more-btn">
                    <span>
                      더보기 <i class="fas fa-arrow-right"></i>
                    </span>
                  </div>
                </div>
              </div>
            </el-scrollbar>
          </div>
        </div>

        <!-- RECENT review -->
        <div class="community-category">
          <div class="category-header">
            <div class="category-title">
              최신 리뷰
            </div>
          </div>
          <div class="category-intro">
            다른 사람들은 지금 무슨 술을 먹고 있을까요?
          </div>
          <div class="category-carousel">
            <el-scrollbar>
              <div class="carousel-container">
                <div class="review-card" v-for="review, idx in reviewsData.dto?.recentreview">
                  <div @click="linkTo('recentreview', idx + 1)">
                    <div class="image">
                      <img :src="review.img[0]" alt="reviewThumbnail" v-if="review.img[0]">
                      <span class="like">
                        <i class="fas fa-heart heart"></i> {{ review.like }}
                      </span>
                    </div>
                    <div class="card-content">
                      <div class="wooju cl-ellipsis">
                        {{ review.product_name }}
                      </div>
                      <div class="rate">
                        <i class="fas fa-star star"></i> {{ review.star }}
                      </div>
                      <div class="preview">
                        {{ review.content }}
                      </div>
                    </div>
                  </div>
                </div>
                <div class="more-card review-card bs-none" @click="linkTo('recentreview', 0)">
                  <div class="more-btn">
                    <span>
                      더보기 <i class="fas fa-arrow-right"></i>
                    </span>
                  </div>
                </div>
              </div>
            </el-scrollbar>
          </div>
        </div>

      </div>
    </div>
  </div>
</template>

<script setup>
import ModeToggle from "@/views/common/ModeToggle.vue"
import { ref, computed, onMounted } from "vue";
import { useRouter } from "vue-router";
import { useStore } from "vuex";

const router = useRouter();
const linkTo = (category, post) => router.push({ name: "CommunityCategory", query: { name: category, post: post }})

// vuex axios
const store = useStore();
const reviewsData = computed(() => store.getters.reviews);

onMounted(() => {
  store.dispatch("fetchAllReviews")
})


// header scroll event js
const position = ref(0)

onMounted(() => {
  const innerContainer = document.getElementsByClassName("inner-container")
  window.onscroll = function() {scrollInnerHeader()};
  
  function scrollInnerHeader() {
    position.value = innerContainer[0].getBoundingClientRect()

    if (position.value.top < 60) {
      document.querySelector(".tab-header .tab-title").style.top = "0";
      document.querySelector(".tab-header").style.boxShadow = "0px 4px 6px 6px rgba(0, 0, 0, 0.05)";
    } else {
      document.querySelector(".tab-header").style.boxShadow = "0px 4px 6px 6px rgba(0, 0, 0, 0.0)";
      document.querySelector(".tab-header .tab-title").style.top = `${50 * (position.value.top / 450)}px`;
      document.querySelector(".tab-header .tab-title").style.fontSize = `${1.2 + position.value.top / 450}rem`;
      document.querySelector(".header-content .content-img").style.opacity = `${((position.value.top - 260) / 250 )}`;
      document.querySelector(".header-content .content-img").style.scale = `${((position.value.top - 60) /  390)}`;
      document.querySelector(".header-content .header-intro").style.opacity = `${((position.value.top - 260) / 190 )}`;
    }
  }
})

</script>

<style>
.mt {
  width: 100%;
  max-width: 500px;
  height: 60px;
  background-color: transparent;
}

</style>