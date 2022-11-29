<template>
  <div class="result-container">
    <div class="result-title">
      <span class="nick">당신의 전통주 취향</span>은<br/>
    </div>

    <TypeMak v-if="recommData.usertype?.type == 1"/>
    <TypeSommelier v-if="recommData.usertype?.type == 2"/>
    <TypeExpert v-if="recommData.usertype?.type == 3"/>
    <TypeSparkle v-if="recommData.usertype?.type == 4"/>
    <TypeParty v-if="recommData.usertype?.type == 5"/>
    <TypeWizard v-if="recommData.usertype?.type == 6"/>
    <TypeExplorer v-if="recommData.usertype?.type == 7"/>
    <TypeCritics v-if="recommData.usertype?.type == 8"/>

    <RaderChart v-if="isRecommLoaded" :analysis="recommData.usertype?.analysis"/>

    <div class="preferance">
      <div class="sweet flavor" id="analysis-content">
        {{ recommData.usertype?.analysis.max }}
      </div>
      <div class="proof flavor">
        {{ recommData.usertype?.analysis.min }}
      </div>
    </div>

    <div class="recommends rec-type">
      <div class="result-intro">
        아마 이 전통주들이 취향에 <span>딱</span> 맞을 거예요.
      </div>
      <div class="bottles">
        <div class="bottle-card" v-for="product in recommData.taste">
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
  </div>
</template>

<script setup>
import HeaderView from '../common/HeaderView.vue';
import TypeCritics from './SurveyResult/TypeCritics.vue';
import TypeExplorer from './SurveyResult/TypeExplorer.vue';
import TypeMak from './SurveyResult/TypeMak.vue';
import TypeParty from './SurveyResult/TypeParty.vue';
import TypeSommelier from './SurveyResult/TypeSommelier.vue';
import TypeSparkle from './SurveyResult/TypeSparkle.vue';
import TypeExpert from './SurveyResult/TypeExpert.vue';
import TypeWizard from './SurveyResult/TypeWizard.vue';
import RaderChart from "./SurveyResult/RaderChart.vue"

import { ref, onMounted ,computed } from "vue";
import { useStore } from "vuex";
import { useRouter } from "vue-router";


const store = useStore();
const recommData = computed(() => store.getters.recommendation)
const isRecommLoaded = computed(() => store.getters.isRecommLoaded)

const router = useRouter();
const linkToProduct = (productPk) => router.push({ name: "WoojooDetail", params: { productPk: productPk }})


onMounted(() => {
  store.commit("SET_IS_RECOMM_LOADED", false)
  store.dispatch("fetchRecommendationResult")
})

</script>

<style>
  
</style>