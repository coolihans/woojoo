<template>
  <HeaderView/>
  <div class="nonmember-container">
    <div class="illustration">
      <img src="@/assets/images/drinking_people.png" alt="drinking_people">
    </div>
    <div class="nonmenber-intro">
      더 많은 기능,<br/>더 많은 우주를 둘러보기 위해<br/><span>로그인</span>을 해주세요!
    </div>
    <div class="login">
      <div class="login-btn" @click="linkTo('LoginBase')">로그인</div>
    </div>
    <!-- <div class="signup">
      <div class="signup-btn" @click="linkTo('SignupView')">회원 가입</div>
    </div> -->
  </div>
  <!-- <MenuView menuTab="my-page-menu"/> -->
</template>

<script setup>
import HeaderView from "@/views/common/HeaderView.vue"
import MenuView from "@/views/common/MenuView.vue"
import { useRouter } from "vue-router";
import { useStore } from "vuex";

const router = useRouter();
const linkTo = (name) => router.push({ name: name })

</script>

<style>
  .nonmember-container {
  margin-top: 60px;
  width: 100vw;
  max-width: 500px;
  height: calc(100vh - 130px);
  background-color: var(--main-bg-color);
  display: flex;
  flex-direction: column;
  justify-content: center
}

.nonmember-container .illustration {
  width: 300px;
  height: 300px;
  border-radius: 20px;
  overflow: hidden;
}

.nonmember-container .illustration img {
  height: 300px;
  height: 300px;
}

.nonmember-container .nonmenber-intro {
  margin: 40px;
  height: 87px;
  font-size: 1.1rem;
  line-height: 29px;
}

.nonmember-container .nonmenber-intro span {
  font-size: 1.2rem;
  font-weight: 600;
}

.nonmember-container .login {
  margin: 60px 0 20px 0;
  width: 100%;
  height: 30px;
}

.nonmember-container .login .login-btn {
  width: 250px;
  height: 50px;
  line-height: 50px;
  font-size: 1.1rem;
  font-weight: 600;
  border-radius: 15px;
  background-color: var(--main-color);
  color: #000;
}

.nonmember-container .signup {
  margin: 30px 0 30px 0;
  width: 100%;
  height: 30px;
}

.nonmember-container .signup .signup-btn {
  width: 250px;
  height: 50px;
  line-height: 50px;
  font-size: 1.1rem;
  font-weight: 600;
  border-radius: 15px;
  border: 1px solid var(--main-color);
}


</style>