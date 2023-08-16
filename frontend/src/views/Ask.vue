<template>
    <div class="container mt-4">
        <div class="card">
            <div class="card-header">
                <h4>Ask</h4>
            </div>
            <div class="card-body">
                <div class="mb-3">
                    <label for="">Question?</label>
                    <input type="text" v-model="model.query.question" v-on:keyup.enter="submit" class="form-control" />
                    <p v-if="this.result.answer" class="lh-lg mt-2">{{ this.result.answer }}</p>
                </div>
                <div class="mb-3">
                    <button type="button" v-on:click="submit" class="btn btn-primary">Submit</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios'

const backendUrl = import.meta.env.VITE_BACKEND_URL

export default {
    name: 'ask',
    data() {
        return {
            model: {
                query: {
                    question: ''
                }
            },
            result: ''
        }
    },
    methods: {
        submit() {
            console.log(this.model.query)

            this.result = { answer: 'Loading...'}

            axios.post(`${backendUrl}/testcases/q`, this.model.query)
                .then(res => {
                    console.log(res)
                    this.result = res.data
                })
                .catch(err => {
                    console.error(err)
                    this.result = { answer: `Error: ${err}`}
                })
        }
    }
}

</script>

<style scoped>
.index-span {
    margin-right: 10px;
}

.break-text {
  word-wrap: break-word;
}
</style>