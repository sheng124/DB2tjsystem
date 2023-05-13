import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/login',
    method: 'post',
    data,
    baseURL: 'http://localhost:8080'
  })
}

export function register(data) {
  return request({
    url: '/register',
    method: 'post',
    data,
    baseURL: 'http://localhost:8080'
  })
}

export function getInfo(token) {
  return request({
    url: '/info',
    method: 'get',
    params: { token },
    baseURL: 'http://localhost:8080'
  })
}

export function logout() {
  return request({
    url: '/vue-admin-template/user/logout',
    method: 'post',
    baseURL: 'http://localhost:8080'
  })
}
