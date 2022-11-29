<template>
  <div class="likes-container mypage-inner">
    <div class="shop-container"  @click="openShop(shop.url)" v-for="shop in woojooInfo.object?.shops">
      <div class="shop-img-container">
        <div class="shop-img">
          <img class="shop-img" :src="shop.image" alt="">
        </div>
      </div>
      <div class="shop-detail"> 
        <div class="shop-name">
          {{shop.mall_name}}
        </div>
        <div class="shop-price">
          가격 : {{shop.price}} 원
        </div>
        <div class="shop-link">
          <span>바로가기 ></span>
        </div> 
        <!-- <div class="introduction">
          <div class="product-title">
            {{ shop.mall_name }}
          </div>
          <div class="content">
            <span class="proof">{{ shop.alcohol }}</span> 
            <span class="heart">
              <i class="fas fa-heart heart-icon"></i>
            </span>
            <span class="count">{{ shop.like }}</span>
          </div>
        </div> -->
      </div>
    </div>
  </div>
</template>

<script setup>
import { computed } from "vue";
import { useRouter } from "vue-router";
import { useStore } from "vuex";

const store = useStore();
const woojooInfo = computed(() => store.getters.woojooInfo);

const openShop = (url) => {
  console.log(url)
  window.open(url)
}

</script> 

<style>
.shop-img-container {
  display: flex;
  align-items: center;
}
.shop-container {
  margin-bottom: 10px;
  background-color: var(--card-bg-color);
  border-radius: 25px;
  box-shadow: 2px 2px 9px 2px rgba(0, 0, 0, var(--shadow-percentage-03));
  overflow: hidden;
  display: inline-block;
  text-align: left;
  display: flex;
  width: 325px;
  height: 150px;
}
.shop-img {
  margin: 5px;
  border-radius: 25px;
  width: 100px;
}
.shop-detail {
  margin-left: 5px;
  width: 225px;
}
.shop-detail .shop-name {
  font-size: 1.2rem;
  font-weight: 900;
  margin: 25px 15px 10px 15px;
}
.shop-detail .shop-price {
  margin: 15px 15px 10px 15px;
  font-size: 1rem;
}

.shop-detail .shop-link {
  margin: 30px 20px 0px 0px;
  text-align: right;
  font-size: 0.8rem;
}
</style>