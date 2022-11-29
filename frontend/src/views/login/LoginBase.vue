<template>
  <HeaderView/>
  <div class="login-container">
    <div class="login-image"><img src="@/assets/images/community.png" alt=""></div>
    <div class="login-button-group">
      <p class="login-message">소셜 계정으로 쉽게 로그인하고<br/>우주를 즐겨보세요</p>
      <img class="login-google" src="@/assets/images/login/google.png" alt="login with google"
        @click="loginPopup('google')" />
      <img class="login-naver" src="@/assets/images/login/naver.png" alt="login with naver"
        @click="loginPopup('naver')" />
    </div>
  </div>
  <MenuView menuTab="my-page-menu"/>
</template>

<script setup>
import HeaderView from "@/views/common/HeaderView.vue"
import MenuView from "@/views/common/MenuView.vue"
import { useStore } from "vuex"
import { useRouter } from "vue-router"

const store = useStore()
const router = useRouter()

window.checkLogin = () => {
  const token = localStorage.getItem('token')
  console.log(token)
  if (!!token) {
    store.dispatch('saveToken', token)
    router.push({ name: 'MainHome' })
  } else {
    alert('로그인 실패')
  }
}
// 'menubar=no, status=no, toolbar=no'
const loginPopup = (path) => window.open(`/login/popup/${path}`, '_blank', )
</script>

<style scoped>
.login-image {
  width: 60%;
  border-radius: 20px;
  overflow: hidden;
}

.login-google,
.login-naver {
  width: 55vw;
  max-width: 250px;
  border-radius: 15px;
}

.login-google {
  border: 1px solid #4285F4;
}

.login-naver {
  margin-top: 10px;
  border: 1px solid #03c75a;
}
</style>