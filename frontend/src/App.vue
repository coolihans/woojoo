<template>
  <div class="app-container">
    <!-- <HeaderView/> -->
    <RouterView/>
    <!-- <MenuView/> -->
    <div id="tel"></div>  
  </div>

</template>

<script setup>
import { RouterView } from "vue-router";

// import HeaderView from "@/views/common/HeaderView.vue"
// import MenuView from "@/views/common/MenuView.vue"
</script>

<style>
@import "@/assets/css/common.css";
@import "@/assets/css/CommunityMain.css";
@import "@/assets/css/communityHot.css";
/* @import "@/assets/css/WoojooSearch.css"; */
@import "@/assets/css/myPage.css";
@import "@/assets/css/woojooDetail.css";
@import "@/assets/css/MyRecommendation.css";
@import "@/assets/css/reviewCreate.css";
@import "@/assets/css/mainHome.css";

</style>
