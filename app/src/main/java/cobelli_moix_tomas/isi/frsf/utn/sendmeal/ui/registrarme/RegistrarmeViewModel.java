package cobelli_moix_tomas.isi.frsf.utn.sendmeal.ui.registrarme;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RegistrarmeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public RegistrarmeViewModel() {
       /* mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");*/
    }

    public LiveData<String> getText() {
        return mText;
    }
}