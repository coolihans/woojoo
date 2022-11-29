import http from "@/api/index.js"

const community = {
	state: {
		products: [],
    product: {},
		isProductLoad: false,
	},
	mutations: {
		// SET_REVIEWS: (state, reviews) => (state.reviews = reviews),
		SET_PRODUCTS: (state, products) => (state.products = products),
    SET_PRODUCT: (state, product) => (state.products = product),
		SET_IS_PRODUCT_LOAD: (state, isProductLoad) => (state.isProductLoad = isProductLoad)
	},
	getters: {
		products: (state) => state.products,
    product: (state) => state.product,
		isProductLoad: (state) => state.isProductLoad,
	},
	actions: {
		fetchProducts({ commit }, searchInfo) {
			commit("SET_IS_PRODUCT_LOAD", false)
			http.post("/product", searchInfo)
				.then(({ data }) => {
					commit("SET_PRODUCTS", data.object)
					commit("SET_IS_PRODUCT_LOAD", true)
				})
				.catch((err) => console.log(err))
		}, 
    fetchProduct({ commit }) {
			http.post("/product",)
				.then(({ data }) => {
					commit("SET_PRODUCT", data)
				})
				.catch((err) => console.log(err))
		}, 
	},
}

export default community