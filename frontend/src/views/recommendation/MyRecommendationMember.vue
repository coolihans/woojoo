<template>
  <!-- life style -->
  <div class="rec-type rec-preferance">
    <div class="rec-title">
      당신의 오늘에 딱 맞는 술
    </div>

    <!-- v-if not surveyed -->
    <div class="not-surveyed" v-if="_.isEmpty(recommendData.today)">
      <div class="not-surveyed-content">
        아직 좋아요 수가 부족해요!<br/>좋아요를 더 많이 누르면 우주가 추천해드릴게요.
      </div>
      <div class="survey-btn life-style-btn" @click="linkTo('WoojooSearch')">
        <div class="btn-box">좋아요 누르러 가기 ></div>
      </div>
    </div>

    <!-- v-if surveyed -->
    <div class="bottles" v-else>
      <div class="bottle-card" v-for="product in recommendData.today">
        <div @click="linkToProduct(`${product.id}`)">
          <div class="bottle-img">
            <img :src="product.image" alt="bottle">
          </div>
          <div class="bottle-content">
            <div class="bottle-title cl-ellipsis">
              {{ product.name }}
            </div>
            <div class="bottle-intro">
              도수: {{ product.alcohol }}%
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <!-- preferance -->
  <div class="rec-type rec-preferance">
    <div class="rec-title">
      당신의 취향에 딱 맞는 술
    </div>

    <!-- v-if not surveyed -->
    <div class="not-surveyed" v-if="_.isEmpty(recommendData.taste)">
      <div class="not-surveyed-content">
        아직 취향 조사를 하지 않으셨어요! <br/> 당신의 우주를 같이 알아볼까요?
      </div>
      <div class="survey-btn" @click="linkTo('MyRecommendationType')">
        <div class="btn-box">취향 알아보러 가기 ></div>
      </div>
    </div>

    <!-- v-if surveyed -->
    <div class="bottles" v-else>
      <div class="bottle-card" v-for="product in recommendData.taste">
        <div @click="linkToProduct(`${product.id}`)">
          <div class="bottle-img">
            <img :src="product.image" alt="bottle">
          </div>
          <div class="bottle-content">
            <div class="bottle-title cl-ellipsis">
              {{ product.name }}
            </div>
            <div class="bottle-intro">
              도수: {{ product.alcohol }}%
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <!-- award -->
  <div class="rec-type rec-preferance">
    <div class="rec-title award-title">
      아직 전통주에 대해 잘 모르겠다면,<br/>모두의 인정을 받은 술들은 어떤가요?
    </div>
    <div class="bottles">
      <div class="bottle-card" v-for="product in recommendData.award">
        <div @click="linkToProduct(`${product.id}`)">
          <div class="bottle-img">
            <img :src="product.image" alt="bottle">
          </div>
          <div class="bottle-content">
            <div class="bottle-title cl-ellipsis">
              {{ product.name }}
            </div>
            <div class="bottle-intro">
              도수: {{ product.alcohol }}%
            </div>
          </div>
        </div>
      </div>
    </div>


  </div>

</template>

<script setup>
import { ref, computed, onMounted } from "vue";
import { useRouter } from "vue-router";
import { useStore } from "vuex";
import _ from 'lodash';

const router = useRouter();
const linkTo = (name) => router.push({ name: name })
const linkToProduct = (productPk) => router.push({ name: "WoojooDetail", params: { productPk: productPk }})

// vuex axios
const store = useStore();
const recommendData = computed(() => store.getters.recommendation);

onMounted(() => {
})

// header scroll event js
const position = ref(0)

onMounted(() => {
  const innerContainer = document.getElementsByClassName("inner-container")
  window.onscroll = function() {scrollInnerHeader()};
  
  function scrollInnerHeader() {
    position.value = innerContainer[0].getBoundingClientRect()

    if (position.value.top < 60) {
      document.querySelector(".tab-header .tab-title").style.top = "0";
      document.querySelector(".tab-header").style.boxShadow = "0px 4px 6px 6px rgba(0, 0, 0, 0.05)";
    } else {
      document.querySelector(".tab-header").style.boxShadow = "0px 4px 6px 6px rgba(0, 0, 0, 0.0)";
      document.querySelector(".tab-header .tab-title").style.top = `${50 * (position.value.top / 450)}px`;
      document.querySelector(".tab-header .tab-title").style.fontSize = `${1.2 + position.value.top / 450}rem`;
      document.querySelector(".header-content .content-img").style.opacity = `${((position.value.top - 260) / 250 )}`;
      document.querySelector(".header-content .content-img").style.scale = `${((position.value.top - 60) /  390)}`;
      document.querySelector(".header-content .header-intro").style.opacity = `${((position.value.top - 260) / 190 )}`;
    }
  }
})

</script>

<style>
.mt {
  width: 100%;
  max-width: 500px;
  height: 60px;
  background-color: transparent;
}

.fake img {
width: 50% !important;
}

.fake2 img {
  width: 100% !important;
}
</style>