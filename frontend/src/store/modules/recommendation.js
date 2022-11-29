import http from "@/api/index.js"
import axios from "axios"
import router from "../../router";

const recommendation = {
  state: {
    recommendation: {
      taste: [],
      today: [],
      award: [],
    },
    isSurveyed: false,
    isRecommLoaded: false,
  },
  mutations: {
    SET_RECOMMENDATION: (state, recommendation) => (state.recommendation = recommendation),
    SET_IS_SURVEYED:(state, isSurveyed) => (state.isSurveyed = isSurveyed),
    SET_IS_RECOMM_LOADED:(state, isRecommLoaded) => (state.isRecommLoaded = isRecommLoaded),
  },
  getters: {
    recommendation: (state) => state.recommendation,
    isSurveyed: (state) => state.isSurveyed,
    isRecommLoaded: (state) => state.isRecommLoaded,
  },
  actions: {
    submitSurvey({ commit, getters }, surveyInfo) {
      console.log(surveyInfo)
      http.put("/user/survey", 
        surveyInfo,
        {headers: { Authorization: getters.authHeader },
      })
      .then(({data}) => {
        commit("SET_IS_SURVEYED", true)
        commit("SET_RECOMMENDATION", data.obj)
        router.push({ 
          name: "MyRecommendationResult",
        });
      })
      .catch((err) => console.log(err))
    },
    fetchRecommendationResult({ commit, getters }) {
      commit("SET_IS_RECOMM_LOADED", false)
      var headers = {Authorization : ''}
      if (getters.isLoggedIn) {
        headers.Authorization = getters.authHeader
      }
      http.get("/user/survey", {'headers': headers})
        .then(({ data }) => {
          http.post("/user/survey",
          data.dto,
          {'headers': headers})
          .then(({ data }) => {
            commit("SET_RECOMMENDATION", data.obj)
            commit("SET_IS_RECOMM_LOADED", true)
          })
          .catch((err) => console.log(err))
        })
        .catch((err) => console.log(err))
    }
  },
}

export default recommendation