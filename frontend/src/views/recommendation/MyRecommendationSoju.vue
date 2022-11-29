<template>
  <HeaderView/>
  <div class="survey-container">
    <div class="weekday">
      <!-- monday -->
      <div class="ques-container">
        <div class="day">
          <span>
            월요일
          </span>
        </div>
        <div class="question">
          <div class="ques-img-containerg">
            <img src="@/assets/images/survey/perfume.png" alt="qest-img">
          </div>
          <div class="ask">
            아침에 분주하게 나갈 준비를 합니다.<br/>나가기 직전,<br/>당신은 어떤 것을 옷에 뿌리겠어요?
          </div>
        </div>
        <div id="question1" class="options">

          <div class="five opt">
            <div class="biggest circle" @click="question(1, 0)"></div>
            <div class="bigger circle" @click="question(1, 1)"></div>
            <div class="big circle" @click="question(1, 2)"></div>
            <div class="bigger circle" @click="question(1, 3)"></div>
            <div class="biggest circle last-opt" @click="question(1, 4)"></div>
          </div>
          <div class="opt-content">
            <div class="left-content">
              좋아하는 향기를 두를래!<br/>
            <span>향수 뿌리기</span>
            </div>
            <div class="right-content">
              깔끔한 게 좋지!<br/>
            <span>탈취제 뿌리기</span>
            </div>
          </div>
        </div>
      </div>

      <!-- tuesday -->
      <div class="ques-container">
        <div class="day">
          <span>
            화요일
          </span>
        </div>
        <div class="question">
          <div class="ques-img-container">
            <img src="@/assets/images/survey/company.png" alt="qest-img">
          </div>
          <div class="ask">
            동기들과 후식으로 마실 것을 사려고 합니다.<br/>둘 중 어느 음료가 더 당기나요?
          </div>
        </div>
        <div id="question2" class="options">
          <div class="five opt">
            <div class="biggest circle" @click="question(2, 0)"></div>
            <div class="bigger circle" @click="question(2, 1)"></div>
            <div class="big circle" @click="question(2, 2)"></div>
            <div class="bigger circle" @click="question(2, 3)"></div>
            <div class="biggest circle last-opt" @click="question(2, 4)"></div>
          </div>
          <div class="opt-content">
            <div class="left-content">
              입안이 가벼워지는<br/>
              <span>루이보스 차</span>
            </div>
            <div class="right-content">
              부드럽게 넘어가는<br/>
              <span>밀크 쉐이크</span>
            </div>
          </div>
        </div>
      </div>

      <!-- wednesday -->
      <div class="ques-container">
        <div class="day">
          <span>
            수요일
          </span>
        </div>
        <div class="question">
          <div class="ques-img-container">
            <img src="@/assets/images/survey/drunken.png" alt="qest-img">
          </div>
          <div class="ask">
            오랜만에 동창회에 갔습니다.<br/>술자리에서 당신은 어떻게 행동하나요?
          </div>
        </div>
        <div id="question3" class="options">

          <div class="five opt">
            <div class="biggest circle" @click="question(3, 0)"></div>
            <div class="bigger circle" @click="question(3, 1)"></div>
            <div class="big circle" @click="question(3, 2)"></div>
            <div class="bigger circle" @click="question(3, 3)"></div>
            <div class="biggest circle last-opt" @click="question(3, 4)"></div>
          </div>
          <div class="opt-content">
            <div class="left-content">
              취한 느낌을 즐기는 거라고<br/>
              <span>술은 취하려고<br/>마시는 거지!</span>
            </div>
            <div class="right-content">
              그 조명, 온도, 습도….<br/>
              <span>술자리에서의<br/>분위기가 좋아</span>
            </div>
          </div>
        </div>
      </div>

      <!-- thursday -->
      <div class="ques-container">
        <div class="day">
          <span>
            목요일
          </span>
        </div>
        <div class="question">
          <div class="ques-img-container">
            <img src="@/assets/images/survey/curry.png" alt="qest-img">
          </div>
          <div class="ask">
            새로 생긴 커리 전문점에 갔습니다.<br/>다양한 옵션의 커리, 어떻게 주문하시겠어요?
          </div>
        </div>
        <div id="question4" class="options">
          <div class="five opt">
            <div class="biggest circle" @click="question(4, 0)"></div>
            <div class="bigger circle" @click="question(4, 1)"></div>
            <div class="big circle" @click="question(4, 2)"></div>
            <div class="bigger circle" @click="question(4, 3)"></div>
            <div class="biggest circle last-opt" @click="question(4, 4)"></div>
          </div>
          <div class="opt-content">
            <div class="left-content">
              강황, 고수, 계피…. 제대로 즐길래<br/>
            <span>향신료 넣어주세요!</span>
            </div>
            <div class="right-content">
              본연의 마일드한 맛을 즐길래<br/>
            <span>기본으로 주세요~</span>
            </div>
          </div>
        </div>
      </div>

      <!-- friday -->
      <div class="ques-container">
        <div class="day">
          <span>
            금요일
          </span>
        </div>
        <div class="question">
          <div class="ques-img-container">
            <img src="@/assets/images/survey/salad.png" alt="qest-img">
          </div>
          <div class="ask">
            이번주, 너무 잘 먹고 다닌 것 같아요.<br/>다이어트를 결심한 당신,<br/>샐러드에 어떤 드레싱을 곁들일래요?
          </div>
        </div>
        <div id="question5" class="options">
          <div class="five opt">
            <div class="biggest circle" @click="question(5, 0)"></div>
            <div class="bigger circle" @click="question(5, 1)"></div>
            <div class="big circle" @click="question(5, 2)"></div>
            <div class="bigger circle" @click="question(5, 3)"></div>
            <div class="biggest circle last-opt" @click="question(5, 4)"></div>
          </div>
          <div class="opt-content">
            <div class="left-content">
              고소하고 먹기 부담스럽지 않은<br/>
            <span>참깨 드레싱</span>
            </div>
            <div class="right-content">
              상큼하고 냄새가 좋은<br/>
            <span>오리엔탈 드레싱</span>
            </div>
          </div>
        </div>
      </div>
    </div>
    
    <div class="ques-container submit-survey">
      <div class="last-img">
        <img src="@/assets/images/survey/last.png" alt="last">
      </div>
      <div class="submit-btn" @click="SubmitSurvey">
        나의 술 취향은?
      </div>
    </div>

  </div>
</template>

<script setup>
import HeaderView from "@/views/common/HeaderView.vue"
import { ref, onMounted } from "vue";
import { useStore } from "vuex";
import { useRouter } from "vue-router";

const store = useStore()


onMounted (() => {
  window.scrollTo(0, 0)
})

const answerList = ref({
  "question1": -1,
  "question2": -1,
  "question3": -1,
  "question4": -1,
  "question5": -1,
  "question6": -1,
  "type": "증류주",
})



const question = (idx, option) => {
  answerList.value[`question${idx}`] = option

  document.querySelectorAll(`#question${idx} div.opt .circle`)[0].classList.remove('active')
  document.querySelectorAll(`#question${idx} div.opt .circle`)[1].classList.remove('active')
  document.querySelectorAll(`#question${idx} div.opt .circle`)[2].classList.remove('active')
  document.querySelectorAll(`#question${idx} div.opt .circle`)[3].classList.remove('active')
  document.querySelectorAll(`#question${idx} div.opt .circle`)[4].classList.remove('active')

  document.querySelectorAll(`#question${idx} div.opt .circle`)[option].classList.add('active')

}


const weekday = {
  1: "월요일",
  2: "화요일",
  3: "수요일",
  4: "목요일",
  5: "금요일",
}

const checkSurvey =() => {
  for (let index = 1; index < 6; index++) {
    const answer = answerList.value[`question${index}`];
    if (answer == -1) {
      const day = weekday[index]
      alert(`${day} 문항에 체크해주세요!`)
      return false
    }
  }
  return true
}

const SubmitSurvey = () => {
  const checked = ref(false)
  checked.value = checkSurvey()
  if (checked.value) {
    store.dispatch("submitSurvey", answerList.value)
  }
}

const router = useRouter();
const linkTo = (name) => router.push({ name: name })

</script>

<style>
  
</style>