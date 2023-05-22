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

export function getCheckInfo(doctor_id) {
  return request({
    url: '/userCheckInfo',
    method: 'get',
    params: { doctor_id },
    baseURL: 'http://localhost:8080'
  })
}

export function setCheckState(data) {
  return request({
    url: '/setCheckState',
    method: 'put',
    data,
    baseURL: 'http://localhost:8080'
  })
}

export function updateQueue(data) {
  return request({
    url: '/UpdateQueue',
    method: 'post',
    data,
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

export function UploadFormData(data) {
  return request({
    url: '/uploadFormData',
    method: 'put',
    data,
    baseURL: 'http://localhost:8080'
  })
}

// 获取项目
export function addProjects(patient_id, doctor_id) {
  return request({
    url: '/ProjectsToAdd',
    method: 'get',
    params: { patient_id, doctor_id },
    baseURL: 'http://localhost:8080'
  })
}

// 新增项目
export function addProject(data) {
  return request({
    url: '/AddProject',
    method: 'post',
    data,
    baseURL: 'http://localhost:8080'
  })
}
