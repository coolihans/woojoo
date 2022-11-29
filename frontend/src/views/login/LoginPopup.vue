<template>
  <!-- <p>{{ path }}로 로그인 중...</p> -->
</template>

<script setup>
import { useRoute } from 'vue-router';
import { onMounted } from 'vue';

const route = useRoute();
const path = route.params.path;

onMounted(() => {
  if (path == 'naver') {
    window.location = 'https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=rVcPxogwriBczwVBXTYE&redirect_uri=https://j7a304.p.ssafy.io/login/redirect/naver'
  } else if (path == 'google') {
    window.location = 'https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&response_type=code&client_id=912895445913-nlm76q623uccufvo6g31rc15jividhg7.apps.googleusercontent.com&redirect_uri=https://j7a304.p.ssafy.io/login/redirect/google'
  } else {
    window.close()}
})
</script>

<style>
</style>