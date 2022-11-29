import http from "@/api/index.js"

const community = {
  state: {
    reviews: {review: [{}, {}, {},]},
    review: {},
    isCommunityLoaded: false,
    isLikeReview: false,
  },
  mutations: {
    SET_REVIEWS: (state, reviews) => (state.reviews = reviews),
    SET_REVIEW: (state, review) => (state.review = review),
    SET_IS_COMMUNITY_LOADED: (state, isCommunityLoaded) => (state.isCommunityLoaded = isCommunityLoaded),
    SET_ISLIKE_REVIEW: (state, isLikeReview) => (state.isLikeReview = isLikeReview),
  },
  getters: {
    reviews: (state) => state.reviews,
    isCommunityLoaded: (state) => state.isCommunityLoaded,
    isLikeReview: (state) => state.isLikeReview,
  },
  actions: {
    fetchAllReviews({ commit }) {
      http.get("/review")
        .then(({ data }) => {
          commit("SET_REVIEWS", data)
        })
        .catch((err) => console.log(err))
    },
    fetchReviews({ commit }, fetchInfo) {
      commit("SET_IS_COMMUNITY_LOADED", false)
      http.get(`/review/more/${fetchInfo.category}`)
        .then(({ data }) => {
          commit("SET_REVIEWS", data)
          commit("SET_REVIEW", data.review[fetchInfo.post - 1])
        })
        .then(() => {
          commit("SET_IS_COMMUNITY_LOADED", true)
        })
        .catch((err) => console.log(err))
      },
      
    likeReview({ getters, commit }, reviewId) {
      console.log('reviewlike')
      console.log(getters.isLikeReview)
      http.post('/review/like', {
        review_id: reviewId,
      }, 
      { headers: 
        {Authorization: getters.authHeader},
        // {Authorization: 'Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIyIiwiaXNzIjoid29vanUiLCJleHAiOjE2NjYzMTA5NDEsImlhdCI6MTY2NTAxNDk0MX0.mQbI8BNMjowD6Uu-WXulx65jyi2NfwXXmD0PA486w1IxOz8FQWtvBMKxwWC0dmU5STdoiOIG_uuGKNXAyybsOw'}
        
      })
      .then(({data}) => {
        console.log(data+'1')
        commit("SET_ISLIKE_REVIEW", true)
        this.$router.go()
      }
      )
      .catch((err) => console.log(err)
      )
    },

    unlikeReview({getters, commit}, reviewId) {
      console.log('unlike')
      http.delete(`review/like/${reviewId}`, {
        headers: 
        {Authorization: getters.authHeader}
        // {Authorization: 'Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIyIiwiaXNzIjoid29vanUiLCJleHAiOjE2NjYzMTA5NDEsImlhdCI6MTY2NTAxNDk0MX0.mQbI8BNMjowD6Uu-WXulx65jyi2NfwXXmD0PA486w1IxOz8FQWtvBMKxwWC0dmU5STdoiOIG_uuGKNXAyybsOw'}
      })
      .then(() => {
        commit("SET_ISLIKE_REVIEW", false)
      })
      .catch((err) => console.log(err))
    },
    likeCheckReview({commit, getters}, reviewId) {
      console.log('리뷰 라이크 체크')
      console.log(getters.isLikeReview)
      http.get(`/review/likeCheck/${reviewId}`,
      { headers: 
        {Authorization: getters.authHeader},  
        // {Authorization: 'Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIyIiwiaXNzIjoid29vanUiLCJleHAiOjE2NjYzMTA5NDEsImlhdCI6MTY2NTAxNDk0MX0.mQbI8BNMjowD6Uu-WXulx65jyi2NfwXXmD0PA486w1IxOz8FQWtvBMKxwWC0dmU5STdoiOIG_uuGKNXAyybsOw'}
      })
      .then(({data}) => {
        console.log('----')
        console.log(data)
        commit("SET_ISLIKE_REVIEW", data.likecheck)
        console.log(getters.isLikeReview)
        console.log('----')
      }
      )
    },
    },
      
    }
    
    export default community