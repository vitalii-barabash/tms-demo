<template>
    <div class="container mt-5">
        <div class="card">
            <div class="card-header">
                <h4>Add Test Case</h4>
            </div>
            <div class="card-body">
                <div class="mb-3">
                    <label for="">Description</label>
                    <input type="text" v-model="model.testcase.description" class="form-control" />
                </div>
                <div class="mb-3">
                    <label for="">Steps</label>
                    <div v-for="(input, index) in model.testcase.steps" :key="index" class="input-group mb-2">
                        <div class="input-group-prepend">
                            <span class="input-group-text index-span">{{ index + 1 }}</span>
                        </div>
                        <input type="text" v-model="model.testcase.steps[index]" class="form-control mb-2"
                            v-on:keyup.enter="addStep" v-on:keydown.backspace="removeStep($event, index)" v-on:keydown="cmdEnter($event)" ref="input" />
                    </div>
                </div>
                <div class="mb-3">
                    <button type="button" v-on:click="createTestCase" class="btn btn-primary">Save</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios'

const backendUrl = import.meta.env.VITE_BACKEND_URL

export default {
    name: 'addStep',
    data() {
        return {
            model: {
                testcase: {
                    description: '',
                    steps: ['']
                }
            }
        }
    },
    methods: {
        addStep() {
            this.model.testcase.steps.push('')
            this.$nextTick(() => {
                this.$refs.input[this.model.testcase.steps.length - 1].focus()
            })
        },
        removeStep(event, index) {
            if (event.target.value === '' && this.model.testcase.steps.length > 1) {
                event.preventDefault()
                this.model.testcase.steps.splice(index, 1)
                this.$nextTick(() => {
                    if (index > 0) {
                        this.$refs.input[index - 1].focus()
                    } else {
                        this.$refs.input[0].focus()
                    }
                })
            }
        },
        cmdEnter(event) {
            if (event.metaKey && event.keyCode === 13) {
                this.createTestCase()
            }
        },
        createTestCase() {
            axios.post(`${backendUrl}/testcases`, this.model.testcase)
            .then(res => {
                console.log(res.data)
                this.$router.push('/testcases')
            })
            .catch(err => {
                console.error(err)
            })
        }
    }
}

</script>

<style scoped>
.index-span {
  margin-right: 10px;
}
</style>