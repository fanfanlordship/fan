import axios from 'axios';

axios.defaults.baseURL = '/api';

const service = axios.create({
    baseURL: '/api',
    timeout: 10000,
});

// 请求拦截器--所有通过axios发送的请求，全部都先处理：在请求头中添加
service.interceptors.request.use(req => {
    const token = sessionStorage.getItem('accessToken');
    if (token != null) {
        req.headers.Authorization = token;
    }
    return req;
});

// 响应了拦截器（在响应之后对数据进行一些处理）
service.interceptors.response.use(async (res) => {
    // 刷新token
    if (res.data.data.code === '401') {

        await service.post('/fan-cloud-nacos-auth/auth/refreshToken', {
            refreshToken: sessionStorage.getItem('refreshToken')
        }).then(res1 => {
            const {data} = res1.data;
            if (data === '') {
                console.log("掉线啦")
            }

            const {accessToken} = data;

            sessionStorage.setItem('accessToken', accessToken);
            sessionStorage.setItem('refreshToken', data.refreshToken);
        });

        res.config.headers.Authorization = sessionStorage.getItem('accessToken');
        const response = await service.request(res.config);
        return response;
    }
    return res;
});

export default service;
