<script setup>
import httpService from '@/service/httpService';
import { reactive } from 'vue';
import { useRouter } from 'vue-router';

const router = useRouter()

const state = reactive({
    data: {
        title: '',
        contents: ''
    }
})
const submit = async () => {
    if (!state.data.title) {
        alert("제목을 작성해주세요")
    } else if (!state.data.contents) {
        alert("내용을 작성해주세요")
    }

    const result = await httpService.save(state.data);

    if (result) {
        alert("등록성공")
        router.push('/')
    }else{
        alert("등록실패")
    }
}
</script>

<template>
    <h3>Write</h3>
    <main>
        <div>title: <input type="text" v-model="state.data.title"></div>
        <div>contents: <textarea v-model="state.data.contents"></textarea></div>
        <div><button @click="submit">작성</button></div>
    </main>
</template>

<style scoped></style>