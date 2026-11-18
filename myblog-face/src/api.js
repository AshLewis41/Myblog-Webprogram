// ==================== 内核:怎么拆 ====================
async function getJson(url) {
    const res = await fetch(url)
    const body = await res.json()
    if (body.code !== 0) {
        throw new Error(body.message || 'request failed')
    }
    return body.data
}

// ==================== 电线:去哪拿 ====================

/**
 * 拉文章列表 → 真身 PageResult { items: [...], total: n }
 * 对应后端:GET http://localhost:8080/articles
 */
export async function fetchArticles() {
    return getJson('http://localhost:8080/articles')
}

/**
 * 拉单篇文章 → 真身 Article { id, title, content }
 * 对应后端:GET http://localhost:8080/articles/{id}
 */
export async function fetchArticle(id) {
    return getJson(`http://localhost:8080/articles/${id}`)
}