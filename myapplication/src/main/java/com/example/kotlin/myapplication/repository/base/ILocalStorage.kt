package com.example.kotlin.myapplication.repository.base

interface ILocalStorage<in E, out VM> {
  fun get(): VM
  fun set(vararg data: E)
}