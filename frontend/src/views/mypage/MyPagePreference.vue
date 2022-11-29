<template>
  <SurveyResult/>
  <div class="again-container rec-preferance">
    <div class="again-content">
      다시 취향을 알아보고 싶다면
      <div class="survey-btn" @click="linkTo('MyRecommendationType')">
        <div class="btn-box">취향 알아보러 가기 ></div>
      </div>
    </div>
  </div>
</template>

<script setup>
  import SurveyResult from '../recommendation/SurveyResult.vue';

import { computed } from "vue";
import { useStore } from "vuex";
import { useRouter } from "vue-router";

const router = useRouter();
const linkTo = (name) => router.push({ name: name })

const store = useStore();

</script>

<style scoped>
  .result-container {
    margin-top: 0;
    padding-bottom: 0;
  }
</style>