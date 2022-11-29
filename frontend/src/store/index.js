import { createStore } from "vuex";

import community from "./modules/community"
import mypage from "./modules/mypage"
import woojooDetail from "./modules/woojooDetail"
import reviewCreate from "./modules/reviewCreate"
import woojooSearch from "./modules/woojooSearch"
import recommendation from "./modules/recommendation";
import auth from "./modules/auth"
import product from "./modules/product"

const store = createStore({
  modules: {
    community,
    mypage,
    woojooDetail,
    reviewCreate,
    woojooSearch,
    recommendation,
    auth,
    product
  },
})

export default store;