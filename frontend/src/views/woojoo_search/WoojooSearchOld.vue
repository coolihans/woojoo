<template>
  <div class="tab-template container">
    <div class="tab-header">
      <div class="tab-title">
        <!-- 탭메뉴 이름 -->
        우-주 찾기
      </div>
      <div class="mode-toggle">
        <ModeToggle/>
      </div>
    </div>
    <div class="header-content">
      <!-- 탭메뉴 이미지 -->
      <img class="content-img" src="@/assets/images/우주헤더.png" alt="">
      <!-- 탭메뉴 설명 -->
      <p class="header-intro">모든 우-주 찾아보기</p>
    </div>
    
    <div class="inner-container">
      <div class="inner-section">
        <!-- 본문 입력 -->
        <div class="woojoo-searchbar">
          <div class="category-title">종류별로 보기</div>
          <div class="category-list">
            <div class="category-box">
              <img class="image" src="@/assets/images/탁주.png" alt="">
              <p>소주</p>
            </div>
            <div class="category-box">
              <img class="image" src="@/assets/images/탁주.png" alt="">
              <p>탁주</p>
            </div>
            <div class="category-box">
              <img class="image" src="@/assets/images/탁주.png" alt="">
              <p>과실주</p>
            </div>
            <div class="category-box">
              <img class="image" src="@/assets/images/탁주.png" alt="">
              <p>증류주</p>
            </div>
          </div>
        </div>
        <div class="woojo-list">
          <div class="woojoo-list-box" v-for="i in 4">
            <div class="image">
              <img src="@/assets/images/woojoo1.jpg" alt="">
              <span class="like">
                <i class="fas fa-heart heart"></i> 73
              </span>
            </div>
            <div class="content">
              <p>제목 : 옛날 술</p>
              <p>별점 : 4.3</p>
              <p>상세설명 : 이 술은 옛날부터 전해져 내려오는 어쩌구 저쩌구</p>
            </div>
          </div>
        </div>
        <div>
          
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import ModeToggle from "@/views/common/ModeToggle.vue"
import { ref, onMounted } from "vue";
import { useRouter, useRoute } from "vue-router";
import { useStore } from "vuex"

const router = useRoute();
const store = useStore();
const position = ref(0);

onMounted(() => {
  store.dispatch("fetchWoojooList")
})

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
  
</style>