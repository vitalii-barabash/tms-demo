<script setup>
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
  <header>

    <div class="wrapper">

      <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
          <a class="navbar-brand mx-auto" href="https://u24.gov.ua/">Support Ukraine 🇺🇦</a>
          <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
              <li class="nav-item">
                <RouterLink class="nav-link" to="/ask">Ask</RouterLink>
              </li>
              <li class="nav-item">
                <RouterLink class="nav-link" to="/testcases">Test Cases</RouterLink>
              </li>
            </ul>
          </div>
        </div>
      </nav>

    </div>
  </header>

  <RouterView />
</template>

