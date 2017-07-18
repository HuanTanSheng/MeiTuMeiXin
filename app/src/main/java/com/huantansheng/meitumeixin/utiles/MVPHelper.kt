package com.example.huan.myapplication

import java.io.File


/**
 * 自动生成mvp架构工具
 * Created by HuanTanSheng 2017/6/26.
 *
 */

fun main(args: Array<String>) {
    //配置新建总文件夹名称
    val packageName = "photo"
    //配置文件名前缀
    val classNamePrefix = "Photo"
    //配置新建总文件夹位置
    val packageParentPath = "C:\\HuanTanSheng\\workSpace\\applications\\MeiTuMeiXin\\app\\src\\main\\java\\com\\huantansheng\\meitumeixin"
    //配置applicationId
    val applicationId = "com.huantansheng.meitumeixin"

    //***********************配置结束*************************************************

    val packageMessagePrefix = "package $applicationId"
    val improtMessagePrefix = "import $applicationId"
    val packagePath = "$packageParentPath\\$packageName"

    val contractPath = "$packagePath/contract"
    val modelPath = "$packagePath/model"
    val presenterPath = "$packagePath/presenter"

    val contractName = "${classNamePrefix}Contract.kt"
    val modelName = "${classNamePrefix}Model.kt"
    val presenterName = "${classNamePrefix}Presenter.kt"

    val superIView = " :BaseContract.IView"
    val superIModel = " :BaseContract.IModel"
    val superIPresenter = " :BaseContract.IPresenter"

    val contractFileContent = "$packageMessagePrefix.$packageName.contract\n\n$improtMessagePrefix.base.BaseContract\n\ninterface ${classNamePrefix}Contract {\n\n    interface IModel${superIModel} {\n    }\n\n    interface IView${superIView} {\n    }\n\n    interface IPresenter${superIPresenter} {\n    }\n\n} "

    val modelFileContent = "$packageMessagePrefix.$packageName.model\n\n$improtMessagePrefix.$packageName.contract.${classNamePrefix}Contract\n\nclass ${classNamePrefix}Model : ${classNamePrefix}Contract.IModel{\n\n}"

    val presenterFileContent = "$packageMessagePrefix.$packageName.presenter\n\n$improtMessagePrefix.$packageName.contract.${classNamePrefix}Contract\n\nclass ${classNamePrefix}Presenter : ${classNamePrefix}Contract.IPresenter{\n\n}"

    createFile(contractPath, contractName, contractFileContent)
    createFile(modelPath, modelName, modelFileContent)
    createFile(presenterPath, presenterName, presenterFileContent)
}

fun createFile(filePath: String, fileName: String, fileContent: String) {

    val dir = File(filePath)
    var mkdirs = dir.mkdirs()
    println("${dir.name}文件夹是否为新创建：$mkdirs")

    val file = File(dir, fileName)
    var fileCreated = file.isFile
    println("${fileName}文件是否为新创建：${!fileCreated}")

    if (!fileCreated) {
        fileCreated = file.createNewFile()
    }
    println("${fileName}文件是否创建完毕$fileCreated")

    file.writeText(fileContent)
    println("----------")
}
