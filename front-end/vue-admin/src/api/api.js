import axios from 'axios';

let base = 'http://localhost:8088/news';
let newsDetectionUrl = 'http://localhost:5000'
//newsdisply
export const requestLogin = params => { return axios.post(`${baselocal}/login`, params).then(res => res.data); };
export const getNewsList = params => { return axios.get(`${base}/newsdisply/query/newsList/${params.datasetId}`, { params: params }); };
export const getNewsListPage = params => { return axios.get(`${base}/newsdisply/query/newsList/${params.datasetId}`, { params: params }); };
export const removeNews = params => { return axios.post(`${base}/newsdisply/${params.id}/delete`, { params: params }); };
export const batchRemoveNews = params => { return axios.get(`${base}/user/batchremove`, { params: params }); };
export const editNews = params =>  { return axios.post(`${base}/newsdisply/${params.id}/modify`, params).then(res => res.data);; };
export const addNews = params => { return axios.post(`${base}/newsdisply/add`, params).then(res => res.data); };

//datasetManager
export const addDataset = params => { return axios.post(`${base}/datasetManager/add`, params).then(res => res.data); };
export const getDatasetList = params => { return axios.get(`${base}/datasetManager/queryList`, { params: params }); };
export const removeDataset = params => { return axios.post(`${base}/datasetManager/${params.datasetId}/delete`, { params: params }); };

//NewsDetection
export const newsDetection = params =>  { return axios.post(`${newsDetectionUrl}/newsDetection`, params).then(res => res.data);; };
export const newsListDetection = params =>  { return axios.post(`${newsDetectionUrl}/newsListDetection`, params).then(res => res.data);; };

//echarts
export const newsCount= params =>  { return axios.post(`${base}/datasetManager/count/${params.datasetId}`, params).then(res => res.data);; };
export const getNewsCount = params => { return axios.get(`${base}/datasetManager/query/${params.datasetId}`, { params: params }); };

//index_news
export const addIndexNews = params => { return axios.post(`${base}/add`, params).then(res => res.data); };
export const getIndexNewsList = params => { return axios.get(`${base}/queryList`, { params: params }); };
export const removeIndexNews = params => { return axios.post(`${base}/delete/${params.id}`, { params: params }); };
export const editIndexNews = params =>  { return axios.post(`${base}/modify`, params).then(res => res.data);; };
export const getIndexNews = params => { return axios.get(`${base}/query/${params}`, { params: params }); };

//user userLogin userRegister
export const userLogin = params => { return axios.get(`${base}/user/login`, { params: params }); };
export const userRegister = params => { return axios.post(`${base}/user/add`, { params: params }); };

//爬虫
export const getNewsByCrawler = count =>  { return axios.post(`${newsDetectionUrl}/getNews`, { count: count }); };



//用户数据集
//export const getIndexNewsList = params => { return axios.get(`${base}/queryList`, { params: params }); };
