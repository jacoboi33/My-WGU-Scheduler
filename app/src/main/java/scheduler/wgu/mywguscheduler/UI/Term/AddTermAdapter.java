package scheduler.wgu.mywguscheduler.UI.Term;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import scheduler.wgu.mywguscheduler.Entity.Course;
import scheduler.wgu.mywguscheduler.R;

public class AddTermAdapter extends RecyclerView.Adapter<AddTermAdapter.AddTermViewHolder> {

    private final LayoutInflater mInflater;
    private HandleAddTermClick clickListener;
    private List<Course> mCourses;
    private final Context context;

    public AddTermAdapter(Context context, HandleAddTermClick clicklistener) {
        this.mInflater = LayoutInflater.from(context);
        this.clickListener = clicklistener;
        this.context = context;
    }

    class AddTermViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public AddTermViewHolder(@NonNull View itemView, HandleAddTermClick clickListener) {
            super(itemView);
        }

        @Override
        public void onClick(View v) {

        }
    }

    @NonNull
    @Override
    public AddTermViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.course_list_item, parent, false);
        return  new AddTermViewHolder(itemView, clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull AddTermAdapter.AddTermViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public interface HandleAddTermClick {
        void onTermClick(int position);
        void removeCourse(Course course);

    }
}
