package com.alokda.loginwithfacebook.Repository;

import com.alokda.loginwithfacebook.NetWorkControler.WebModel.Response.ListEventResponse;
import com.alokda.loginwithfacebook.NetWorkControler.WebServices.WebServicesEvents;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListEventRepository {
    private WebServicesEvents webServicesList;

    // region singleton implementation
    private static class Loader {
        static ListEventRepository INSTANCE = new ListEventRepository();
    }

    public static ListEventRepository getInstance() {
        return Loader.INSTANCE;
    }

    public ListEventRepository() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        String BASE_URL = "Base URL ";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL).client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        webServicesList = retrofit.create(WebServicesEvents.class);
    }

    // endregion

    //Add Special build
    public Observable<ListEventResponse> getAllListRepo() {
        return Observable.create(new ObservableOnSubscribe<ListEventResponse>() {
            @Override
            public void subscribe(final ObservableEmitter<ListEventResponse> emitter) {
                webServicesList.getListEvent("Authorization").subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(new Observer<ListEventResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(ListEventResponse response) {

                        emitter.onNext(response);

                    }

                    @Override
                    public void onError(Throwable e) {
                        emitter.onNext(null);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
            }
        });
    }

}
