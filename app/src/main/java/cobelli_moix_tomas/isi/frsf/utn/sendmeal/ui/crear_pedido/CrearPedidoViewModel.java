package cobelli_moix_tomas.isi.frsf.utn.sendmeal.ui.crear_pedido;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CrearPedidoViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public CrearPedidoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}