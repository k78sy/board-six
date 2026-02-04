<script setup>
import httpService from '@/service/httpService';
import { onMounted, reactive } from 'vue';
import { useRoute, useRouter } from 'vue-router';

import '@vueup/vue-quill/dist/vue-quill.snow.css';

const route = useRoute();
const router = useRouter();

const state = reactive({
    data: {
        id: 0,
        title: '',
        contents: '',
        createdAt: ''
    }
})

const id = route.params.id;

onMounted(async () => {
    state.data = await httpService.findOne(id);
})

const doDel = async () =>{
    if(!confirm("삭제하시겠어요?")){
        return;
    }
    const params = {id:state.data.id}
    const result = await httpService.delete(params);

    if(result){
        alert("삭제 성공!")
        router.push('/')
    }
}
</script>

<template>
    <h3>Detail</h3>
    <main v-if="state.data.id"> <div class="meta-info">
            <span>No. {{ state.data.id }}</span> | 
            <span>Date: {{ state.data.createdAt }}</span>
        </div>
        
        <h2 class="detail-title">{{ state.data.title }}</h2>
        
        <hr>

        <div class="ql-snow">
            <div class="ql-editor" v-html="state.data.contents"></div>
        </div>

        <hr>
        
        <div class="btn-group">
            <button @click="router.push(`/mod/${state.data.id}`)">수정</button>
            <button @click="doDel">삭제</button>
            <button @click="router.push('/')">목록으로</button>
        </div>
    </main>
</template>

<style scoped>
:deep(.ql-editor img) {
    max-width: 100%;
    height: auto;
}

/* 코드 블록 디자인 강조 */
:deep(.ql-editor pre) {
    background-color: #23241f;
    color: #f8f8f2;
    padding: 10px;
    border-radius: 5px;
}

.detail-title {
    margin: 20px 0;
}
.btn-group {
    margin-top: 20px;
    display: flex;
    gap: 10px;
}
</style>