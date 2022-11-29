import http from "@/api/index.js"

const community = {
	state: {
		woojooList: [],

	},
	mutations: {
		// SET_REVIEWS: (state, reviews) => (state.reviews = reviews),
		SET_WOOJOOLIST: (state, woojooList) => (state.woojooList = woojooList)
	},
	getters: {
		// reviews: (state) => state.reviews,
		woojooList: (state) => state.woojooList,
	},
	actions: {
		// fetchAllReviews({ commit }) {
		// 	http.post("/review")
		// 		.then(({ data }) => {
		// 			commit("SET_REVIEWS", data)
		// 		})
		// 		.catch((err) => console.log(err))
		// },
		fetchWoojooList({ commit }) {
      console.log('----1')
			http.post("/product", {
          "alcohol": 30,
          "award": false,
          "page": 1,
          "size": 10,
          "types": []
      })
				.then(({ data }) => {
					console.log(data)
					// commit("SET_WOOJOOLIST", data)
				})
				.catch((err) => console.log(err))
		}
	},
}

export default community