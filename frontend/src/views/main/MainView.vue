<template>
  <MainHomeVue v-if="isLoggedIn" />
  <MainHomeNonmemberVue v-else />

  <!-- <div>
    <RouterView/>
  </div> -->
</template>

<script setup>
import { onMounted, computed } from 'vue';
import { useStore } from 'vuex';
import MainHomeNonmemberVue from './MainHomeNonmember.vue';
import MainHomeVue from './MainHome.vue';

const store = useStore()

const isLoggedIn = computed(() => store.getters.isLoggedIn)

onMounted (() => {
  window.scrollTo(0, 0)
})
</script>

<style>  
</style>