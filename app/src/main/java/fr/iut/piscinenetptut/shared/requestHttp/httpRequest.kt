package fr.iut.piscinenetptut.shared.requestHttp

class httpRequest {

    val url: String = "https://piscinenetptut3.tunnel.datahub.at/"

    fun convertData(data: String): String{
        return  (data.replace("\"", "")
                    .replace("{", "")
                    .replace("}", "")
                    .replace(":", "=")
                    .replace(",", "&"))
    }
}