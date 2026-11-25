<script setup>
import { ref, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import { getData } from '../http.js'

const route = useRoute()
const article = ref(null)
const error = ref('')

onMounted(async () => {
  try {
    const data = await getData(`/articles/${route.params.id}`)
    article.value = data                  // ← 拆包后直接是文章对象
  } catch (e) {
    error.value = e.message
  }
})
</script>

<template>
  <div class="page" v-if="article">
    <h1>{{ article.title }}</h1>
    <p class="content">{{ article.content }}</p>
    <router-link to="/">← back</router-link>
  </div>
  <p v-else-if="error">加载失败:{{ error }}</p>
  <p v-else>加载中…</p>
</template>

<style scoped>
.content { white-space: pre-wrap; line-height: 1.8; }
</style>