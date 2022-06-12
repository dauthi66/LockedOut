package com.example.lockedout

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//database class implementing Parcelable
class GameState(var actionList: List<String> = listOf("take", "open", "use"),
                var haveKey: Boolean = false,
                var haveHammer: Boolean = false,
                var haveladder: Boolean = false)
                : Parcelable {
    constructor(parcel: Parcel) : this() {
        actionList = parcel.createStringArrayList()!!
        haveKey = parcel.readByte() != 0.toByte()
        haveHammer = parcel.readByte() != 0.toByte()
        haveladder = parcel.readByte() != 0.toByte()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeStringList(actionList)
        parcel.writeByte(if (haveKey) 1 else 0)
        parcel.writeByte(if (haveHammer) 1 else 0)
        parcel.writeByte(if (haveladder) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GameState> {
        override fun createFromParcel(parcel: Parcel): GameState {
            return GameState(parcel)
        }

        override fun newArray(size: Int): Array<GameState?> {
            return arrayOfNulls(size)
        }
    }
}