import axios from "axios";
import store from "../store";

// axios 객체 생성
export default axios.create({
  // baseURL: "http://192.168.195.105:8080/api/",
  // baseURL: "http://172.16.101.80:8082/",
  // baseURL: "http://localhost:8181/api/",
  // baseURL: "http://j7a304.p.ssafy.io:8082/",
  baseURL: "https://j7a304.p.ssafy.io/api/",
});
