<script setup>
import httpService from '@/service/httpService';
import { onMounted, reactive, watch } from 'vue';
import { useRouter, useRoute } from 'vue-router';

const route = useRoute();
const router = useRouter();

const state = reactive({
    list: [],
    searchText: '',
    today: new Date().toISOString().slice(0, 10)
})



const httpSearch = async params => {
    state.list = await httpService.findAll(params);
}

watch(
    () => route.query, // 주소창 뒤의 ?search_text=... 부분을 감시!
    async (newQuery) => {
        // 주소창에서 검색어를 가져와 state에 동기화합니다.
        state.searchText = newQuery.search_text || '';
        
        // 백엔드 파라미터 형식에 맞춰서 통신 요청!
        const params = {
            search_text: state.searchText
        };
        await httpSearch(params);
    },
    { immediate: true } // 페이지 처음 뜰 때도 실행!
);

// onMounted(() => {
//     // state.list = await httpService.findAll();
//     // console.log(state.list);
//     httpSearch()
// })

const search = async () => {
    const params = {
        search_text: state.searchText
    }
    httpSearch(params);
    router.push({
        path: '/',
        query: { search_text: state.searchText }
    });
}

const goDetail = (id) => {
    router.push(`/detail/${id}`);
}


// const getToday = () => {
//     let date = new Date();
//     let year = date.getFullYear();
//     let month = date.getMonth() < 10 ? '0' + (date.getMonth() + 1) : date.getMonth + 1;
//     let day = date.getDate() < 10 ? '0' + date.getDate() : date.getDate();
//     return year+month+day;
// }

const articleDate = (createdDate) => {
    // const dateArr = date.split('-'); // -기준으로 잘라서 배열화
    // dateArr[2] = dateArr[2].substring(0,2); // 일자 이후(시간)은 자르기

    // const arrDate = dateArr[0]+dateArr[1]+dateArr[2]; //20260204

    // const articleTime = date.substring(10,20) // 시간: 00:00:00
    // const articleDate = date.substring(0,10) // 날짜: 2026-02-04

    // return arrDate == getToday() ? articleTime : articleDate;

    if(!createdDate) return ''; // 방어코드 먼저 작성
    
    // 날짜와 시간 분리
    const [date, time] = createdDate.split(' ');

    return date === state.today ? time.substring(0,5) : date;
}

</script>

<template>
    <h3>List</h3>
    <div>
        검색:<label> <input type="search" v-model="state.searchText" @keyup.enter="search">
            <button @click="search">검색</button>
        </label>
    </div>
    <div v-if="!state.list.length">글이 없습니다.</div>
    <table v-else>
        <tr>
            <th>NO</th>
            <th>TITLE</th>
            <th>DATE</th>
        </tr>
        <tr v-for="item in state.list" :key="item.id" @click="goDetail(item.id)">
            <td>{{ item.id }}</td>
            <td>{{ item.title }}</td>
            <td>{{ articleDate(item.createdAt) }}</td>
        </tr>
    </table>

</template>

<style scoped>
table {
    width: 100%;
    border-collapse: collapse;
}

tr:hover {cursor: pointer;background-color: skyblue;}

th,
td {
    border: 1px solid #ddd;
}

th:nth-of-type(1),
td:nth-of-type(1) {
    width: 30px;
    text-align: center;
}

th:nth-of-type(2),
td:nth-of-type(2) {
    width: calc(100% - 180px);
}

th:nth-of-type(3),
td:nth-of-type(3) {
    width: 150px;
}
</style>