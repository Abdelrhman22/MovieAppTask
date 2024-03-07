package com.task.movieapp.utils

import java.io.IOException

class NetworkConnectivityException(val errorMessage: String) :
    IOException() {
}