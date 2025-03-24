package com.juani48.minimalistlaucher.viewmodel

class AppObject(
    private val name: String,
    private val packageName: String,
){
    fun getName(): String {return this.name}
    fun getPackageName(): String {return this.packageName}
}
