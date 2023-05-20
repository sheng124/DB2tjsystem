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

export function getCheckInfo(data) {
  return request({
    url: '/userCheckInfo',
    method: 'get',
    params: { data },
    baseURL: 'http://localhost:8080'
  })
}

export function setCheckState(data) {
  return request({
    url: '/setCheckState',
    method: 'put',
    params: { data },
    baseURL: 'http://localhost:8080'
  })
}

export function getUploadInfo(Doctor_id) {
  return request({
    url: '/userUploadInfo',
    method: 'get',
    params: { Doctor_id },
    baseURL: 'http://localhost:8080'
  })
}

export function uploadFormData(data) {
  return request({
    url: '/uploadFormData',
    method: 'put',
    data,
    baseURL: 'http://localhost:8080'
  })
}
