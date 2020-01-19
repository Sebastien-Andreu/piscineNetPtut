package fr.iut.piscinenetptut.shared.requestHttp

class httpRequest {

    val url: String = "http://77.145.32.156:3000/"

    fun convertData(data: String): String{
        return  (data.replace("\"", "")
                    .replace("{", "")
                    .replace("}", "")
                    .replace(":", "=")
                    .replace(",", "&"))
    }
}