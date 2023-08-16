<template>
    <div class="container mt-4">
        <div class="card">
            <div class="card-header">
                <h4>
                    Test Cases
                    <RouterLink to="/testcases/create" class="btn btn-primary float-end">
                        Add Test Case
                    </RouterLink>
                </h4>
            </div>
            <div class="card-body">
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Description</th>
                            <th class="col-6">Steps</th>
                        </tr>
                    </thead>
                    <tbody v-if="this.testcases.length > 0">
                        <tr v-for="(testcase, index) in this.testcases" :key="index">
                            <td>{{ testcase.id }}</td>
                            <td>{{ testcase.description }}</td>
                            <div>
                        <tr v-for="(step, i) in testcase.steps" :key="i">{{ i + 1 }}. {{ step }}</tr>
            </div>
            <td class="align-top col-1">
                <RouterLink to="/" class="btn btn-outline-primary d-inline-block w-100 mb-1">
                    Edit
                </RouterLink>
                <button type="button" v-on:click="deleteTestCase(testcase.id)" class="btn btn-danger d-inline-block w-100">
                    Delete
                </button>
            </td>
            </tr>
            </tbody>
            <tbody v-else>
                <tr>
                    <td colspan="3">Loading...</td>
                </tr>
            </tbody>
            </table>
        </div>
    </div>
    </div>
</template>

<script>
import axios from 'axios'

const backendUrl = import.meta.env.VITE_BACKEND_URL

export default {
    name: 'testcases',
    data() {
        return {
            testcases: []
        }
    },
    mounted() {
        this.getTestCases();
    },
    methods: {
        getTestCases() {
            axios.get(`${backendUrl}/testcases`)
                .then(res => {
                    this.testcases = res.data
                })
                .catch(err => {
                    console.error(err)
                })
        },
        deleteTestCase(id) {
            axios.delete(`${backendUrl}/testcases/${id}`)
                .then(res => {
                    this.getTestCases()
                })
                .catch(err => {
                    console.error(err)
                })
        }
    }
}

</script>