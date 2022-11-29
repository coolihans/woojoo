<template>
  <div class="tab-template container">
    <div class="tab-header">
      <div class="tab-title">
        오늘의 추천
      </div>
      <div class="mode-toggle">
        <ModeToggle/>
      </div>
    </div>
    <div class="header-content">
      <img class="content-img" src="@/assets/images/community.png" alt="communityMainPic">
      <p class="header-intro">당신이 오늘 좋아할 만한 우주를 소개해드려요</p>
    </div>
    
    <div class="inner-container">
      <div class="rec-container inner-section">
        <!-- <div class="mt"></div> -->
        <MyRecommendationMember v-if="isLoggedIn"/>
        <MyRecommendationNon v-if="!isLoggedIn"/>
      </div>
    </div>
  </div>
</template>

<script setup>
import ModeToggle from "@/views/common/ModeToggle.vue"
import MyRecommendationMember from "@/views/recommendation/MyRecommendationMember.vue"
import MyRecommendationNon from "@/views/recommendation/MyRecommendationNon.vue"
import { ref, computed, onMounted } from "vue";
import { useRouter } from "vue-router";
import { useStore } from "vuex";

const router = useRouter();
const linkTo = (name) => router.push({ name: name })

// vuex axios
const store = useStore();
const isLoggedIn = computed(() => store.getters.isLoggedIn)
const isSurveyed = computed(() => store.getters.isSurveyed)

onMounted(() => {
  if (!isSurveyed.value) {
    store.dispatch("fetchRecommendationResult")
  }
})


// whether surveyed
const preference = ref(false)
const lifestyle = ref(false)


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

.fake img {
width: 50% !important;
}

.fake2 img {
  width: 100% !important;
}
</style>