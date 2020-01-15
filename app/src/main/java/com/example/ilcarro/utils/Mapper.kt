package com.example.ilcarro.utils

import com.example.ilcarro.data.dto.user.RegisterUserRequest
import com.example.ilcarro.data.dto.user.UpdateUserRequest
import com.example.ilcarro.data.dto.user.ui.RegisterUserUI
import com.example.ilcarro.data.dto.user.ui.UpdateUserUI

class Mapper {
    companion object {
        fun toRegisterUserRequest(user: RegisterUserUI): RegisterUserRequest =
            RegisterUserRequest(
                firstName = user.firstName,
                secondName = user.secondName
            )

        fun toUpdateUserRequest(user: UpdateUserUI): UpdateUserRequest =
            UpdateUserRequest(
                firstName = user.firstName,
                secondName = user.secondName,
                photo = user.photo
            )
    }
}