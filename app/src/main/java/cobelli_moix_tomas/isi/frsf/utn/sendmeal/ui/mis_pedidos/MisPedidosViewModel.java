package cobelli_moix_tomas.isi.frsf.utn.sendmeal.ui.mis_pedidos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class MisPedidosViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public MisPedidosViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is lista items fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
