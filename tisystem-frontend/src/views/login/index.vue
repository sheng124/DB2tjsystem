<template>
  <div class="root">
    <div class="all">
      <div class="container right-panel-active">
        <!-- 注册 -->
        <div class="container__form container--signup">
          <form
            id="form1"
            ref="registerData"
            class="form"
          >
            <h2 class="form__title">注 册</h2>
            <input v-model="registerData.userName" type="text" placeholder="用户名" class="input">
            <input v-model="registerData.password" type="password" placeholder="密码" class="input">
            <input v-model="registerData.password_confirm" type="password" placeholder="确认密码" class="input">
            <button class="btn" @click.prevent="register">注 册</button>
          </form>
        </div>

        <!-- 登录 -->
        <div class="container__form container--signin">
          <form
            id="form2"
            ref="loginData"
            class="form"
          >
            <h2 class="form__title">登 录</h2>
            <input v-model="loginData.userName" type="text" placeholder="用户名" class="input">
            <input v-model="loginData.password" type="password" placeholder="密码" class="input">
            <a href="#" class="link">忘记密码?</a>
            <button class="btn" @click.prevent="loginCheck">登 录</button>
          </form>
        </div>

        <!-- 遮盖图片 -->
        <div class="container__overlay">
          <div class="overlay">
            <div class="overlay__panel overlay--left">
              <button id="signIn" class="btn" @click="signInClick">登 录</button>
            </div>
            <div class="overlay__panel overlay--right">
              <button id="signUp" class="btn" @click="signUpClick">注 册</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { register } from '@/api/user'
export default {
  name: 'Login',
  data() {
    // 数据模型
    return {
      loginData: {
        userName: '',
        password: ''
      },
      registerData: {
        userName: '',
        password: '',
        password_confirm: ''
      },
      loading: false
    }
  },
  watch: {
    $route: {
      handler: function(route) {
        this.redirect = route.query && route.query.redirect
      },
      immediate: true
    }
  },
  methods: {
    signInClick() {
      const container = document.querySelector('.container')
      container.classList.remove('right-panel-active')
    },
    signUpClick() {
      const container = document.querySelector('.container')
      container.classList.add('right-panel-active')
    },
    myRegisterValiate() {
      if (this.registerData.userName === '') {
        this.$message.error('请输入用户名！')
        return false
      } else if (this.registerData.password === '') {
        this.$message.error('请输入密码！')
        return false
      } else if (this.registerData.password_confirm === '') {
        this.$message.error('请确认密码！')
        return false
      } else if (this.registerData.password !== this.registerData.password_confirm) {
        this.$message.error('两次输入的密码不一致！')
        return false
      } else {
        return true
      }
    },
    myLoginValiate() {
      if (this.loginData.userName === '') {
        this.$message.error('请输入用户名！')
        return false
      } else if (this.loginData.password === '') {
        this.$message.error('请输入密码！')
        return false
      } else {
        return true
      }
    },
    loginCheck() {
      if (this.myLoginValiate()) {
        this.loading = true
        this.$store.dispatch('user/login', this.loginData).then(() => {
          this.$router.push({ path: this.redirect || '/' })
          this.loading = false
        }).catch(() => {
          this.loading = false
        })
      }
    },
    register() {
      if (this.myRegisterValiate()) {
        register(this.registerData).then((result) => {
          console.log(result)
          if (result.code === 1) {
            console.log('register success')
            this.$message.success(result.msg)
            this.registerData.userName = ''
            this.registerData.password = ''
            this.registerData.password_confirm = ''
            this.signInClick()
          } else {
            this.$message.error(result.msg)
            this.loading = false
          }
        })
      }
    }
  }
}
</script>

<style>
  .root {
    /* COLORS */
    --white: #e9e9e9;
    --gray: #333;
    --blue: #0367a6;
    --lightblue: #008997;

    /* RADII */
    --button-radius: 0.7rem;

    /* SIZES */
    --max-width: 758px;
    --max-height: 420px;

    font-size: 16px;
    font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen,
      Ubuntu, Cantarell, "Open Sans", "Helvetica Neue", sans-serif;
  }

  .all {
    align-items: center;
    background-color: var(--white);
    background: url("~@/assets/login.jpg");
    background-attachment: fixed;
    background-position: center;
    background-repeat: no-repeat;
    background-size: cover;
    display: grid;
    height: 100vh;
    place-items: center;
  }

  .form__title {
    font-weight: 300;
    margin: 0;
    margin-bottom: 1.25rem;
  }

  .link {
    color: var(--gray);
    font-size: 0.9rem;
    margin: 1.5rem 0;
    text-decoration: none;
  }

  .container {
    background-color: var(--white);
    border-radius: var(--button-radius);
    box-shadow: 0 0.9rem 1.7rem rgba(0, 0, 0, 0.25),
      0 0.7rem 0.7rem rgba(0, 0, 0, 0.22);
    height: var(--max-height);
    max-width: var(--max-width);
    overflow: hidden;
    position: relative;
    width: 100%;
  }

  .container__form {
    height: 100%;
    position: absolute;
    top: 0;
    transition: all 0.6s ease-in-out;
  }

  .container--signin {
    left: 0;
    width: 50%;
    z-index: 2;
  }

  .container.right-panel-active .container--signin {
    transform: translateX(100%);
  }

  .container--signup {
    left: 0;
    opacity: 0;
    width: 50%;
    z-index: 1;
  }

  .container.right-panel-active .container--signup {
    -webkit-animation: show 0.6s;
            animation: show 0.6s;
    opacity: 1;
    transform: translateX(100%);
    z-index: 5;
  }

  .container__overlay {
    height: 100%;
    left: 50%;
    overflow: hidden;
    position: absolute;
    top: 0;
    transition: transform 0.6s ease-in-out;
    width: 50%;
    z-index: 100;
  }

  .container.right-panel-active .container__overlay {
    transform: translateX(-100%);
  }

  .overlay {
    background-color: var(--lightblue);
    background: url("~@/assets/login.jpg");
    background-attachment: fixed;
    background-position: center;
    background-repeat: no-repeat;
    background-size: cover;
    height: 100%;
    left: -100%;
    position: relative;
    transform: translateX(0);
    transition: transform 0.6s ease-in-out;
    width: 200%;
  }

  .container.right-panel-active .overlay {
    transform: translateX(50%);
  }

  .overlay__panel {
    align-items: center;
    display: flex;
    flex-direction: column;
    height: 100%;
    justify-content: center;
    position: absolute;
    text-align: center;
    top: 0;
    transform: translateX(0);
    transition: transform 0.6s ease-in-out;
    width: 50%;
  }

  .overlay--left {
    transform: translateX(-20%);
  }

  .container.right-panel-active .overlay--left {
    transform: translateX(0);
  }

  .overlay--right {
    right: 0;
    transform: translateX(0);
  }

  .container.right-panel-active .overlay--right {
    transform: translateX(20%);
  }

  .btn {
    background-color: var(--blue);
    background-image: linear-gradient(90deg, var(--blue) 0%, var(--lightblue) 74%);
    border-radius: 20px;
    border: 1px solid var(--blue);
    color: var(--white);
    cursor: pointer;
    font-size: 0.8rem;
    font-weight: bold;
    letter-spacing: 0.1rem;
    padding: 0.9rem 4rem;
    text-transform: uppercase;
    transition: transform 80ms ease-in;
  }

  .form > .btn {
    margin-top: 1.5rem;
  }

  .btn:active {
    transform: scale(0.95);
  }

  .btn:focus {
    outline: none;
  }

  .form {
    background-color: var(--white);
    display: flex;
    align-items: center;
    justify-content: center;
    flex-direction: column;
    padding: 0 3rem;
    height: 100%;
    text-align: center;
  }

  .input {
    background-color: #fff;
    border: none;
    padding: 0.9rem 0.9rem;
    margin: 0.5rem 0;
    width: 100%;
  }

  @-webkit-keyframes show {
    0%,
    49.99% {
      opacity: 0;
      z-index: 1;
    }

    50%,
    100% {
      opacity: 1;
      z-index: 5;
    }
  }

  @keyframes show {
    0%,
    49.99% {
      opacity: 0;
      z-index: 1;
    }

    50%,
    100% {
      opacity: 1;
      z-index: 5;
    }
  }
</style>
