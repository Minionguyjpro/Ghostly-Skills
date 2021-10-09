package androidx.lifecycle;

public class ViewModelProvider {
    private final Factory mFactory;
    private final ViewModelStore mViewModelStore;

    public interface Factory {
        <T extends ViewModel> T create(Class<T> cls);
    }

    static abstract class KeyedFactory implements Factory {
        public abstract <T extends ViewModel> T create(String str, Class<T> cls);

        KeyedFactory() {
        }
    }

    public ViewModelProvider(ViewModelStore viewModelStore, Factory factory) {
        this.mFactory = factory;
        this.mViewModelStore = viewModelStore;
    }

    public <T extends ViewModel> T get(Class<T> cls) {
        String canonicalName = cls.getCanonicalName();
        if (canonicalName != null) {
            return get("androidx.lifecycle.ViewModelProvider.DefaultKey:" + canonicalName, cls);
        }
        throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
    }

    public <T extends ViewModel> T get(String str, Class<T> cls) {
        T t;
        T t2 = this.mViewModelStore.get(str);
        if (cls.isInstance(t2)) {
            return t2;
        }
        Factory factory = this.mFactory;
        if (factory instanceof KeyedFactory) {
            t = ((KeyedFactory) factory).create(str, cls);
        } else {
            t = factory.create(cls);
        }
        this.mViewModelStore.put(str, t);
        return t;
    }
}
