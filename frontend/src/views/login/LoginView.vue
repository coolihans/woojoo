<template>
  <RouterView/>
</template>

<script setup>
</script>

<style>
.login-container {
  margin-top: 60px;
  height: calc(100vh - 130px);
  background-color: var(--bg-color);
  display: flex;
  align-items: center;
  flex-direction: column;
  justify-content: center;
}

.login-container .login-message {
  margin-bottom: 40px;
  font-size: 1.2rem;
  font-weight: 600;
  line-height: 30px;
  text-transform: uppercase;
}

.login-container .login-button-image {
  width: 20%;
  margin: 9%;
  cursor: pointer;
}

.login-container .login-button-image:active {
  filter: brightness(0.8);
}
</style>