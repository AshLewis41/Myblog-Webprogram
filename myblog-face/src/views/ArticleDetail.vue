<script setup>
import { ref, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import { fetchArticle } from '../api.js'

const route = useRoute()              // 从 URL 里拿 :id
const article = ref(null)
const error = ref('')

onMounted(async () => {
  try {
    article.value = await fetchArticle(route.params.id)
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