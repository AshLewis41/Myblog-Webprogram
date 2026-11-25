<script setup>
import { ref, onMounted } from 'vue'
import { getData } from '../http.js'   // ← 用你自己的桥梁

const articles = ref([])
const error = ref('')
const loading = ref(true)        // ← 唯一新增的零件:登记 loading

onMounted(async () => {
  try {
    const data = await getData('/articles', { params: { page: 1, size: 10 } })
    articles.value = data.items        // ← 你原来的拆包逻辑,原样保留
  } catch (e) {
    error.value = e.message
  } finally {
    loading.value = false             // ← 成功/失败都关掉"加载中"
  }
})
</script>

<template>
  <div class="page">
    <h1>MyBlog</h1>

    <!-- 三种诚实表情:顺序很重要 —— 先查失败,再查加载,最后查空,都不是才画真话 -->
    <p v-if="error" class="error">加载失败:{{ error }}</p>

    <p v-else-if="loading" class="loading">加载中…</p>

    <p v-else-if="articles.length === 0" class="empty">还没有文章</p>

    <!-- 真话区:每一个字都能在 GET /articles 的响应里找到出处 -->
    <ul v-else class="articles">
      <li v-for="a in articles" :key="a.id">
        <router-link :to="`/articles/${a.id}`">{{ a.title }}</router-link>
      </li>
    </ul>
  </div>
</template>

<style scoped>
.page {
  max-width: 640px;
  margin: 40px auto;
  padding: 0 16px;
  font-family: system-ui, -apple-system, "Segoe UI", sans-serif;
}
h1 {
  color: #2c3e50;
  border-bottom: 2px solid #42b883;
  padding-bottom: 8px;
}
.articles {
  list-style: none;
  padding: 0;
}
.articles li {
  margin: 10px 0;
  padding: 8px 0;
  border-bottom: 1px dashed #e0e0e0;
}
.articles a {
  text-decoration: none;
  color: #2c3e50;
  font-size: 16px;
}
.articles a:hover {
  color: #42b883;
}
.error   { color: #c0392b; }   /* 失败:红色,一眼可辨 */
.loading { color: #7f8c8d; }   /* 加载:灰色,安静的等待 */
.empty   { color: #7f8c8d; }   /* 空:灰色,诚实的告白 */
</style>