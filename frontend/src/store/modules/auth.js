import router from "../../router";
import axios from "axios";
import http from "@/api/index.js";

const mypage = {
  state: {
    token: sessionStorage.getItem("token") || "",
  },
  mutations: {
    SET_TOKEN: (state, token) => (state.token = token),
  },
  getters: {
    token: (state) => state.token,
    isLoggedIn: (state) => !!state.token,
    authHeader: (state) => `Bearer ${state.token}`,
  },
  actions: {
    saveToken({ commit }, token) {
      commit("SET_TOKEN", token);
      localStorage.setItem("token", "");
      sessionStorage.setItem("token", token);
    },
    logout({ commit, getters }) {
      if (getters.isLoggedIn) {
        sessionStorage.setItem("token", "");
        commit("SET_TOKEN", "");
        router.push({ name: "MainHome" });
      }
    },
    signup({}, signupInfo) {
      console.log(signupInfo);
      axios({
        method: "POST",
        url: "https://j7a304.p.ssafy.io/api/image/upload",
        headers: {
          "Content-Type": "multipart/form-data",
        },
        data: signupInfo.file,
      })
        .then(({ data }) => {
          http
            .post("/user/signup", {
              birthdate: signupInfo.birthdate,
              email: signupInfo.email,
              gender: signupInfo.gender,
              img: data.img,
              nickname: signupInfo.nickname,
              usertype: signupInfo.usertype,
            })
            .then(({ data }) => {
              // console.log(data)        // router push to login page
              router.push({ name: "LoginBase" });
            })
            .catch((err) => console.log(err));
        })
        .catch((err) => console.log(err));
    },
  },
};

export default mypage;
