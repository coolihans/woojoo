<template>
  <div class="tab-template container">
    <div class="tab-header">
      <div class="mainhome-logo">
        <img src="@/assets/images/logo-bg.png" alt="homelogo">
      </div>
      <div class="mode-toggle">
        <ModeToggle/>
      </div>
    </div>
    <div class="header-content">
      <p class="home-intro">당신의 우주에 전통주를 채워보세요</p>
    </div>
    
    <div class="inner-container mainhome-container">
      <div class="rec-container inner-section">
        <div class="mt"></div>

        <!-- recommendation -->
        <div class="home-corner recommendation-corner">
          <div class="corner-img-box">
            <img src="@/assets/images/home/recommendation.png" alt="" class="corner-img">
          </div>
          <div class="home-content rec-intro">
            아직 어떤 전통주를 마셔봐야할지<br/>모르는 여러분에게
            <span>나만의 취향에 맞는 술을<br/>추천 받아 보세요</span>
          </div>
          <div class="btn-box">
            <div class="togo-btn" @click="linkTo('MyRecommendationType')">
              취향 알아보러 가기 >
            </div>
          </div>
        </div>
        
        <!-- product search -->
        <div class="home-corner search-corner">
          <div class="corner-img-box">
            <img src="@/assets/images/home/search.png" alt="" class="corner-img">
          </div>
          <div class="home-content search-intro">
            전통주의 상세 정보부터 최저가까지<br/>한눈에 알고 싶은 여러분에게
            <span>전통주 검색 기능도 누려보세요</span>
          </div>
          <div class="btn-box">
            <div class="togo-btn" @click="linkTo('WoojooSearch')">
              전통주 검색하기 >
            </div>
          </div>
        </div>

        <!-- community -->
        <div class="home-corner community-corner">
          <div class="corner-img-box">
            <img src="@/assets/images/home/community.png" alt="" class="corner-img">
          </div>  
          <div class="home-content com-intro">
            전통주에 대해 더 많은 이야기를<br/>나누고 싶은 여러분에게
            <span>커뮤니티에서<br/>다양한 우주들을 만나보세요</span>
          </div>
          <div class="btn-box">
            <div class="togo-btn" @click="linkTo('CommunityMain')">
              커뮤니티 둘러보기 >
            </div>
          </div>  
        </div>  

        <!-- signup -->
        <div class="home-corner signup-corner">
          <div class="corner-img-box">
            <img src="@/assets/images/home/signup.png" alt="" class="corner-img">
          </div>
          <div class="home-content signup-intro">
            취향에 맞는 술을<br/>매일 다르게 추천 받고 싶은 여러분에게
            <span>우주의 회원이 되어<br/> 더 풍부한 전통주를 즐겨보세요</span>
          </div>
          <div class="btn-box">
            <div class="togo-btn" @click="linkTo('LoginBase')">
              로그인 <span>또는</span> 회원가입 >
            </div>
          </div>
        </div>
      </div>
    </div>

  </div>
  <MenuView menuTab="main-home-menu"/>
</template>

<script setup>
import ModeToggle from "@/views/common/ModeToggle.vue"
import MenuView from "@/views/common/MenuView.vue"
import { ref, computed, onMounted } from "vue";
import { useRouter } from "vue-router";
import { useStore } from "vuex";

const router = useRouter();
const linkTo = (name) => router.push({ name: name })

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
      document.querySelector(".tab-header .mainhome-logo").style.top = "0";
      document.querySelector(".tab-header .mainhome-logo img").style.width = "150px";
      document.querySelector(".tab-header").style.boxShadow = "0px 4px 6px 6px rgba(0, 0, 0, 0.05)";
    } else {
      document.querySelector(".tab-header").style.boxShadow = "0px 4px 6px 6px rgba(0, 0, 0, 0.0)";
      document.querySelector(".tab-header .mainhome-logo").style.top = `${150 * ((position.value.top - 60) / 390)}px`;
      document.querySelector(".tab-header .mainhome-logo img").style.width = `${150 + 100 * ((position.value.top - 60) / 390)}px`;
      document.querySelector(".header-content .home-intro").style.opacity = `${((position.value.top - 260) / 190 )}`;
      document.querySelector(".header-content .home-intro").style.top = `${35 + 30 * ((position.value.top - 260) / 190 )}%`;
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

.togo-btn span {
  font-weight: 400;
  font-size: 1rem;
}
</style>