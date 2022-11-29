<template>
  <HeaderView/>

  <!-- main section -->
  <div class="recomm-type-container" v-if="!selected">
    <div class="title">
      <img src="@/assets/images/test.png" alt="" class="testimg">
    </div>

    <div class="question0">
      어떤 주종을 좋아하시나요?
    </div>
    <div class="select">
      <div class="option opt1" @click="waitAndLinkTo('MyRecommendationTak')">
        <img src="@/assets/images/tak.svg" alt="">
        <div class="content">막걸리/탁주</div>
      </div>
      <div class="option opt2" @click="waitAndLinkTo('MyRecommendationFruit')">
        <img src="@/assets/images/fruit.svg" alt="">
        <div class="content">약주/과실주</div>
      </div>
      <div class="option opt3" @click="waitAndLinkTo('MyRecommendationSoju')">
        <img src="@/assets/images/soju.svg" alt="">
        <div class="content">증류주</div>
      </div>
    </div>
  </div>
  
  <div class="recomm-type-container" v-if="selected">
    <MyRecommendationWait/>
  </div>
</template>

<script setup>
import HeaderView from "@/views/common/HeaderView.vue"
import MyRecommendationWait from "@/views/recommendation/MyRecommendationWait.vue"
import { ref } from "vue";
import { useRouter } from "vue-router";

const router = useRouter();
const selected = ref(false)
const waitAndLinkTo = (name) => {
  selected.value = true
  setTimeout(() => {
    router.push({ name: name })
  }, 2800);
}

</script>

<style>
  
</style>