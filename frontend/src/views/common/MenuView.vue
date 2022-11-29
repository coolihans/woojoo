<template>
  <div id="main-home-menu" class="menu-container">
    <div class="my-page menu-div" @click="linkTo('MainHome')">
      <i class="fas fa-home icon is-inactive"></i>
      <span class="menu-title">홈</span>
    </div>
    <div id="my-recommendation-menu" class="my-page menu-div" @click="linkTo('MyRecommendationMain')">
      <i class="fas fa-hand-holding-heart icon is-inactive"></i>
      <span class="menu-title">오늘의 추천</span>
    </div>
    <div id="community-menu" class="my-page menu-div" @click="linkTo('CommunityMain')">
      <i class="fas fa-users icon is-inactive"></i>
      <span class="menu-title">커뮤니티</span>
    </div>
    <div id="woojoo-search-menu" class="my-page menu-div" @click="linkTo('WoojooSearch')">
      <i class="fas fa-wine-bottle icon is-inactive"></i>
      <span class="menu-title">우리술 찾기</span>
    </div>
    <div id="my-page-menu" class="my-page menu-div" @click="linkTo('MyPageMain')">
      <i class="fas fa-user icon is-inactive"></i>
      <span class="menu-title">마이 페이지</span>
    </div>
  </div>
</template>

<script setup>
import { onMounted } from "vue";
import { useRouter } from "vue-router";

const router = useRouter();
const linkTo = (name) => router.push({ name: name })

const props = defineProps({
  menuTab: String,
})

onMounted(() => {
  const tabName = document.querySelector(`#${props.menuTab} .icon`)

  tabName.classList.replace("is-inactive", "is-active")
})
</script>

<style>
  
</style>