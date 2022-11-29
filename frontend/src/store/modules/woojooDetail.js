import http from "@/api/index.js"

const WoojooDetail = {
  state: {
    // woojooReviews : [],
    woojooInfo : {},
    isLike : false,
    like_num :'',
  },
  mutations: {
    // SET_WOOJOOREVIEWS: (state, woojooReviews) => (state.woojooReviews = woojooReviews),
    SET_WOOJOOINFO: (state, woojooInfo) => (state.woojooInfo = woojooInfo),
    // SET_WOOJOO: (state, woojoo) => (state.woojoo = woojoo),
    SET_ISLIKE: (state, isLike) => (state.isLike = isLike),
  },
  getters: {
    // woojooReviews: (state) => state.woojooReviews,
    woojooInfo: (state) => state.woojooInfo,
    // woojoo: (state) => state.woojoo,
    isLike: (state) => state.isLike,
  },
  actions: {
    // fetchWoojooReviews({ commit }) {
    //   console.log('-----3---')
    //   http.get('/product/103')
    //     .then(({ data }) => {
    //       console.log(data.list)
    //       commit("SET_WOOJOOREVIEWS", data.list)
    //     })
    //     .catch((err) => console.log(err))
    // },
    fetchWoojooInfo({ commit }, productId) {
      http.get(`/product/${productId}`)
        .then(({ data }) => {
          console.log(data)
          commit("SET_WOOJOOINFO", data)
        })
        .catch((err) => console.log(err))
    },

    likeCheck({commit, getters}, productId) {
      console.log('라이크 체크')
      console.log(getters.isLike)
      http.get(`/product/likeCheck/${productId}`,
        { headers: 
          {Authorization: getters.authHeader},  
          // {Authorization: 'Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIyIiwiaXNzIjoid29vanUiLCJleHAiOjE2NjYzMTA5NDEsImlhdCI6MTY2NTAxNDk0MX0.mQbI8BNMjowD6Uu-WXulx65jyi2NfwXXmD0PA486w1IxOz8FQWtvBMKxwWC0dmU5STdoiOIG_uuGKNXAyybsOw'}
        })
        .then(({data}) => {
          console.log(data.likecheck)
          commit("SET_ISLIKE", data.likecheck)
          console.log(getters.isLike)
          }
        )
    },

    likeProduct({ getters, commit }, productId) {
      console.log('like')
      console.log(getters.isLike)
      http.post('/product/like', {
        product_id: productId,
      }, {
        headers: 
        {Authorization: getters.authHeader},  
        // {Authorization: 'Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIyIiwiaXNzIjoid29vanUiLCJleHAiOjE2NjYzMTA5NDEsImlhdCI6MTY2NTAxNDk0MX0.mQbI8BNMjowD6Uu-WXulx65jyi2NfwXXmD0PA486w1IxOz8FQWtvBMKxwWC0dmU5STdoiOIG_uuGKNXAyybsOw'}
      })
        .then(({data}) => {
          console.log(data)
          commit("SET_ISLIKE", true)
          this.$router.go()
          }
        )
        .catch((err) => console.log(err.message)
        )
    },

    unlikeProduct({getters, commit}, productId) {
      console.log('unlike')
      http.delete(`product/like/${productId}`, {
        headers: 
        {Authorization: getters.authHeader}
        // {Authorization: 'Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIyIiwiaXNzIjoid29vanUiLCJleHAiOjE2NjYzMTA5NDEsImlhdCI6MTY2NTAxNDk0MX0.mQbI8BNMjowD6Uu-WXulx65jyi2NfwXXmD0PA486w1IxOz8FQWtvBMKxwWC0dmU5STdoiOIG_uuGKNXAyybsOw'}
      })
        .then(() => {
          commit("SET_ISLIKE", false)
        })
        .catch((err) => console.log(err))
    }

    // {
    //   if (err.message === 'Request failed with status code 405'){
    //     http.delete(`/product/like/${productId}`)
    //     .then(({data})=> {
    //     console.log(data)
    // })
    //   }
    //   else{ console.log(err)}
    // } 

    // fetchWoojoo({ commit }) {
    //   http.get('/product',
    //     {
    //       params: {
    //         size: 10,
    //       }
    //     }
    //   )
    //     .then(({ data }) => {
    //       commit("SET_WOOJOO", data.items)
    //     })
    //     .catch((err) => console.log(err))
    // }
  },
}

export default WoojooDetail