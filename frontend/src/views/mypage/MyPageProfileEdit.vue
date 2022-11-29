<template>
  <HeaderView/>
  <div class="profile-edit-container" v-if="isLoaded">
    <div class="title">
      개인 정보 수정
    </div>
    <div class="avatar">
      <div class="pic">
        <img id="pic-thumbnail" :src="profileData.profile.img" alt="">
      </div>
      <div class="pic-edit" @click="openGallery">
        <div class="pic-edit-btn">
          <i class="fas fa-pen"></i>
        </div>
        <input
          type="file"
          accept="image/jpeg, image/jpg, image/png"
          @change="onFileChange"
          id="pic-upload-input"
        >
      </div>

      <div class="profile-info">
        <div class="nick">
          <el-input type="text" class="edit-nickname" v-model="profileData.profile.nickname"/>
        </div>
        <div class="email">
          <i class="fas fa-envelope envelope"></i> {{ profileData.profile?.email }}
        </div>
      </div>

      <div class="submit" @click="onSubmit">
        수정
      </div>
    </div>
  </div>
</template>

<script setup>
import HeaderView from "@/views/common/HeaderView.vue"
import { ref, onMounted ,computed } from "vue";
import { useStore } from "vuex";

const store = useStore();
const profileData = computed(() => store.getters.profile)
const isLoaded = computed(() => store.getters.isLoaded )

onMounted(() => {
  store.dispatch("fetchProfile")
})


// image upload  
const uploadImage = ref([])
var imageData = new FormData()
const flag = ref(false)

const openGallery = () => {
  document.querySelector("#pic-upload-input").click()
}

const onFileChange = (image) => {
  // uploadImage.value.push(image.target.files[0])
  flag.value = true
  getResized(image, 0)
  getThumbnail()
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
      
      if ( originalWidth.value > 400 && originalHeight.value > 400) {
        if ( originalWidth.value > originalHeight.value ) {
          resizedWidth.value = 400
          resizedHeight.value = originalHeight.value * 400 / originalWidth.value
        } else {
          resizedHeight.value = 400
          resizedWidth.value = originalWidth.value * 400 / originalHeight.value
        }
      } else {
        resizedWidth.value = originalWidth.value
        resizedHeight.value = originalHeight.value
      }
      
      var canvas = document.createElement("canvas")
      
      canvas.width = resizedWidth.value
      canvas.height = resizedHeight.value
      console.log(canvas.width, canvas.height)
      // draw resized image
      canvas.getContext("2d").drawImage(image, 0, 0, resizedWidth.value, resizedHeight.value)

      // append image in formData
      canvas.toBlob(function(blob) {
        imageData.append("file", blob)
      })
    }
  }
  
}


const getThumbnail = () => {
  const pic = document.getElementById("pic-upload-input").files[0]
  var reader = new FileReader()

  if (pic) {
    reader.readAsDataURL(pic);
    reader.addEventListener('load', function() {
      document.querySelector("#pic-thumbnail").src = reader.result;
    });
  }
}

// const 
const onSubmit = () => {
  // var file = new FormData()
  // file.append("file", uploadImage.value[0])
      
  const profileInfo = {
    flag: flag.value,
    file: imageData,
    img: profileData.value.profile.img,
    nickname: profileData.value.profile.nickname,
  }

  store.dispatch("profileEdit", profileInfo)
}

</script>

<style>
  
</style>