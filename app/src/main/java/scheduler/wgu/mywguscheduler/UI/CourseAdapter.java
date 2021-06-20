package scheduler.wgu.mywguscheduler.UI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import scheduler.wgu.mywguscheduler.Entity.Course;
import scheduler.wgu.mywguscheduler.R;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    private final LayoutInflater mInflater;
    private final Context context;
    private List<Course> mCourses;
    private HandleCourseClick clickListener;

    public CourseAdapter(Context context, HandleCourseClick clickListener) {
        mInflater = LayoutInflater.from(context);
        this.clickListener = clickListener;
        this.context = context;
    }

    public CourseAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public CourseAdapter.CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.course_list_item, parent, false);
        return new CourseViewHolder(itemView, clickListener);
    }

    class CourseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private final TextView courseTitle;
        private final TextView courseNotes;
        private final TextView courseStatus;
        private final TextView startDate;
        private final TextView endDate;

        private final Button deleteCourseButton;
        private final Button editCourseButton;

        HandleCourseClick clickListener;

        public CourseViewHolder(@NonNull View itemView, HandleCourseClick clickListener) {
            super(itemView);

            courseTitle = itemView.findViewById(R.id.course_title);
            courseNotes = itemView.findViewById(R.id.course_notes);
            courseStatus = itemView.findViewById(R.id.course_status);
            startDate = itemView.findViewById(R.id.start_date);
            endDate = itemView.findViewById(R.id.end_date);

            deleteCourseButton = itemView.findViewById(R.id.delete_course);
            editCourseButton = itemView.findViewById(R.id.edit_course);

            this.clickListener = clickListener;
        }

        @Override
        public void onClick(View v) {
            clickListener.onCourseClick(getAbsoluteAdapterPosition());
        }
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.CourseViewHolder holder, int position) {
        if (mCourses != null) {
            Course current = mCourses.get(position);
            holder.courseTitle.setText(current.getTitle());
            holder.courseNotes.setText(current.getNote());
            holder.courseStatus.setText(current.getStatus());
            holder.startDate.setText(current.getStartDate());
            holder.endDate.setText(current.getEndDate());
        } else {
            holder.courseTitle.setText("No Course Title");
            holder.courseNotes.setText("No course notes");
            holder.courseStatus.setText("No status");
            holder.startDate.setText("No start date");
            holder.endDate.setText("No end date");
        }

        holder.deleteCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.removeCourse(mCourses.get(position));
            }
        });

        holder.editCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.editCourse(mCourses.get(position));
            }
        });
    }

    public void setWords(List<Course> courses) {
        mCourses = courses;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mCourses != null) return mCourses.size();
        else return 0;
    }

    public interface HandleCourseClick {
        void onCourseClick(int position);
        void removeCourse(Course course);
        void editCourse(Course course);
    }
}
