<template>
  <div class="tab-template detail-header-container">
    <div class="detail-header">
      <!-- @load="resize(this)" -->
      <div class="bg-box">
        <img @click="likeProduct(productId)" :src="woojooInfo.object?.image" alt="" >
      </div>
      <span class="like-add">
        <i class="fas fa-heart icon"></i>{{woojooInfo.object?.like_num}}
      </span>
      
    </div>
    <HeaderView/>
    <div class="mypage-container" id="detail-container">
      <!-- tabs menu -->
      <div class="mypage-tabs">
        <div class="tabs-menu-container">
          <div class="tab tab-type" @click="tabPosition('detail-content')">
            상세정보
          </div>
          <div class="tab tab-likes" @click="tabPosition('mypage-likes')">
            구매처 링크
          </div>
          <div class="tab tab-reviews" @click="tabPosition('detail-reviews')">
            리뷰
          </div>
          <div class="tabs-now">
            <span class="under-bar"></span>
          </div>
        </div>
      </div>
      <div class="tabs-container">
        <div class="tab-control">
          <!-- tab1 -->
          <div class="tab-section detail-content">
            <el-scrollbar>
              <DetailContent/>
            </el-scrollbar>
          </div>
          <!-- tab2 -->
          <div class="tab-section mypage-likes">
            <el-scrollbar>
              <ShopLinks/>
            </el-scrollbar>
          </div>
          <!-- tab3 -->
          <div class="tab-section detail-reviews">
            <el-scrollbar>
              <DetailReviews/>
            </el-scrollbar>
          </div>
        </div>
        <div class="scroll-false"></div>
      </div>
    </div>

  </div>
</template>

<script setup>
import HeaderView from "@/views/common/HeaderView.vue"
import DetailContent from "@/views/woojoo_search/DetailContent.vue"
import DetailReviews from "@/views/woojoo_search/DetailReviews.vue"
import ShopLinks from "@/views/woojoo_search/ShopLinks.vue"
import { ref, computed, onMounted } from "vue";
import { useRouter, useRoute } from "vue-router";
import { useStore } from "vuex";

const router = useRouter();
const route = useRoute()
// const linkTo = () => router.push({ name: "", params: { }})
const store = useStore();
const woojooInfo = computed(() => store.getters.woojooInfo);
// const likeNum = computed(()=> store.getters.woojooInfo.object.like_num);
const position = ref(0)
const productId = route.params.productPk
// const likeList = computed(() => store.getters.profile.likeList)
const isLike = computed(() => store.getters.isLike);

onMounted(() => {
  store.dispatch('likeCheck', productId)
  store.dispatch("fetchWoojooInfo", productId)
  const mypageTabs = document.querySelector("#detail-container")
  window.onscroll = function() {scrollTabMenu()};
  function scrollTabMenu() {
    position.value = mypageTabs.getBoundingClientRect().top

    if (position.value <= 120) {
      // document.querySelector(".mypage-header .header-nick").style.top = "0"
      // document.querySelector(".mypage-header .header-nick").style.fontSize = "1.2rem"
      document.querySelector(".scroll-false").style.display = "none"
    } else {
      // document.querySelector(".mypage-header .header-nick").style.top = `${240 * ((position.value - 120) / 390)}px`
      // document.querySelector(".mypage-header .header-nick").style.fontSize = `${1.5 - 0.3 * (1- (position.value - 120) / 390)}rem`
      document.querySelector(".detail-header .bg-box").style.opacity = `${(position.value - 200) / 80}`
      // document.querySelector(".mypage-profile .profile-pic").style.opacity = `${(position.value - 350) / 160}`
      // document.querySelector(".mypage-profile .level").style.opacity = `${(position.value - 350) / 160}`
      // document.querySelector(".mypage-profile .email").style.opacity = `${(position.value - 350) / 160}`
      // document.querySelector(".mypage-profile .edit-profile").style.opacity = `${(position.value - 350) / 160}`
      document.querySelector(".scroll-false").style.display = "none"
    }
  }
})

function likeProduct(productId) {
  console.log(store.getters.isLike)
  if (store.getters.isLike === false) {
    console.log('좋아요 누름')
    store.dispatch('likeProduct', productId)
    router.go()
  } else if (store.getters.isLike === true) {
    console.log('싫어요')
    store.dispatch('unlikeProduct', productId)
    router.go()
  }
}

function resize(img){
  // 원본 이미지 사이즈 저장
  var width = img.width;
  var height = img.height;
  // 가로, 세로 최대 사이즈 설정
  var maxWidth = 100;   // 원하는대로 설정. 픽셀로 하려면 maxWidth = 100  이런 식으로 입력
  var maxHeight = 100;   // 원래 사이즈 * 0.5 = 50%
  // 가로나 세로의 길이가 최대 사이즈보다 크면 실행  
  if(width > maxWidth || height > maxHeight){
    // 가로가 세로보다 크면 가로는 최대사이즈로, 세로는 비율 맞춰 리사이즈
    if(width > height){
        resizeWidth = maxWidth;
        resizeHeight = Math.Round((height * resizeWidth) / width);
    // 세로가 가로보다 크면 세로는 최대사이즈로, 가로는 비율 맞춰 리사이즈
    }else{
        resizeHeight = maxHeight;
        resizeWidth = Math.Round((width * resizeHeight) / height);
    }
  // 최대사이즈보다 작으면 원본 그대로
  }else{
    resizeWidth = width;
    resizeHeight = height;
  }
  // 리사이즈한 크기로 이미지 크기 다시 지정
  img.width = resizeWidth;
  img.height = resizeHeight;
}
// function likeProduct(a) {
//   store.dispatch('likeProduct',a)
// }

function tabPosition(tabName) {
  if (window.innerWidth >= 500) {
    if (tabName == "detail-content") {
      document.querySelector(".tabs-container .tab-control").style.margin = "0 0 0 0"
      document.querySelector(".tabs-container .scroll-false").style.left = "0"
      document.querySelector(".tabs-now").style.left = "0"
    } else if (tabName == "mypage-likes") {
      document.querySelector(".tabs-container .tab-control").style.margin = "0 0 0 -500px"
      document.querySelector(".tabs-container .scroll-false").style.left = "-500px"
      document.querySelector(".tabs-now").style.left = "33%"
    } else if (tabName == "detail-reviews") {
      document.querySelector(".tabs-container .tab-control").style.margin = "0 0 0 -1000px"
      document.querySelector(".tabs-container .scroll-false").style.left = "-1000px"
      document.querySelector(".tabs-now").style.left = "66%"
    }
  } else {
    if (tabName == "detail-content") {
      document.querySelector(".tabs-container .tab-control").style.margin = "0 0 0 0"
      document.querySelector(".tabs-container .scroll-false").style.left = "0"
      document.querySelector(".tabs-now").style.left = "0"
    } else if (tabName == "mypage-likes") {
      document.querySelector(".tabs-container .tab-control").style.margin = "0 0 0 -100vw"
      document.querySelector(".tabs-container .scroll-false").style.left = "-100vw"
      document.querySelector(".tabs-now").style.left = "33%"
    } else if (tabName == "detail-reviews") {
      document.querySelector(".tabs-container .tab-control").style.margin = "0 0 0 -200vw"
      document.querySelector(".tabs-container .scroll-false").style.left = "-200vw"
      document.querySelector(".tabs-now").style.left = "66%"
    }
  }
}
</script>

<style>

</style>