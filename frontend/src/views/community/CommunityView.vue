<template>
  <div>
    <NonmemberView v-if="!isLoggedIn"/>
    <RouterView v-if="isLoggedIn"/>
    <MenuView menuTab="community-menu"/>
  </div>
</template>

<script setup>
import NonmemberView from "../common/NonmemberView.vue";
import MenuView from "@/views/common/MenuView.vue"
import { ref, onMounted, computed } from "vue";
import { useStore } from "vuex";

const store = useStore();
const isLoggedIn = computed(() => store.getters.isLoggedIn)



onMounted (() => {
  window.scrollTo(0, 0)
})


</script>

<style>  
</style>