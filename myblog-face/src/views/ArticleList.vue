<script setup>
// ============ Week09 列表页:脸的第一间房 ============
// 链路纪律在组件层的落点:本组件只管【第③步渲染】,
// 第①步 fetch 和第②步拆包都住在 ../api.js —— 页面不自己发请求、不自己猜数据
import { ref, onMounted } from 'vue'
import { fetchArticles } from '../api.js'

// ---------- 页面的记忆 ----------
const articles = ref([])     // 真话容器:等 fetch 回来填 PageResult.items
const error = ref('')        // 诚实表情之一:失败时说失败
const loading = ref(true)    // 诚实表情之二:加载中说加载中(Level 3 的 Loading 态)
                             // —— 初始为 true,拿到结果(无论成败)后翻回 false

// ---------- 组件挂载 → 发请求 → 拆好的真身 → 填进记忆 ----------
onMounted(async () => {
  try {
    const data = await fetchArticles()   // ← 拿到的已是真身 PageResult{items,total}
    articles.value = data.items          // ← 只取 items 画到脸上
  } catch (e) {
    error.value = e.message              // 失败也要诚实:把错误亮出来,不装没事
  } finally {
    loading.value = false                // 无论成败,加载阶段结束
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