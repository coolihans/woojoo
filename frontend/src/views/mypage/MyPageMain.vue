<template>
  <div class="tab-template profile-container">
    <div class="mypage-header">
      <div class="header-nick">
        {{ profileData.profile?.nickname }}
      </div>
      <div class="mode-toggle">
        <ModeToggle/>
      </div>
    </div>
    <div class="mypage-profile">
      <div class="bg-box"></div>
      <div class="profile-pic" :style="`background-image: url(${profileData.profile?.img})`">
      </div>
      <div class="profilebox">
        <div class="edit-profile" @click="linkTo('MyPageProfileEdit')">
          <i class="fas fa-cog"></i>
        </div>
      </div>
      <div class="level" v-if="profileData.profile?.gosu">
        <i class="fas fa-award gosu"></i> 맛고수
      </div>
      <div class="email">
        {{ profileData.profile?.email }}
      </div>
      <div class="logout" @click="logout()">
        <i class="fas fa-sign-out-alt"></i>
      </div>
    </div>
    
    
    <div class="mypage-container">
      <!-- tabs menu -->
      <div class="mypage-tabs">
        <div class="tabs-menu-container">
          <div class="tab tab-type" @click="tabPosition('mypage-type')">
            내 취향
          </div>
          <div class="tab tab-likes" @click="tabPosition('mypage-likes')">
            내 좋아요
          </div>
          <div class="tab tab-reviews" @click="tabPosition('mypage-reviews')">
            내 리뷰
          </div>
          <div class="tabs-now">
            <span class="under-bar"></span>
          </div>
        </div>
      </div>
      <div class="tabs-container">
        <div class="tab-control">
          <!-- tab1 -->
          <div class="tab-section mypage-type">
            <el-scrollbar>
              <MyPagePreference/>
            </el-scrollbar>
          </div>
          <!-- tab2 -->
          <div class="tab-section mypage-likes">
            <el-scrollbar>
              <MyPageLikes :likesData="profileData.likeList"/>
            </el-scrollbar>
          </div>
          <!-- tab3 -->
          <div class="tab-section mypage-reviews">
            <el-scrollbar>
              <MyPageReviews/>
            </el-scrollbar>
          </div>
        </div>
        <div class="scroll-false"></div>
      </div>
    </div>

  </div>
</template>

<script setup>
import ModeToggle from "@/views/common/ModeToggle.vue"
import MyPagePreference from "@/views/mypage/MyPagePreference.vue"
import MyPageLikes from "@/views/mypage/MyPageLikes.vue"
import MyPageReviews from "@/views/mypage/MyPageReviews.vue"
import { ref, onMounted ,computed } from "vue";
import { useRouter, useRoute } from "vue-router";
import { useStore } from "vuex";


// script
const router = useRouter();
const route = useRoute();
const store = useStore();
const profileData = computed(() => store.getters.profile)
const linkTo = (name) => router.push({ name: name, })
const reviewIdx = route.query.reviewIdx

const logout = () => {  
  store.dispatch("logout")
}

onMounted(() => {
  store.dispatch("fetchProfile")
  if (!!reviewIdx) {
    window.scrollTo(450, 450)
    tabPosition('mypage-reviews')
  }
})


// js scroll function
const position = ref(0)

onMounted(() => {
  window.onscroll = function() {scrollTabMenu()};
  const mypageTabs = document.querySelector(".mypage-container")
  
  function scrollTabMenu() {
    position.value = mypageTabs.getBoundingClientRect().top

    if (position.value <= 120) {
      document.querySelector(".mypage-header .header-nick").style.top = "0"
      document.querySelector(".mypage-header .header-nick").style.fontSize = "1.2rem"
      document.querySelector(".scroll-false").style.display = "none"
    } else {
      document.querySelector(".mypage-header .header-nick").style.top = `${240 * ((position.value - 120) / 390)}px`
      document.querySelector(".mypage-header .header-nick").style.fontSize = `${1.5 - 0.3 * (1- (position.value - 120) / 390)}rem`
      document.querySelector(".mypage-profile .bg-box").style.opacity = `${(position.value - 350) / 160}`
      document.querySelector(".mypage-profile .edit-profile").style.opacity = `${(position.value - 350) / 160 * 0.8} !important`
      document.querySelector(".mypage-profile .logout").style.opacity = `${(position.value - 350) / 160 * 0.5}`
      document.querySelector(".mypage-profile .profile-pic").style.opacity = `${(position.value - 350) / 160}`
      document.querySelector(".mypage-profile .level").style.opacity = `${(position.value - 350) / 160}`
      document.querySelector(".mypage-profile .email").style.opacity = `${(position.value - 350) / 160}`
      document.querySelector(".mypage-profile .edit-profile").style.opacity = `${(position.value - 350) / 160}`
      document.querySelector(".scroll-false").style.display = "block"
    }
  }

})

function tabPosition(tabName) {
  if (window.innerWidth >= 500) {
    if (tabName == "mypage-type") {
      document.querySelector(".tabs-container .tab-control").style.margin = "0 0 0 0"
      document.querySelector(".tabs-container .scroll-false").style.left = "0"
      document.querySelector(".tabs-now").style.left = "0"
    } else if (tabName == "mypage-likes") {
      document.querySelector(".tabs-container .tab-control").style.margin = "0 0 0 -500px"
      document.querySelector(".tabs-container .scroll-false").style.left = "-500px"
      document.querySelector(".tabs-now").style.left = "33%"
    } else if (tabName == "mypage-reviews") {
      document.querySelector(".tabs-container .tab-control").style.margin = "0 0 0 -1000px"
      document.querySelector(".tabs-container .scroll-false").style.left = "-1000px"
      document.querySelector(".tabs-now").style.left = "66%"
    }
  } else {
    if (tabName == "mypage-type") {
      document.querySelector(".tabs-container .tab-control").style.margin = "0 0 0 0"
      document.querySelector(".tabs-container .scroll-false").style.left = "0"
      document.querySelector(".tabs-now").style.left = "0"
    } else if (tabName == "mypage-likes") {
      document.querySelector(".tabs-container .tab-control").style.margin = "0 0 0 -100vw"
      document.querySelector(".tabs-container .scroll-false").style.left = "-100vw"
      document.querySelector(".tabs-now").style.left = "33%"
    } else if (tabName == "mypage-reviews") {
      document.querySelector(".tabs-container .tab-control").style.margin = "0 0 0 -200vw"
      document.querySelector(".tabs-container .scroll-false").style.left = "-200vw"
      document.querySelector(".tabs-now").style.left = "66%"
    }
  }
}
</script>

<style>
  
</style>
