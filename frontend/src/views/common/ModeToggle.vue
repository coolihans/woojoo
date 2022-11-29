<template>
  <button @click="toggleFunc" class="mode-toggle">
    <span class="mode-icon">
      <i class="fas fa-moon" id="mode-icon"></i>
    </span>
  </button>
</template>

<script setup>
import { ref, onMounted } from 'vue'

const toggleValue = ref(!sessionStorage.getItem('darkMode'));

const setDarkmode = (mode) => {
  if (mode == 'dark') {
    document.querySelector('meta[name="theme-color"]').setAttribute('content',  '#091022')

    document.documentElement.style.setProperty('--main-color', '#ffd524');
    document.documentElement.style.setProperty('--main-bg-color', '#1d222e'); // darker
    document.documentElement.style.setProperty('--main-header-color', '#1d222e');
    document.documentElement.style.setProperty('--highlight-bg-color', '#091022'); // darkest
    document.documentElement.style.setProperty('--menu-color', '#23283c');
    document.documentElement.style.setProperty('--text-color', '230, 230, 230');
    document.documentElement.style.setProperty('--text-highlight-color', '#FFD634');
    document.documentElement.style.setProperty('--card-bg-color', '#282B37'); // dark
    document.documentElement.style.setProperty('--card-transparent-color', '#282B37');
    document.documentElement.style.setProperty('--border-color', '#282B37');
    document.documentElement.style.setProperty('--review-body-color', '#091022');

    document.documentElement.style.setProperty("--shadow-percentage-03", "0.08")
    document.documentElement.style.setProperty("--shadow-percentage-04", "0.09")

    document.documentElement.style.setProperty("--checked-brightness", "0.7")

    document.querySelector("#mode-icon").classList.add('fa-sun')
    document.querySelector("#mode-icon").classList.remove('fa-moon')

  } else {
    document.querySelector('meta[name="theme-color"]').setAttribute('content',  '#FFD634')

    document.documentElement.style.setProperty('--main-color', '#FFD634');    
    document.documentElement.style.setProperty('--main-bg-color', '#f9f9f9');
    document.documentElement.style.setProperty('--main-header-color', '#FFD634');
    document.documentElement.style.setProperty('--highlight-bg-color', '#FFD634');
    document.documentElement.style.setProperty('--menu-color', '#fafafa');
    document.documentElement.style.setProperty('--text-color', '0, 0, 0');
    document.documentElement.style.setProperty('--text-highlight-color', '#FF9314');
    document.documentElement.style.setProperty('--card-bg-color', '#fff');
    document.documentElement.style.setProperty('--card-transparent-color', 'transparent');
    document.documentElement.style.setProperty('--border-color', '#E8E8E8');
    document.documentElement.style.setProperty('--review-body-color', '#fff');
    
    document.documentElement.style.setProperty("--shadow-percentage-03", "0.03")
    document.documentElement.style.setProperty("--shadow-percentage-04", "0.04")

    document.documentElement.style.setProperty("--checked-brightness", "0.97")

    document.querySelector("#mode-icon").classList.add('fa-moon')
    document.querySelector("#mode-icon").classList.remove('fa-sun')
  }

}

const toggleFunc = () => {
  if ( toggleValue.value ) {
    // 다크모드
    sessionStorage.setItem('darkMode', 'yes')

    toggleValue.value = !toggleValue.value
    setDarkmode('dark')
  } else {
    // 라이트모드
    sessionStorage.setItem('darkMode', '')

    toggleValue.value = !toggleValue.value
    setDarkmode('light')
  }
}

onMounted(()=> {
  if (!toggleValue.value) {
    setDarkmode('dark')
  }
})
</script>

<style>
.mode-toggle {
  width: 40px;
  height: 40px;
  background-color: transparent;
  border: 0px solid #aaa;
  position: absolute;
  top: 5px;
  right: 10px;
} 
</style>