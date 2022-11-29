<template>
  <div>
    <RouterView/>
    <MenuView menuTab="my-recommendation-menu"/>
  </div>
</template>

<script setup>
import MenuView from "@/views/common/MenuView.vue"
import { onMounted } from "vue";


onMounted (() => {
  window.scrollTo(0, 0)
})
</script>

<style>  
</style>