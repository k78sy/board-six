<script setup>
import httpService from '@/service/httpService';
import { onMounted, reactive, computed } from 'vue';
import { useRoute, useRouter } from 'vue-router';

import MyEditor from './MyEditor.vue';

const router = useRouter()
const route = useRoute();

const state = reactive({
    data: {
        title: '',
        contents: ''
    }
})

const id = route.params.id;

onMounted(async () => {
    if (route.params.id) {
        state.data = await httpService.findOne(id);
    }
})

const submit = async () => {
    if (!state.data.title) {
        alert("제목을 작성해주세요")
        return;
    } else if (!state.data.contents) {
        alert("내용을 작성해주세요")
        return;
    }

    const result = route.params.id ? await httpService.update(state.data) : await httpService.save(state.data);

    if (result) {
        alert("등록성공")
        router.push(
            route.params.id ? `/detail/${id}` : '/'
        )
    } else {
        alert("등록실패")
    }
}

</script>

<template>
    <h3>Write</h3>
    <main>
        <div>title: <input type="text" v-model="state.data.title"></div>
        <!-- <div>contents: <textarea v-model="state.data.contents"></textarea></div> -->

        <MyEditor v-model="state.data.contents" />
        <div><button @click="submit">작성</button></div>
    </main>
</template>

<style scoped>
/* 에디터 전체 높이 설정 */
:deep(.ql-editor) {
    min-height: 300px;
    font-size: 16px;
}

/* 툴바 상단 고정 느낌 주기 */
:deep(.ql-toolbar) {
    position: sticky;
    top: 0;
    z-index: 1;
    background-color: white;
}
</style>