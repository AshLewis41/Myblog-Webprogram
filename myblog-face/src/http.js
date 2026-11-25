import axios from 'axios'

/**
 * 共享客户端(桥墩):一次创建,处处引入。
 * 后端地址从此只住在这里一个地方 ——
 * 开工前数出来的 N 处硬编码,本周起收敛为 1。
 * 十页之后要换后端地址?只改这一行。
 */
export const http = axios.create({
    baseURL: 'http://localhost:8080' ,
})

/**
 * Result 助手(桥的拆包员):拥有"饭盒"的读法 ——
 * 看第一格 code 对不对,拆第三格 data 出来。
 * 页面从此不用知道 Result 的存在,只管拿纯数据。
 * (Week08 定契约 → Week09 首次吃 → Week10 由专人拆包)
 */
export async function getData(url, config) {
    const { data: body } = await http.get(url, config)
    if (body.code !== 0) {
        // 成功判定住在这里,只此一处 —— 改读法只改这里,页面集体无感
        throw new Error(body.message || 'request failed')
    }
    return body.data   // ← 拆包后返回纯数据
}

axios.interceptors.response.use(
    res => {
        console.log('[axios 响应]', res.config.url, res.status)
        return res
    },
    err => {
        console.error('[axios 报错]', err.config?.url, err.message)
        return Promise.reject(err)
    }
)