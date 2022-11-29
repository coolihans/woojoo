<template>
  <div class="container pt-60">
    <HeaderView />
    <div class="search-container">
      <div class="search-filter">
        <div class="category-title">전통주 종류</div>
        <div class="search-checkbox-group">
          <input type="checkbox" id="types-tak" value="탁주" v-model="searchInfo.types" class="search-checkbox">
          <label class="search-label" for="types-tak">
            <div class="type-option">
              <div class="type-img">
                  <img class="checkbox-img" src="@/assets/images/tak.svg" alt="">
                </div>
              <div class="type-name">탁주</div>
            </div>
          </label>
          <input type="checkbox" id="types-fruit" value="약주, 과실주" v-model="searchInfo.types" class="search-checkbox">
          <label class="search-label" for="types-fruit">
            <div class="type-option">
              <div class="type-img">
                  <img class="checkbox-img" src="@/assets/images/fruit.svg" alt="">
                </div>
              <div class="type-name">과실주/약주</div>
            </div>
          </label>
          <input type="checkbox" id="types-soju" value="소주" v-model="searchInfo.types" class="search-checkbox">
          <label class="search-label" for="types-soju">
            <div class="type-option">
              <div class="type-img">
                  <img class="checkbox-img" src="@/assets/images/soju.svg" alt="">
                </div>
              <div class="type-name">증류주</div>
            </div>
          </label>
        </div>
        <div class="search-alcohol">
          <div class="alcohol">
            도수
            <el-input-number size="small" v-model="searchInfo.alcohol" :min="3" :max="54" />
            <!-- <el-slider v-model="searchInfo.alcohol" tooltip-class="search-tooltip" :max="50" /> -->
          </div>
          <div class="text">
            이하인 술
          </div>
        </div>
        <div class="search-award">
          <div class="award-text">
            수상 경력
          </div>
          <input type="checkbox" id="check-award" v-model="searchInfo.award" class="search-checkbox">
        </div>
        <div class="search-keyword">
          <el-input
            v-model="searchInfo.keyword"
            placeholder="검색어를 입력하세요"
          />
          <el-button @click="searchProducts">검색</el-button>
          <!-- <input type="text" v-model="searchInfo.keyword">
          <button class="search-button" @click="searchProducts">검색</button> -->
        </div>
      </div>
    </div>
    <div class="products-container">
      <div class="products-list">
        <div class="products-list-box" v-for="product in products.items" v-if="isProductLoad"
          @click="goToDetail(product.id)">
          <div class="image">
            <img :src="product.image" alt="">
            <span class="like">
              <i class="fas fa-heart heart"></i> 73
            </span>
          </div>
          <div class="content">
            <p class="product-name">{{ product.name }}</p>
            <p>{{ product.alcohol }}% | {{ product.volume }} </p>
            <!-- <p>도수: {{ product.alcohol }}%</p>
            <p>용량: {{ product.volume }}</p> -->
            <!-- <p class="product-price">가격: {{ product.price }}원</p> -->
          </div>
        </div>
      </div>
      <el-pagination layout="prev, pager, next" :current-page="searchInfo.page" @current-change="changePage"
        :total="products.total" />
    </div>
  </div>
</template>

<script setup>
import HeaderView from "@/views/common/HeaderView.vue"
import ModeToggle from "@/views/common/ModeToggle.vue"
import { ref, onMounted, computed } from "vue";
import { useRouter } from "vue-router";
import { useStore } from "vuex"

const router = useRouter();
const store = useStore();
const position = ref(0);
const products = computed(() => store.getters.products)
const isProductLoad = computed(() => store.getters.isProductLoad)
const searchInfo = ref({
  alcohol: 100,
  award: false,
  page: 1,
  size: 10,
  types: [],
  keyword: ""
})
const searchProducts = () => {
  console.log(searchInfo.value)
  store.dispatch('fetchProducts', searchInfo.value)
}
const changePage = (page) => {
  searchInfo.value.page = page
  searchProducts()
}
const goToDetail = (productId) => {
  router.push({ name: 'WoojooDetail', params: { 'productPk': productId } },)
}

store.dispatch('fetchProducts', searchInfo.value)
</script>

<style>
.search-filter {
  margin: 30px 7px;
  padding: 15px;
  width: calc(100vw - 44px);
  max-width: 450px;
  border-radius: 20px;
  /* background-color: var(--card-bg-color); */
  box-shadow: 0px 0px 4px 6px rgba(0, 0, 0, var(--shadow-percentage-03));
}

.search-filter>div {
  margin-bottom: 10px;
}

.search-filter .search-checkbox-group {
  padding: 0 5px;
  display: flex;
}

.search-filter .search-checkbox-group input {
  display: none;
}

.search-filter .search-checkbox-group .checkbox-img {
  width: 80px;
  overflow: hidden;
}

.search-filter .search-checkbox-group .search-label {
  margin: 5px;
  width: 30vw;
  max-width: 150px;
  display: flex;
  flex-direction: column;
  background-color: var(--main-bg-color);
  border-radius: 20px;
  border: 1px solid var(--border-color);
}

.search-filter .search-checkbox-group .type-name {
  width: 90%;
  height: 35px;
  font-size: 0.9rem;
}

.search-checkbox:checked + .search-label {
  box-shadow: inset 1px 1px 6px var(--border-color);
  filter: brightness(var(--checked-brightness));
}

.search-alcohol {
  display: flex;
  justify-content: center;
  --el-fill-color-light: var(--main-bg-color);
  --el-border-color: var(--border-color);
  --el-border: none;
}

.search-alcohol>div {
  margin: 0;
}

.search-alcohol .el-input__inner {
  background-color: var(--main-bg-color);
  color: rgb(var(--text-color));
}

.search-alcohol .el-input-number--small {
  width: 80px;
}

.search-alcohol div~div {
  margin-left: 5px;
}

.search-award {
  display: flex;
  justify-content: center;
}

.search-award>* {
  margin: 0 5px;
}

.search-keyword {
  display: flex;
  justify-content: center;
}

.search-keyword>* {
  margin: 0;
}

.search-keyword input {
  border-right: none;
  border-color: var(--border-color);
  border-bottom-left-radius: 10px;
  border-top-left-radius: 10px;
  color: black;
}

.search-keyword .search-button {
  background-color: var(--main-bg-color);
  border-color: var(--border-color);
  border-bottom-right-radius: 10px;
  border-top-right-radius: 10px;
}

.products-container {
  padding-bottom: 15px;
}

.products-container .el-pagination {
  --el-pagination-button-disabled-bg-color: var(--main-bg-color);
  --el-pagination-bg-color: var(--main-bg-color);
}

.products-list {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-evenly;
  background: var(--main-bg-color);
}

.products-list .products-list-box {
  width: min(170px, 40vw);
  height: 300px;
  padding: 5px;
  margin: 10px 0px 10px 0px;
  text-align: left;
}

.products-list .products-list-box .image img {
  border-radius: 15px;
  max-width: 170px;
  height: 220px;
  object-fit: cover;
  box-shadow: 0px 0px 6px 6px rgba(0, 0, 0, var(--shadow-percentage-04));
}

.products-list .products-list-box .content {
  font-size: 0.9rem;
  text-align: center;
}

.products-list .products-list-box .product-name {
  margin-top: 10px;
  text-align: center;
  font-size: 1rem;
  font-weight: bold;
}

.products-list .products-list-box .product-alcohol {
  font-weight: bold;
}

.article-create .create-title .el-input__wrapper {
  height: 40px;
  padding: 0 11px;
  border: none;
  box-shadow: 0 0 0 0 ;
}

</style>
