package api;

import okhttp3.MediaType;

public interface BaseApi {

    String BASE_URL = "https://ilcarro-backend.herokuapp.com";
    MediaType JSON = MediaType.get("application/json");

}
