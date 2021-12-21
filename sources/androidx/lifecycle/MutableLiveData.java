package androidx.lifecycle;

public class MutableLiveData<T> extends LiveData<T> {
    public void setValue(T t) {
        super.setValue(t);
    }
}
