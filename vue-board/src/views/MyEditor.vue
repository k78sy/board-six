<script setup>
import { ref, onMounted } from 'vue';
import ImageCompress from 'quill-image-compress';
import { QuillEditor,Quill } from '@vueup/vue-quill';
import '@vueup/vue-quill/dist/vue-quill.snow.css';


const props = defineProps(['modelValue']);
const emit = defineEmits(['update:modelValue']);

const editorRef = ref(null);
const textLength = ref(0);

// 글자 수 업데이트 함수
const updateCount = () => {
    if (editorRef.value) {
        // 1. Quill 인스턴스에 직접 접근하여 텍스트 추출
        const quill = editorRef.value.getQuill();
        if (quill) {
            const pureText = quill.getText().trim();
            textLength.value = pureText.length;
        }
    }
};

// 에디터 내용이 바뀔 때 부모에게 전달하고 글자 수 갱신
const handleChange = (content) => {
    emit('update:modelValue', content);
    updateCount();
};

// 처음에 데이터를 불러왔을 때 글자 수 초기 세팅
onMounted(() => {
    setTimeout(() => {
        updateCount();
    }, 500); // 에디터 로딩 대기 시간
});




</script>

<template>
    <div class="editor-container">
        <div id="my-toolbar">
            <span class="ql-formats">
                <select class="ql-header">
                    <option value="1">Heading 1</option>
                    <option value="2">Heading 2</option>
                    <option value="3">Heading 3</option>
                    <option value="4">Heading 4</option>
                    <option selected>Normal</option>
                </select>
                <select class="ql-font">
                    <option selected></option>
                    <option value="serif"></option>
                    <option value="monospace"></option>
                </select>
            </span>

            <span class="ql-formats">
                <button class="ql-bold"></button>
                <button class="ql-italic"></button>
                <button class="ql-underline"></button>
                <button class="ql-strike"></button>
            </span>

            <span class="ql-formats">
                <select class="ql-color"></select>
                <select class="ql-background"></select>
            </span>

            <span class="ql-formats">
                <button class="ql-list" value="ordered"></button>
                <button class="ql-list" value="bullet"></button>
                <select class="ql-align">
                    <option selected></option>
                    <option value="center"></option>
                    <option value="right"></option>
                    <option value="justify"></option>
                </select>
            </span>

            <span class="ql-formats">
                <button class="ql-link"></button>
                <button class="ql-image"></button>
                <button class="ql-video"></button>
                <button class="ql-code-block"></button>
                <button class="ql-blockquote"></button>
            </span>

            <span class="ql-formats">
                <button class="ql-clean"></button>
            </span>
        </div>

        <QuillEditor ref="editorRef" theme="snow" :content="props.modelValue" contentType="html" toolbar="#my-toolbar" @update:content="handleChange" />

        <div class="status-bar">
            <span>현재 글자 수: <strong>{{ textLength }}</strong>자 (공백포함)</span>
        </div>
    </div>
</template>

<style scoped>
.editor-container {
    border: 1px solid #ccc;
    border-radius: 4px;
}

/* 툴바 테두리 제거 (container와 겹치지 않게) */
:deep(.ql-toolbar.ql-snow) {
    border: none;
    border-bottom: 1px solid #ccc;
}

/* 에디터 본체 테두리 제거 */
:deep(.ql-container.ql-snow) {
    border: none;
    min-height: 300px;
}

.status-bar {
    padding: 8px 15px;
    background-color: #f8f9fa;
    border-top: 1px solid #ccc;
    text-align: right;
    font-size: 13px;
    color: #666;
}

:deep(.ql-editor) {
    font-size: 16px;
    line-height: 1.6;
}
</style>