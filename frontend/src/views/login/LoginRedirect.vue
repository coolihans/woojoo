<template>
</template>

<script setup>
import { useRoute } from 'vue-router';
import http from '@/api/index.js'

const route = useRoute();

const path = route.params.path;
const code = route.query.code;

if (path == 'google') {
  http.get("/user/login-google", {params: {code: code}})
    .then(({ data }) => {
      // console.log(data)
      if (data.statusCode == 200) {
        localStorage.setItem('token', data.accesstoken)
        opener.checkLogin()
      } else {
        localStorage.setItem('token', data.accesstoken)
        opener.location = `/signup?email=${data.userEmail}&usertype=${data.userType}`
      }
      window.close()
    })
    .catch((err) => console.log(err))
} else if (path == 'naver') {
  http.get("/user/login-naver", {params: {code: code}})
    .then(({ data }) => {
      // console.log(data)
      if (data.statusCode == 200) {
        localStorage.setItem('token', data.accesstoken)
        opener.checkLogin()
      } else {
        localStorage.setItem('token', data.accesstoken)
        opener.location = `/signup?email=${data.userEmail}&usertype=${data.userType}`
      }
      window.close()
    })
} 
// window.close()

</script>

<style>
</style>