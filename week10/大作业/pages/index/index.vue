<template>
  <view class="container">
    <button type="primary" @click="sendRequest">点击请求后端接口</button>
  </view>
</template>

<script>
export default {
  methods: {
    sendRequest() {
      console.log("开始请求后端接口...");
      uni.request({
        url: "http://127.0.0.1:8080/api/test",
        method: "GET",
        header: {
          "Authorization": "ok",
          "Content-Type": "application/json"
        },
        success: (res) => {
          console.log("✅ 请求成功！响应数据：", res.data);
          uni.showToast({
            title: "请求成功",
            icon: "success"
          });
        },
        fail: (err) => {
          console.error("❌ 请求失败！错误信息：", err);
          uni.showToast({
            title: "请求失败",
            icon: "error"
          });
        }
      });
    }
  }
}
</script>

<style scoped>
.container {
  padding: 20px;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}
</style>