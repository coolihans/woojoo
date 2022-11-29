<template>
  <div class="likes-container mypage-inner">
    <div class="like-wooju" v-for="data in profileData.profile?.likeList">
      <div @click="linkToProduct(data.id)">
        <div class="wooju-img">
          <img :src="data.img" alt="">
        </div>
        <div class="introduction">
          <div class="product-title">
            {{ data.name }}
          </div>
          <div class="content">
            <span class="proof">{{ data.alcohol }}%</span> |
            <span class="heart">
              <i class="fas fa-heart heart-icon"></i>
            </span>
            <span class="count">{{ data.like }}</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { computed } from "vue";
import { useRouter } from "vue-router";
import { useStore } from "vuex";

const router = useRouter();
const linkToProduct = (productPk) => router.push({ name: "WoojooDetail", params: { productPk: productPk }})

// vuex axios
const store = useStore();
const profileData = computed(() => store.getters.profile)

</script> 

<style>
  
</style>