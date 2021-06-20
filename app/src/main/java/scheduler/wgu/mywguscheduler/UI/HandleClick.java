package scheduler.wgu.mywguscheduler.UI;

public interface HandleClick {
    void onObjClick(int position);
    <T> void removeObj(Class<T> obj);
    <T> void editObj(Class<T> obj);
}
