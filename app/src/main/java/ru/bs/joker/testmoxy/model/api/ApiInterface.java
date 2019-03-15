package ru.bs.joker.testmoxy.model.api;

import io.reactivex.Observable;
import retrofit2.http.GET;
import ru.bs.joker.testmoxy.model.data.FillData;

public interface ApiInterface {
    @GET("tmp/JSONSample.json")
    Observable<FillData> getAllData();
}
