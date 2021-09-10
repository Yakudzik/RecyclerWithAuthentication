package com.example.recyclerauthentication.DataTransfer

import com.example.recyclerauthentication.JsModel.JsonModelTwinItem

interface FragmentDataTransfer {
    fun transferData(model:JsonModelTwinItem)
}