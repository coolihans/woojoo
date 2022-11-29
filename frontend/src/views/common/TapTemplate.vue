<template>
  <div class="tab-template container">
    <div class="tab-header">
      <div class="tab-title">
        <!-- 탭메뉴 이름 -->
      </div>
      <div class="mode-toggle">
        <ModeToggle/>
      </div>
    </div>
    <div class="header-content">
      <!-- 탭메뉴 이미지 -->
      <img class="content-img" src="" alt="">
      <!-- 탭메뉴 설명 -->
      <p class="header-intro"></p>
    </div>
    
    <div class="inner-container">
      <div class="inner-section">
        <!-- 본문 입력 -->
      </div>
    </div>
  </div>
</template>

<script setup>
import ModeToggle from "@/views/common/ModeToggle.vue"
import { ref, onMounted } from "vue";

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
  
</style>