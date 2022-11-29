<template>
  <div class="mypage-review-container mypage-inner">
    <div class="list-row">
      <div class="mypage-review-card" v-for="(data, idx) in profileData.profile?.reviewList" @click="openModal(idx)">
        <div class="review-img">
          <img :src="data.img[0]" alt="" v-if="data.img[0]">
          <!-- <img src="@/assets/image/nonpic.png" alt="" v-else> -->
        </div>
        <div class="review-content">
          <div class="review-wooju">
            {{ data.product_name }}
          </div>
          <div class="review-date">
            {{ data.time }}
          </div>
          <div class="review-rate">
            <i class="fas fa-star star"></i> {{ data.star }}
          </div>
        </div>
      </div>
    </div>

  </div>
  
  <teleport to='#tel'>
    <transition name="background">
      <div v-if="isOpen" class="teleport-background"></div>
    </transition>
    <transition name="review">
      <div v-if="isOpen" class="teleport-container">
        <div class="modal-header">
          <div class="date">
            {{ profileData.profile?.reviewList[postIdx].time }}
          </div>
          <div class="edit-btn" @click="linkToEdit(profileData.profile?.reviewList[postIdx].id)">
            <div class="btn-box">
              수정
            </div>
          </div>
          <div class="delete-btn" @click="linkToDelete(profileData.profile?.reviewList[postIdx].id)">
            <div class="btn-box">
              삭제
            </div>
          </div>
        </div>
        
        <el-scrollbar>
          <div class="image-carousel">
            <el-carousel trigger=click :autoplay="false" arrow="always">
              <el-carousel-item v-for="image in profileData.profile?.reviewList[postIdx].img">
                <img :src="image" alt="pic">
              </el-carousel-item>
            </el-carousel>
          </div>

          <div class="review-stat">
            <div class="wooju" @click="linkToProduct(profileData.profile?.reviewList[postIdx].product_id)">
              술 이름: <span class="name">{{ profileData.profile?.reviewList[postIdx].product_name }}</span> >
            </div>
            <div class="rate">
              내 평점: <i class="fas fa-star star" v-for="i in 4"></i> <span class="rate-num">{{ profileData.profile?.reviewList[postIdx].star }}</span>
            </div>
          </div>

          <div class="review-content">
            {{ profileData.profile?.reviewList[postIdx].content }}
          </div>

          <button class="close-btn" @click="closeModal()">목록으로</button>
        </el-scrollbar>
      </div>
    </transition>
  </teleport>
</template>

<script setup>
import { ref, computed, onMounted } from "vue"
import { useStore } from "vuex";
import { useRouter, useRoute } from "vue-router";

const router = useRouter();
const route = useRoute();
const store = useStore();
const linkToProduct = (productPk) => router.push({ name: "WoojooDetail", params: { productPk: productPk }})
const linkToEdit = (reviewPk) => router.push({ name: "WoojooReviewEdit", params: { reviewPk: reviewPk }})
const linkToDelete = (reviewPk) => {
  store.dispatch("deleteReview", reviewPk)
}

const reviewIdx = route.query.reviewIdx

const profileData = computed(() => store.getters.profile)

const isOpen = ref(false)
const postIdx = ref(0)

onMounted(() => {
  if (!!reviewIdx) {
    openModal(reviewIdx)
  }
})

const openModal = (idx) => {
  isOpen.value = true
  openFunc()
  postIdx.value = idx
}
const closeModal = () => {
  isOpen.value = false
  closeFunc()
}

const openFunc = () => {
  const telContainer = document.querySelector("#tel")
  telContainer.style.display = "block"
}

const closeFunc = () => {
  const telContainer = document.querySelector("#tel")
  telContainer.style.display = "none" 
}

</script>

<style>
  button {
  width: 100px;
  height: 40px;
  }
  
  .review-enter-from {
    top: calc(100vh - 190px);
  }

  .review-enter-to {
    top: 0;
  }

  .review-leave-to {
    top: calc(100vh - 190px);
  }
  
  .background-enter-from {
    opacity: 0;
  }

  .background-enter-to {
    opacity: 1;
  }

  .background-leave-to {
    opacity: 0;
  }
  
</style>
