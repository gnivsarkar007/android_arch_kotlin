package com.example.kotlin.myapplication.repository.base

interface IRemoteStorage<in E, out VM> {
  fun get(): VM
  fun set(vararg data: E)
}