package fanxing;

public interface Result<T> {

    String getMessage();
    int getStatus();
    T getData();
}
