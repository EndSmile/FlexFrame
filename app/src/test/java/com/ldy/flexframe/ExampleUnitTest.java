package com.ldy.flexframe;

import com.ldy.flexframe.base.BasePresenter;

import org.junit.Test;

import java.lang.reflect.ParameterizedType;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testRxJava() {

        Disposable disposable = Single.just("ldy").
                map(new Function<String, String>() {
                    @Override
                    public String apply(@NonNull String s) throws Exception {
                        return null;
                    }
                }).
                filter(new Predicate<String>() {
                    @Override
                    public boolean test(@NonNull String s) throws Exception {
                        return s != null;
                    }
                }).
                subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        System.out.println(s);
                    }
                });
        System.out.println(disposable.isDisposed());
    }

    @Test
    public void testCreatePresenter(){
        assertEquals(null,new TestPresenter<LoginPresenter>().get());
        assertEquals(true,new TestPresenter<LoginPresenter>(){}.get()!=null);
    }

    private static class TestPresenter<P extends BasePresenter>{
        public P get(){
            if (this.getClass().getGenericSuperclass() instanceof ParameterizedType &&
                    ((ParameterizedType) (this.getClass().getGenericSuperclass())).getActualTypeArguments().length > 0) {
                Class presenterClass = (Class) ((ParameterizedType) (this.getClass()
                        .getGenericSuperclass())).getActualTypeArguments()[0];
                return (P) BasePresenter.getPresenter(presenterClass);

            }
            return null;
        }
    }
}