package com.alokda.loginwithfacebook.ViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.alokda.loginwithfacebook.Model.ListModel;
import com.alokda.loginwithfacebook.NetWorkControler.WebModel.Response.ListEventResponse;
import com.alokda.loginwithfacebook.Repository.ListEventRepository;
import java.util.List;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class ListEventViewModel extends ViewModel {
    //--get All Event
    private MutableLiveData<List<ListModel>> listEvent = new MutableLiveData<>();

    public MutableLiveData<List<ListModel>> getListEvent() {
        return listEvent;
    }

    public void getListEventFromServer() {

        //set data from flat to request
        ListEventRepository.getInstance().getAllListRepo().subscribe(new Observer<ListEventResponse>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(ListEventResponse value) {
                //
                listEvent.postValue(matchEvent(value));
            }

            @Override
            public void onError(Throwable e) {
                listEvent.postValue(null);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    private List<ListModel> matchEvent(ListEventResponse response) {

        //here we will set data from response to  model and emit list of model to activity

        return null;
    }
}
