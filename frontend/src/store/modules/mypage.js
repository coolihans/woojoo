import router from "../../router";
import axios from "axios";
import http from "@/api/index.js"

const mypage = {
  state: {
    profile: {},
    woojoo: [],
    isLoaded: false,
  },
  mutations: {
    SET_PROFILE: (state, profile) => (state.profile = profile),
    SET_WOOJOO: (state, woojoo) => (state.woojoo = woojoo),
    SET_ISLOADED: (state, isLoaded) => (state.isLoaded = isLoaded),
  },
  getters: {
    profile: (state) => state.profile,
    woojoo: (state) => state.woojoo,
    isLoaded: (state) => state.isLoaded,
  },
  actions: {
    fetchProfile({ commit, getters }) {
      commit("SET_ISLOADED", false)
      http.get("/user/profile", {
        headers: {Authorization: getters.authHeader}
      })
        .then(({ data }) => {
          commit("SET_PROFILE", data)
        })
        .then(() => {
          commit("SET_ISLOADED", true)
          }
        )
        .catch((err) => console.log(err))
    },
    profileEdit({ getters }, profileInfo) {
      if (profileInfo.flag) {
        axios({
          method: "POST",
          url: "https://j7a304.p.ssafy.io/api/image/upload",
          headers: {
            Authorization: getters.authHeader,
            "Content-Type": "multipart/form-data",
          },
          data: profileInfo.file,
        })
          .then(({ data }) => {
            http.put("/user/profile", {
              nickname: profileInfo.nickname,
              img: data.img
            }, {
              headers: {Authorization: getters.authHeader}
            })
            .then(({ data }) => {
              router.push({
                name: "MyPageMain",
              })
            })
            .catch((err) => console.log(err))
        })
        .catch((err) => console.log(err))
      } else {
        http.put("/user/profile", {
          nickname: profileInfo.nickname,
          img: profileInfo.img
        }, {
          headers: {Authorization: getters.authHeader}
        })
        .then(({ data }) => {
          router.push({
            name: "MyPageMain",
          })
        })
        .catch((err) => console.log(err))
      }
    },
  },
}

export default mypage