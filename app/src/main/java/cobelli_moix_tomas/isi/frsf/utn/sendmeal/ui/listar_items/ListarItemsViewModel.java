package cobelli_moix_tomas.isi.frsf.utn.sendmeal.ui.listar_items;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ListarItemsViewModel extends ViewModel {

        private MutableLiveData<String> mText;

        public ListarItemsViewModel() {
            /*mText = new MutableLiveData<>();
            mText.setValue("This is lista items fragment");*/
        }

        public LiveData<String> getText() {
            return mText;
        }
}
