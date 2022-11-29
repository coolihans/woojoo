<template>
  <HeaderView/>
  <SurveyResult/>
</template>

<script setup>
import HeaderView from '../common/HeaderView.vue';
import SurveyResult from './SurveyResult.vue';
import { onMounted } from 'vue';

onMounted (() => {
  window.scrollTo(0, 0)
})


</script>

<style> 
  
</style>