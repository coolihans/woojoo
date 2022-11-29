<template>
  <!-- <HeaderView/> -->
  <div class="header-container">
    <div class="create-header">
      <div class="create-title">
        리뷰 수정
      </div>
      <div class="submit">
        <div class="submit-btn" @click="onSubmit">등록</div>
      </div>
    </div>
  </div>
  
  <div class="create-container" v-if="isReviewLoaded">
    <div class="create-input">
      <div class="sool-name">
        술 이름: <span>{{ reviewData.review.product_name }}</span>
      </div>
      <div class="line"></div>
      <div class="sool-rate">
        <el-rate
          v-model="reviewData.review.star"
          allow-half
          size="large"
          show-score
          ></el-rate>
      </div>
      <div class="review-textarea">
        <el-input
          v-model="reviewData.review.content"
          :rows="12"
          type="textarea"
          placeholder="내용을 입력해주세요"
        />
      </div>
      <div class="img-upload">
        <div class="thumbnail-box">
          <div class="thumbnail gallary-btn">
            <img :src="reviewData.review?.img[0]" alt="" class="thumb0">
          </div>
          <div class="thumbnail">
            <img :src="reviewData.review?.img[1]" alt="" class="thumb1">
          </div>
          <div class="thumbnail">
            <img :src="reviewData.review?.img[2]" alt="" class="thumb2">
          </div>
        </div>
        <div class="gallary" @click="openGallery">
          <div class="img-icon">
            + 사진 첨부
          </div>
          <input
          type="file"
          accept="image/jpeg, image/jpg, image/png"
          @change="onFileChange"
          multiple
          id="review-upload-input"
          >
        </div>
        <div class="reset" @click="resetGallery">
          <div class="img-icon reset-btn">
            - 첨부 초기화
          </div>
        </div>
        <!-- <div class="alert">파일은 최대 3개까지 첨부가 가능합니다.</div> -->
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from "vue";
import { useStore } from "vuex";
import { useRoute, useRouter } from "vue-router"

const route = useRoute()
const store = useStore();
const reviewPk = route.params.reviewPk

const reviewData = computed(() => store.getters.review);
const isReviewLoaded = computed(() => store.getters.isReviewLoaded);

store.commit("SET_IS_REVIEW_LOADED", false)
store.dispatch("fetchReview", reviewPk)


// image upload
const uploadImages = ref([])
const maxLength = ref(3)
var imageData = new FormData()
const flag = ref(false)

const onFileChange = (image) => {
  if (image.target.files.length > maxLength.value) {
    alert("이미지는 최대 3개까지 첨부가 가능합니다.")
    document.getElementById("review-upload-input").value = "";
  } else {
    for (let index = 0; index < image.target.files.length; index++) {
      // uploadImages.value.push(image.target.files[index])
      flag.value = true
      eachThumbnail(index)
      getResized(image, index) 
    }
  }
}

const getResized = (image, index) => {
  const originalWidth = ref(0)
  const originalHeight = ref(0)
  const resizedWidth = ref(0)
  const resizedHeight = ref(0)
    
  
  const pic = image.target.files[index]
  var reader = new FileReader()
  reader.readAsDataURL(pic);
  
  // get size of image
  reader.onload = function (e) {
    var image = new Image();
    image.src = e.target.result;
    
    image.onload = function() {
      originalWidth.value = this.width
      originalHeight.value = this.height
      
      if ( originalWidth.value > 500 && originalHeight.value > 500) {
        if ( originalWidth.value > originalHeight.value ) {
          resizedWidth.value = 500
          resizedHeight.value = originalHeight.value * 500 / originalWidth.value
        } else {
          resizedHeight.value = 500
          resizedWidth.value = originalWidth.value * 500 / originalHeight.value
        }
      } else {
        resizedWidth.value = originalWidth.value
        resizedHeight.value = originalHeight.value
      }
      
      var canvas = document.createElement("canvas")
      
      canvas.width = resizedWidth.value
      canvas.height = resizedHeight.value
      
      // draw resized image
      canvas.getContext("2d").drawImage(image, 0, 0, resizedWidth.value, resizedHeight.value)

      // append image in formData
      canvas.toBlob(function(blob) {
        imageData.append("file", blob)
      })
      console.log(imageData)
    }
  }
}

const eachThumbnail = (index) => {
  const pic = document.getElementById("review-upload-input").files[index]
  var reader = new FileReader()

  if (pic) {
    reader.readAsDataURL(pic);
    reader.addEventListener('load', function() {
      document.querySelector(`.thumb${index}`).src = reader.result;
    });
  }
}

const openGallery = () => {
  document.querySelector("#review-upload-input").click()
}

const resetGallery = () => {
  reviewData.value.review.img = []

  for (let index = 0; index < 3; index++) {
    document.querySelector(`.thumb${index}`).src = "";
   
  }
}


// submit
const onSubmit = () => {
  var reviewInfo = {}

  if (flag.value) {
    reviewInfo = {
      id: reviewPk,
      star: reviewData.value.review.star,
      content: reviewData.value.review.content,
      file: imageData,
      flag: flag.value,
    }
  } else {
    reviewInfo = {
      id: reviewPk,
      star: reviewData.value.review.star,
      content: reviewData.value.review.content,
      img: reviewData.value.review.img,
      flag: flag.value,
    }
  }
  store.dispatch('editReview', reviewInfo)
};


</script>

<style>
</style>