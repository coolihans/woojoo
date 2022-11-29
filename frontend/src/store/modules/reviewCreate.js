import router from "../../router";
import axios from "axios";
import http from "@/api/index.js"

const reviewCreate = {
  state: {
    review: {},
    isReviewLoaded: false,
  },
  mutations: {
    SET_REVIEW: (state, review) => (state.review = review),
    SET_IS_REVIEW_LOADED: (state, isReviewLoaded) => (state.isReviewLoaded = isReviewLoaded),
  },
  getters: {
    review: (state) => state.review,
    isReviewLoaded: (state) => state.isReviewLoaded,
  },
  actions: {
    fetchReview({ commit }, reviewPk) {
      commit("SET_IS_REVIEW_LOADED", false)
      http.get(`review/${reviewPk}`)
        .then(({ data }) => {
          console.log(data)
          commit("SET_IS_REVIEW_LOADED", true)
          commit("SET_REVIEW", data)
        })
        .catch((err) => console.log(err))
    },
    createReview({ getters }, reviewData) {
      if (reviewData.flag) {
        axios({
          method: "POST",
          url: "https://j7a304.p.ssafy.io/api/image/uploads",
          headers: {
            "Content-Type": "multipart/form-data",
          },
          data: reviewData.file,
        })
          .then(({ data }) => {
            http.post("/review", {
              content: reviewData.content,
              img: data.imgs,
              product_id: reviewData.product_id,
              star: reviewData.star,
            }, {
              headers: {Authorization: getters.authHeader}
            })
            .then(({ data }) => {
              router.push({ 
                name: "WoojooDetail",
                params: { productPk: reviewData.product_id }
              })
            })
            .catch((err) => console.log(err))
        })
        .catch((err) => console.log(err))
      } else {
        http.post("/review", {
          content: reviewData.content,
          img: [],
          product_id: reviewData.product_id,
          star: reviewData.star,
        }, {
          headers: {Authorization: getters.authHeader}
        })
        .then(({ data }) => {
          router.push({ 
            name: "WoojooDetail",
            params: { productPk: reviewData.product_id }
          })
        })
        .catch((err) => console.log(err))
      }
    },
    editReview({ getters }, reviewData) {
      if (reviewData.flag) {
        axios({
          method: "POST",
          url: "https://j7a304.p.ssafy.io/api/image/uploads",
          headers: {
            "Content-Type": "multipart/form-data",
          },
          data: reviewData.file,
        })
          .then(({ data }) => {
            http.put("/review", {
              content: reviewData.content,
              img: data.imgs,
              id: reviewData.id,
              star: reviewData.star,
            }, {
              headers: {Authorization: getters.authHeader}
            })
            .then(({ data }) => {
              router.push({ 
                // name: "WoojooDetail",
                // params: { productPk: reviewData.product_id }
                name: "MyPageMain"
              })
            })
            .catch((err) => console.log(err))
        })
        .catch((err) => console.log(err))
      } else {
        http.put("/review", {
          content: reviewData.content,
          img: reviewData.img,
          id: reviewData.id,
          star: reviewData.star,
        }, {
          headers: {Authorization: getters.authHeader}
        })
        .then(({ data }) => {
          router.push({ 
            name: "WoojooDetail",
            params: { productPk: reviewData.id }
          })
        })
        .catch((err) => console.log(err))
      }
    },
    deleteReview({ getters }, reviewPk) {
      http.delete(`/review/${reviewPk}`, {
        headers: {Authorization: getters.authHeader}
      })
        .then((res) => {
          // router.push({ name: "MyPageMain"})
          location.reload()
        })
        .catch((err) => console.log(err))
    },
  },
}

export default reviewCreate